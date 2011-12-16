package fr.ramiere.codestory;

public class FooBarQixBenchmarkLombokTest extends FooBarQixBenchmarkTest {
	@Override
	public FooBarQix buildFooBarQix() {
		return new FooBarQixLombok();
	}
}
