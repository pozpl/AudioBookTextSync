/*
 * Use openNLP or whatever to tokenize input text
 */
package ru.urbancamper.audiobookmarker.text;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


/**
 *
 * @author pozpl
 */
public class TextTokenizer {

    private TokenizerModel tokenizerModel;

    public TextTokenizer(TokenizerModel tokenizrModel){
        this.tokenizerModel = tokenizrModel;
    }


    public String[] tokenize(String text){

        Tokenizer tokenizer = new TokenizerME(this.tokenizerModel);
        String[] tokens = tokenizer.tokenize(text);


        return tokens;
    }
}
