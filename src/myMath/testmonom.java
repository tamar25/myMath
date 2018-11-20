package myMath;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testmonom {
/*
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	*/

	@Test

		public void testfx() {
			Monom m1=new Monom(3,4);
			double actual=m1.f(2.3);
			double expected=3*(Math.pow(2.3, 4));
			if(expected!=actual) {
				fail();
			}
	}
	

}
