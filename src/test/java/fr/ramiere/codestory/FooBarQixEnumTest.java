package fr.ramiere.codestory;

public class FooBarQixEnumTest extends FooBarQixTest {
	@Override
	public FooBarQix buildFooBarQix() {
		return new FooBarQixEnum();
	}
}
