package fr.ramiere.codestory;

public class FooBarQixLombokTest extends FooBarQixTest {
	@Override
	public FooBarQix buildFooBarQix() {
		return new FooBarQixLombok();
	}
}
