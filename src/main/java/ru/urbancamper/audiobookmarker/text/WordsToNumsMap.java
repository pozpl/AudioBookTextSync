/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author pozpl
 */
public class WordsToNumsMap {

    private Integer newWordsCounter;

    private HashMap<String, Integer> wordsNumbersMap;

    public WordsToNumsMap(){
        this.newWordsCounter = 0;
        this.wordsNumbersMap = new HashMap<String, Integer>();
    }


    public Integer[] getNumbersFromWords(ArrayList<String> words){
        ArrayList<Integer> wordsNumbersList = new ArrayList<Integer>();
        for (Iterator<String> wordIterator = words.iterator(); wordIterator.hasNext();) {
            String word = wordIterator.next();
            Integer numberForWord;
            if(this.wordsNumbersMap.containsKey(word)){
                numberForWord = this.wordsNumbersMap.get(word);
            }else{
                this.wordsNumbersMap.put(word, this.newWordsCounter);
                numberForWord = this.newWordsCounter;
                this.newWordsCounter++;
            }
            wordsNumbersList.add(numberForWord);
        }
        return wordsNumbersList.toArray(new Integer[wordsNumbersList.size()]);
    }

    public Integer[] getNumbersFromWords(String[] words){
        ArrayList<Integer> wordsNumbersList = new ArrayList<Integer>();
        for (String word: words) {
            Integer numberForWord;
            if(this.wordsNumbersMap.containsKey(word)){
                numberForWord = this.wordsNumbersMap.get(word);
            }else{
                this.wordsNumbersMap.put(word, this.newWordsCounter);
                numberForWord = this.newWordsCounter;
                this.newWordsCounter++;
            }
            wordsNumbersList.add(numberForWord);
        }
        return wordsNumbersList.toArray(new Integer[wordsNumbersList.size()]);
    }
}
