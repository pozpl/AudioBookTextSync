/*
 *@autor pozpl
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *Class to hold book text, it's tokenized structure and
 * perform operations to improve it with audiobook information.
 * @author pozpl
 */
public class BookText {

    /**
     * the full text of Audiobook in plain text fomat
     */
    private String fullText = "";

    private String[] tokenizedBookText;

    private Integer[] textInNumericForm;

    private ArrayList<RecognizedTextOfSingleAudiofile> recognizedAudioFiles;

    private TextTokenizer textTokenizer;

    private WordsToNumsMap wordsToNumMapper;

    private LongestSubsequenceFinder longestSubsequenceFinder;

    public BookText(TextTokenizer textTokenizer,
            WordsToNumsMap wordsToNumMapper,
            LongestSubsequenceFinder subsequnceFinder
            ){
        this.recognizedAudioFiles = new ArrayList<RecognizedTextOfSingleAudiofile>();
        this.textTokenizer = textTokenizer;
        this.wordsToNumMapper = wordsToNumMapper;
        this.longestSubsequenceFinder = subsequnceFinder;
    }

    public void setFullText(String fullText){
        this.fullText = fullText;
        this.tokenizedBookText = textTokenizer.tokenize(fullText);
        this.textInNumericForm = this.wordsToNumMapper.getNumbersFromWords(tokenizedBookText);
    }

    public void registerRecognisedTextPeace(RecognizedTextOfSingleAudiofile recognizedFileText){
        this.recognizedAudioFiles.add(recognizedFileText);
    }

    public RecognizedTextOfSingleAudiofile[] getListOfRegistredAudiofiles(){
        return this.recognizedAudioFiles.toArray(
                new RecognizedTextOfSingleAudiofile[this.recognizedAudioFiles.size()]);
    }

    public String buildTextWithAudioMarks(){
        ArrayList<TreeMap<Integer, Integer>> recognizedTextLongestSubsequences = this.getLongestSubsequenceMappingFromRecognizedTexts();
        String markedText = "";
        Integer subsequenceCounter = 0;
        for(TreeMap<Integer, Integer> longestSubsequence: recognizedTextLongestSubsequences){
            RecognizedTextOfSingleAudiofile recognizedFile = this.recognizedAudioFiles.get(subsequenceCounter);
            for(Entry<Integer, Integer> fullTextToRecText: longestSubsequence.entrySet()){
                Integer fullTextWordIndex = fullTextToRecText.getKey();
                Integer recognizedTextWordIndex = fullTextToRecText.getValue();

                Double beginTime = recognizedFile.getBeginTimeOfTokenAtPosition(recognizedTextWordIndex);
                Double endTime = recognizedFile.getEndTimeOfTokenAtPosition(recognizedTextWordIndex);
            }
            subsequenceCounter++;
        }

        return "";
    }

    private ArrayList<TreeMap<Integer, Integer>> getLongestSubsequenceMappingFromRecognizedTexts(){
        ArrayList<TreeMap<Integer, Integer>> recognizedTextLongestSubsequences = new ArrayList<TreeMap<Integer, Integer>>();
        for(RecognizedTextOfSingleAudiofile recognizedText: this.recognizedAudioFiles){
            String[] recognizedTextAsTokens = recognizedText.getTokens();
            Integer[] recognizedTextAsNumbers = this.wordsToNumMapper.getNumbersFromWords(tokenizedBookText);
            TreeMap<Integer, Integer> recTextLongestSubsequence = this.longestSubsequenceFinder.findLogestSubsequence(this.textInNumericForm, recognizedTextAsNumbers);
            recognizedTextLongestSubsequences.add(recTextLongestSubsequence);
        }

        return recognizedTextLongestSubsequences;
    }
}
