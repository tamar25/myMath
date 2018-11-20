package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	@Override
	public int compare(Monom arg0, Monom arg1) {
		if (arg0.get_coefficient() == arg1.get_coefficient() && arg0.get_power() == arg1.get_power()){
			return 0;
		}
		return Double.compare(arg1.get_power(), arg1.get_power());
	}
}
