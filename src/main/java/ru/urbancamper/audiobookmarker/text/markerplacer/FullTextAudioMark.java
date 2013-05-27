package ru.urbancamper.audiobookmarker.text.markerplacer;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: pozpl
 * Date: 27.05.13
 * Time: 8:10
 * Store sequence of words
 */
public class FullTextAudioMark {

    private ArrayList<Integer> fullTextTokens;

    private ArrayList<Integer> audioFileIndex;

    private ArrayList<Double>  beginTime;

    private ArrayList<Double>  endTime;

    public FullTextAudioMark(){
        this.audioFileIndex = new ArrayList<Integer>();
        this.beginTime = new ArrayList<Double>();
        this.endTime = new ArrayList<Double>();
    }

    public void putAudioInfo(Integer fullTextTokenIndex,
                             Integer fileIndex,
                             Double beginTime,
                             Double endTime){
        this.fullTextTokens.add(fullTextTokenIndex);
        this.audioFileIndex.add(fullTextTokenIndex, fileIndex);
        this.beginTime.add(fullTextTokenIndex, beginTime);
        this.endTime.add(fullTextTokenIndex, endTime);
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
