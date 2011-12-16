package fr.ramiere.codestory;

import static org.fest.assertions.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public abstract class FooBarQixTest {
	FooBarQix fooBarQix;

	public abstract FooBarQix buildFooBarQix();

	@Before
	public void setup() {
		fooBarQix = buildFooBarQix();
	}

	@Test
	public void one_to_ten() {
		assertThat(fooBarQix.convert(1)).isEqualTo("1");
		assertThat(fooBarQix.convert(2)).isEqualTo("2");
		assertThat(fooBarQix.convert(3)).isEqualTo("FooFoo");
		assertThat(fooBarQix.convert(4)).isEqualTo("4");
		assertThat(fooBarQix.convert(5)).isEqualTo("BarBar");
		assertThat(fooBarQix.convert(6)).isEqualTo("Foo");
		assertThat(fooBarQix.convert(7)).isEqualTo("QixQix");
		assertThat(fooBarQix.convert(8)).isEqualTo("8");
		assertThat(fooBarQix.convert(9)).isEqualTo("Foo");
		assertThat(fooBarQix.convert(10)).isEqualTo("Bar");
	}

	@Test
	public void dividers_first() {
		assertThat(fooBarQix.convert(51)).isEqualTo("FooBar");
	}

	@Test
	public void digit_orders() {
		assertThat(fooBarQix.convert(53)).isEqualTo("BarFoo");
	}

	@Test
	public void divider_orders() {
		assertThat(fooBarQix.convert(21)).isEqualTo("FooQix");
	}

	@Test
	public void contains_3() {
		assertThat(fooBarQix.convert(13)).isEqualTo("Foo");
	}

	@Test
	public void divisible_by_3_and_5_contains_5() {
		assertThat(fooBarQix.convert(15)).isEqualTo("FooBarBar");
	}

	@Test
	public void contains_double_3_and_is_divisible_by_3() {
		assertThat(fooBarQix.convert(33)).isEqualTo("FooFooFoo");
	}

	@Test
	public void test_45() {
		assertThat(fooBarQix.convert(45)).isEqualTo("FooBarBar");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalid_display() {
		fooBarQix.displayUpTo(0);
	}

	@Test
	public void display_one_to_one_thousand() {
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		final PrintStream out = System.out;
		System.setOut(new PrintStream(output));
		try {
			fooBarQix.displayUpTo(10);
			assertThat(output.toString()).isEqualTo("" //
					+ "1\n"//
					+ "2\n"//
					+ "FooFoo\n"//
					+ "4\n"//
					+ "BarBar\n"//
					+ "Foo\n"//
					+ "QixQix\n"//
					+ "8\n"//
					+ "Foo\n"//
					+ "Bar\n"//
			);
		} finally {
			System.setOut(out);
		}
	}

	@Test
	public void display_1_to_100() {
		System.out.println("Results for " + fooBarQix.getClass().getSimpleName());
		fooBarQix.displayUpTo(100);
	}
}
