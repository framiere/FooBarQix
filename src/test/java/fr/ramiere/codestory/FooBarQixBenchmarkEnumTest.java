package fr.ramiere.codestory;

public class FooBarQixBenchmarkEnumTest extends FooBarQixBenchmarkTest {
	@Override
	public FooBarQix buildFooBarQix() {
		return new FooBarQixEnum();
	}
}
