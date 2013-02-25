/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.audio;

import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudioFile;

/**
 *
 * @author pozpl
 */
public interface AudioFileRecognizerInterface {

    public RecognizedTextOfSingleAudioFile recognize(String filePath, String unicFileIdentifier);
    /**
     *
     * @param audioFilePath
     * @param fileIdentification 
     * @return
     */
    public String getTextFromAudioFile(String audioFilePath, String fileIdentification);
}
