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
import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudiofile;

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizerSphinxCachedTest extends TestCase {

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
        AudioFileRecognizerSphinx recognizer = ctxt.getBean(AudioFileRecognizerSphinx.class);

        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/test.properties"));
            RecognizedTextOfSingleAudiofile recognizedAudioFile = recognizer.recognize(prop.getProperty("TEST_SPHINX_AUDIO_NUMBERS"), "numbers");
            RecognizedTextOfSingleAudiofile recognizedFromCache = recognizer.recognize(prop.getProperty("TEST_SPHINX_AUDIO_NUMBERS"), "numbers");
            assertEquals(recognizedAudioFile, recognizedFromCache);
        } catch (IOException ex) {
            Logger.getLogger(AudioFileRecognizerSphinx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
