/*
 * This class is supposed to find snippet of text in full text pattern
 * by means of Bitap algorithm
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.BitSet;

/**
 *
 * @author pozpl
 */
public class BitapSubtextFinding {

    public BitSet fillBitSetFromWordsNumberArray(Integer[] text, Integer wordToMark){
        BitSet textVectorBitSet = new BitSet(text.length);
        for(Integer wordsCounter = 0; wordsCounter < text.length; wordsCounter++){
            if(text[wordsCounter] == wordToMark){
                textVectorBitSet.set(wordsCounter);
            }else{
                textVectorBitSet.set(wordsCounter, false);
            }
        }
        return textVectorBitSet;
    }


    @Deprecated
    public Byte[] fillByteArrayFromWordsNumbersArray(Integer[] text, Integer wordTomark){
        Integer bytesArrayLength = text.length / 8;
        bytesArrayLength = text.length % 8 == 0 ? bytesArrayLength : bytesArrayLength + 1;
        Byte[] byteArray = new Byte[bytesArrayLength];
        Byte byteOne = 1;
        for(Integer bytesCounter = 0; bytesCounter < bytesArrayLength; bytesCounter++){
            Integer textArrayIndex = bytesCounter * 8;
            byteArray[bytesCounter] = 0;
            for(Integer bitCounter = 0; bitCounter < 8; bitCounter++){
                if(text.length >= textArrayIndex + bitCounter + 1){
                   if ( text[textArrayIndex + bitCounter] == wordTomark){
                    byteArray[bytesCounter] = (byte)(byteArray[bytesCounter] | byteOne << bitCounter);
                   }
                }
            }
        }
        return byteArray;
    }


//    /**
//     * Shift bytes array on n positions to the right
//     * @param bytes
//     * @param rightShifts
//     */
//    public static void shiftBitsRight(Byte[] bytes, final Integer rightShifts) {
//        assert rightShifts >= 1 && rightShifts <= 7;
//
//        final Integer leftShifts = 8 - rightShifts;
//
//        Byte previousByte = bytes[0]; // keep the byte before modification
//        bytes[0] = (byte) (((bytes[0] & 0xff) >> rightShifts) | ((bytes[bytes.length - 1] & 0xff) << leftShifts));
//        for (Integer i = 1; i < bytes.length; i++) {
//            Byte tmp = bytes[i];
//            bytes[i] = (byte) (((bytes[i] & 0xff) >> rightShifts) | ((previousByte & 0xff) << leftShifts));
//            previousByte = tmp;
//        }
//    }

    /**
     * Shifts bytes array for one bit. Last bit from previous byte
     * is tranmitted to current
     * @param bytes
     * @return Shifted vector of bytes
     */
    @Deprecated
    private Byte[]  shiftBitsLeft(Byte[] bytes) {
        Byte previousBit = 0;
        Byte currentBit = 0;
        previousBit = (byte)(bytes[0] & (1 << 7));
        for(Integer bytesCounter = 0; bytesCounter < bytes.length; bytesCounter++){
            currentBit = (byte)(bytes[bytesCounter] & (1 << 7));
            bytes[bytesCounter] = (byte)(bytes[bytesCounter] << 1);
            if(bytesCounter > 0){
                bytes[bytesCounter] = (byte)(bytes[bytesCounter] | previousBit);
            }
            previousBit = (byte)((currentBit >> 7) & 1);
        }
        return bytes;
    }

    /**
     * Shifts Bit set for on bit to the left
     * @param bitSet
     * @return
     */
    public BitSet shiftBitSetLeft(BitSet bitSet) {
        final long maskOfCarry = 0x8000000000000000L;
        long[] aLong = bitSet.toLongArray();

        boolean carry = false;
        for (int i = 0; i < aLong.length; ++i) {
            if (carry) {
                carry = ((aLong[i] & maskOfCarry) != 0);
                aLong[i] <<= 1;
                ++aLong[i];
            } else {
                carry = ((aLong[i] & maskOfCarry) != 0);
                aLong[i] <<= 1;
            }
        }

        if (carry) {
            long[] tmp = new long[aLong.length + 1];
            System.arraycopy(aLong, 0, tmp, 0, aLong.length);
            ++tmp[aLong.length];
            aLong = tmp;
        }

        return BitSet.valueOf(aLong);
    }

    private Byte[] byteArrayAnd(Byte[] firstArray, Byte[] secondArray){
        assert firstArray.length == secondArray.length;
        Byte[] resultArray = new Byte[firstArray.length];
        for(Integer indexCounter = 0; indexCounter < firstArray.length; indexCounter++){
            resultArray[indexCounter] = (byte)(firstArray[indexCounter] & secondArray[indexCounter]);
        }
        return resultArray;
    }

}
