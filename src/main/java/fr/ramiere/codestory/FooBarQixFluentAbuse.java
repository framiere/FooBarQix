package fr.ramiere.codestory;

import static fr.ramiere.codestory.FooBarQixFluentAbuseBuilder.digitFound;
import static fr.ramiere.codestory.FooBarQixFluentAbuseBuilder.when;
import static fr.ramiere.codestory.FooBarQixFluentAbuseBuilder.Rules.on;
import static java.util.Arrays.asList;
import fr.ramiere.codestory.FooBarQixFluentAbuseBuilder.Rule;

/**
 * Abuse fluent declaration
 */
public class FooBarQixFluentAbuse extends FooBarQix {
    Iterable<Rule> rules = asList( //
            when().number().is().divisibleBy(3).or(digitFound(3)).then().write("Foo"), //
            when().number().is().divisibleBy(5).or(digitFound(5)).then().write("Bar"), //
            when().number().is().divisibleBy(7).or(digitFound(7)).then().write("Qix"));

    @Override
    public String convert(int number) {
        return on(number).foreach(rules).divisibility().convertdigits();
    }
}
