/*
 * Use openNLP or whatever to tokenize input text
 */
package ru.urbancamper.audiobookmarker.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import opennlp.tools.cmdline.tokenizer.DictionaryDetokenizerTool;
import opennlp.tools.tokenize.Detokenizer.DetokenizationOperation;
import opennlp.tools.tokenize.*;


/**
 *
 * @author pozpl
 */
public class LanguageModelBasedTextTokenizer {

    private TokenizerModel tokenizerModel;
    private String detokenizeDictionaryPath;

    public LanguageModelBasedTextTokenizer(TokenizerModel tokenizrModel, String detokenizeDictionaryPath){
        this.tokenizerModel = tokenizrModel;
        this.detokenizeDictionaryPath = detokenizeDictionaryPath;
    }


    public String[] tokenize(String text){

        Tokenizer tokenizer = new TokenizerME(this.tokenizerModel);
        String[] tokens = tokenizer.tokenize(text);


        return tokens;
    }

    public String deTokenize(String[] tokens) {
        InputStream dictIn;
        String sentence = "";
        try {
            dictIn = new FileInputStream(detokenizeDictionaryPath);
//        InputStream dictIn = LanguageModelBasedTextTokenizer.class.getResourceAsStream(
//                this.detokenizeDictionaryPath);


            DetokenizationDictionary dict = new DetokenizationDictionary(dictIn);
            dictIn.close();
            DictionaryDetokenizer detokenizer = new DictionaryDetokenizer(dict);

            DetokenizationOperation operations[] = detokenizer.detokenize(tokens);
            sentence = DictionaryDetokenizerTool.detokenize(tokens, operations);
        } catch (IOException ex) {
            Logger.getLogger(LanguageModelBasedTextTokenizer.class.getName()).log(Level.SEVERE, null, ex);
        }


        return sentence;

    }
}
