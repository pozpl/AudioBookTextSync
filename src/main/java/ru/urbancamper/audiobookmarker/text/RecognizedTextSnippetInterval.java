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
        for(int clusterCounter = 0; clusterCounter < allClustersNumber; clusterCounter++){
            TreeMap<Integer, Integer>
        }
        return null;
    }

    private TreeMap<Integer, Integer> wordsFrequencesForTextSnippet(Integer[] textArray,
            Integer firstElementIndex, Integer lastElementIndex){
        TreeMap<Integer, Integer> wordsFrequences = new TreeMap<Integer, Integer>();
        for(Integer wordsCounter = firstElementIndex; wordsCounter < lastElementIndex; wordsCounter++){
            if(wordsFrequences.containsKey(textArray[wordsCounter])){
                Integer oldFrequence = wordsFrequences.get(textArray[wordsCounter]);
                wordsFrequences.put(textArray[wordsCounter], oldFrequence + 1);
            }
        }

        return wordsFrequences;
    }

}
