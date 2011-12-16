package fr.ramiere.codestory;

public class FooBarQixBenchmarkPerfTest extends FooBarQixBenchmarkTest {
	@Override
	public FooBarQix buildFooBarQix() {
		return new FooBarQixPerf();
	}
}
