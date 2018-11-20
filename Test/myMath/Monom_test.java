package myMath;

import static org.junit.Assert.*;

import org.junit.Test;

public class Monom_test {
	
	private double eps = 1e-10;

	@Test
	public void test_get_coefficient() {
		Monom m = new Monom(1, 2);
		double coef = m.get_coefficient();
		assertEquals(1, coef, eps);
	}
	
	@Test
	public void test_get_power() {
		Monom m = new Monom(1, 2);
		int power = m.get_power();
		assertEquals(2, power);
	}

	@Test
	public void test_f() {
		Monom m = new Monom(1, 2);
		double y = m.f(10);
		assertEquals(100, y, eps);
	}

}
