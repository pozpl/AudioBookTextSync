/*
 *@autor pozpl
 */
package ru.urbancamper.audiobookmarker.text.markerplacer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.urbancamper.audiobookmarker.text.*;

/**
 *Class to hold book text, it's tokenized structure and
 * perform operations to improve it with audiobook information.
 * @author pozpl
 */
public class BookTextAudioAggregation {

    private String[] tokenizedBookText;



    private ArrayList<RecognizedTextOfSingleAudioFile> recognizedAudioFiles;

    private HashMap<String, Integer> registredFileMapper;

    /**
     *
     * @param tokenizedBookText
     * @param recognizedAudioFiles
     * @param registredFileMapper
     */
    public BookTextAudioAggregation(String[] tokenizedBookText,
                                    ArrayList<RecognizedTextOfSingleAudioFile> recognizedAudioFiles,
                                    HashMap<String, Integer> registredFileMapper) {
        this.tokenizedBookText = tokenizedBookText;
        this.recognizedAudioFiles = recognizedAudioFiles;
        this.registredFileMapper = registredFileMapper;
    }


    public String[] getTokenizedBookText() {
        return tokenizedBookText;
    }

    public ArrayList<RecognizedTextOfSingleAudioFile> getRecognizedAudioFiles() {
        return recognizedAudioFiles;
    }

    public HashMap<String, Integer> getRegistredFileMapper() {
        return registredFileMapper;
    }
}
