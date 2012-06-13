/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.context.BeansAnnotations;

/**
 *
 * @author pozpl
 */
public class BookTextTest extends TestCase {

    private ArrayList<RecognizedTextOfSingleAudiofile> recognizedFiles;

    String[] recognizedTextStubs = {
        "word1(0.1, 1.4) word2(1.5, 2.0) word3(2.1, 2.4) word4(2.6, 3.0) ",
        "word4(3.1, 3.4) word3(4.1, 4.4) word2(5.1, 5.4) word1(6.1, 6.4) ",
        "word1(7.1, 7.4) word2(8.1, 8.4) word3(9.1, 9.4) word4(10.1, 10.4) ",
        "word3(11.1, 11.4) word2(12.1, 12.4) word3(13.1, 13.4) word4(14.1, 14.4) ",
        "word1(15.1, 15.4) word2(16.1, 16.4) word3(17.1, 17.4) word4(18.1, 18.4) ",
    };

    String[] audioFilesHashes = {"1", "2", "3", "4", "5"};

    public BookTextTest(String testName) {
        super(testName);
    }



    @Override
    protected void setUp() throws Exception {
        this.recognizedFiles = new ArrayList<RecognizedTextOfSingleAudiofile>();

        Integer audioFileHashCounter = 0;
        for(String recognizedText : this.recognizedTextStubs){
            this.recognizedFiles.add(new RecognizedTextOfSingleAudiofile(recognizedText, this.audioFilesHashes[audioFileHashCounter]));
            audioFileHashCounter++;
        }


        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of registerRecognisedTextPeace method, of class BookText.
     */
    public void testRegisterRecognisedTextPeace() {
        System.out.println("registerRecognisedTextPeace");

        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotations.class);

        BookText instance = ctxt.getBean(BookText.class);
        for(RecognizedTextOfSingleAudiofile recognizedFile: this.recognizedFiles){
            instance.registerRecognisedTextPeace(recognizedFile);
        }

        ArrayList<RecognizedTextOfSingleAudiofile> retunedRecFiles = instance.getListOfRegistredAudiofiles();

        assertEquals(retunedRecFiles.size(), recognizedFiles.size());

        for(Integer recFilesCounter = 0; recFilesCounter < retunedRecFiles.size(); recFilesCounter++){
            assertEquals(retunedRecFiles.get(recFilesCounter),
                    recognizedFiles.get(recFilesCounter));
        }
    }

    /**
     * Test of getListOfRegistredAudiofiles method, of class BookText.
     */
    public void testGetListOfRegistredAudiofiles() {
        System.out.println("getListOfRegistredAudiofiles");
        BookText instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getListOfRegistredAudiofiles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildTextWithAudioMarks method, of class BookText.
     */
    public void testBuildTextWithAudioMarks() {
        System.out.println("buildTextWithAudioMarks");
        BookText instance = null;
        String expResult = "";
        String result = instance.buildTextWithAudioMarks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
