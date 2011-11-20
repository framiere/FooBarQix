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
        Foo(3), Bar(5), Qix(7);
        private final int digit;
        private final char digitChar;

        Code(int digit) {
            this.digit = digit;
            this.digitChar = forDigit(digit, 10);
        }

        String divisible(int number) {
            return number % digit == 0 ? name() : "";
        }

        static String convertDigit(char digitChar) {
            for (Code code : values()) {
                if (digitChar == code.digitChar) {
                    return code.name();
                }
            }
            return "";
        }
    }
}
