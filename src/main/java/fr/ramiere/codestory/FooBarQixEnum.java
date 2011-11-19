package fr.ramiere.codestory;

import static fr.ramiere.codestory.FooBarQixEnum.Code.*;
import static java.lang.Character.forDigit;

/**
 * 15% less effective than {@link FooBarQixPerf}
 */
public class FooBarQixEnum extends FooBarQix {
    @Override
    public String convert(int number) {
        final String value = Integer.toString(number);
        final StringBuilder ret = new StringBuilder(value.length() * 3);
        for (Code code : values()) {
            ret.append(code.divisible(number));
        }
        for (int i = 0; i < value.length(); i++) {
            ret.append(convertDigit(value.charAt(i)));
        }
        return ret.length() == 0 ? value : ret.toString();
    }

    static enum Code {
        foo("Foo", 3), //
        bar("Bar", 5), //
        qix("Qix", 7);
        private final String val;
        private final int digit;
        private final char digitChar;

        Code(String val, int digit) {
            this.val = val;
            this.digit = digit;
            this.digitChar = forDigit(digit, 10);
        }

        String divisible(int number) {
            return number % digit == 0 ? val : "";
        }

        static String convertDigit(char digitChar) {
            for (Code p : values()) {
                if (digitChar == p.digitChar) {
                    return p.val;
                }
            }
            return "";
        }
    }
}
