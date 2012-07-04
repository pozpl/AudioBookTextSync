/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import opennlp.tools.tokenize.TokenizerModel;


/**
 *
 * @author pozpl
 */
public class LanguageModelBasedTextTokenizerTest extends TestCase {

    private TokenizerModel tokenizerModel;
    private String DETOKENIZER_DICTONARY_PATH = "resources/tokenizer_models/en-detokenizer.xml";

    public LanguageModelBasedTextTokenizerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(LanguageModelBasedTextTokenizerTest.class);
        return suite;
    }

    @Override
    protected void setUp() throws Exception {
        String modelPath = "resources/tokenizer_models/en-token.bin";

        InputStream modelPathInputStream = null;
        try {
            File modelFile = new File(modelPath);
            modelPathInputStream = new FileInputStream(modelFile.getAbsolutePath());

            this.tokenizerModel = new TokenizerModel(modelPathInputStream);
        } catch (IOException ex) {
            Logger.getLogger(LanguageModelBasedTextTokenizer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                modelPathInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(LanguageModelBasedTextTokenizer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of tokenize method, of class LanguageModelBasedTextTokenizer.
     */
    public void testTokenize() {
        System.out.println("tokenize");

        String text = "test, token model.";
        LanguageModelBasedTextTokenizer instance = new LanguageModelBasedTextTokenizer(this.tokenizerModel,
                this.DETOKENIZER_DICTONARY_PATH);
        String[] expResult = {"test", ",", "token", "model", "."};
        String[] result = instance.tokenize(text);
        assertEquals(expResult.length, result.length);
        for(int tokenIndex = 0; tokenIndex < expResult.length; tokenIndex++){
            assertEquals(expResult[tokenIndex], result[tokenIndex]);
        }
    }

    /**
     * Test of deTokenize method, of class LanguageModelBasedTextTokenizer.
     */
    public void testDeTokenize() {
        System.out.println("deTokenize");
        String[] tokens = {"test", ",", "token", "model", "."};
        LanguageModelBasedTextTokenizer instance = new LanguageModelBasedTextTokenizer(this.tokenizerModel,
                this.DETOKENIZER_DICTONARY_PATH);
        String expResult = "test, token model.";
        String result = instance.deTokenize(tokens);
        assertEquals(expResult, result);
    }


}
