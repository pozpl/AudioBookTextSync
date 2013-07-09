/*
 * This class is supposed to find snippet of text in full text pattern
 * by means of Bitap algorithm
 */
package ru.urbancamper.audiobookmarker.text;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.TreeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author pozpl
 */
public class BitapSubtextFinding {

    protected final Log logger = LogFactory.getLog(getClass());

    public BitSet fillBitSetFromWordsNumberArray(Integer[] text, Integer wordToMark){
        BitSet textVectorBitSet = new BitSet(text.length);
        for(Integer wordsCounter = 0; wordsCounter < text.length; wordsCounter++){
            if(text[wordsCounter] == wordToMark){
                textVectorBitSet.set(wordsCounter);
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

    @Deprecated
    private Byte[] byteArrayAnd(Byte[] firstArray, Byte[] secondArray){
        assert firstArray.length == secondArray.length;
        Byte[] resultArray = new Byte[firstArray.length];
        for(Integer indexCounter = 0; indexCounter < firstArray.length; indexCounter++){
            resultArray[indexCounter] = (byte)(firstArray[indexCounter] & secondArray[indexCounter]);
        }
        return resultArray;
    }

    /**
     * Return the list of indexes where the pattern was found.
     *
    * The indexes are not exacts because of the addition and deletion : Example
     * : the text "aa bb cc" with the pattern "bb" and k=1 will match "
     * b","b","bb","b ". and only the index of the first result " b" is added to
     * the list even if "bb" have q lower error rate.
     *
     * @param doc
     * @param pattern
     * @param k
     * @return
     */
    public List<Integer> find(Integer[] doc, Integer[] pattern, int k) {

        int firstMatchedText = -1;
// Indexes where the pattern was found
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        BitSet[] r = new BitSet[k + 1];

//        BitSet patternMask = new BitSet(pattern.length);
        for (int i = 0; i <= k; i++) {
            r[i] = new BitSet();
            r[i].set(0);
        }

// Example : The mask for the letter 'e' and the pattern "hello" is
// 11101 (0 means this letter is at this place in the pattern)
        TreeMap<Integer, BitSet> patternMask = new TreeMap<Integer, BitSet>();
//        BitSet[] patternMask = new BitSet[pattern.length];
        for (int i = 0; i < pattern.length; ++i) {
            if(! patternMask.containsKey(pattern[i])){
                BitSet patternMaskForWord = this.fillBitSetFromWordsNumberArray(pattern, pattern[i]);
                patternMask.put(pattern[i], patternMaskForWord);
            }

        }
        int i = 0;

        while (i < doc.length) {
            BitSet symbolMask = (patternMask.containsKey(doc[i])) ? patternMask.get(doc[i])
                    :new BitSet();
            BitSet old = new BitSet();
            BitSet nextOld = new BitSet();

            for (int d = 0; d <= k; ++d) {
                BitSet rD = (BitSet)(r[d].clone());//this.shiftBitSetLeft(r[d]);
                rD.and(symbolMask);
                BitSet rDShifted = this.shiftBitSetLeft(rD);
                BitSet ins = (BitSet)(rDShifted.clone());
                BitSet del = (BitSet)(rD.clone());
                BitSet sub = (BitSet)(rD.clone());

                ins.or(old);
                sub.or(old);
                sub = this.shiftBitSetLeft(sub);
                del.or(nextOld);
                del = this.shiftBitSetLeft(del);

                old = (BitSet)(r[d].clone());
                nextOld = (BitSet)(ins.clone());
                nextOld.or(sub);
                nextOld.or(del);
                nextOld.set(0);
                r[d] = nextOld;

// Three operations of the Levenshtein distance
//                long sub = (old | (r[d] & patternMask[doc.charAt(i)])) << 1;
//                long ins = old | ((r[d] & patternMask[doc.charAt(i)]) << 1);
//                long del = (nextOld | (r[d] & patternMask[doc.charAt(i)])) << 1;
//                old = r[d];
//                r[d] = sub | ins | del | 1;
//                nextOld = r[d];
            }
// When r[k] is full of zeros, it means we matched the pattern
// (modulo k errors)
            if(r[k].get(pattern.length)){
//            if (0 < (r[k] & (1 << pattern.length()))) {
// The pattern "aaa" for the document "bbaaavv" with k=2
// will slide from "bba","baa","aaa","aav","avv"
// Because we allow two errors !
// This test keep only the first one and skip all the others.
// (We can't skip by increasing i otherwise the r[d] will be
// wrong)
                if ((firstMatchedText == -1) || (i - firstMatchedText > pattern.length)) {
                    firstMatchedText = i;
                     indexes.add(firstMatchedText - pattern.length);
                     indexes.add(firstMatchedText);
                }
            }
            i++;
        }

        return indexes;
    }

    /**
     * Find only one best fitting text snippet for pattern
     * @param doc - document in number form
     * @param patternBig
     * @return
     */
    public Integer[] findWithReducedError(Integer[] doc, Integer[] patternBig){

        Integer subPatternToFindLength = 1000;
        Integer[] pattern = this.getSubPattern(patternBig, 0,subPatternToFindLength);
        Integer maxErrorsRate = pattern.length / 2;
//        maxErrorsRate = maxErrorsRate < 600 ? 600 : maxErrorsRate;
        Integer foundSnippetIndexBegin = 0;
        Integer foundSnippetIndexEnd = 0;
        this.logger.info("Start to find subtext of " + pattern.length
                + " in text with lenght "
                + doc.length );
        Integer finalErrorsRate = 0;
        for(Integer errorsCount = maxErrorsRate; errorsCount < pattern.length -1;  errorsCount += 10){
            this.logger.info("current errors rate " + errorsCount);
            List<Integer> foundSnippets = this.find(doc, pattern, errorsCount);
            if(foundSnippets.size() == 2){
                foundSnippetIndexBegin =  foundSnippets.get(0);
                foundSnippetIndexEnd =  foundSnippets.get(1);
                finalErrorsRate = errorsCount;
                this.logger.info("Subtext found with errors rate "
                        + errorsCount
                        + " index begin " + foundSnippetIndexBegin
                        + " index end " + foundSnippetIndexEnd);
                break;
            }else if(foundSnippets.size() > 2){
                this.logger.info("Minimum errors count 0 was reached return first found index "
                        + foundSnippets.get(0));
                foundSnippetIndexBegin =  foundSnippets.get(0);
                foundSnippetIndexEnd =  foundSnippets.get(1);
                finalErrorsRate = errorsCount;
            }else if(foundSnippets.isEmpty()){
                this.logger.info("Not sufficient errors rate " + errorsCount + " brak!!!");
//                break;
            }
        }

        Integer fullPatternErrorsRate = this.evaluateErrorForFullPattern(patternBig.length, pattern.length, finalErrorsRate);
        foundSnippetIndexEnd += patternBig.length  + fullPatternErrorsRate - subPatternToFindLength;
        foundSnippetIndexEnd = (foundSnippetIndexEnd.intValue() < patternBig.length) ? foundSnippetIndexEnd : patternBig.length - 1;
        foundSnippetIndexBegin = (foundSnippetIndexBegin > 0) ? foundSnippetIndexBegin : 0;
        Integer[] beginEndArray = {foundSnippetIndexBegin, foundSnippetIndexEnd};
        this.logger.info("Subtext found first index " + foundSnippetIndexBegin
                + " last index " + foundSnippetIndexEnd);
        return beginEndArray;
    }

    /**
     *
     * @param fullPatternLength
     * @param partPatternLength
     * @param partPatternErrorsRate
     * @return Errors rate for full pattern
     */
    private Integer evaluateErrorForFullPattern(Integer fullPatternLength, Integer partPatternLength,
                                                Integer partPatternErrorsRate){
        Integer fullPatternErrorsRate = fullPatternLength * partPatternErrorsRate / partPatternLength;
        return fullPatternErrorsRate;
    }

    /**
     * @param doc
     * @param pattern
     * @param beginIndex
     * @return
     */
    @Deprecated
    private Integer[] findFullPattern(Integer[] doc, Integer[] pattern, Integer beginIndex){

//        Integer[] pattern = this.getSubPattern(patternBig, 1000);
        Integer[] subDocument = this.getSubPattern(doc, beginIndex, doc.length - beginIndex);
        Integer maxErrorsRate = pattern.length / 2;
        Integer foundSnippetIndexBegin = 0;
        Integer foundSnippetIndexEnd = 0;
        this.logger.info("Start to find subtext of " + pattern.length
                + " in text with lenght "
                + subDocument.length );
        for(Integer errorsCount = 0; errorsCount <=  maxErrorsRate; errorsCount++){
            this.logger.info("current errors rate " + errorsCount);
            List<Integer> foundSnippets = this.find(subDocument, pattern, errorsCount);
            if(foundSnippets.size() == 2){
                this.logger.info("Subtext found with errors rate " + errorsCount);
                foundSnippetIndexBegin =  foundSnippets.get(0);
                foundSnippetIndexEnd =  foundSnippets.get(1);
                break;
            }else if(foundSnippets.size() > 2){
                this.logger.info("Minimum errors count 0 was reached return first found index "
                        + foundSnippets.get(0));
                foundSnippetIndexBegin =  foundSnippets.get(0);
                foundSnippetIndexEnd =  foundSnippets.get(1);
            }
        }

        foundSnippetIndexBegin = foundSnippetIndexBegin > 0 ? foundSnippetIndexBegin : 0;
        Integer[] beginEndArray = {foundSnippetIndexBegin, foundSnippetIndexEnd};
        return beginEndArray;
    }

    private Integer[] getSubPattern(Integer[] pattern, Integer offset, Integer numberOfElementsInSubPattern){
        Integer subElementsCount = numberOfElementsInSubPattern < pattern.length ? numberOfElementsInSubPattern
                : pattern.length;
        Integer[] subPattern = new Integer[subElementsCount];
        System.arraycopy(pattern, offset, subPattern, 0, subElementsCount);
        return subPattern;
    }

}
