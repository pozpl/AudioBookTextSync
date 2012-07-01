/*
 * Use openNLP or whatever to tokenize input text
 */
package ru.urbancamper.audiobookmarker.text;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


/**
 *
 * @author pozpl
 */
public class LanguageModelBasedTextTokenizez {

    private TokenizerModel tokenizerModel;

    public LanguageModelBasedTextTokenizez(TokenizerModel tokenizrModel){
        this.tokenizerModel = tokenizrModel;
    }


    public String[] tokenize(String text){

        Tokenizer tokenizer = new TokenizerME(this.tokenizerModel);
        String[] tokens = tokenizer.tokenize(text);


        return tokens;
    }
}
