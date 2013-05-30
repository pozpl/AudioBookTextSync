package ru.urbancamper.audiobookmarker.text.markerplacer;

import ru.urbancamper.audiobookmarker.text.formats.BareTextToDocumentMap;

/**
 * Created with IntelliJ IDEA.
 * User: pozpl
 * Date: 24.05.13
 * Time: 7:51
 * This class places markers for text file format
 */
public class Text implements IMarkersPlacer{

    /**
     *
     * @param fullTextAudioMark
     * @param bareText
     * @param document
     * @return
     */
    @Override
    public String produceDocumentWithMarkers(FullTextAudioMark fullTextAudioMark, String[] bareText, String document){
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
            previousMarkerIndex = bareWordIndexInDoc;
            documentOffset = bareWordIndexInDoc + bareTextWord.length() - 1;

            if(documentOffset >= docLastCharacterIndex){
                String lastTextBit = document.substring(previousMarkerIndex, documentOffset);
                markedDocument += lastTextBit;
                break;
            }
        }
        return markedDocument;
    }

    private String constructMarker(Double beginTime, Double endTime, Integer fileIndex){
        String marker = "<" + fileIndex + ":" + beginTime + ">";
        return marker;
    }
}
