package fr.ramiere.codestory;

public class FooBarQixFluentAbuseTest extends FooBarQixTest {
	@Override
	public FooBarQix buildFooBarQix() {
		return new FooBarQixFluentAbuse();
	}
}