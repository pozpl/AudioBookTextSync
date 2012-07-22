/*
 * This class purpose is to recognize audiofile to text
 */
package ru.urbancamper.audiobookmarker.audio;

import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;
import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudiofile;

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizer {

    private ConfigurationManager sphinxConfigurationManager;

    public AudioFileRecognizer(ConfigurationManager sphinxConfigManager){
        this.sphinxConfigurationManager = sphinxConfigManager;
    }

    public RecognizedTextOfSingleAudiofile recognize(URL fileURL){
        

        return null;
    }


    public RecognizedTextOfSingleAudiofile recognize(String filePath){
        URL fileURL = null;
        try {
            fileURL = new URL(filePath);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AudioFileRecognizer.class.getName()).log(Level.SEVERE, null, ex);

        }

        return this.recognize(fileURL);
    }


    private String getFileHash(File file){
        return "";
    }



//     public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
//
//        ConfigurationManager cm = new ConfigurationManager("config/sphinx-custom.xml");
//        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
//
//        TextAlignerGrammar grammar = (TextAlignerGrammar) cm.lookup("textAlignGrammar");
//
//        recognizer.addResultListener(grammar);
//
//        /* allocate the resource necessary for the recognizer */
//        recognizer.allocate();
//
//        // configure the audio input for the recognizer
//        AudioFileDataSource dataSource = (AudioFileDataSource) cm.lookup("audioFileDataSource");
//
//        dataSource.setAudioFile(new URL("file:media/1-test-got.wav"), null);
//
//        Result result;
//        String resultTextAggregated = "";
//        while ((result = recognizer.recognize()) != null) {
//
//            String resultText = result.getTimedBestResult(false, true);
//            System.out.println(resultText);
//            resultTextAggregated += resultText;
//        }
//        System.out.println(resultTextAggregated + "\n");
//    }

}
