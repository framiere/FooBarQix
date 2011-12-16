package fr.ramiere.codestory;

public class FooBarQixBenchmarkFluentAbuseTest extends FooBarQixBenchmarkTest {
	@Override
	public FooBarQix buildFooBarQix() {
		return new FooBarQixFluentAbuse();
	}
}
