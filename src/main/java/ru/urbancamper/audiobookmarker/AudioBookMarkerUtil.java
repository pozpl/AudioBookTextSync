/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker;

import java.io.File;
import java.util.HashMap;
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

    private HashMap<String, String> audioFilesIdentificatorMap;

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

    private String getAudioFileName(String audioFilePath){
        String fileName;
        fileName = new File(audioFilePath).getName();
        return fileName;
    }

}
