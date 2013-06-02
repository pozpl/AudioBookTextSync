package ru.urbancamper.audiobookmarker.text.markerplacer;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: pozpl
 * Date: 27.05.13
 * Time: 8:10
 * Store sequence of words
 */
public class FullTextAudioMark {

    private ArrayList<Integer> fullTextTokens;

    private TreeMap<Integer, Integer> audioFileIndex;

    private TreeMap<Integer, Double>  beginTime;

    private TreeMap<Integer, Double>  endTime;

    public FullTextAudioMark(){
        this.fullTextTokens = new ArrayList<Integer>();
        this.audioFileIndex = new TreeMap<Integer, Integer>();
        this.beginTime = new TreeMap<Integer, Double>();
        this.endTime = new TreeMap<Integer, Double>();
    }

    public void putAudioInfo(Integer fullTextTokenIndex,
                             Integer fileIndex,
                             Double beginTime,
                             Double endTime){
        this.fullTextTokens.add(fullTextTokenIndex);
        this.audioFileIndex.put(fullTextTokenIndex, fileIndex);
        this.beginTime.put(fullTextTokenIndex, beginTime);
        this.endTime.put(fullTextTokenIndex, endTime);
    }

    public Boolean isMarkerDefinedForFullTextIndex(Integer fullTextWordIndex){
        return audioFileIndex.containsKey(fullTextWordIndex);
    }

    public Integer[] getFullTextTokenIndexes(){
        return this.fullTextTokens.toArray(new Integer[this.fullTextTokens.size()]);
    }

    public Integer getFileIndex(Integer fullTextTokenIndex){
        return audioFileIndex.get(fullTextTokenIndex);
    }

    public Double getBeginTime(Integer fullTextTokenIndex){
        return beginTime.get(fullTextTokenIndex);
    }

    public Double getEndTime(Integer fullTextTokenIndex){
        return endTime.get(fullTextTokenIndex);
    }
}
