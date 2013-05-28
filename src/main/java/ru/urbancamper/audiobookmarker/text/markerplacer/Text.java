package ru.urbancamper.audiobookmarker.text.markerplacer;

import ru.urbancamper.audiobookmarker.text.formats.BareTextToDocumentMap;

import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: pozpl
 * Date: 24.05.13
 * Time: 7:51
 * This class places markers for text file format
 */
public class Text implements IMarkersPlacer{

    private BareTextToDocumentMap bareTextToDocumentMap;

    public Text(BareTextToDocumentMap bareTextToDocumentMap){
        this.bareTextToDocumentMap = bareTextToDocumentMap;
    }

    @Override
    public String placeMarkers(FullTextAudioMark fullTextAudioMark, String[] fullTextTokenized, String bookDocumentFillText) {
        TreeMap<Integer, Integer> wordsMap =
                                this.bareTextToDocumentMap.mapBareTextToDocument(fullTextTokenized, bookDocumentFillText);
        Integer markersOffsetAddition = 0;
        String documentWithAudioMarks = "";
        for(Integer fullTextWordIndex :fullTextAudioMark.getFullTextTokenIndexes()){
            Double beginTime = fullTextAudioMark.getBeginTime(fullTextWordIndex);
            Double endTime = fullTextAudioMark.getEndTime(fullTextWordIndex);
            Integer audioFileIndex = fullTextAudioMark.getFileIndex(fullTextWordIndex);
            String marker = this.constructMarker(beginTime, endTime, audioFileIndex);
        }

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private String insert

    private String constructMarker(Double beginTime, Double endTime, Integer fileIndex){
        String marker = "<" + fileIndex + ":" + beginTime + ">";
        return marker;
    }
}
