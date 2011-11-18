package fr.ramiere.codestory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;

@SuppressWarnings("deprecation")
public class FooBarQixBenchmarkTest {

    @Rule
    public MethodRule benchmarkRun = new BenchmarkRule();

    private FooBarQix fooBarQix;

    @Before
    public void setup() {
        fooBarQix = new FooBarQix();
    }

    @Test
    @BenchmarkOptions(benchmarkRounds = 2000, warmupRounds = 100, concurrency = 10)
    public void bench() {
        for (int i = 1; i < 10000; i++) {
            fooBarQix.convert(i);
        }
    }

}
