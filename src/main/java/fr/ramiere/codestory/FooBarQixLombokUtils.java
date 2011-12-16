package fr.ramiere.codestory;

public class FooBarQixLombokUtils {
	private static final String QIX = "Qix";
	private static final String BAR = "Bar";
	private static final String FOO = "Foo";

	private static String rule(Integer number, int i, String ret) {
		return number % i == 0 ? ret : "";
	}

	public static String foo(Integer number) {
		return rule(number, 3, FOO);
	}

	public static String bar(Integer number) {
		return rule(number, 5, BAR);
	}

	public static String qix(Integer number) {
		return rule(number, 7, QIX);
	}

	public static String foobarqix(Character car) {
		if (car == '3') {
			return FOO;
		} else if (car == '5') {
			return BAR;
		} else if (car == '7') {
			return QIX;
		} else {
			return "";
		}
	}

	public static String emptyRule(StringBuilder ret, String value) {
		return ret.length() == 0 ? value : ret.toString();
	}

	public static void append(StringBuilder ret, String... values) {
		for (String value : values) {
			ret.append(value);
		}
	}
}