/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.audio;

import java.net.URL;
import junit.framework.TestCase;
import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudiofile;

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizerSphinxTest extends TestCase {

    public AudioFileRecognizerSphinxTest(String testName) {
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

    /**
     * Test of recognize method, of class AudioFileRecognizerSphinx.
     */
    public void testRecognize_URL_String() {
        System.out.println("recognize");
        URL fileURL = null;
        String fileUnicIdentifier = "";
        AudioFileRecognizerSphinx instance = null;
        RecognizedTextOfSingleAudiofile expResult = null;
        RecognizedTextOfSingleAudiofile result = instance.recognize(fileURL, fileUnicIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recognize method, of class AudioFileRecognizerSphinx.
     */
    public void testRecognize_String_String() {
        System.out.println("recognize");
        String filePath = "";
        String unicFileIdentifier = "";
        AudioFileRecognizerSphinx instance = null;
        RecognizedTextOfSingleAudiofile expResult = null;
        RecognizedTextOfSingleAudiofile result = instance.recognize(filePath, unicFileIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
