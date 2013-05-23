package ru.urbancamper.audiobookmarker.text.formats;

import junit.framework.Assert;
import org.junit.Test;

import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: pozpl
 * Date: 23.05.13
 * Time: 8:05
 * To change this template use File | Settings | File Templates.
 */
public class BareTextToDocumentMapTest {

    private String documentText = "<b> this? is <t>test</t> text. </b>";

    @Test
    public void testMapBareTextToDocument() throws Exception {
        String[] bareText = {"this", "is", "test", "text"};
        TreeMap<Integer, Integer> bareWordsToDocumentMapControl =  new TreeMap<Integer, Integer>();
        bareWordsToDocumentMapControl.put(0, 4);
        bareWordsToDocumentMapControl.put(1, 10);
        bareWordsToDocumentMapControl.put(2, 16);
        bareWordsToDocumentMapControl.put(3, 25);

        BareTextToDocumentMap textToDocMapper = new BareTextToDocumentMap();
        TreeMap<Integer, Integer> bareTextToDocObtained = textToDocMapper.mapBareTextToDocument(bareText, this.documentText);
        for(Integer wordKey: bareWordsToDocumentMapControl.keySet()){
            Assert.assertTrue(bareTextToDocObtained.containsKey(wordKey));
            Assert.assertEquals(bareWordsToDocumentMapControl.get(wordKey), bareTextToDocObtained.get(wordKey));
        }

    }
}
