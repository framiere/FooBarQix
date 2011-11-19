package fr.ramiere.codestory;

public class FooBarQixPerf extends FooBarQix {
    private static final String QIX = "Qix";
    private static final String BAR = "Bar";
    private static final String FOO = "Foo";

    @Override
    public String convert(int number) {
        final String value = Integer.toString(number);
        final StringBuilder ret = new StringBuilder(value.length() * 3);

        // divisible
        boolean foo = number % 3 == 0;
        boolean bar = number % 5 == 0;
        boolean qix = number % 7 == 0;

        // divisibles rule
        ret.append(foo ? FOO : "");
        ret.append(bar ? BAR : "");
        ret.append(qix ? QIX : "");

        // foo/bar/qix digit presence rule
        foo |= value.indexOf('3') != -1;
        bar |= value.indexOf('5') != -1;
        qix |= value.indexOf('7') != -1;

        // no conversion to be done ? get out
        if (!foo && !bar && !qix) {
            return value;
        }

        // iterate on the digits and apply foo/bar/qix conversions
        final int length = value.length();
        for (int i = 0; i < length; i++) {
            switch (value.charAt(i)) {
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