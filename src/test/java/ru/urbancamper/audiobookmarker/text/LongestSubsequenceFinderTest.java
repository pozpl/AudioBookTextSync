/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.ArrayList;
import java.util.TreeMap;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author pozpl
 */
public class LongestSubsequenceFinderTest extends TestCase {

    private Integer[] fullText;
    private Integer[] partialText;
    private WordsToNumsMap wordsToNums;

    public LongestSubsequenceFinderTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(LongestSubsequenceFinderTest.class);
        return suite;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.wordsToNums = new WordsToNumsMap();
        this.fullText = this.createFullTextArray();
        this.partialText = this.createSubsequentTextArray();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public Integer[] createFullTextArray(){
        ArrayList<String> testWordsArray = new ArrayList<String>();
        String baseWord = "word";
        for (int wordsCounter = 0; wordsCounter < 10; wordsCounter++) {
            String wordToAdd = baseWord + String.valueOf(wordsCounter);
            testWordsArray.add(wordToAdd);
        }


        Integer[] textInNumbers = this.wordsToNums.getNumbersFromWords(testWordsArray);

        return textInNumbers;
    }

    public Integer[] createSubsequentTextArray(){
        ArrayList<String> testWordsArray = new ArrayList<String>();
        String baseWord = "word";
        String seedWord = "faile_wod";
        for (int wordsCounter = 0; wordsCounter < 10; wordsCounter+=2) {
            String wordToAdd = baseWord + String.valueOf(wordsCounter);
            testWordsArray.add(wordToAdd);
            testWordsArray.add(seedWord + String.valueOf(wordsCounter));
            testWordsArray.add(seedWord + String.valueOf(wordsCounter));
        }

        Integer[] textInNumbers = this.wordsToNums.getNumbersFromWords(testWordsArray);

        return textInNumbers;
    }

    /**
     * Test of findLogestSubsequence method, of class LongestSubsequenceFinder.
     */
    public void testFindLogestSubsequence() {

        LongestSubsequenceFinder instance = new LongestSubsequenceFinder();
        TreeMap<Integer, Integer> expResult = new TreeMap<Integer, Integer>();
        expResult.put(Integer.valueOf(0), Integer.valueOf(0));
        expResult.put(Integer.valueOf(3), Integer.valueOf(2));
        expResult.put(Integer.valueOf(6), Integer.valueOf(4));
        expResult.put(Integer.valueOf(9), Integer.valueOf(6));
        expResult.put(Integer.valueOf(12), Integer.valueOf(8));
        TreeMap<Integer, Integer> result = instance.findLogestSubsequence(this.fullText, this.partialText);
        for(Integer wordsCounter = 0; wordsCounter < partialText.length; wordsCounter++){
            assertEquals(wordsCounter * Integer.valueOf(3), result.get(wordsCounter).intValue());
        }
        assertEquals(expResult, result);
    }
}
