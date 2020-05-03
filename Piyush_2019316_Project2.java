import java.util.*;

public class Piyush_2019316_Project2 
{
	public static void main(String[] args) 
	{
		
		// The execution of the program begins with main(). 
		// This is the first function to be executed when the assembler is loaded.
		
		Scanner scn = new Scanner(System.in);
		System.out.println("Please enter 2 numbers in Decimal Format, to be multiplied:");
		
		int a = scn.nextInt();
		System.out.println("first input is : " + a);
		
		int b = scn.nextInt();
		System.out.println("second input is : " + b);
		
		System.out.println();
		System.out.println("The Binary Notation of the given Decimal numbers are: ");
		printBinNum(a);
		printBinNum(b);
		
		System.out.println();
		System.out.println("The result after multiplication is:");
		multiplication(a, b);
		scn.close();
	}

	public static void printBinNum(int n) 
	{
		String b = Integer.toBinaryString(n);
		System.out.println(n + " = " + b);
	}

	public static void multiplication(int x, int y)
	{
		boolean flag = false;
		if (x < 0) {
			x = -x;
			flag = !flag;
		}
		if (y < 0) {
			y = -y;
			flag = !flag;
		}
		
		int n = 0;
		if (x >= y) {
			String t = Integer.toBinaryString(x);
			n = t.length();
		} else {
			String t = Integer.toBinaryString(y);
			n = t.length();
		}
		
		String K = intToBinary(x, n + 1);
		String negativeK = twosComp(K);
		String Q = intToBinary(y, n);
		String AC = intToBinary(0, n + 1);
		String Qnp1 = "0";
		String Qn = Q;
		n++;

		for (int i = n; i > 0; i--) 
		{
			if ((Qn.charAt(Qn.length() - 1) + Qnp1).equals("10")) {
				AC = addBinary(AC, negativeK);
				String values[] = Shift_Right(AC, Qn, Qnp1, n);
				AC = values[0];
				Qn = values[1];
				Qnp1 = values[2];
			}

			else if ((Qn.charAt(Qn.length() - 1) == '1') && Qnp1.equals("1")) {
				String values[] = Shift_Right(AC, Qn, Qnp1, n);
				AC = values[0];
				Qn = values[1];
				Qnp1 = values[2];
			}

			else if ((Qn.charAt(Qn.length() - 1) == '0') && Qnp1.equals("0")) {
				String values[] = Shift_Right(AC, Qn, Qnp1, n);
				AC = values[0];
				Qn = values[1];
				Qnp1 = values[2];
			}

			else if ((Qn.charAt(Qn.length() - 1) + Qnp1).equals("01")) {
				AC = addBinary(AC, K);
				String values[] = Shift_Right(AC, Qn, Qnp1, n);
				AC = values[0];
				Qn = values[1];
				Qnp1 = values[2];
			}
		}
		
		System.out.println("MULTIPLIED RESULT : ");
		String result = AC.concat(Qn);
		
		if (!flag) 
		{
			System.out.println("Binary  Form Result : " + result);
			System.out.println("Decimal Form Result : " + binToint(result));
		} 
		else 
		{
			System.out.println("Decimal Form Result : -" + binToint(result));
			System.out.println("Binary  Form Result : "  + BinToInt(result));
		}
		
	}

	public static double binToint(String binary) 
	{
		double count = 0;
		int i = 0;

		while (i < binary.length()) 
		{
			if (binary.charAt(binary.length() - 1 - i) == '1')
				count = count + Math.pow(2, i);
			i++;
		}
		return count;
	}

	public static String addBinary(String a, String b) 
	{
		String result = "";
		int s = 0;
		
		for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0 || s == 1; i--, j--) 
		{
			s = s + ((i >= 0) ? a.charAt(i) - '0' : 0);
			s = s + ((j >= 0) ? b.charAt(j) - '0' : 0);
			result = (char) (s % 2 + '0') + result;
			s /= 2;
		}
		return result;
	}
	
	public static String twosComp(String str) 
	{
		
		// Creates a new StringBuffer to hold the existing string (str).        
		// Checks each element of the stringBuffer (s) until the EOL and performs 2s Complement

		StringBuffer s = new StringBuffer(str);
		int n = s.length();
		int i;
		
		for (i = n - 1; i >= 0; i--) 
		{
			if (s.charAt(i) == '1') 
			{
				break;
			}
		}
		
		if (i == -1) 
		{
			return "1" + s;
		}
		
		for (int k = i - 1; k >= 0; k--) 
		{
			int start = k;
			int end = k + 1;
			if (!(s.charAt(start) == '1')) 
			{
				s.replace(start, end, "1");
			} 
			else 
			{
				s.replace(start, end, "0");
			}
		}
		
		String finalString = s.toString();
		// System.out.println(finalString);
		return finalString;
	}

	public static String onesComp(String str) 
	{
		
	//	Creates a new int to hold the string (str)
	//	Checks each element of the int (a) until the EOL and 
	//	replaces 0s with 1 and 1s with 0 to perform 1s Complement
	//	Converts the int back to a String and then returns it 
		       
		int a = Integer.parseInt(str);
		int n = (int) (Math.floor(Math.log(a) / Math.log(2))) + 1;
		int res = ((1 << n) - 1) ^ n;
		String s = Integer.toString(res);
		return s;
	}
	
	public static String BinToInt(String str) {
		
		// Converts the given binary number into an int string and
		// adds leading zeroes to it, if needed
		// returns the interger
		
		StringBuffer s = new StringBuffer(str);
		int n = s.length();
		int i;
		
		for (i = n - 1; i >= 0; i--) 
		{
			if (s.charAt(i) == '1') 
			{
				break;
			}
		}
		
		if (i == -1) 
		{
			return "1" + s;
		}
		
		for (int k = i - 1; k >= 0; k--) 
		{
			int start = k;
			int end = k + 1;
			if (!(s.charAt(start) == '1')) 
			{
				s.replace(start, end, "1");
			} 
			else 
			{
				s.replace(start, end, "0");
			}
		}
		
		String finalString = s.toString();
		// System.out.println(finalString);
		return finalString;
	}

	public static String intToBinary(int a, int bits) 
	{
	//	Converts the given integer a into a binary number and then
	//	adds leading 0s to it until the length is eqaul to no. of bits and 
	//	then returns the final number of binary as a string value

		String t = Integer.toBinaryString(a);

		for (; t.length() != bits;)
			t = "0" + t;
		// System.out.println(t);
		return t;
	}

	public static String[] Shift_Right(String AC, String multiplier, String qm1, int count) 
	{
		int indexing = multiplier.length() - 1;
		qm1 = Character.toString(multiplier.charAt(indexing));
		
		int ac = Integer.parseInt(AC, 2) >> 1;
		StringBuffer AC1 = new StringBuffer(intToBinary(ac, count));
		
		int m = Integer.parseInt(multiplier, 2) >> 1;
		StringBuffer multiplier1 = new StringBuffer(intToBinary(m, count));
		
		int indexing2 = AC.length() - 1;
		multiplier1.replace(0, 1, Character.toString(AC.charAt(indexing2)));
		
		if (AC.length() > 1) 
			AC1.replace(0, 1, Character.toString(AC1.charAt(1)));
			
		String[] arr = { AC1.toString(), multiplier1.toString(), qm1 };
		return arr;
	}
}