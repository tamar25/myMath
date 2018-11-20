package myMath;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class PolynomTest {
	
	private double eps = 1e-3;
	
	@Test
	public void test_string_ctor() {
		Polynom polynom = new Polynom("1x^0");
		List<Monom> monoms = polynom._polynom;
		assertEquals(1, monoms.size());
		assertEquals(1, monoms.get(0).get_coefficient(), eps);
		assertEquals(0, monoms.get(0).get_power());
	}
	
	@Test
	public void test_string_ctor1() {
		Polynom polynom = new Polynom("+1x");
		List<Monom> monoms = polynom._polynom;
		assertEquals(1, monoms.size());
		assertEquals(1, monoms.get(0).get_coefficient(), eps);
		assertEquals(1, monoms.get(0).get_power());
	}
	
	@Test
	public void test_string_ctor2() {
		Polynom polynom = new Polynom("+1x^1");
		List<Monom> monoms = polynom._polynom;
		assertEquals(1, monoms.size());
		assertEquals(1, monoms.get(0).get_coefficient(), eps);
		assertEquals(1, monoms.get(0).get_power());
	}
	
	@Test
	public void test_string_ctor3() {
		Polynom polynom = new Polynom("1x");
		List<Monom> monoms = polynom._polynom;
		assertEquals(1, monoms.size());
		assertEquals(1, monoms.get(0).get_coefficient(), eps);
		assertEquals(1, monoms.get(0).get_power());
	}
	
	@Test
	public void test_string_ctor4() {
		Polynom polynom = new Polynom("x");
		List<Monom> monoms = polynom._polynom;
		assertEquals(1, monoms.size());
		assertEquals(1, monoms.get(0).get_coefficient(), eps);
		assertEquals(1, monoms.get(0).get_power());
	}
	
	@Test
	public void test_string_ctor5() {
		Polynom polynom = new Polynom("0+1*x");
		List<Monom> monoms = polynom._polynom;
		assertEquals(1, monoms.size());
		assertEquals(1, monoms.get(0).get_coefficient(), eps);
		assertEquals(1, monoms.get(0).get_power());
	}
	
	@Test
	public void test_string_ctor_second_order() {
		Polynom polynom = new Polynom("x^2+x+1");
		List<Monom> monoms = polynom._polynom;
		assertEquals(3, monoms.size());
		
		
		boolean has2nd = false;
		boolean has1st = false;
		boolean hasConst = false;

		for (Monom monom : monoms) {
			int power = monom.get_power();
			if (power == 0) {
				hasConst = true; 
			}
			else if (power == 1) {
				has1st = true;
			}
			else if (power == 2) {
				has2nd = true;
			}
			assertEquals(1, monom.get_coefficient(), eps);
		}
		assertTrue(hasConst);
		assertTrue(has2nd);
		assertTrue(has1st);

	}
	
	@Test
	public void test_string_ctor_second_order2() {
		Polynom polynom = new Polynom("1+x+x^2");
		List<Monom> monoms = polynom._polynom;
		assertEquals(3, monoms.size());
		
		
		boolean has2nd = false;
		boolean has1st = false;
		boolean hasConst = false;

		for (Monom monom : monoms) {
			int power = monom.get_power();
			if (power == 0) {
				hasConst = true; 
			}
			else if (power == 1) {
				has1st = true;
			}
			else if (power == 2) {
				has2nd = true;
			}
			assertEquals(1, monom.get_coefficient(), eps);
		}
		assertTrue(hasConst);
		assertTrue(has2nd);
		assertTrue(has1st);

	}
	
	@Test
	public void test_string_ctor_second_order3() {
		Polynom polynom = new Polynom("x^2+1.0x+1");
		List<Monom> monoms = polynom._polynom;
		assertEquals(3, monoms.size());
		
		
		boolean has2nd = false;
		boolean has1st = false;
		boolean hasConst = false;

		for (Monom monom : monoms) {
			int power = monom.get_power();
			if (power == 0) {
				hasConst = true; 
			}
			else if (power == 1) {
				has1st = true;
			}
			else if (power == 2) {
				has2nd = true;
			}
			assertEquals(1, monom.get_coefficient(), eps);
		}
		assertTrue(hasConst);
		assertTrue(has2nd);
		assertTrue(has1st);

	}
	

	@Test
	public void test_area_const() {
		Polynom polynom = new Polynom("1");
		double area = polynom.area(0, 5, 0.001);
		assertEquals(5, area, eps);
	}
	
	@Test
	public void test_area_linear() {
		Polynom polynom = new Polynom("x");
		double area = polynom.area(0, 5, 0.00001);
		assertEquals(25/2., area, eps);
	}
	
	@Test
	public void test_root_linear() {
		Polynom polynom = new Polynom("x");
		double root = polynom.root(-1, 1, 0.00001);
		assertEquals(0, root, eps);
	}
	
	@Test
	public void test_root_affine() {
		Polynom polynom = new Polynom("x + 5");
		double root = polynom.root(-6, -4, 0.00001);
		assertEquals(-5, root, eps);
	}
	
	@Test
	public void test_root_affine_not_root() {
		Polynom polynom = new Polynom("x + 5");
		double root = polynom.root(-1, 1, 0.00001);
		assertTrue(Double.isInfinite(root));
	}
	
	@Test
	public void test_eq() {
		Polynom polynom1 = new Polynom("x + 5");
		Polynom polynom2 = new Polynom("5 + 1*x");
		assertTrue(polynom1.equals(polynom2));
	}
	
	@Test
	public void test_not_eq() {
		Polynom polynom1 = new Polynom("x + 5");
		Polynom polynom2 = new Polynom("3 + 1*x");
		assertFalse(polynom1.equals(polynom2));
	}
	
	
	@Test
	public void test_deriv() {
		Polynom polynom = new Polynom("x^2+x+1");
		Polynom deriv = (Polynom) polynom.derivative();
		List<Monom> monoms = deriv._polynom;
		
		
		
		assertEquals(2, monoms.size());
		boolean hasConst = false;
		boolean has1st = false;

		for (Monom monom : monoms) {
			int power = monom.get_power();
			if (power == 1) {
				has1st = true; 
				assertEquals(2, monom.get_coefficient(), eps);
			}
			else if (power == 0) {
				hasConst = true;
				assertEquals(1, monom.get_coefficient(), eps);
			}
			
		}
		assertTrue(hasConst);
		assertTrue(has1st);

	}
	
	@Test
	public void test_add() {
		Polynom polynom1 = new Polynom("x^2");
		Polynom polynom2 = new Polynom("x^2+x+1");
		polynom1.add(polynom2);
		List<Monom> monoms = polynom1._polynom;
		
		
		
		assertEquals(3, monoms.size());
		boolean hasConst = false;
		boolean has1st = false;
		boolean has2nd = false;

		for (Monom monom : monoms) {
			int power = monom.get_power();
			if (power == 1) {
				has1st = true; 
				assertEquals(1, monom.get_coefficient(), eps);
			}
			else if (power == 0) {
				hasConst = true;
				assertEquals(1, monom.get_coefficient(), eps);
			}
			else if (power == 2) {
				has2nd = true;
				assertEquals(2, monom.get_coefficient(), eps);
			}
			
		}
		assertTrue(hasConst);
		assertTrue(has2nd);
		assertTrue(has1st);

	}
	
	@Test
	public void test_sub() {
		Polynom polynom1 = new Polynom("x^2");
		Polynom polynom2 = new Polynom("x^2+x+1");
		polynom1.substract(polynom2);
		List<Monom> monoms = polynom1._polynom;
		
		
		
		assertEquals(2, monoms.size());
		boolean hasConst = false;
		boolean has1st = false;

		for (Monom monom : monoms) {
			int power = monom.get_power();
			if (power == 1) {
				has1st = true; 
				assertEquals(-1, monom.get_coefficient(), eps);
			}
			else if (power == 0) {
				hasConst = true;
				assertEquals(-1, monom.get_coefficient(), eps);
			}
				
		}
		assertTrue(hasConst);
		assertTrue(has1st);

	}
	@Test
	public void test_mult() {
		Polynom polynom1 = new Polynom("x^2+2x");
		Polynom polynom2 = new Polynom("x^2+x+1");
		polynom1.multiply(polynom2);
		List<Monom> monoms = polynom1._polynom;
		
		
		
		assertEquals(4, monoms.size());
		boolean has1st = false;
		boolean has2nd = false;
		boolean has3rd = false;
		boolean has4th = false;


		for (Monom monom : monoms) {
			int power = monom.get_power();
			if (power == 1) {
				has1st = true; 
				assertEquals(2, monom.get_coefficient(), eps);
			}
			else if (power == 2) {
				has2nd = true;
				assertEquals(3, monom.get_coefficient(), eps);
			}
			else if (power == 3) {
				has3rd = true;
				assertEquals(3, monom.get_coefficient(), eps);
			}
			else if (power == 4) {
				has4th = true;
				assertEquals(1, monom.get_coefficient(), eps);
			}
			
		}
		assertTrue(has3rd);
		assertTrue(has4th);

		assertTrue(has2nd);
		assertTrue(has1st);

	}
}
