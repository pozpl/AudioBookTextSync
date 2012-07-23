/*
 * This class purpose is to recognize audiofile to text
 */
package ru.urbancamper.audiobookmarker.audio;

import edu.cmu.sphinx.frontend.util.AudioFileDataSource;
import edu.cmu.sphinx.linguist.language.grammar.TextAlignerGrammar;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudiofile;

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizerSphinx implements AudioFileRecognizerInterface{

    private ConfigurationManager sphinxConfigurationManager;

    public AudioFileRecognizerSphinx(String sphinxConfigPath) {
        ConfigurationManager cm = new ConfigurationManager(sphinxConfigPath);
        this.sphinxConfigurationManager = cm;
    }

    public AudioFileRecognizerSphinx(ConfigurationManager sphinxConfigManager) {
        this.sphinxConfigurationManager = sphinxConfigManager;

    }

    public RecognizedTextOfSingleAudiofile recognize(URL fileURL, String fileUnicIdentifier) {

        try {
            Recognizer recognizer = (Recognizer) this.sphinxConfigurationManager.lookup("recognizer");
            TextAlignerGrammar grammar = (TextAlignerGrammar) this.sphinxConfigurationManager.lookup("textAlignGrammar");
            recognizer.addResultListener(grammar);

            /*
             * allocate the resource necessary for the recognizer
             */
            recognizer.allocate();

            // configure the audio input for the recognizer
            AudioFileDataSource dataSource = (AudioFileDataSource) sphinxConfigurationManager.lookup("audioFileDataSource");

            dataSource.setAudioFile(fileURL, null);

            Result result;
            String resultTextAggregated = "";

            result = recognizer.recognize();
            while ((result) != null) {
                String resultText = result.getTimedBestResult(false, true);
                resultTextAggregated += resultText;

                result = recognizer.recognize(); //get next chunk of text
            }

            RecognizedTextOfSingleAudiofile recognizedTextObj = new RecognizedTextOfSingleAudiofile(resultTextAggregated, fileUnicIdentifier);



        } catch (PropertyException ex) {
            Logger.getLogger(AudioFileRecognizerSphinx.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public RecognizedTextOfSingleAudiofile recognize(String filePath, String unicFileIdentifier) {
        URL fileURL;
        try {
            fileURL = new URL(filePath);
            return this.recognize(fileURL, unicFileIdentifier);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AudioFileRecognizerSphinx.class.getName()).log(Level.SEVERE, null, ex);

        }

        return null;
    }
}
