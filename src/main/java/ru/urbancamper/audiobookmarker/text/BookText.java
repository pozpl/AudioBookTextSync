/*
 *@autor pozpl
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.ArrayList;

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

    private ArrayList<RecognizedTextOfSingleAudiofile> audiofilesHashes;

    public BookText(TextTokenizer textTokenizer,
            WordsToNumsMap wordsToNumMapper,
            LongestSubsequenceFinder subsequnceFinder
            ){

    }

    public void setFullText(String fullText){
        this.fullText = fullText;
    }

    public void registerRecognisedTextPeace(RecognizedTextOfSingleAudiofile recognizedFileText){

    }

    public ArrayList<RecognizedTextOfSingleAudiofile> getListOfRegistredAudiofiles(){
        return new ArrayList<RecognizedTextOfSingleAudiofile>();
    }

    public String buildTextWithAudioMarks(){
        return "";
    }
}
