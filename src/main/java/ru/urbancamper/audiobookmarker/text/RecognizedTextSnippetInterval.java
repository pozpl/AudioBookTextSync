/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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

        TreeMap<Integer, Integer> subTextWordsFrequences =
                this.wordsFrequencesForTextSnippet(subText, 0, subText.length);

        TreeMap<Integer, Integer> fullTextSnippetWordsFrequences =
                this.wordsFrequencesForTextSnippet(fullText, 0, subText.length);

        Integer allClustersNumber = fullText.length - subText.length;
        Integer previowsWord = fullText[0];
        for(int clusterCounter = 1; clusterCounter < allClustersNumber; clusterCounter++){
            Integer addedWord = fullText[clusterCounter + subText.length - 1];
            fullTextSnippetWordsFrequences =
                    this.wordsFrequencesForTextSnippet(fullText, clusterCounter, subText.length);
        }
        return null;
    }

    /**
     * Calculate equlidian norm for two frequencies vectors
     * @param fullTestSnippetFreqs
     * @param subTestSnippetFreqs
     * @return
     */
    private Double equlidianDistanceBetwinWorsFrequencoesVectors(
            TreeMap<Integer, Integer> fullTestSnippetFreqs,
            TreeMap<Integer, Integer> subTestSnippetFreqs){
        Set<Integer> subTextkeysSet = subTestSnippetFreqs.keySet();
        Set<Integer> fullTextkeysSet = fullTestSnippetFreqs.keySet();

        Set<Integer> keysUnion = this.union(subTextkeysSet, fullTextkeysSet);
        Iterator<Integer> keysIterator = keysUnion.iterator();
        Integer aggregatedSumm = 0;
        while(keysIterator.hasNext()){
            Integer key = keysIterator.next();
            Boolean fullTextContainsKey = fullTestSnippetFreqs.containsKey(key);
            Boolean subTextContainsKey =  subTestSnippetFreqs.containsKey(key);
            if(fullTextContainsKey && subTextContainsKey){
                Integer subWordFreq = subTestSnippetFreqs.get(key);
                Integer fullWordFreq = fullTestSnippetFreqs.get(key);
                aggregatedSumm += (fullWordFreq - subWordFreq) * (fullWordFreq - subWordFreq);
            }else if(fullTextContainsKey){
                Integer fullWordFreq = fullTestSnippetFreqs.get(key);
                aggregatedSumm += fullWordFreq * fullWordFreq;
            }else if(subTextContainsKey){
                Integer subWordFreq = subTestSnippetFreqs.get(key);
                aggregatedSumm += subWordFreq * subWordFreq;
            }
        }
        
        return 0.0;
    }

    public static <T> Set<T> union(Set<T> setA, Set<T> setB) {
        Set<T> tmp = new TreeSet<T>(setA);
        tmp.addAll(setB);
        return tmp;
    }

    public static <T> Set<T> intersection(Set<T> setA, Set<T> setB) {
        Set<T> tmp = new TreeSet<T>();
        for (T x : setA) {
            if (setB.contains(x)) {
                tmp.add(x);
            }
        }
        return tmp;
    }

    public static <T> Set<T> difference(Set<T> setA, Set<T> setB) {
        Set<T> tmp = new TreeSet<T>(setA);
        tmp.removeAll(setB);
        return tmp;
    }

    public static <T> Set<T> symDifference(Set<T> setA, Set<T> setB) {
        Set<T> tmpA;
        Set<T> tmpB;

        tmpA = union(setA, setB);
        tmpB = intersection(setA, setB);
        return difference(tmpA, tmpB);
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
