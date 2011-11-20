package fr.ramiere.codestory;

import static java.lang.Character.forDigit;

/**
 * don't panic, this mess is created on purpose :)
 */
public class FooBarQixFluentAbuseBuilder {
    static $when when() {
        return new $when();
    }

    static $digitFound digitFound(int i) {
        return new $digitFound(i);
    }

    static class $when {
        $number number() {
            return new $number();
        }
    }

    static class $number {
        $is is() {
            return new $is();
        }
    }

    static class $is {
        $divisibleby divisibleBy(int divisiblesBy) {
            return new $divisibleby(divisiblesBy);
        }

        $digitFound digitFound(int digitFound) {
            return new $digitFound(digitFound);
        }
    }

    static class $divisibleby {
        int divisiblesBy;

        $divisibleby(int divisiblesBy) {
            this.divisiblesBy = divisiblesBy;
        }

        $then then() {
            return new $then(this);
        }

        $or or($digitFound digitFound) {
            return new $or(this, digitFound);
        }
    }

    static class $or {
        $divisibleby divisibleby;
        $digitFound digitFound;

        $or($divisibleby divisibleby, $digitFound digitFound) {
            this.divisibleby = divisibleby;
            this.digitFound = digitFound;
        }

        $then then() {
            return new $then(divisibleby, digitFound);
        }
    }

    static class $then {
        $divisibleby divisibleby;
        $digitFound digitFound;

        $then($divisibleby divisibleby) {
            this.divisibleby = divisibleby;
        }

        $then($divisibleby divisibleby, $digitFound digitFound) {
            this.divisibleby = divisibleby;
            this.digitFound = digitFound;
        }

        Rule write(String val) {
            return digitFound == null ? new Rule(val, divisibleby.divisiblesBy) : new Rule(val, divisibleby.divisiblesBy, digitFound.digit);
        }
    }

    static class $digitFound {
        int digit;

        $digitFound(int digit) {
            this.digit = digit;
        }

        $then then() {
            return new $then(null);
        }
    }

    static public class Rule {
        private final String val;
        private final int divisibleBy;
        private final char digitChar;

        Rule(String val, int divisibleBy) {
            this.val = val;
            this.divisibleBy = divisibleBy;
            this.digitChar = '\0';
        }

        Rule(String val, int divisibleBy, int digitChar) {
            this.val = val;
            this.divisibleBy = divisibleBy;
            this.digitChar = forDigit(digitChar, 10);
        }

        String divisible(int number) {
            return number % divisibleBy == 0 ? val : "";
        }

        String val() {
            return val;
        }

        char digitChar() {
            return digitChar;
        }
    }

    public static class Rules {
        public static $foreach on(int number) {
            return new $foreach(number);
        }

        public static class $foreach {
            int number;
            Iterable<Rule> rules;

            public $foreach(int number) {
                this.number = number;
            }

            $rules foreach(Iterable<Rule> rules) {
                this.rules = rules;
                return new $rules(number, this);
            }
        }

        public static class $rules {
            int number;
            $foreach foreach;

            $rules(int number, $foreach foreach) {
                this.number = number;
                this.foreach = foreach;
            }

            replacement$ divisibility() {
                return new replacement$(this);
            }
        }

        public static class replacement$ {
            $rules rules;

            replacement$($rules rules) {
                this.rules = rules;
            }

            String convertdigits() {
                final int val = rules.number;
                final StringBuilder ret = new StringBuilder();
                for (Rule rule : rules.foreach.rules) {
                    ret.append(rule.divisible(val));
                }
                final String value = Integer.toString(val);
                for (int i = 0; i < value.length(); i++) {
                    ret.append(convertDigit(value.charAt(i)));
                }
                return ret.length() == 0 ? value : ret.toString();
            }

            String convertDigit(char digitChar) {
                for (Rule rule : rules.foreach.rules) {
                    if (digitChar == rule.digitChar()) {
                        return rule.val();
                    }
                }
                return "";
            }
        }
    }
}
