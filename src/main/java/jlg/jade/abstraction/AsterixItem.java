/* 
* Created by dan-geabunea on 4/18/2016.
* This code is the property of JLG Consulting. Please
* check the license terms for this product to see under what
* conditions you can use or modify this source code.
*/
package jlg.jade.abstraction;

import jlg.jade.common.AsterixDecodingException;
import jlg.jade.asterix.AsterixItemLength;

/**
 * Generic definition of an Asterix item. All implementation of items will derive from
 * this base class
 */
public abstract class AsterixItem extends DebugMessageSource implements DecodableFixedLength, EncodableFixedLength {
    protected AsterixItemLength itemLength;

    protected void validate(){
        return;
    }

    /**
     * Reset all Asterix item fields to their default values
     */
    public abstract void reset();

    /**
     * Checks if the remaining length in the input array is greater or equal than the needed length for
     * this item. If not, an UnexpectedEndOfData exception is thrown
     * @param currentIndex the current index in the input array
     * @param inputLength the length of the input array
     * @throws jlg.jade.common.AsterixDecodingException.UnexpectedEndOfData
     */
    protected void checkLength(int currentIndex, int inputLength){
        String itemName = this.getClass().getSimpleName();

        appendDebugMsg(itemName + ":");

        int remainingLength = inputLength - currentIndex;
        if (itemLength.getValue() > remainingLength) {
            appendDebugMsg("Unexpected end of data found while creating " + itemName);
            throw new AsterixDecodingException.UnexpectedEndOfData("Unexpected end of data found while creating " + itemName);
        }
    }

}