/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.audio.AudioFileRecognizerStub;
import ru.urbancamper.audiobookmarker.context.BeansAnnotationsForTests;
import ru.urbancamper.audiobookmarker.document.MarkedDocument;
import ru.urbancamper.audiobookmarker.text.LanguageModelBasedTextTokenizer;
import ru.urbancamper.audiobookmarker.text.markerplacer.BookTextAudioAggregation;
import ru.urbancamper.audiobookmarker.text.markerplacer.BookTextAudioAggregationBuilder;
import ru.urbancamper.audiobookmarker.text.markerplacer.BookTextRecognizedTextAggregationService;
import ru.urbancamper.audiobookmarker.text.markerplacer.Text;

/**
 *
 * @author pozpl
 */
public class AudioBookMarkerUtilTest extends TestCase {

    private String RECOGNIZED_AND_ALIGNED_STUB_TEXT = "some(2.1, 2.7) kind(3.0, 4.0) of(4.5, 5.0)"
            + " text(6.1, 7.0) here(9.0, 10.0)";
    private String BOOK_TEXT = "there is some kind of magnificent peace of literature text here";

    public AudioBookMarkerUtilTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testMakeMarkers() {

        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);
        AudioFileRecognizerStub recognizer = (AudioFileRecognizerStub) ctxt.getBean("audioFileRecognizerStub");
        recognizer.setStubText(RECOGNIZED_AND_ALIGNED_STUB_TEXT);
        BookTextRecognizedTextAggregationService bookText = ctxt.getBean(BookTextRecognizedTextAggregationService.class);
        BookTextAudioAggregationBuilder bookTextAudioAggregationBuilder =
                ctxt.getBean(BookTextAudioAggregationBuilder.class);
        LanguageModelBasedTextTokenizer textTokenizer = ctxt.getBean(LanguageModelBasedTextTokenizer.class);
        Text textMarkerPlacer = ctxt.getBean(Text.class);
        AudioBookMarkerUtil util = new AudioBookMarkerUtil(bookText, recognizer, bookTextAudioAggregationBuilder,
                textTokenizer, textMarkerPlacer);

        String[] filePaths = {"/some/fictional/path"};
        MarkedDocument markedDocument = util.makeMarkers(filePaths, this.BOOK_TEXT);
        String markedText = markedDocument.getMarkedText();

        assertEquals("there is <0:2.1>some <0:3.0>kind of magnificent peace <0:4.5>of literature <0:6.1>text <0:9.0>here", markedText);

        HashMap<String, String> filesNamesToUidMap = markedDocument.getFileNamesToUidsMap();
        HashMap<String, String> filesNamesToUidMapExpected = new HashMap<String, String>();
        filesNamesToUidMapExpected.put("path", String.valueOf(0));
        assertEquals(filesNamesToUidMapExpected, filesNamesToUidMap);

    }

    public void testMakeMarkersDirVersion() {

        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);
        AudioFileRecognizerStub recognizer = (AudioFileRecognizerStub) ctxt.getBean("audioFileRecognizerStub");
        recognizer.setStubText(RECOGNIZED_AND_ALIGNED_STUB_TEXT);
        BookTextRecognizedTextAggregationService bookText = ctxt.getBean(BookTextRecognizedTextAggregationService.class);
        BookTextAudioAggregationBuilder bookTextAudioAggregationBuilder =
                                                            ctxt.getBean(BookTextAudioAggregationBuilder.class);
        LanguageModelBasedTextTokenizer textTokenizer = ctxt.getBean(LanguageModelBasedTextTokenizer.class);
        Text textMarkerPlacer = ctxt.getBean(Text.class);
        AudioBookMarkerUtil util = new AudioBookMarkerUtil(bookText, recognizer, bookTextAudioAggregationBuilder,
                textTokenizer, textMarkerPlacer);
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/test.properties"));

            String fileDir = prop.getProperty("TEST_AUDIO_DIR");
            String bookFileDir = prop.getProperty("TEST_BOOK_PATH");
            MarkedDocument markedDocument = util.makeMarkers(fileDir, bookFileDir);
            String markedText = markedDocument.getMarkedText();

            assertEquals("there is <0:2.1>some <0:3.0>kind of magnificent peace <0:4.5>of literature <0:6.1>text <0:9.0>here", markedText);

            HashMap<String, String> filesNamesToUidMap = markedDocument.getFileNamesToUidsMap();
            HashMap<String, String> filesNamesToUidMapExpected = new HashMap<String, String>();
            filesNamesToUidMapExpected.put("single_file", String.valueOf(0));
            assertEquals(filesNamesToUidMapExpected, filesNamesToUidMap);
        } catch (IOException ex) {
            Logger.getLogger(AudioBookMarkerUtilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
