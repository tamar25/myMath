package tirgul;

import java.util.Arrays;

public class Parser {

	public static void main(String[] args) {
		//String polynom_string = "2X^2+5 +x -6 *X^3+ 2x^2+5X ^3+9.7X";
		
		String polynom_string = "2X + 5x +3";

		polynom_string = polynom_string.replaceAll("\\*", "");
		polynom_string = polynom_string.trim();
		System.out.println(polynom_string);

		String[] split = polynom_string.split("[xX]");
		System.out.println(Arrays.toString(split));

		for (int i = 0; i < split.length; i++) {
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
					}
				}
			}
			String curr_power = "0";
			if (i == split.length - 1) {
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
		System.out.println(curr_coef+","+curr_power);	
		
		//TODO : cast to int and double check non-negative power

		}
	}
}
