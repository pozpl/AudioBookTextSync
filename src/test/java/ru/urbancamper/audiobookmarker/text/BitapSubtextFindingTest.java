/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.urbancamper.audiobookmarker.text;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import junit.framework.TestCase;

/**
 *
 * @author pozpl
 */
public class BitapSubtextFindingTest extends TestCase {

    private BitapSubtextFinding bitapSubtextFinding;

    public BitapSubtextFindingTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.bitapSubtextFinding = new BitapSubtextFinding();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testFillByteArrayFromWordsNumbersArray() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Integer[] textWords = {1, 2, 3, 4, 5, 1, 2, 5, 1, 1, 1, 1,1, 1, 1,1,
        1,5,5, 6, 7};
        Integer wordToFind = 5;
        Method method = BitapSubtextFinding.class.getDeclaredMethod("fillByteArrayFromWordsNumbersArray",
                Integer[].class, Integer.class);
        method.setAccessible(true);
        Byte[] output;
        output = (Byte[]) method.invoke(bitapSubtextFinding,
                 textWords, wordToFind);

        assertEquals(3, output.length);

        Byte idealValue = -112;
        assertEquals(idealValue, output[0]);
        idealValue = 0;
        assertEquals(idealValue, output[1]);
        idealValue = 6;
        assertEquals(idealValue, output[2]);
    }

//    public void testShiftBitsRight(){
//        Byte[] bytesArray = new Byte[]{1, 4, 8, 16};
//
//        this.bitapSubtextFinding.shiftBitsRight(bytesArray, 1);
//        Byte[] idealResult = new Byte[]{0, 2, 8, 16};
//        for(Integer elemCounter = 0; elemCounter < idealResult.length; elemCounter++){
//            assertEquals(idealResult[elemCounter], bytesArray[elemCounter]);
//        }
//    }
    public void testShiftBitsLeft(){
        Byte[] bytesArray = new Byte[]{1, 4, 8, 16};

        this.bitapSubtextFinding.shiftBitsLeft(bytesArray);
        Byte[] idealResult = new Byte[]{2, 8, 16, 32};
        for(Integer elemCounter = 0; elemCounter < idealResult.length; elemCounter++){
            assertEquals(idealResult[elemCounter], bytesArray[elemCounter]);
        }

        bytesArray = new Byte[]{-128, 4, 8, 16};
        idealResult = new Byte[]{0, 9, 16, 32};
        this.bitapSubtextFinding.shiftBitsLeft(bytesArray);
        for(Integer elemCounter = 0; elemCounter < idealResult.length; elemCounter++){
            assertEquals(idealResult[elemCounter], bytesArray[elemCounter]);
        }
    }
}
