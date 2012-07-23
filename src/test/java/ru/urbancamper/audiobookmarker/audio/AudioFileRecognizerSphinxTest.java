/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.audio;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.context.BeansAnnotationsForTests;

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizerSphinxTest {

    public void main() {
        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);
        AudioFileRecognizerSphinx ercognizer = ctxt.getBean(AudioFileRecognizerSphinx.class);
    }
}
