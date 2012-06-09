/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author pozpl
 */
public class BookTextTest extends TestCase {

    public BookTextTest(String testName) {
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
     * Test of registerRecognisedTextPeace method, of class BookText.
     */
    public void testRegisterRecognisedTextPeace() {
        System.out.println("registerRecognisedTextPeace");
        RecognizedTextOfSingleAudiofile recognizedFileText = null;
        BookText instance = null;
        instance.registerRecognisedTextPeace(recognizedFileText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListOfRegistredAudiofiles method, of class BookText.
     */
    public void testGetListOfRegistredAudiofiles() {
        System.out.println("getListOfRegistredAudiofiles");
        BookText instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getListOfRegistredAudiofiles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildTextWithAudioMarks method, of class BookText.
     */
    public void testBuildTextWithAudioMarks() {
        System.out.println("buildTextWithAudioMarks");
        BookText instance = null;
        String expResult = "";
        String result = instance.buildTextWithAudioMarks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
