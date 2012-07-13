/*
 * This class purpose is to recognize audiofile to text
 */
package ru.urbancamper.audiobookmaker.audio;

import java.io.File;
import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudiofile;

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizer {
    public RecognizedTextOfSingleAudiofile recognize(String filePath){
        File file = new File(filePath);
        return this.recognize(file);
    }
    public RecognizedTextOfSingleAudiofile recognize(File file){
        //@TODO Implement this
        return null;
    }

    private String getFileHash(File file){
        return "";
    }
}
