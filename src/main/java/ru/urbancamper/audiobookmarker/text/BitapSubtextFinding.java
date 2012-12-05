/*
 * This class is supposed to find snippet of text in full text pattern
 * by means of Bitap algorithm
 */
package ru.urbancamper.audiobookmarker.text;

/**
 *
 * @author pozpl
 */
public class BitapSubtextFinding {


    private Byte[] fillByteArrayFromWordsNumbersArray(Integer[] text, Integer wordTomark){
        Integer bytesArrayLength = text.length / 8;
        return null;
    }


    /**
     * Shift bytes array on n positions to the right
     * @param bytes
     * @param rightShifts
     */
    private static void shiftBitsRight(byte[] bytes, final int rightShifts) {
        assert rightShifts >= 1 && rightShifts <= 7;

        final int leftShifts = 8 - rightShifts;

        byte previousByte = bytes[0]; // keep the byte before modification
        bytes[0] = (byte) (((bytes[0] & 0xff) >> rightShifts) | ((bytes[bytes.length - 1] & 0xff) << leftShifts));
        for (int i = 1; i < bytes.length; i++) {
            byte tmp = bytes[i];
            bytes[i] = (byte) (((bytes[i] & 0xff) >> rightShifts) | ((previousByte & 0xff) << leftShifts));
            previousByte = tmp;
        }
    }

}
