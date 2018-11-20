package myMath;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Polynom polynom = new Polynom("1+X^3+2x");
		Polynom polynom2 = new Polynom("3X^2 + 5*x");

		double area = polynom.area(0, 5, 0.001);
		System.out.println(area);
		double root = polynom.root(-0.1, 0.1, 0.001);
		System.out.println(root);
		
		Polynom der = (Polynom) polynom.derivative();
//		
		System.out.println(polynom);
		System.out.println(polynom2);

		System.out.println(der);
		polynom.add(polynom2);
		System.out.println(polynom);

		//test1();

	}

	private static void test1() {
		double[] coeffs = new double[] {1};
		int[] powers = new int[] {1};
		
		for (int i = 0; i < powers.length; i++) {
			int power = powers[i];
		
			if (power < 0) {
				System.out.println("Illegal input");
				return;
			}
			for (int j = 0; j < powers.length; j++) {
				int power2 = powers[j];
				if (j != i && power == power2) {
					System.out.println("Illegal input");
					return;
				}
			}
		}
		
		if (coeffs.length!= powers.length) {
			System.out.println("Illegal input");
			return;
		}
		
		List<Monom >polynom_list = new ArrayList<Monom>();
		for (int i = 0; i < coeffs.length; i++) {
			polynom_list.add(new Monom(coeffs[i], powers[i]));
		}
		
		Polynom polynom = new Polynom(polynom_list);
		double area = polynom.area(0, 5, 0.001);
		System.out.println(area);
		double root = polynom.root(-0.1, 0.1, 0.001);
		System.out.println(root);
		
		Polynom der = (Polynom) polynom.derivative();
		
		System.out.println(polynom);
		
		System.out.println(der);
	}
}
