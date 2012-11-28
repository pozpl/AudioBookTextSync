/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.TreeMap;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.context.BeansAnnotationsForTests;

/**
 *
 * @author pozpl
 */
public class BookTextTest extends TestCase {

    private ArrayList<RecognizedTextOfSingleAudiofile> recognizedFiles;

    private static String[] recognizedTextStubs = {
        "word1(0.1, 1.4) word2(1.5, 2.0) word3(2.1, 2.4) word4(2.6, 3.0) ",
        "word4(3.1, 3.4) word3(4.1, 4.4) word2(5.1, 5.4) word1(6.1, 6.4) ",
//        "word1(7.1, 7.4) word2(8.1, 8.4) word3(9.1, 9.4) word4(10.1, 10.4) ",
//        "word3(11.1, 11.4) word2(12.1, 12.4) word3(13.1, 13.4) word4(14.1, 14.4) ",
//        "word1(15.1, 15.4) word2(16.1, 16.4) word3(17.1, 17.4) word4(18.1, 18.4) ",
    };

    private static String[]  audioFilesHashes = {"1", "2", "3", "4", "5"};

    private static String bookFullText = "word1 word2 word3 noword word4"
            + " word1 word1 word1  word4 word3 noword noword nword word2 word1";
    private static String bookFullTextWithMarks =
            "<1:0.1/>word1 <1:1.5/>word2 <1:2.1/>word3 noword <1:2.6/>word4"
            + " word1 word1 word1 <2:3.1/>word4 <2:4.1/>word3 noword noword nword <2:5.1/>word2 <2:6.1/>word1";

    public BookTextTest(String testName) {
        super(testName);
    }



    @Override
    protected void setUp() throws Exception {
        this.recognizedFiles = new ArrayList<RecognizedTextOfSingleAudiofile>();

        Integer audioFileHashCounter = 0;
        for(String recognizedText : BookTextTest.recognizedTextStubs){
            RecognizedTextOfSingleAudiofile recognizedFile = new RecognizedTextOfSingleAudiofile(
                    recognizedText,
                    BookTextTest.audioFilesHashes[audioFileHashCounter]);
            this.recognizedFiles.add(recognizedFile);
            audioFileHashCounter++;
        }


        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of registerRecognizedTextPiece method, of class BookText.
     */
    public void testRegisterRecognisedTextPeace() {
        System.out.println("registerRecognisedTextPeace");

        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);

        BookText instance = ctxt.getBean(BookText.class);
        for(RecognizedTextOfSingleAudiofile recognizedFile: this.recognizedFiles){
            instance.registerRecognizedTextPiece(recognizedFile);
        }

        RecognizedTextOfSingleAudiofile[] retunedRecFiles = instance.getListOfRegistredAudiofiles();

        assertEquals(retunedRecFiles.length, recognizedFiles.size());

        for(Integer recFilesCounter = 0; recFilesCounter < retunedRecFiles.length; recFilesCounter++){
            assertEquals(retunedRecFiles[recFilesCounter],
                    recognizedFiles.get(recFilesCounter));
        }
    }

    /**
     * Test of getListOfRegistredAudiofiles method, of class BookText.
     */
    public void testGetListOfRegistredAudiofiles() {
        System.out.println("getListOfRegistredAudiofiles");
        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);

        BookText instance = ctxt.getBean(BookText.class);
        for(RecognizedTextOfSingleAudiofile recognizedFile: this.recognizedFiles){
            instance.registerRecognizedTextPiece(recognizedFile);
        }

        RecognizedTextOfSingleAudiofile[] retunedRecFiles = instance.getListOfRegistredAudiofiles();

        assertEquals(retunedRecFiles.length, recognizedFiles.size());

        for(Integer recFilesCounter = 0; recFilesCounter < retunedRecFiles.length; recFilesCounter++){
            assertEquals(retunedRecFiles[recFilesCounter],
                    recognizedFiles.get(recFilesCounter));
        }
    }

    /**
     * Test of buildTextWithAudioMarks method, of class BookText.
     */
    public void testBuildTextWithAudioMarks() {
        System.out.println("buildTextWithAudioMarks");
        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);

        BookText instance = ctxt.getBean(BookText.class);
        instance.setFullText( bookFullText);
        for(RecognizedTextOfSingleAudiofile recognizedFile: this.recognizedFiles){
            instance.registerRecognizedTextPiece(recognizedFile);
        }


        String markedText = instance.buildTextWithAudioMarks();
        System.out.println("|" + markedText + "|");
        System.out.println("|" + bookFullTextWithMarks + "|");
        BookTextTest.assertEquals(bookFullTextWithMarks, markedText);
    }

    public void testGetLongestSubsequenceMappingFromRecognizedTexts()  throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);
        BookText instance = ctxt.getBean(BookText.class);
        instance.setFullText( bookFullText);
        for(RecognizedTextOfSingleAudiofile recognizedFile: this.recognizedFiles){
            instance.registerRecognizedTextPiece(recognizedFile);
        }

        Method method =
          RecognizedTextSnippetInterval.class.getDeclaredMethod("getLongestSubsequenceMappingFromRecognizedTexts",
                new Class[]{});
        method.setAccessible(true);
        TreeMap<Integer, Integer> output;
        output = (TreeMap<Integer, Integer>) method.invoke(instance, new Object[]{});

    }
}
