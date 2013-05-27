package ru.urbancamper.audiobookmarker.text.markerplacer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.urbancamper.audiobookmarker.text.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: pozpl
 * Date: 24.05.13
 * Time: 8:10
 * To change this template use File | Settings | File Templates.
 */
public class BookTextRecognizedTextAggregationService {
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * the full text of Audiobook in plain text fomat
     */
//    private String fullText = "";
//
//    private String[] tokenizedBookText;
//
//    private Integer[] textInNumericForm;
//
//    private ArrayList<RecognizedTextOfSingleAudioFile> recognizedAudioFiles;

    private LanguageModelBasedTextTokenizer textTokenizer;

    private WordsToNumsMap wordsToNumMapper;

    private LongestSubsequenceFinder longestSubsequenceFinder;

//    private HashMap<String, Integer> registratedFileMapper;

    private BitapSubtextFinding bitapSubtextFinder;

    public BookTextRecognizedTextAggregationService(LanguageModelBasedTextTokenizer textTokenizer,
                                    WordsToNumsMap wordsToNumMapper,
                                    LongestSubsequenceFinder subsequnceFinder,
                                    BitapSubtextFinding bitapSubtextFinder
    ){
//        this.recognizedAudioFiles = new ArrayList<RecognizedTextOfSingleAudioFile>();
        this.textTokenizer = textTokenizer;
        this.wordsToNumMapper = wordsToNumMapper;
        this.longestSubsequenceFinder = subsequnceFinder;
//        this.registratedFileMapper = new HashMap<String, Integer>();
        this.bitapSubtextFinder = bitapSubtextFinder;
    }



//    public void setFullText(String fullText){
//        this.fullText = fullText;
//        this.tokenizedBookText = textTokenizer.tokenize(fullText);
//        this.textInNumericForm = this.wordsToNumMapper.getNumbersFromWords(tokenizedBookText);
//    }
//
//    public void registerRecognizedTextPiece(RecognizedTextOfSingleAudioFile recognizedFileText){
//        this.recognizedAudioFiles.add(recognizedFileText);
//        this.registratedFileMapper.put(recognizedFileText.getAudioFileHash(), this.recognizedAudioFiles.size());
//    }

//    public RecognizedTextOfSingleAudioFile[] getListOfRegistredAudiofiles(){
//        return this.recognizedAudioFiles.toArray(
//                new RecognizedTextOfSingleAudioFile[this.recognizedAudioFiles.size()]);
//    }

    /**
     * Iterate through recognized text peaces and find mapping between words of
     * this recognized peaces to full text words
     * @return ArrayList of mappings of recognized text to full text
     */
    private ArrayList<TreeMap<Integer, Integer>> getLongestSubSequenceMappingFromRecognizedTexts(
                                                                    BookTextAudioAggregation bookTextAudioAggregation){

        ArrayList<TreeMap<Integer, Integer>> recognizedTextLongestSubSequences = new ArrayList<TreeMap<Integer, Integer>>();
        for (Iterator<RecognizedTextOfSingleAudioFile> it = bookTextAudioAggregation.getRecognizedAudioFiles().iterator(); it.hasNext();) {
            RecognizedTextOfSingleAudioFile recognizedText = it.next();
            String[] recognizedTextAsTokens = recognizedText.getTokens();
            Integer[] recognizedTextAsNumbers = this.wordsToNumMapper.getNumbersFromWords(recognizedTextAsTokens);

            Integer[] recognizedTextBeginEndIndexes = this.bitapSubtextFinder.findWithReducedError(
                    bookTextAudioAggregation.getTextInNumericForm(),
                    recognizedTextAsNumbers);
            this.logger.info("Text with length " + recognizedTextAsNumbers.length +
                    "was found in " + recognizedTextBeginEndIndexes[0] + " position");
            Integer[] fullTextSnippetToAlign;

            fullTextSnippetToAlign = Arrays.copyOfRange(bookTextAudioAggregation.getTextInNumericForm(),
                    recognizedTextBeginEndIndexes[0], recognizedTextBeginEndIndexes[1]);
            TreeMap<Integer, Integer> recTextLongestSubSequence =
                    this.longestSubsequenceFinder.getLongestSubsequenceWithMinDistance(fullTextSnippetToAlign, recognizedTextAsNumbers);
            recTextLongestSubSequence = this.shiftMappingOfSubText(recTextLongestSubSequence, recognizedTextBeginEndIndexes[0]);
            recognizedTextLongestSubSequences.add(recTextLongestSubSequence);
        }

        return recognizedTextLongestSubSequences;
    }


    /**
     * Shift mapping of sub text relatively of shift
     * @param fullSubTextMap - tree map of equality between full text and sub text map
     * @param shiftIndex - shift index of sub text in the full text recognized index
     * @return return Tree map with shifted results
     */
    private TreeMap<Integer, Integer> shiftMappingOfSubText(TreeMap<Integer, Integer> fullSubTextMap,
                                                            Integer shiftIndex){
        TreeMap<Integer, Integer> shiftedTreeMap = new TreeMap<Integer, Integer>();
        for(Integer fullTextWordIndex : fullSubTextMap.keySet()){
            Integer shiftedFullTextWordIndex = fullTextWordIndex + shiftIndex;
            shiftedTreeMap.put(shiftedFullTextWordIndex,
                    fullSubTextMap.get(fullTextWordIndex));
        }
        return shiftedTreeMap;
    }


    public FullTextAudioMark buildTokenizedTextAudioMarksMap(BookTextAudioAggregation bookTextAudioAggregation){
        ArrayList<TreeMap<Integer, Integer>> recognizedTextLongestSubSequences =
                this.getLongestSubSequenceMappingFromRecognizedTexts(bookTextAudioAggregation);

        Integer subSequenceCounter = 0;
        FullTextAudioMark fullTextAudioMark = new FullTextAudioMark();
        for(TreeMap<Integer, Integer> longestSubSequence : recognizedTextLongestSubSequences){
            RecognizedTextOfSingleAudioFile recognizedFile = bookTextAudioAggregation.getRecognizedAudioFiles().get(subSequenceCounter);
            Integer fileIndex = bookTextAudioAggregation.getRegistredFileMapper().get(recognizedFile.getAudioFileHash());
            for(Map.Entry<Integer, Integer> fullTextToRecText: longestSubSequence.entrySet()){
                Integer fullTextWordIndex = fullTextToRecText.getKey();
                Integer recognizedTextWordIndex = fullTextToRecText.getValue();

                Double beginTime = recognizedFile.getBeginTimeOfTokenAtPosition(recognizedTextWordIndex);
                Double endTime = recognizedFile.getEndTimeOfTokenAtPosition(recognizedTextWordIndex);

                fullTextAudioMark.putAudioInfo(fullTextWordIndex, fileIndex, beginTime, endTime);
            }
            subSequenceCounter++;
        }

        return fullTextAudioMark;
    }


}
