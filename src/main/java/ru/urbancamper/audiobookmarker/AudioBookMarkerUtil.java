/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker;

import ru.urbancamper.audiobookmarker.audio.AudioFileRecognizerInterface;
import ru.urbancamper.audiobookmarker.text.BookText;
import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudiofile;

/**
 *
 * @author pozpl
 */
public class AudioBookMarkerUtil {

    private BookText bookTextAggregator;

    private AudioFileRecognizerInterface audioRecognizer;

    public AudioBookMarkerUtil(BookText bookText, AudioFileRecognizerInterface audioRecognizer){
        this.bookTextAggregator = bookText;
        this.audioRecognizer = audioRecognizer;
    }

    public String makeMarkers(String[] audioBookFilesPaths, String fullText){
        this.bookTextAggregator.setFullText(fullText);
        for(String audioFilePath: audioBookFilesPaths){
            RecognizedTextOfSingleAudiofile recognizedFile = this.audioRecognizer.recognize(audioFilePath, audioFilePath);
            this.bookTextAggregator.registerRecognizedTextPiece(recognizedFile);
        }
        String markedText = this.bookTextAggregator.buildTextWithAudioMarks();
        return markedText;
    }


}
