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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ru.urbancamper.audiobookmarker.text.LanguageModelBasedTextTokenizer;
/**
 *
 * @author pozpl
 */
@Configuration
@PropertySource("classpath:production.properties")
public class BeansAnnotations {
    @Autowired
    private Environment env;
    
    @Bean
    public TokenizerModel tokenizerModel(){
        InputStream modelPathInputStream = null;
        TokenizerModel tokenizerModel = null;
        try {
            File modelFile = new File(env.getProperty("TOKENIZER_MODEL_PATH"));
            modelPathInputStream = new FileInputStream(modelFile.getAbsolutePath());
            tokenizerModel = new TokenizerModel(modelPathInputStream);
        } catch (IOException ex) {
            Logger.getLogger(LanguageModelBasedTextTokenizer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                modelPathInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(LanguageModelBasedTextTokenizer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tokenizerModel;
    }

    @Bean
    public LanguageModelBasedTextTokenizer textTokenizer(){
        return new LanguageModelBasedTextTokenizer(tokenizerModel(), env.getProperty("DETOKENIZER_DICTONARY_PATH"));
    }
}
