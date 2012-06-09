/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.ArrayList;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author pozpl
 */
public class WordsToNumsMapTest extends TestCase {

    public WordsToNumsMapTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(WordsToNumsMapTest.class);
        return suite;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
    public void testGetNumbersArrayFromWordsArray(){
        ArrayList<String> testWordsArray = new ArrayList<String>();
        String baseWord = "word";
        for (int wordsCounter = 0; wordsCounter < 10; wordsCounter++) {
            String wordToAdd = baseWord + String.valueOf(wordsCounter);
            testWordsArray.add(wordToAdd);
        }

        WordsToNumsMap wordsToNums = new WordsToNumsMap();
        Integer[] textInNumbers = wordsToNums.getNumbersFromWords(testWordsArray);

        WordsToNumsMapTest.assertEquals(textInNumbers.length, testWordsArray.size());
        for (int numCounter = 0; numCounter < 10; numCounter++) {
            WordsToNumsMapTest.assertEquals(textInNumbers[numCounter], Integer.valueOf(numCounter));
        }

        textInNumbers = wordsToNums.getNumbersFromWords(testWordsArray);
        //Words<->numbers representation do not have to change in second time with th same
        //text
        WordsToNumsMapTest.assertEquals(textInNumbers.length, testWordsArray.size());
        for (int numCounter = 0; numCounter < 10; numCounter++) {
            WordsToNumsMapTest.assertEquals(textInNumbers[numCounter], Integer.valueOf(numCounter));
        }
    }

    public void testGetNumbersArrayFromWordsForTwoDiffrentTexts(){
        ArrayList<String> firstWordsArray = new ArrayList<String>();
        String baseWord = "word";
        for (int wordsCounter = 0; wordsCounter < 10; wordsCounter++) {
            String wordToAdd = baseWord + String.valueOf(wordsCounter);
            firstWordsArray.add(wordToAdd);
        }
        ArrayList<String> secondWordsArray = new ArrayList<String>();
        baseWord = "sloop";
        for (int wordsCounter = 0; wordsCounter < 10; wordsCounter++) {
            String wordToAdd = baseWord + String.valueOf(wordsCounter);
            secondWordsArray.add(wordToAdd);
        }

        WordsToNumsMap wordsToNums = new WordsToNumsMap();
        Integer[] firstTextInNumbers = wordsToNums.getNumbersFromWords(firstWordsArray);

        WordsToNumsMapTest.assertEquals(firstTextInNumbers.length, firstWordsArray.size());
        for (int numCounter = 0; numCounter < 10; numCounter++) {
            WordsToNumsMapTest.assertEquals(firstTextInNumbers[numCounter], Integer.valueOf(numCounter));
        }

        Integer[] secondTextInNumbers = wordsToNums.getNumbersFromWords(secondWordsArray);
        //Words<->numbers representation do not have to change in second time with th same
        //text
        WordsToNumsMapTest.assertEquals(secondTextInNumbers.length, secondWordsArray.size());
        for (int numCounter = 0; numCounter < 10; numCounter++) {
            WordsToNumsMapTest.assertEquals(secondTextInNumbers[numCounter], Integer.valueOf(numCounter + 10));
        }
    }

    public void testGetNumbersArrayFromWordsForIntersectedTexts(){
        ArrayList<String> firstWordsArray = new ArrayList<String>();
        String baseWord = "word";
        for (int wordsCounter = 0; wordsCounter < 10; wordsCounter++) {
            String wordToAdd = baseWord + String.valueOf(wordsCounter);
            firstWordsArray.add(wordToAdd);
        }
        ArrayList<String> secondWordsArray = new ArrayList<String>();
        baseWord = "word";
        for (int wordsCounter = 5; wordsCounter < 10; wordsCounter++) {
            String wordToAdd = baseWord + String.valueOf(wordsCounter);
            secondWordsArray.add(wordToAdd);
        }
        baseWord = "sloop";
        for (int wordsCounter = 0; wordsCounter < 5; wordsCounter++) {
            String wordToAdd = baseWord + String.valueOf(wordsCounter);
            secondWordsArray.add(wordToAdd);
        }
        WordsToNumsMap wordsToNums = new WordsToNumsMap();
        Integer[] firstTextInNumbers = wordsToNums.getNumbersFromWords(firstWordsArray);

        WordsToNumsMapTest.assertEquals(firstTextInNumbers.length, firstWordsArray.size());
        for (int numCounter = 0; numCounter < 10; numCounter++) {
            WordsToNumsMapTest.assertEquals(firstTextInNumbers[numCounter], Integer.valueOf(numCounter));
        }

        Integer[] secondTextInNumbers = wordsToNums.getNumbersFromWords(secondWordsArray);

        WordsToNumsMapTest.assertEquals(secondTextInNumbers.length, secondWordsArray.size());
        for (int numCounter = 0; numCounter < 10; numCounter++) {
            WordsToNumsMapTest.assertEquals(secondTextInNumbers[numCounter], Integer.valueOf(numCounter + 5));
        }
    }


}
