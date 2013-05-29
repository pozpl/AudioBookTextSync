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
        Integer previousMarkerIndex = 0;
        String documentWithAudioMarks = "";
        for(Integer fullTextWordIndex :fullTextAudioMark.getFullTextTokenIndexes()){
            Double beginTime = fullTextAudioMark.getBeginTime(fullTextWordIndex);
            Double endTime = fullTextAudioMark.getEndTime(fullTextWordIndex);
            Integer audioFileIndex = fullTextAudioMark.getFileIndex(fullTextWordIndex);
            String marker = this.constructMarker(beginTime, endTime, audioFileIndex);
            Integer wordOffset = wordsMap.get(fullTextWordIndex);
            String markedTextBit = bookDocumentFillText.substring(previousMarkerIndex, wordOffset);
            documentWithAudioMarks += markedTextBit + marker
        }

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String mapBareTextToDocument(FullTextAudioMark fullTextAudioMark, String[] bareText, String document){
        Integer documentOffset = 0;
        Integer docLastCharacterIndex = document.length() - 1;


        String markedDocument = "";
        Integer previousMarkerIndex = 0;
        for(Integer bareTextWordIndex = 0; bareTextWordIndex < bareText.length; bareTextWordIndex++){
            String bareTextWord = bareText[bareTextWordIndex];
            Integer bareWordIndexInDoc = document.indexOf(bareTextWord, documentOffset);

            Double beginTime = fullTextAudioMark.getBeginTime(bareTextWordIndex);
            Double endTime = fullTextAudioMark.getEndTime(bareTextWordIndex);
            Integer audioFileIndex = fullTextAudioMark.getFileIndex(bareTextWordIndex);
            String marker = this.constructMarker(beginTime, endTime, audioFileIndex);

            String markedTextBit = document.substring(previousMarkerIndex, bareWordIndexInDoc);
            markedDocument += markedTextBit + marker;

            documentOffset = bareWordIndexInDoc + bareTextWord.length() - 1;

            if(documentOffset >= docLastCharacterIndex){
                break;
            }
        }
        return null;
    }

    private String constructMarker(Double beginTime, Double endTime, Integer fileIndex){
        String marker = "<" + fileIndex + ":" + beginTime + ">";
        return marker;
    }
}
