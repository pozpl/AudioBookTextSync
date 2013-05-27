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

    private String fullText;

    private ArrayList<RecognizedTextOfSingleAudioFile> recognizedAudioFiles;

    private HashMap<String, Integer> registredFileMapper;

    public BookTextAudioAggregationBuilder clearBuilder(){
        this.fullText = null;
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
        this.recognizedAudioFiles = aggregationObj.getRecognizedAudioFiles();
        this.registredFileMapper = aggregationObj.getRegistredFileMapper();
        return this;
    }

    public BookTextAudioAggregation build(){
        BookTextAudioAggregation aggregation = new BookTextAudioAggregation(
                this.fullText,
                this.recognizedAudioFiles,
                this.registredFileMapper
        );

        return aggregation;
    }


    public BookTextAudioAggregationBuilder setFullText(String fullText) {
        this.fullText = fullText;
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
