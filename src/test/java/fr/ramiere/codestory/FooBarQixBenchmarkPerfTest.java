package fr.ramiere.codestory;

import org.junit.Before;

public class FooBarQixBenchmarkPerfTest extends FooBarQixBenchmarkTest {
    @Before
    public void buildFooBarQix() {
        fooBarQix = new FooBarQixPerf();
    }
}
