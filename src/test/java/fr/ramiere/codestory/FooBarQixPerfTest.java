package fr.ramiere.codestory;

import org.junit.Before;

public class FooBarQixPerfTest extends FooBarQixTest {
    @Before
    @Override
    public void buildFooBarQix() {
        fooBarQix = new FooBarQixPerf();
    }
}