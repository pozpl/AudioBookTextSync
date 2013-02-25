/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.context.BeansAnnotationsForTests;
import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudioFile;

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizerSphinxCachedTest extends TestCase {
    private String RECOGNIZED_AND_ALIGNED_STUB_TEXT = "some(2.1, 2.7) kind(3.0, 4.0) of(4.5, 5.0)"
            + " text(6.1, 7.0) here(9.0, 10.0)";



    public AudioFileRecognizerSphinxCachedTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/test.properties"));
        String pathOfFileToRecognize = prop.getProperty("TEST_SPHINX_AUDIO_NUMBERS");
        String cachedFilePath = pathOfFileToRecognize + AudioFileRecognizerSphinxCached.CACHE_FILR_EXTENSION;
        File cachedFIle = new File(cachedFilePath);
        if(cachedFIle.exists()){
            boolean success = cachedFIle.delete();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/test.properties"));
        String pathOfFileToRecognize = prop.getProperty("TEST_SPHINX_AUDIO_NUMBERS");
        String cachedFilePath = pathOfFileToRecognize + AudioFileRecognizerSphinxCached.CACHE_FILR_EXTENSION;
        File cachedFIle = new File(cachedFilePath);
        if(cachedFIle.exists()){
            boolean success = cachedFIle.delete();
        }
    }

    /**
     * Test of recognize method, of class AudioFileRecognizerSphinxCached.
     */
    public void testRecognize() {
        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);
        AudioFileRecognizerStub recognizerStub = (AudioFileRecognizerStub) ctxt.getBean("audioFileRecognizerStub");
        recognizerStub.setStubText(RECOGNIZED_AND_ALIGNED_STUB_TEXT);
        AudioFileRecognizerSphinxCached recognizer = ctxt.getBean(AudioFileRecognizerSphinxCached.class);

        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/test.properties"));
            RecognizedTextOfSingleAudioFile recognizedAudioFile = recognizer.recognize(prop.getProperty("TEST_SPHINX_AUDIO_NUMBERS"), "numbers");
            RecognizedTextOfSingleAudioFile recognizedFromCache = recognizer.recognize(prop.getProperty("TEST_SPHINX_AUDIO_NUMBERS"), "numbers");

            assertEquals(recognizedAudioFile.getAudioFileHash(), recognizedFromCache.getAudioFileHash());
            String[] tokensFromFile = recognizedAudioFile.getTokens();
            String[] tokensFromCache = recognizedFromCache.getTokens();
            assertEquals(tokensFromFile.length, tokensFromCache.length);
            for(Integer tokenIterator = 0; tokenIterator < tokensFromFile.length; tokenIterator++){
                assertEquals(tokensFromFile[tokenIterator], tokensFromCache[tokenIterator]);
                assertEquals(recognizedAudioFile.getBeginTimeOfTokenAtPosition(tokenIterator),
                        recognizedFromCache.getBeginTimeOfTokenAtPosition(tokenIterator));
                assertEquals(recognizedAudioFile.getEndTimeOfTokenAtPosition(tokenIterator),
                        recognizedFromCache.getEndTimeOfTokenAtPosition(tokenIterator));
            }


        } catch (IOException ex) {
            Logger.getLogger(AudioFileRecognizerSphinx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
