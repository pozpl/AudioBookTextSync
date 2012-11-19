/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.TreeMap;

/**
 *
 * @author pozpl
 */
public class RecognizedTextSnippetInterval {

    /**
     * This function calculate two bounds in a full text array in which  a recognized
     * snippet are probably reside.
     * @param fullText full text in integers array form
     * @param subText sub text in integers array form
     * @return Integer[] bounds indexes of first word and last word to place
     * recognized text to.
     */
    public Integer[] calculateFullTextBoundsForRecognizedSnippet(
            Integer[] fullText, Integer[] subText){
        Integer allClustersNumber = fullText.length - subText.length;
        TreeMap<Integer, Integer> subTextWordsFrequences =
                this.wordsFrequencesForTextSnippet(subText, 0, subText.length);
        for(int clusterCounter = 0; clusterCounter < allClustersNumber; clusterCounter++){
            //TreeMap<Integer, Integer>
        }
        return null;
    }

    /**
     * Function to calculate for text snippet an array where key will be an word
     * and value will be a count of this word in text snippet
     * @param textArray
     * @param offset - offset in array
     * @param limit - max number of elements in array
     * @return
     */
    private TreeMap<Integer, Integer> wordsFrequencesForTextSnippet(Integer[] textArray,
            Integer offset, Integer limit){
        TreeMap<Integer, Integer> wordsFrequences = new TreeMap<Integer, Integer>();
        for(Integer wordsCounter = offset; wordsCounter <= offset + limit; wordsCounter++){
            if(wordsFrequences.containsKey(textArray[wordsCounter])){
                Integer oldFrequence = wordsFrequences.get(textArray[wordsCounter]);
                wordsFrequences.put(textArray[wordsCounter], oldFrequence + 1);
            }
        }

        return wordsFrequences;
    }

}
