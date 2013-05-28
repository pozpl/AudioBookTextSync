package ru.urbancamper.audiobookmarker.text.markerplacer;

import ru.urbancamper.audiobookmarker.text.RecognizedTextOfSingleAudioFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: pozpl
 * Date: 24.05.13
 * Time: 7:59
 * Interface to marker placers those will place audio markers in text.
 */
public interface IMarkersPlacer {
    public String placeMarkers(FullTextAudioMark fullTextAudioMark );
}
