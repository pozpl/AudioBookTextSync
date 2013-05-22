package ru.urbancamper.audiobookmarker.text.formats;

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
        TreeMap<Integer, Integer> bareWordsToDocumentMap =
    }
}
