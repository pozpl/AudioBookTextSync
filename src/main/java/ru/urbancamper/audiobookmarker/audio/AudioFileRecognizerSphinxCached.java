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

/**
 *
 * @author pozpl
 */
public class AudioFileRecognizerSphinxCached extends AudioFileRecognizerSphinx{
    public AudioFileRecognizerSphinxCached(String sphinxConfigPath) {
        super(sphinxConfigPath);
        ConfigurationManager cm = new ConfigurationManager(sphinxConfigPath);
    }

    public AudioFileRecognizerSphinxCached(ConfigurationManager sphinxConfigManager) {
        super(sphinxConfigManager);
    }
    
}
