package LCM;

public class LCM {
	
	public static int GCD(int a, int b) {
		if ((a == 0 && b == 0) || (a == 0) || (b == 0)) {
			throw new ArithmeticException("Incorect data");
		}
		int gcd = 0;
		for (int i = 1; i <= a && i <= b; i++) {
			// Checks if i is factor of both integers
			if (a % i == 0 && b % i == 0)
				gcd = i;
		}
		return gcd;
	}

	public static int caculate_LCM(int[] times) {
		int mult = times[0];
		int result = times[0];
		int gsd = 0;
		int length = times.length;
		int flag = 0;
		int flag_for_zero = 0;
		for(int i = 0; i<length; i++) {
			if(times[i]==0) {
				flag_for_zero++;
			}
		}
		if(flag_for_zero == length) {
			throw new ArithmeticException("Incorect data");
		}
		for (int i = 0; i < length; i++) {
			if (times[i] == 0) {
				flag++;
			}
		}
		boolean flg = true;
		if (flag == length) {
			flg = false;
		}
		if (length == 0) {
			throw new ArithmeticException("Incorect data");
		}
		if (flg) {
			for (int i = 0; i < length; i++) {
				mult *= times[i];
				gsd = LCM.GCD(result, times[i]);
				result = mult / gsd;
				mult = result;
				
			}
			if (gsd == 0) {
				throw new ArithmeticException("Division by zero");
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		// TODO Remove this
		int[] arg = {15,20,30};
		int result = 0;
		result = LCM.caculate_LCM(arg);
		System.out.println(result);
	}
}