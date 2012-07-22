/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmaker.audio;

import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.audio.AudioFileRecognizer;
import ru.urbancamper.audiobookmarker.context.BeansAnnotationsForTests;
import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudiofile;

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizerTest extends TestCase {

    public AudioFileRecognizerTest(String testName) {
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
     * Test of recognize method, of class AudioFileRecognizer.
     */
    public void testRecognize_String() {
        System.out.println("recognize");
        String filePath = "";
        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);
        AudioFileRecognizer instance = new AudioFileRecognizer(ctxt.getBean(ConfigurationManager.class));
        RecognizedTextOfSingleAudiofile expResult = null;
        RecognizedTextOfSingleAudiofile result = instance.recognize(filePath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of recognize method, of class AudioFileRecognizer.
     */
    public void testRecognize_File() {
        System.out.println("recognize");
        URL file = null;
        try {
            file = new URL("File path");
        } catch (MalformedURLException ex) {
            Logger.getLogger(AudioFileRecognizerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);
        AudioFileRecognizer instance = new AudioFileRecognizer(ctxt.getBean(ConfigurationManager.class));
        RecognizedTextOfSingleAudiofile expResult = null;
        RecognizedTextOfSingleAudiofile result = instance.recognize(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
