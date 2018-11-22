package test.lcm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import LCM.LCM;

public class LCM_test {
	int length = 0;
	int[] test1 = {15,20,30};
	int[] test2 = {0,0,0};
	int[] test3 = {14,0,5};
	int test_number = 0;
	
	@Test
	public void test1() {
		//fail("Not yet implemented");
		assertEquals(60,test_number = LCM.caculate_LCM(test1));
	}
	
	@Test(expected = ArithmeticException.class)
	public void test_div_by_zero () {
		assertEquals(0,test_number = LCM.caculate_LCM(test2));
	}
	
	@Test(expected = ArithmeticException.class)
	public void test_one_element_is_zero() {
		assertEquals(0,test_number = LCM.caculate_LCM(test3));
	}
}
