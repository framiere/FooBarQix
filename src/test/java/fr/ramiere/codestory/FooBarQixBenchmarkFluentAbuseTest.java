package fr.ramiere.codestory;

import org.junit.Before;

public class FooBarQixBenchmarkFluentAbuseTest extends FooBarQixBenchmarkTest {
    @Before
    public void buildFooBarQix() {
        fooBarQix = new FooBarQixFluentAbuse();
    }
}
