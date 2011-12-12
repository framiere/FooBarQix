# FooBarQix

* Concours CodeStory : http://www.code-story.net/
* Regles : http://www.code-story.net/2011/11/16/foobarqix.html
* Implementation des regles du 16 Nov 2011

# Versions J

* Java
 * With enums : https://github.com/framiere/FooBarQix/blob/master/src/main/java/fr/ramiere/codestory/FooBarQixEnum.java
 * For performance : https://github.com/framiere/FooBarQix/blob/master/src/main/java/fr/ramiere/codestory/FooBarQixPerf.java
 * Fluent abuse : https://github.com/framiere/FooBarQix/blob/master/src/main/java/fr/ramiere/codestory/FooBarQixFluentAbuse.java
* C
 * https://github.com/framiere/FooBarQix/blob/master/src/main/c/foobarqix.c
* ASM (work in progress)
 * https://github.com/framiere/FooBarQix/blob/master/src/main/asm/foobarqix.asm

# Results

1
2
FooFoo
4
BarBar
Foo
QixQix
8
Foo
Bar
11
Foo
Foo
Qix
FooBarBar
16
Qix
Foo
19
Bar
FooQix
22
Foo
Foo
BarBar
26
FooQix
Qix
29
FooBarFoo
Foo
Foo
FooFooFoo
Foo
BarQixFooBar
FooFoo
FooQix
Foo
FooFoo
Bar
41
FooQix
Foo
44
FooBarBar
46
Qix
Foo
Qix
BarBar
FooBar
Bar
BarFoo
FooBar
BarBarBar
QixBar
FooBarQix
Bar
Bar
FooBar
61
62
FooQixFoo
64
BarBar
Foo
Qix
68
Foo
BarQixQix
Qix
FooQix
QixFoo
Qix
FooBarQixBar
Qix
QixQixQix
FooQix
Qix
Bar
Foo
82
Foo
FooQix
BarBar
86
FooQix
88
89
FooBar
Qix
92
FooFoo
94
BarBar
Foo
Qix
Qix
Foo
Bar


# Bench result

```
Running fr.ramiere.codestory.FooBarQixBenchmarkEnumTest
FooBarQixBenchmarkEnumTest.bench: [measured 2000 out of 2500 rounds, threads: 10 (physical processors: 4)]
 round: 0.01 [+- 0.01], round.gc: 0.00 [+- 0.00], GC.calls: 382, GC.time: 0.19, time.total: 2.74, time.warmup: 0.75, time.bench: 1.99
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.829 sec
Running fr.ramiere.codestory.FooBarQixBenchmarkFluentAbuseTest
FooBarQixBenchmarkFluentAbuseTest.bench: [measured 2000 out of 2500 rounds, threads: 10 (physical processors: 4)]
 round: 0.01 [+- 0.01], round.gc: 0.00 [+- 0.00], GC.calls: 430, GC.time: 0.24, time.total: 3.05, time.warmup: 0.68, time.bench: 2.37
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.059 sec
Running fr.ramiere.codestory.FooBarQixBenchmarkPerfTest
FooBarQixBenchmarkPerfTest.bench: [measured 2000 out of 2500 rounds, threads: 10 (physical processors: 4)]
 round: 0.01 [+- 0.01], round.gc: 0.00 [+- 0.00], GC.calls: 205, GC.time: 0.12, time.total: 1.81, time.warmup: 0.49, time.bench: 1.32
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.827 sec
```

