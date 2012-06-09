/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.ArrayList;
import java.util.Collections;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author pozpl
 */
public class RecognizedTextOfSingleAudiofileTest extends TestCase {

    private RecognizedTextOfSingleAudiofile classInstance;

    private String recTextString;

    public RecognizedTextOfSingleAudiofileTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(RecognizedTextOfSingleAudiofileTest.class);
        return suite;
    }

    @Override
    protected void setUp() throws Exception {
        this.recTextString = "word1(1.2, 1.4) word2(1.6, 1.9)";
        this.classInstance = new RecognizedTextOfSingleAudiofile(this.recTextString, "simpleaudio_hash");

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getTokens method, of class RecognizedTextOfSingleAudiofile.
     */
    public void testGetTokens() {
        System.out.println("getTokens");
        RecognizedTextOfSingleAudiofile instance = this.classInstance;

        String[] recognisedWordsList = {"word1", "word2"};
        String[] result = instance.getTokens();

        for(int tokenIndex = 0; tokenIndex < recognisedWordsList.length; tokenIndex++){
            assertEquals(recognisedWordsList[tokenIndex], result[tokenIndex]);
        }
    }

    /**
     * Test of getBeginTimeOfTokenAtPosition method, of class RecognizedTextOfSingleAudiofile.
     */
    public void testGetBeginTimeOfTokenAtPosition() {
        System.out.println("getBeginTimeOfTokenAtPosition");
        int wordIndex = 0;
        RecognizedTextOfSingleAudiofile instance = this.classInstance;
        Double expResult = Double.valueOf(1.2);;
        Double result = instance.getBeginTimeOfTokenAtPosition(wordIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndTimeOfTokenAtPosition method, of class RecognizedTextOfSingleAudiofile.
     */
    public void testGetEndTimeOfTokenAtPosition() {
        System.out.println("getEndTimeOfTokenAtPosition");
        int wordIndex = 0;
        RecognizedTextOfSingleAudiofile instance = this.classInstance;
        Double expResult = Double.valueOf(1.4);
        Double result = instance.getEndTimeOfTokenAtPosition(wordIndex);
        assertEquals(expResult, result);
    }
}
