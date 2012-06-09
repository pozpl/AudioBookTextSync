/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import opennlp.tools.tokenize.TokenizerModel;
import org.springframework.context.annotation.Bean;
import ru.urbancamper.audiobookmarker.text.TextTokenizer;

/**
 *
 * @author pozpl
 */
public class BeansAnnotationsForTests {

    private String modelPath = "resources/tokenizer_models/en-token.bin";

    @Bean
    public TokenizerModel tokenizerModel(){
        InputStream modelPathInputStream = null;
        TokenizerModel tokenizerModel = null;
        try {
            File modelFile = new File(modelPath);
            modelPathInputStream = new FileInputStream(modelFile.getAbsolutePath());
            tokenizerModel = new TokenizerModel(modelPathInputStream);
        } catch (IOException ex) {
            Logger.getLogger(TextTokenizer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                modelPathInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(TextTokenizer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tokenizerModel;
    }

    @Bean
    public TextTokenizer textTokenizer(){
        return new TextTokenizer(tokenizerModel());
    }

    
}
