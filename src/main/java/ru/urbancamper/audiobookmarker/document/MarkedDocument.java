/*
 * This class is jast a storage for result document. It wil contain marked text
 * and other necesserities
 */
package ru.urbancamper.audiobookmarker.document;

import java.util.HashMap;

/**
 *
 * @author pozpl
 */
public class MarkedDocument {
    private String markedText;
    private HashMap<String, String> fileNamesToUidsMap;

    public MarkedDocument(String markedText, HashMap<String, String> fileNamesToUidsMap){
        this.markedText = markedText;
        this.fileNamesToUidsMap = fileNamesToUidsMap;
    }

    public String getMarkedText(){
        return this.markedText;
    }

    /**
     *
     * @return
     */
    public HashMap<String, String> getFileNamesToUidsMap(){
        return this.fileNamesToUidsMap;
    }
}
