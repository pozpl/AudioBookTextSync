/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.audio;

import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudiofile;

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizerStub implements AudioFileRecognizerInterface{
    private String stubText;
    private String stubFileIdentificator;

    public AudioFileRecognizerStub(String stubText, String stubFileIdentificator) {
        this.stubText = stubText;
        this.stubFileIdentificator = stubFileIdentificator;
    }


    public RecognizedTextOfSingleAudiofile recognize(String filePath, String unicFileIdentifier) {
        return new RecognizedTextOfSingleAudiofile(stubText, stubFileIdentificator);
    }

}
