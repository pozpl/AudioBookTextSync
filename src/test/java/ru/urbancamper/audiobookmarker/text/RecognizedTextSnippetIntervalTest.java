/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.Set;
import junit.framework.TestCase;

/**
 *
 * @author pozpl
 */
public class RecognizedTextSnippetIntervalTest extends TestCase {

    public RecognizedTextSnippetIntervalTest(String testName) {
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
     * Test of calculateFullTextBoundsForRecognizedSnippet method, of class RecognizedTextSnippetInterval.
     */
    public void testCalculateFullTextBoundsForRecognizedSnippet() {
        System.out.println("calculateFullTextBoundsForRecognizedSnippet");
        Integer[] fullText = null;
        Integer[] subText = null;
        RecognizedTextSnippetInterval instance = new RecognizedTextSnippetInterval();
        Integer[] expResult = null;
        Integer[] result = instance.calculateFullTextBoundsForRecognizedSnippet(fullText, subText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
