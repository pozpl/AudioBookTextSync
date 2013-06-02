package ru.urbancamper.audiobookmarker.text.markerplacer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.context.BeansAnnotationsForTests;
import ru.urbancamper.audiobookmarker.text.LanguageModelBasedTextTokenizer;
import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudioFile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: pozpl
 * Date: 31.05.13
 * Time: 7:38
 * To change this template use File | Settings | File Templates.
 */
public class BookTextRecognizedTextAggregationServiceTest {

    private ArrayList<RecognizedTextOfSingleAudioFile> recognizedFiles;

    private static String[] recognizedTextStubs = {
            "word1(0.1, 1.4) word2(1.5, 2.0) word3(2.1, 2.4) word4(2.6, 3.0) nm(3.0, 4.0) ssl(4.1, 5.0)",
            "word5(3.1, 3.4) word3(4.1, 4.4) word3(5.1, 5.4) word1(6.1, 6.4) word4(7.1, 7.4)",
//        "word1(7.1, 7.4) word2(8.1, 8.4) word3(9.1, 9.4) word4(10.1, 10.4) ",
//        "word3(11.1, 11.4) word2(12.1, 12.4) word3(13.1, 13.4) word4(14.1, 14.4) ",
//        "word1(15.1, 15.4) word2(16.1, 16.4) word3(17.1, 17.4) word4(18.1, 18.4) ",
    };

    private static String[]  audioFilesHashes = {"1", "2", "3", "4", "5"};

    private static String bookFullText = "word1 word2 word3 noword word4"
            + " word1 word1 word1  word5 word3 word1 word4 word2 word1";

    private static String bookFullTextWithMarks =
            "<1:0.1/>word1 <1:1.5/>word2 <1:2.1/>word3 noword <1:2.6/>word4"
                    + " word1 word1 word1 <2:3.1/>word5 <2:4.1/>word3 <2:6.1/>word1 <2:7.1/>word4 word2 word1";

//    public BookTextRecognizedTextAggregationServiceTest(String testName) {
//        super(testName);
//    }



    @Before
    public void setUp() throws Exception {
        this.recognizedFiles = new ArrayList<RecognizedTextOfSingleAudioFile>();

        Integer audioFileHashCounter = 0;
        for(String recognizedText : BookTextRecognizedTextAggregationServiceTest.recognizedTextStubs){
            RecognizedTextOfSingleAudioFile recognizedFile = new RecognizedTextOfSingleAudioFile(
                    recognizedText,
                    BookTextRecognizedTextAggregationServiceTest.audioFilesHashes[audioFileHashCounter]);
            this.recognizedFiles.add(recognizedFile);
            audioFileHashCounter++;
        }
    }


    @Test
    public void testBuildTokenizedTextAudioMarksMap() throws Exception {
        System.out.println("buildTextWithAudioMarks");
        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotationsForTests.class);

        BookTextRecognizedTextAggregationService instance = ctxt.getBean(BookTextRecognizedTextAggregationService.class);
        BookTextAudioAggregationBuilder bookTextAudioAggregationBuilder = new BookTextAudioAggregationBuilder();

        LanguageModelBasedTextTokenizer textTokenizer = ctxt.getBean(LanguageModelBasedTextTokenizer.class);
        String[] tokenizedFullText = textTokenizer.tokenize(BookTextRecognizedTextAggregationServiceTest.bookFullText);

        BookTextAudioAggregation bookTextAudioAggregation = bookTextAudioAggregationBuilder.clearBuilder()
            .setRecognizedAudioFiles(this.recognizedFiles)
            .setFullTextTokenized(tokenizedFullText)
            .build();

        FullTextAudioMark fullTextAudioMark = instance.buildTokenizedTextAudioMarksMap(bookTextAudioAggregation);

        Integer[] foundWordsIndexes = fullTextAudioMark.getFullTextTokenIndexes();
        Integer[] realWordsIndexes = {0, 1, 2, 4, 8, 9, 10, 11};
        Assert.assertArrayEquals(realWordsIndexes, foundWordsIndexes);

        Double[] realBeginTimes = {0.1, 1.5, 2.1, 2.6, 3.1, 4.1, 6.1, 7.1};
        Double[] realEndTimes =   {1.4, 2.0, 2.4, 3.0, 3.4, 4.4, 6.4, 7.4};
        Integer[] audioFIleIndex =    {0,   0,   0,   0,   1,   1,   1,   1 };
        for(Integer fullTextWordsCounter = 0; fullTextWordsCounter < foundWordsIndexes.length; fullTextWordsCounter++){
            Integer fullTextWordIndex =  foundWordsIndexes[fullTextWordsCounter];
            Assert.assertEquals(realBeginTimes[fullTextWordsCounter], fullTextAudioMark.getBeginTime(fullTextWordIndex));
            Assert.assertEquals(realEndTimes[fullTextWordsCounter], fullTextAudioMark.getEndTime(fullTextWordIndex));
            Assert.assertEquals(audioFIleIndex[fullTextWordsCounter], fullTextAudioMark.getFileIndex(fullTextWordIndex));
        }

    }
}
