package com.github.wakingrufus.packedsign;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

/**
 * Class: PackedSignNumberFormatter
 *
 * @author wakingrufus
 */
public class PackedSignNumberFormat extends NumberFormat {

    /**
     * generally, decimal points are implied anyway, so I did not implement the double method.
     *
     * @param number
     * @param toAppendTo
     * @param pos
     * @return
     */
    @Override
    public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     * pass a long in to format it in packed sign form
     *
     * @param number
     * @param toAppendTo
     * @param pos
     * @return
     */
    @Override
    public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
        boolean isNegative = false;
        if (number < 0) {
            isNegative = true;
        }
        String amtStr;
        if (isNegative) {
            amtStr = Long.toString(number * -1);
        } else {
            amtStr = Long.toString(number);
        }

        int length = amtStr.length();
        char lastDigit = amtStr.charAt(length - 1);
        String tmp = null;
        String amount = null;
        if (!isNegative) {
            if (lastDigit == '0') {
                tmp = "{";
            } else if (lastDigit == '1') {
                tmp = "A";
            } else if (lastDigit == '2') {
                tmp = "B";
            } else if (lastDigit == '3') {
                tmp = "C";
            } else if (lastDigit == '4') {
                tmp = "D";
            } else if (lastDigit == '5') {
                tmp = "E";
            } else if (lastDigit == '6') {
                tmp = "F";
            } else if (lastDigit == '7') {
                tmp = "G";
            } else if (lastDigit == '8') {
                tmp = "H";
            } else if (lastDigit == '9') {
                tmp = "I";
            }

        } else if (isNegative) {
            if (lastDigit == '0') {
                tmp = "}";
            } else if (lastDigit == '1') {
                tmp = "J";
            } else if (lastDigit == '2') {
                tmp = "K";
            } else if (lastDigit == '3') {
                tmp = "L";
            } else if (lastDigit == '4') {
                tmp = "M";
            } else if (lastDigit == '5') {
                tmp = "N";
            } else if (lastDigit == '6') {
                tmp = "O";
            } else if (lastDigit == '7') {
                tmp = "P";
            } else if (lastDigit == '8') {
                tmp = "Q";
            } else if (lastDigit == '9') {
                tmp = "R";
            }
        }
        amount = amtStr.substring(0, amtStr.length() - 1) + tmp;
        // END IF
        toAppendTo.append(amount);
        return toAppendTo;
    }

    /**
     * parse a packed sign string into a Number (uses long)
     *
     * @param source
     * @return and long representation of the number
     * @throws ParseException
     */
    @Override
    public Number parse(String source) throws ParseException {
        String packedForm = source;
        int length = packedForm.length();
        char lastDigit = packedForm.charAt(length - 1);
        String tmp = null;
        boolean isNeg = false;
        String amtStr = null;
        long amount;

        if (lastDigit == '{' || lastDigit == '}') {
            tmp = "0";
        } else if (lastDigit == 'A' || lastDigit == 'J') {
            tmp = "1";
        } else if (lastDigit == 'B' || lastDigit == 'K') {
            tmp = "2";
        } else if (lastDigit == 'C' || lastDigit == 'L') {
            tmp = "3";
        } else if (lastDigit == 'D' || lastDigit == 'M') {
            tmp = "4";
        } else if (lastDigit == 'E' || lastDigit == 'N') {
            tmp = "5";
        } else if (lastDigit == 'F' || lastDigit == 'O') {
            tmp = "6";
        } else if (lastDigit == 'G' || lastDigit == 'P') {
            tmp = "7";
        } else if (lastDigit == 'H' || lastDigit == 'Q') {
            tmp = "8";
        } else if (lastDigit == 'I' || lastDigit == 'R') {
            tmp = "9";
        } else {
            throw new ParseException("Invalid Last Char " + lastDigit, 0);
        }

        if (lastDigit == '}' || lastDigit == 'J' || lastDigit == 'K' || lastDigit
                == 'L' || lastDigit == 'M' || lastDigit == 'N' || lastDigit
                == 'O' || lastDigit == 'P' || lastDigit == 'Q' || lastDigit
                == 'R') {
            isNeg = true;
        }

        amtStr = packedForm.substring(0, length - 1) + tmp;
        amount = Long.parseLong(amtStr);

        if (isNeg) {
            amount *= (-1);
        }

        return amount;
    }

    /**
     * I do not see a use case for this method
     *
     * @param source
     * @param parsePosition
     * @return
     */
    @Override
    public Number parse(String source, ParsePosition parsePosition) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
