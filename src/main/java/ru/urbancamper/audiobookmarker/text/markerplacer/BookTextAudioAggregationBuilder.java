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

    private String[] fullTextTokenized;

    private ArrayList<RecognizedTextOfSingleAudioFile> recognizedAudioFiles;

    private HashMap<String, Integer> registredFileMapper;

    public BookTextAudioAggregationBuilder clearBuilder(){
        this.fullTextTokenized = null;
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
        this.fullTextTokenized = aggregationObj.getFullTextTokenized();
        this.recognizedAudioFiles = aggregationObj.getRecognizedAudioFiles();
        this.registredFileMapper = aggregationObj.getRegistredFileMapper();
        return this;
    }

    public BookTextAudioAggregation build(){
        BookTextAudioAggregation aggregation = new BookTextAudioAggregation(
                this.fullTextTokenized,
                this.recognizedAudioFiles,
                this.registredFileMapper
        );

        return aggregation;
    }


    public BookTextAudioAggregationBuilder setFullTextTokenized(String[] fullTextTokenized) {
        this.fullTextTokenized = fullTextTokenized;
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
