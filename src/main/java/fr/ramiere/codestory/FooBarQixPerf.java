package fr.ramiere.codestory;

public class FooBarQixPerf extends FooBarQix {
    private static final String QIX = "Qix";
    private static final String BAR = "Bar";
    private static final String FOO = "Foo";

    @Override
    public String convert(int number) {
        final String value = Integer.toString(number);
        final StringBuilder ret = new StringBuilder(value.length() * 3);

        // divisibles rule
        ret.append(number % 3 == 0 ? FOO : "");
        ret.append(number % 5 == 0 ? BAR : "");
        ret.append(number % 7 == 0 ? QIX : "");

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
        return ret.length() == 0 ? value : ret.toString();
    }
}