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
public class AudioFileRecognizerStub implements AudioFileRecognizerInterface{
    private String stubText;


    public AudioFileRecognizerStub(String stubText) {
        this.stubText = stubText;

    }

    public void setStubText(String stubText){
        this.stubText = stubText;
    }

    public RecognizedTextOfSingleAudioFile recognize(String filePath, String unicFileIdentifier) {
        return new RecognizedTextOfSingleAudioFile(stubText, unicFileIdentifier);
    }

    public String getTextFromAudioFile(String audioFilePath, String fileIdentification) {
        return this.stubText;
    }



}
