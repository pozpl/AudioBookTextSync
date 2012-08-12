/*
 * This class will provide recognition of audio files with a caching ability.
 * A structure of the cache is folowing:
 * Before a file recognition a programm will check if file with a
 * fileToRecognizePath + .recognized.txt path is exists.
 * If so, read text from this file and move on, else recognize file.
 * Text of recognized file should be stored under the path mentioned before.
 * It's assumed that program has rights to write into this path.
 */
package ru.urbancamper.audiobookmarker.audio;

import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudiofile;

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizerSphinxCached extends AudioFileRecognizerSphinx{
    /**
     *Extension for cached files. This files resides in the same directory with
     * original audio files and contained aligned recognized text.
     */
    public static final  String CACHE_FILR_EXTENSION = ".cached";

    public AudioFileRecognizerSphinxCached(String sphinxConfigPath) {
        super(sphinxConfigPath);
        ConfigurationManager cm = new ConfigurationManager(sphinxConfigPath);
    }

    public AudioFileRecognizerSphinxCached(ConfigurationManager sphinxConfigManager) {
        super(sphinxConfigManager);
    }

    private Boolean isCacheExists(String filePath){
        String cacheFilePath = filePath + AudioFileRecognizerSphinxCached.CACHE_FILR_EXTENSION;
        File cachedFile = new File(cacheFilePath);
        if(cachedFile.exists()){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    /**Convert file to string
     * @param filePath
     * @return
     */
    public String fileToString(String filePath){
        StringBuilder strBuffer = new StringBuilder();
        int BLOC_SIZE = 512;
        char[] b = new char[BLOC_SIZE];
        Reader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
            int n;
            while((n = fileReader.read(b))>0){
                strBuffer.append(b, 0, n);
            }
        } catch (IOException ex) {
            this.logger.error("Excsption during file read " + filePath + ": " + ex);
        }
        String retStr = strBuffer.toString();
        return retStr;
    }

    /**
     * Get a aligned text from cache file
     * @param filePath
     * @return
     */
    private String readRecognizedTextFromCache(String filePath){
        String cacheFilePath = filePath + AudioFileRecognizerSphinxCached.CACHE_FILR_EXTENSION;
        String textFromCachedFile = this.fileToString(cacheFilePath);
        return textFromCachedFile;
    }

    @Override
    public RecognizedTextOfSingleAudiofile recognize(String filePath, String fileUnicIdentifier) {
        String resultTextAggregated = getTextFromAudioFile(filePath, fileUnicIdentifier);

        RecognizedTextOfSingleAudiofile recognizedTextObj = new RecognizedTextOfSingleAudiofile(resultTextAggregated, fileUnicIdentifier);

        return recognizedTextObj;
    }

}
