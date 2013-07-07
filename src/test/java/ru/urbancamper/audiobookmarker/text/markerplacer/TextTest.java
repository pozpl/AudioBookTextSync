package ru.urbancamper.audiobookmarker.text.markerplacer;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: pozpl
 * Date: 08.07.13
 * Time: 7:56
 * To change this template use File | Settings | File Templates.
 */
public class TextTest {

    private String documentText = "this is the story of count Dracula, second part of it anyway";
    private String[] bareText = {"this", "is", "the", "story", "of", "count", "Dracula", "second", "part", "of" };

    @Test
    public void testProduceDocumentWithMarkers() throws Exception {
        FullTextAudioMark fullTextAudioMark = new FullTextAudioMark();
        fullTextAudioMark.putAudioInfo(2, 0, 2.44, 3.3);
        fullTextAudioMark.putAudioInfo(4, 0, 3.0, 3.3);
        fullTextAudioMark.putAudioInfo(6, 1, 1.0, 2.3);

        Text textMarkerPlacer = new Text();
        String markedDocument = textMarkerPlacer.produceDocumentWithMarkers(fullTextAudioMark, this.bareText, this.documentText);

        Assert.assertEquals("this is <0:2.44>the story <0:3.0>of count <1:1.0>Dracula, second part of it anyway", markedDocument);
    }
}
