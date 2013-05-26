package ru.urbancamper.audiobookmarker.text.markerplacer;

import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudioFile;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: pozpl
 * Date: 24.05.13
 * Time: 8:15
 * Builder for BookTextAudioAggregation Entitiy
 */
public class BookTextAudioAggregationBuilder {

    /**
     * the full text of Audiobook in plain text fomat
     */
    private String fullText = "";

    private String[] tokenizedBookText;

    private Integer[] textInNumericForm;

    private ArrayList<RecognizedTextOfSingleAudioFile> recognizedAudioFiles;

    private HashMap<String, Integer> registredFileMapper;

    public BookTextAudioAggregationBuilder clearBuilder(){
        this.fullText = null;
        this.tokenizedBookText = null;
        this.textInNumericForm = null;
        this.recognizedAudioFiles = null;
        this.registredFileMapper = null;
        return this;
    }

    /**
     *
     * @param aggregationObj
     * @return this instance
     */
    public BookTextAudioAggregationBuilder fillFromBookAudioAggregation(BookTextAudioAggregation aggregationObj){
        this.fullText = aggregationObj.getFullText();
        this.tokenizedBookText = aggregationObj.getTokenizedBookText();
        this.textInNumericForm = aggregationObj.getTextInNumericForm();
        this.recognizedAudioFiles = aggregationObj.getRecognizedAudioFiles();
        this.registredFileMapper = aggregationObj.getRegistredFileMapper();
        return this;
    }

    public BookTextAudioAggregation build(){
        BookTextAudioAggregation aggregation = new BookTextAudioAggregation(
                this.fullText,
                this.tokenizedBookText,
                this.textInNumericForm,
                this.recognizedAudioFiles,
                this.registredFileMapper
        );

        return aggregation;
    }

    public BookTextAudioAggregationBuilder setFullText(String fullText) {
        this.fullText = fullText;
        return this;
    }

    public BookTextAudioAggregationBuilder setTokenizedBookText(String[] tokenizedBookText) {
        this.tokenizedBookText = tokenizedBookText;
        return this;
    }

    public BookTextAudioAggregationBuilder setTextInNumericForm(Integer[] textInNumericForm) {
        this.textInNumericForm = textInNumericForm;
        return this;
    }

    public BookTextAudioAggregationBuilder setRecognizedAudioFiles(ArrayList<RecognizedTextOfSingleAudioFile> recognizedAudioFiles) {
        this.recognizedAudioFiles = recognizedAudioFiles;
        return this;
    }

    public BookTextAudioAggregationBuilder setRegistredFileMapper(HashMap<String, Integer> registredFileMapper) {
        this.registredFileMapper = registredFileMapper;
        return this;
    }
}
