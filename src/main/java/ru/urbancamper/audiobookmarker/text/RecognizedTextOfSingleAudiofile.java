/*
 * This class implements data structure and methods
 * for the text we get from an audio recognition software
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pozpl
 */
public class RecognizedTextOfSingleAudiofile {
    /**
     * aligned recognized text in string form
     */
    private String alignedTextSting;

    /**
     *Recognized text splited to tokens
     */
    private ArrayList<String> alignedTextTokens;

    /**
     * Time when a token is started in audiofile.
     * Order is in sync with alignedTextTokens
     */
    private ArrayList<Double> startTimeOfTokens;

    /**
     * Time when a token is stop to play in audiofile
     * Order is in sync with alignedTextTokens
     */
    private ArrayList<Double> stopTimeOfTokens;

    /**
     * hash summ from binary content of audiofile
     */
    private String audioFileHash;

    /**
     * Constructor of class RecognizedTextOfSingleAudiofile
     * @param recognizedTextString String with such form:
     * word1(1.2, 1.4) word2(1.6, 1.9)....
     * The input format of recognizedTextString is output format of Spjinx4
     * from aligned tokenizer
     * @param audioFileHash md5, sha or whatever hash of file
     * to distinguish one file from another even if names would changed
     */
    public RecognizedTextOfSingleAudiofile(String recognizedTextString, String audioFileHash){
        this.alignedTextSting = recognizedTextString;
        this.alignedTextTokens = new ArrayList<String>();
        this.startTimeOfTokens = new ArrayList<Double>();
        this.stopTimeOfTokens = new ArrayList<Double>();
        this.audioFileHash = audioFileHash;
        this.splitTextToTokens();
    }

    private void splitTextToTokens(){
        String tokenRegex = "(?<word>\\w+)\\((?<beginTime>\\d+\\.\\d+),\\s+(?<endTime>\\d+\\.\\d+)\\)";

        Pattern tokenPattern = Pattern.compile(tokenRegex);
        Matcher tokensMatcher = tokenPattern.matcher(this.alignedTextSting);
        while(tokensMatcher.find()){
            this.alignedTextTokens.add(tokensMatcher.group("word"));
            this.startTimeOfTokens.add(Double.valueOf(tokensMatcher.group("beginTime")));
            this.stopTimeOfTokens.add(Double.valueOf(tokensMatcher.group("endTime")));
        }
    }

    /**
     *
     * @return
     */
    public String[] getTokens(){
        return this.alignedTextTokens.toArray(new String[this.alignedTextTokens.size()]);
    }

    public Double getBeginTimeOfTokenAtPosition(Integer wordIndex){
        return this.startTimeOfTokens.get(wordIndex);
    }

    public Double getEndTimeOfTokenAtPosition(Integer wordIndex){
        return this.stopTimeOfTokens.get(wordIndex);
    }

    public String getAudioFileHash(){
        return this.audioFileHash;
    }

}
