
package myMath;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and a is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author Boaz
 *
 */
public class Monom implements function {

	public Monom(double a, int b) {
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	// ***************** add your code below **********************

	public double get_coefficient() {
		return _coefficient;
	}

	public int get_power() {
		return _power;
	}
	
	public void set_coefficient(double a) {
		this._coefficient = a;
	}

	public void set_power(int p) {
		this._power = p;
	}
	
	

	@Override
	public String toString() {
		return " "+ _coefficient + "X^" + _power + " ";
	}

	@Override
	public double f(double x) {

		return _coefficient * Math.pow(x, _power);
	}

	// ****************** Private Methods and Data *****************


	private double _coefficient; //
	private int _power;

}
