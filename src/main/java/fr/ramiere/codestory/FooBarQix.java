package fr.ramiere.codestory;

public class FooBarQix {
    private static final String QIX = "Qix";
    private static final String BAR = "Bar";
    private static final String FOO = "Foo";

    public void displayUpTo(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("number should be strictly positive, got " + number);
        }
        for (int i = 1; i <= number; i++) {
            System.out.println(convert(i));
        }
    }

    public String convert(int number) {
        final String value = Integer.toString(number);
        final StringBuffer ret = new StringBuffer();

        // divisible
        boolean foo = number % 3 == 0;
        boolean bar = number % 5 == 0;
        boolean qix = number % 7 == 0;

        // divisibles rule
        ret.append(foo ? FOO : "");
        ret.append(bar ? BAR : "");
        ret.append(qix ? QIX : "");

        // foo/bar/qix digit presence rule
        foo |= value.contains("3");
        bar |= value.contains("5");
        qix |= value.contains("7");

        // no conversion to be done ? get out
        if (!foo && !bar && !qix) {
            return value;
        }

        // iterate on the digits and apply foo/bar/qix conversions
        final int length = value.length();
        for (int i = 0; i < length; i++) {
            final char digit = value.charAt(i);
            switch (digit) {
            case '3':
                ret.append(FOO);
                break;
            case '5':
                ret.append(BAR);
                break;
            case '7':
                ret.append(QIX);
                break;
            default:
                // no op rule
                break;
            }
        }
        return ret.toString();
    }
}
