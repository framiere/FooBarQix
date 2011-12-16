package fr.ramiere.codestory;

import org.junit.Before;

public class FooBarQixLombokTest extends FooBarQixTest {
	@Before
	@Override
	public void buildFooBarQix() {
		fooBarQix = new FooBarQixLombok();
	}
}
