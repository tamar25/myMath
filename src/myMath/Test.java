package myMath;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.axis.NumberAxis;

public class Test {
	public static void main(String[] args) {
		
		
		
		
//		Polynom polynom = new Polynom("1+X^3+2x");
//		Polynom polynom2 = new Polynom("3X^2 + 5*x");
//
//		double area = polynom.area(0, 5, 0.001);
//		System.out.println(area);
//		double root = polynom.root(-0.1, 0.1, 0.001);
//		System.out.println(root);
//		
//		Polynom der = (Polynom) polynom.derivative();
//		
//		System.out.println(polynom);
//		System.out.println(polynom2);
//
//		System.out.println(der);
//		polynom.add(polynom2);
//		System.out.println(polynom);
		Polynom polynom = new Polynom("x^2");
		
		polynom.plot(-10, 10);
		
//	    final double[][] data = new double[11][2];
//	    for (int i = 0; i < 11; i++) {
//	    	data[i][0]= i;
//	    	data[i][1]= polynom.f(i);
//	    }
//
//	    final XYSeries series = new XYSeries("Test");
//	    for (int x = 0; x < 11; x++) {
//	        series.add(data[x][0], data[x][1]);
//	    }
//	    final XYDataset ds = new XYSeriesCollection(series);
//		try {
//			drawChart("test.png",1000,100, ds);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//test1();

	}
	
	public static void drawChart(String filename, int width, int height, XYDataset ds) throws IOException {
		  // Create plot
		  NumberAxis xAxis = new NumberAxis("xAxisLabel");
		  NumberAxis yAxis = new NumberAxis("yAxisLabel");
		  XYSplineRenderer renderer = new XYSplineRenderer();
		  XYPlot plot = new XYPlot(ds, xAxis, yAxis, renderer);
		  plot.setBackgroundPaint(Color.lightGray);
		  plot.setDomainGridlinePaint(Color.white);
		  plot.setRangeGridlinePaint(Color.white);

		  // Create chart
		  JFreeChart chart = new JFreeChart("chartTitle", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
		  ChartUtilities.applyCurrentTheme(chart);
		  ChartPanel chartPanel = new ChartPanel(chart, false);

		  // Draw png
		  BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		  Graphics graphics = bi.getGraphics();
		  chartPanel.setBounds(0, 0, width, height);
		  chartPanel.paint(graphics);
		  ImageIO.write(bi, "png", new File(filename));
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
