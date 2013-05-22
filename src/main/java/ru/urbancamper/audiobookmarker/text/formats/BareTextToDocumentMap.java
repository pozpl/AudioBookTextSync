package ru.urbancamper.audiobookmarker.text.formats;

import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: pozpl
 * Date: 23.05.13
 * Time: 7:34
 * Class to map bare text to it origin in diffrent formats. Here I will
 * implement naive approach wich will find words from bare text in original text
 * and if found will add words to map.
 */
public class BareTextToDocumentMap {

    /**
     * Function that maps bare text that given as array of it strings to document
     * so for each bara word we can say exact position in document
     * @param bareText - array of bare text words
     * @param document - string that contains representation of text
     * @return  Tree map of bareText word index to first character of this word in document
     */
    public TreeMap<Integer, Integer> mapBareTextToDocument(String[] bareText, String document){
        Integer documentOffset = 0;
        Integer doclastCharacterIndex = document.length() - 1;
        TreeMap<Integer, Integer> bareTextToDocumentMap = new TreeMap<Integer, Integer>();
        for(Integer bareTextWordIndex = 0; bareTextWordIndex < bareText.length; bareTextWordIndex++){
            String bareTextWord = bareText[bareTextWordIndex];
            Integer bareWordIndexInDoc = document.indexOf(bareTextWord, documentOffset);
            documentOffset = bareWordIndexInDoc + bareTextWord.length() - 1;
            bareTextToDocumentMap.put(bareTextWordIndex, bareWordIndexInDoc);
            if(documentOffset >= doclastCharacterIndex){
                break;
            }
        }
        return bareTextToDocumentMap;
    }
}
