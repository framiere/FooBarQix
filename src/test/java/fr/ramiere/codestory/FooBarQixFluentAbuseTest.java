package fr.ramiere.codestory;

import org.junit.Before;

public class FooBarQixFluentAbuseTest extends FooBarQixTest {

    @Before
    @Override
    public void buildFooBarQix() {
        fooBarQix = new FooBarQixFluentAbuse();
    }
}