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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.urbancamper.audiobookmarker.text.BookText;
import ru.urbancamper.audiobookmarker.text.LanguageModelBasedTextTokenizer;
import ru.urbancamper.audiobookmarker.text.LongestSubsequenceFinder;
import ru.urbancamper.audiobookmarker.text.WordsToNumsMap;

/**
 *
 * @author pozpl
 */

@Configuration
@PropertySource("classpath:/ru/urbancamper/audiobookmarker/context/test.properties")
public class BeansAnnotationsForTests {
    @Value("${TOKENIZER_MODEL_PATH}")
    private String TOKENIZER_MODEL_PATH;// = "resources/tokenizer_models/en-token.bin";
    private String DETOKENIZER_DICTONARY_PATH = "resources/tokenizer_models/en-detokenizer.xml";




    @Bean
    public TokenizerModel tokenizerModel(){
        InputStream modelPathInputStream = null;
        TokenizerModel tokenizerModel = null;
        try {
            File modelFile = new File(TOKENIZER_MODEL_PATH);
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
        return new LanguageModelBasedTextTokenizer(tokenizerModel(), this.DETOKENIZER_DICTONARY_PATH);
    }

    @Bean
    public WordsToNumsMap wordsToNumMap(){
        return new WordsToNumsMap();
    }

    @Bean
    public LongestSubsequenceFinder longestSubsequenceFinder(){
        return new LongestSubsequenceFinder();
    }

    @Bean
    public BookText bookText(){
        return new BookText(this.textTokenizer(), this.wordsToNumMap(), this.longestSubsequenceFinder());
    }

//    @Bean
//    public RecognizedTextOfSingleAudiofile recognizedTextOfSingleAudiofile(){
//        modelPath
//        RecognizedTextOfSingleAudiofile recText = new RecognizedTextOfSingleAudiofile(modelPath, modelPath)
//        return null;
//    }
}
