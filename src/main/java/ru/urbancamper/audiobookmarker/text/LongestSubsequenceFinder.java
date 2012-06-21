/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author pozpl
 */
public class LongestSubsequenceFinder {

    public TreeMap<Integer, Integer> findLogestSubsequence(Integer[] fullArray,
            Integer[] subArray) {

        int[][] lengths = new int[fullArray.length + 1][subArray.length + 1];

        // row 0 and column 0 are initialized to 0 already

        for (int i = 0; i < fullArray.length; i++) {
            for (int j = 0; j < subArray.length; j++) {
                if (fullArray[i] == subArray[j]) {
                    lengths[i + 1][j + 1] = lengths[i][j] + 1;
                } else {
                    lengths[i + 1][j + 1] =
                            Math.max(lengths[i + 1][j], lengths[i][j + 1]);
                }
            }
        }

        // read the substring out from the matrix
//        StringBuffer sb = new StringBuffer();
        TreeMap<Integer, Integer> resultBuffer = new TreeMap<Integer, Integer>();
        for (int x = fullArray.length, y = subArray.length;
                x != 0 && y != 0;) {
            if (lengths[x][y] == lengths[x - 1][y]) {
                x--;
            } else if (lengths[x][y] == lengths[x][y - 1]) {
                y--;
            } else {
                assert fullArray[x - 1] == subArray[y - 1];
//                sb.append(a.get(x - 1));
                resultBuffer.put(x-1, y-1);
                x--;
                y--;
            }
        }

        return resultBuffer;
    }

    public TreeMap<Integer, Integer> getLongestSubsequenceWithMinDistance(
            Integer[] fullArray,
            Integer[] subArray){
        TreeMap<Integer, Integer> longestSubsequence = this.findLogestSubsequence(fullArray, subArray);
        Integer previousFullTextIndex = 0;
        Integer previousSubTextIndex = 0;

        Integer previousFullTextElement = -1;
        Integer previousSubTextElement = -1;
        ArrayList<Integer> fullTextBuffer = new ArrayList<Integer>();
        ArrayList<Integer> subTextBuffer = new ArrayList<Integer>();
        Integer entriesCounter = 0;
        for(Map.Entry<Integer, Integer> fullTextToRecTextMapEntry: longestSubsequence.entrySet()){
            Integer fullTextWordIndex = fullTextToRecTextMapEntry.getKey();
            Integer subTextWordIndex = fullTextToRecTextMapEntry.getValue();

            fullTextBuffer.add(fullTextWordIndex);
            subTextBuffer.add(subTextWordIndex);

            Integer currentFullTextElement = fullArray[fullTextWordIndex];
            Integer currentSubTextElement = fullArray[subTextWordIndex];
            for(Integer spanCounter = previousFullTextIndex; spanCounter < fullTextWordIndex; spanCounter++){
                Integer elementlToCheck = fullArray[spanCounter];
                if(elementlToCheck < previousFullTextElement){
                    fullTextBuffer.set(entriesCounter, spanCounter);
                }
            }

        }

        return null;
    }
}
