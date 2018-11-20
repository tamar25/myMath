package myMath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import myMath.Monom;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able {

	List<Monom> _polynom;

	/**
	 * Parser ctr attempts to parse a string into a polynomial
	 * 
	 * @param polynom_string
	 */
	public Polynom(String polynom_string) {
		_polynom = new ArrayList<Monom>();
		try {

			polynom_string = polynom_string.replaceAll("\\*", "");
			polynom_string = polynom_string.replaceAll("X", "x");
			polynom_string = polynom_string.replaceAll("\\\\s+", "");

			polynom_string = polynom_string.trim();

			char first = polynom_string.charAt(0);
			if (Character.isDigit(first) || first == '.'|| first == 'x') {
				polynom_string = '+' + polynom_string;
			}

			
			int x_ind = polynom_string.indexOf('x');
			while (x_ind >= 0) {
				boolean hasNoPower = false;
				boolean isLast = (x_ind == polynom_string.length() - 1);
				if (!isLast) {
					hasNoPower = polynom_string.charAt(x_ind + 1) != '^';
				}
				if (isLast || hasNoPower) {
					String s1 = polynom_string.substring(0, x_ind+1);
					String s2 = polynom_string.substring(x_ind+1, polynom_string.length());
					polynom_string = s1 + "^1" + s2;
				}
				x_ind = polynom_string.indexOf('x', x_ind + 1);
			}

			int i = 0;
			while (i < polynom_string.length()) {
				char c = polynom_string.charAt(i);
				if ((i == 0 || polynom_string.charAt(i - 1) != '^') && (Character.isDigit(c) || c == '.')) {
					boolean hasNoX = false;
					int j = i + 1;
					boolean endOfNumber = false;
					while (!endOfNumber && j < polynom_string.length()) {
						char c_j = polynom_string.charAt(j);
						if (Character.isDigit(c_j) || c_j == '.') {
							j++;
						}else {
							endOfNumber = true;
						}
					}
					if (j < polynom_string.length()) {
						hasNoX = polynom_string.charAt(j) != 'x';
					} else {
						hasNoX = true;
					}
					if (hasNoX) {
						String s1 = polynom_string.substring(0, j);
						String s2 = polynom_string.substring(j, polynom_string.length());
						polynom_string = s1 + "x^0" + s2;
					}
					i = j;

				} else {
					i++;
				}
			}

			polynom_string = polynom_string.replaceAll("\\+x", "+1x");
			polynom_string = polynom_string.replaceAll("-x", "-1x");

			String[] split = polynom_string.split("x");

			for (i = 0; i < split.length; i++) {
				String curr_element = split[i];

				String curr_coef = curr_element.trim();
				if (i > 0) {
					if (curr_coef.contains("^")) {
						curr_coef = curr_coef.split("\\^")[1];
						curr_coef = curr_coef.trim();
						if (curr_coef.contains("+")) {
							curr_coef = curr_coef.split("\\+")[1];
							curr_coef = curr_coef.trim();
						} else if (curr_coef.contains("-")) {
							curr_coef = curr_coef.split("-")[1];
							curr_coef = curr_coef.trim();
							curr_coef = "-" + curr_coef;
						} else {
							curr_coef = "0";
						}
					}
				}
				String curr_power = "0";
				if (i > 0 && i == split.length - 1 && !curr_coef.contains("^")) {
					char last_chr = polynom_string.charAt(polynom_string.length() - 1);
					if (last_chr == 'x' || last_chr == 'X') {
						curr_power = "1";
					}
				} else {
					String next_element = split[i + 1];

					curr_power = next_element.trim();
					if (curr_power.contains("^")) {
						curr_power = curr_power.split("\\^")[1];
						curr_power = curr_power.trim();
						if (curr_power.contains("+") || curr_power.contains("-")) {
							curr_power = curr_power.split("[+-]")[0];
							curr_power = curr_power.trim();
						}
					} else {
						curr_power = "1";
					}

				}
				if (Integer.valueOf(curr_power) < 0) {
					throw new IllegalArgumentException("power < 0)");
				}
				Monom m = new Monom(Double.valueOf(curr_coef), Integer.valueOf(curr_power));
				_polynom.add(m);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
		mergeMonoms();
	}

	/**
	 * list ctr
	 * 
	 * @param polynom_list
	 */
	public Polynom(List<Monom> polynom_list) {
		this._polynom = new ArrayList<Monom>(polynom_list);
	}

	/**
	 * default dct
	 */
	public Polynom() {
		_polynom = new ArrayList<Monom>();
	}

	/**
	 * Copy ctr
	 * 
	 * @param other
	 */
	public Polynom(Polynom other) {
		this._polynom = ((Polynom) other.copy())._polynom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.function#f(double)
	 */
	@Override
	public double f(double x) {
		double y = 0;
		for (Monom monom : _polynom) {
			y += monom.f(x);
		}
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.Polynom_able#add(myMath.Polynom_able)
	 */
	@Override
	public void add(Polynom_able p1) {
		for (Iterator<Monom> iterator = p1.iteretor(); iterator.hasNext();) {
			Monom monom = (Monom) iterator.next();
			this.add(monom);
		}
		delete_zeros();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.Polynom_able#add(myMath.Monom)
	 */
	@Override
	public void add(Monom m1) {
		boolean found = false;
		Iterator<Monom> it = iteretor();
		while (!found && it.hasNext()) {
			Monom monom = (Monom) it.next();
			if (monom.get_power() == m1.get_power()) {
				monom.set_coefficient(monom.get_coefficient() + m1.get_coefficient());
				found = true;
			}
		}

		if (!found) {
			_polynom.add(new Monom(m1));
		}
		delete_zeros();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.Polynom_able#substract(myMath.Polynom_able)
	 */
	@Override
	public void substract(Polynom_able p1) {
		for (Iterator<Monom> iterator = p1.iteretor(); iterator.hasNext();) {
			Monom monom = (Monom) iterator.next();
			Monom negativeMonom = new Monom(-monom.get_coefficient(), monom.get_power());
			this.add(negativeMonom);
		}
		delete_zeros();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.Polynom_able#multiply(myMath.Polynom_able)
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom newPol = new Polynom();
		for (Iterator<Monom> iterator = p1.iteretor(); iterator.hasNext();) {
			Monom monom = (Monom) iterator.next();
			Polynom copy = (Polynom) this.copy();
			for (Monom copy_monom : copy._polynom) {
				copy_monom.set_coefficient(copy_monom.get_coefficient() * monom.get_coefficient());
				copy_monom.set_power(copy_monom.get_power() + monom.get_power());
			}
			copy.mergeMonoms();
			newPol.add(copy);
		}
		this._polynom = newPol._polynom;
		delete_zeros();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.Polynom_able#equals(myMath.Polynom_able)
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		Polynom_able this_copy = this.copy();
		this_copy.substract(p1);
		return this_copy.isZero();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.Polynom_able#isZero()
	 */
	@Override
	public boolean isZero() {
		if (_polynom.isEmpty()) {
			return true;
		}
		Iterator<Monom> it = iteretor();
		boolean found_non_zero = false;
		while (!found_non_zero && it.hasNext()) {
			Monom monom = (Monom) it.next();
			if (monom.get_coefficient() != 0) {
				found_non_zero = true;
			}
		}

		return !found_non_zero;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.Polynom_able#root(double, double, double)
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		double root = Double.NEGATIVE_INFINITY;
		for (double x = x0; x < x1; x += eps) {
			if (Math.abs(f(x)) < eps) {
				root = x;
				break;
			}
		}
		return root;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.Polynom_able#copy()
	 */
	@Override
	public Polynom_able copy() {

		List<Monom> polynmListCopy = new ArrayList<Monom>();

		for (Monom monom : this._polynom) {
			polynmListCopy.add(new Monom(monom));
		}

		Polynom_able polynomCopy = new Polynom(polynmListCopy);

		return polynomCopy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.Polynom_able#derivative()
	 */
	@Override
	public Polynom_able derivative() {
		Polynom copy = (Polynom) this.copy();

		for (Monom monom : copy._polynom) {
			monom.set_coefficient(monom.get_coefficient() * monom.get_power());
			if (monom.get_power() > 0) {
				monom.set_power(monom.get_power() - 1);
			}

		}
		copy.delete_zeros();
		return copy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.Polynom_able#area(double, double, double)
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		double area = 0;
		for (double x = x0; x < x1; x += eps) {
			area += eps * f(x);
		}
		return area;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myMath.Polynom_able#iteretor()
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return _polynom.iterator();
	}

	/**
	 * Merges components with the same power
	 */
	private void mergeMonoms() {
		List<Monom> merged = new ArrayList<Monom>();
		int max_power = -Integer.MIN_VALUE;
		for (Monom monom : _polynom) {
			if (monom.get_power() > max_power) {
				max_power = monom.get_power();
			}
		}
		double[] coeff_sums = new double[max_power + 1];
		for (int i = 0; i < coeff_sums.length; i++) {
			coeff_sums[i] = 0;
		}
		for (Monom monom : _polynom) {
			coeff_sums[monom.get_power()] += monom.get_coefficient();
		}

		for (int i = 0; i < coeff_sums.length; i++) {
			if (coeff_sums[i] != 0) {
				merged.add(new Monom(coeff_sums[i], i));
			}
		}
		_polynom = merged;
		delete_zeros();
	}
	
	private void delete_zeros() {
		List<Integer> to_delete = new ArrayList<>();
		for (int i = 0; i < _polynom.size(); i++) {
			Monom monom = _polynom.get(i);
			if (monom.get_coefficient() == 0) {
				to_delete.add(i);
			}
		}
		for (int i = 0; i < to_delete.size(); i++) {
			_polynom.remove(i);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String str = "";
		for (int i = 0; i < _polynom.size(); i++) {
			Monom monom = _polynom.get(i);
			str += monom.toString();
			if (i < _polynom.size() - 1) {
				str += "+";
			}
		}

		return str;
	}

}
