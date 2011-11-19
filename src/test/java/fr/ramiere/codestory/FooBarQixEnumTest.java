package fr.ramiere.codestory;

import org.junit.Before;

public class FooBarQixEnumTest extends FooBarQixTest {
    @Before
    @Override
    public void buildFooBarQix() {
        fooBarQix = new FooBarQixEnum();
    }
}
