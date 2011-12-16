package fr.ramiere.codestory;

import org.junit.Before;

public class FooBarQixBenchmarkLombokTest extends FooBarQixBenchmarkTest {
    @Before
    public void buildFooBarQix() {
        fooBarQix = new FooBarQixLombok();
    }
}
