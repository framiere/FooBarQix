package fr.ramiere.codestory;

public class FooBarQixPerfTest extends FooBarQixTest {
	@Override
	public FooBarQix buildFooBarQix() {
		return new FooBarQixPerf();
	}
}
