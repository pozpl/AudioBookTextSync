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

    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * the full text of Audiobook in plain text fomat
     */
    private String fullText = "";

    private String[] tokenizedBookText;

    private Integer[] textInNumericForm;

    private ArrayList<RecognizedTextOfSingleAudioFile> recognizedAudioFiles;

    private HashMap<String, Integer> registredFileMapper;

    /**
     *
     * @param fullText
     * @param tokenizedBookText
     * @param textInNumericForm
     * @param recognizedAudioFiles
     * @param registredFileMapper
     */
    public BookTextAudioAggregation(String fullText,
                                    String[] tokenizedBookText,
                                    Integer[] textInNumericForm,
                                    ArrayList<RecognizedTextOfSingleAudioFile> recognizedAudioFiles,
                                    HashMap<String, Integer> registredFileMapper) {
        this.fullText = fullText;
        this.tokenizedBookText = tokenizedBookText;
        this.textInNumericForm = textInNumericForm;
        this.recognizedAudioFiles = recognizedAudioFiles;
        this.registredFileMapper = registredFileMapper;
    }

    public String getFullText() {
        return fullText;
    }

    public String[] getTokenizedBookText() {
        return tokenizedBookText;
    }

    public Integer[] getTextInNumericForm() {
        return textInNumericForm;
    }

    public ArrayList<RecognizedTextOfSingleAudioFile> getRecognizedAudioFiles() {
        return recognizedAudioFiles;
    }

    public HashMap<String, Integer> getRegistredFileMapper() {
        return registredFileMapper;
    }
}
