/*
 *@autor pozpl
 */
package ru.urbancamper.audiobookmarker.text.markerplacer;

import java.util.ArrayList;
import java.util.HashMap;

import ru.urbancamper.audiobookmarker.text.*;

/**
 *Class to hold book text, it's tokenized structure and
 * perform operations to improve it with audiobook information.
 * @author pozpl
 */
public class BookTextAudioAggregation {

    private String fullText;



    private ArrayList<RecognizedTextOfSingleAudioFile> recognizedAudioFiles;

    private HashMap<String, Integer> registredFileMapper;

    /**
     *
     * @param fullText
     * @param recognizedAudioFiles
     * @param registredFileMapper
     */
    public BookTextAudioAggregation(String fullText,
                                    ArrayList<RecognizedTextOfSingleAudioFile> recognizedAudioFiles,
                                    HashMap<String, Integer> registredFileMapper) {
        this.fullText = fullText;
        this.recognizedAudioFiles = recognizedAudioFiles;
        this.registredFileMapper = registredFileMapper;
    }


    public String getFullText() {
        return fullText;
    }

    public ArrayList<RecognizedTextOfSingleAudioFile> getRecognizedAudioFiles() {
        return recognizedAudioFiles;
    }

    public HashMap<String, Integer> getRegistredFileMapper() {
        return registredFileMapper;
    }
}
