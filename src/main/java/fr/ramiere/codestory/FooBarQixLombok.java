package fr.ramiere.codestory;

import java.util.Arrays;

import lombok.ExtensionMethod;

@ExtensionMethod({ Arrays.class, FooBarQixLombokUtils.class })
public class FooBarQixLombok extends FooBarQix {

	@Override
	public String convert(int number) {
		return convert(new Integer(number));
	}

	public String convert(Integer number) {
		final String value = Integer.toString(number);
		final StringBuilder ret = new StringBuilder();
		ret.append(number.foo());
		ret.append(number.bar());
		ret.append(number.qix());
		for (int i = 0; i < value.length(); i++) {
			Character car = new Character(value.charAt(i));
			ret.append(car.foobarqix());
		}
		return ret.emptyRule(value);
	}
}