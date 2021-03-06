/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.audio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.context.BeansAnnotationsForTests;
import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudioFile;

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizerSphinxTest {

    /**
     *
     * @param arg
     */
    public static void main(String arg[]) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);
        AudioFileRecognizerSphinx recognizer = ctxt.getBean(AudioFileRecognizerSphinx.class);

        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/test.properties"));
            RecognizedTextOfSingleAudioFile numbersText = recognizer.recognize(prop.getProperty("TEST_SPHINX_AUDIO_NUMBERS"), "numbers");
            String[] tokens = numbersText.getTokens();
            for(String token : tokens){
                System.out.println(token);
            }
        } catch (IOException ex) {
            Logger.getLogger(AudioFileRecognizerSphinx.class.getName()).log(Level.SEVERE, null, ex);
        }
//        recognizer.recognize(null, null)
    }
}
