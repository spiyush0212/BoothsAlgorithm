package Booth;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BoothNet 
{
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("WELCOME TO BOOTH'S ALGORITHM FOR MULTIPLICATION OF NUMBERS" + "\n");
		System.out.println("Please enter 2 numbers in Decimal Format, to be multiplied:");
		int a = scan.nextInt();
		int b = scan.nextInt();
		
		System.out.println();
		System.out.println("The Binary Notation of the given Decimal numbers are: ");
		printBinNum(a);
		printBinNum(b);
		
		System.out.println();
		System.out.println("The result after multiplication is:");
		multiplication(a, b);
		scan.close();
	}
   
	public static void printBinNum (int n)
	{
		// This program is used to print the 2 input decimal numbers into binary and 
		// display it to the user
		
		String b = Integer.toBinaryString(n);
		System.out.println(n + " = " + b);
	}

	public static void multiplication(int x, int y)
    {
		// sets a flag for the negative numbers.
        boolean flag = false;
        
        // checks if any of number is negative.
        if ((x < 0 && y > 0) || (x > 0 && y < 0)) 
        {
            x = Math.abs(x);
            y = Math.abs(y);
            flag = true;
        }
        
        // checks if both the numbers are negative.
        if (x < 0 && y < 0) 
        {
            x = Math.abs(x);
            y = Math.abs(y);
        }

        int n = 0;
        if (x > y) 
        {
            String t = Integer.toBinaryString(x);
            n = t.length();
        } 
        else if (x < y) 
        {
            String t = Integer.toBinaryString(y);
            n = t.length();
        }
        else if (x == y) 
        {
            String t = Integer.toBinaryString(x);
            n = t.length();
        }

        // x
        String M = intToBinary(x, n + 1);
        String minusM = twosComp(M);
        
        // multiplier
        String Q = intToBinary(y, n);

        //Accumulator
        String AC = intToBinary(0, n + 1);

        String Qnp1 = "0";

        String Qn = Q;
        n++;
     
        AppendToFile("E:\\Output.txt", "Accumulator                    Multiplier(Qn)                 Qnp1                   Operation");
        AppendToFile("E:\\Output.txt", "\n");
        AppendToFile("E:\\Output.txt", "\n");
        AppendToFile("E:\\Output.txt", AC + "                                " + Qn + "                         " + Qnp1 + "                    " + "Originally");
        AppendToFile("E:\\Output.txt", "\n");
        AppendToFile("E:\\Output.txt", "\n");
        
        for ( int i = n; i>0; i--)
        {
            AppendToFile("E:\\Output.txt", "Cycle - " + (n - i));
            AppendToFile("E:\\Output.txt", "\n");

            if ((Qn.charAt(Qn.length() - 1) + Qnp1).equals("10")) 
            {
                AC = addBinary(AC, minusM);
                AppendToFile("E:\\Output.txt", AC + "                                " + Qn + "                        " + Qnp1 + "                    " + "AC = AC - M");
                AppendToFile("E:\\Output.txt", "\n");

                String values[] = Shift_Right(AC, Qn, Qnp1, n);
                AC = values[0];
                Qn = values[1];
                Qnp1 = values[2];
                AppendToFile("E:\\Output.txt", AC + "                                " + Qn + "                        " + Qnp1 + "                    " + "ASR");
                AppendToFile("E:\\Output.txt", "\n");
                AppendToFile("E:\\Output.txt", "\n");
            } 
            
            else if ((Qn.charAt(Qn.length() - 1) == '1') && Qnp1.equals("1")) 
            {
                String values[] = Shift_Right(AC, Qn, Qnp1, n);
                AC = values[0];
                Qn = values[1];
                Qnp1 = values[2];
                AppendToFile("E:\\Output.txt", AC + "                                " + Qn + "                        " + Qnp1 + "                    " + "ASR");
                AppendToFile("E:\\Output.txt", "\n");
                AppendToFile("E:\\Output.txt", "\n");
            }
            
            else if ((Qn.charAt(Qn.length() - 1) == '0') && Qnp1.equals("0")) 
            {
                String values[] = Shift_Right(AC, Qn, Qnp1, n);
                AC = values[0];
                Qn = values[1];
                Qnp1 = values[2];
                AppendToFile("E:\\Output.txt", AC + "                                " + Qn + "                        " + Qnp1 + "                    " + "ASR");
                AppendToFile("E:\\Output.txt", "\n");
                AppendToFile("E:\\Output.txt", "\n");
            } 
            
            else if ((Qn.charAt(Qn.length() - 1) + Qnp1).equals("01")) 
            {
                AC = addBinary(AC, M);
                AppendToFile("E:\\Output.txt", AC + "                                " + Qn + "                       " + Qnp1 + "                    " + "AC = AC + M");
                AppendToFile("E:\\Output.txt", "\n");

                String values[] = Shift_Right(AC, Qn, Qnp1, n);
                AC = values[0];
                Qn = values[1];
                Qnp1 = values[2];
                AppendToFile("E:\\Output.txt", AC + "                                " + Qn + "                        " + Qnp1 + "                    " + "ASR");
                AppendToFile("E:\\Output.txt", "\n");
                AppendToFile("E:\\Output.txt", "\n");
            }
        }

        AppendToFile("E:\\Output.txt", "\n");
        AppendToFile("E:\\Output.txt", "Multiplication Result:");
        AppendToFile("E:\\Output.txt", "\n");
        
        String result = AC.concat(Qn);
        if (flag) 
        {
            System.out.println("In Binary Form  : " + twosComp(result));
            System.out.println("In Decimal Form : " + "-" + binToint(result));
            AppendToFile("E:\\Output.txt", "In Binary Form : " + twosComp(result));
            AppendToFile("E:\\Output.txt", "\nIn Decimal Form : " + "-" + binToint(result));

        } 
        else 
        {
            System.out.println("In Binary Form  : " + result);
            System.out.println("In Decimal Form : " + binToint(result));
            AppendToFile("E:\\Output.txt", "In Binary Form : " + result);
            AppendToFile("E:\\Output.txt", "\nIn Decimal Form : " + binToint(result));

        }

        AppendToFile("E:\\Output.txt", "\n");
        AppendToFile("E:\\Output.txt", "\n");
        AppendToFile("E:\\Output.txt", "\n");
        AppendToFile("E:\\Output.txt", "\n");
        AppendToFile("E:\\Output.txt", "\n");
    }
	
	public static double binToint(String binary)
    {
		// This converts the given binary number into an Integer Decimal and 
		// returns the decimal as "double"
		
        double count =0;
        int i=0;
        
        while ( i<binary.length())
        {
            if ( binary.charAt(binary.length()-1-i ) =='1')
                count = count + Math.pow(2,i);
            i++;
        }
        return count;
    }

    public static String addBinary(String a, String b)
    {
        String result = "";

        int s = 0;
        for (int i = a.length()-1, j = b.length()-1; i >= 0 || j >= 0 || s == 1 ; i--, j--)
        {
            s = s + ((i >= 0)? a.charAt(i) - '0': 0);
            s = s + ((j >= 0)? b.charAt(j) - '0': 0);
            result = (char)(s % 2 + '0') + result;
            s /= 2;
        }

        return result;
    }

    public static void AppendToFile(String fileName,  String s)
    {
        try 
        {
            // This opens the given file in append mode to write and produce an output to the file .
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
            bw.write(s);
            bw.close();
        }
        catch (IOException exp) 
        {
        	// Displays an error messgae whenever any error is encountered during the operation of this whole program
            System.out.println("ERROR OCCURED" + exp);
        }
    }

    public static String twosComp(String str)
    {
        int flag = 0;
        // Creates a new StringBuffer to hold the existing string (str)
        StringBuffer s = new StringBuffer(str);
        int i = str.length()-1;
        
        // Checks each element of the stringBuffer (s) until the EOL and 
        // perfroms 2s Complement
        while(i>=0)
        {
            if (flag == 1) 
            {
                if (str.charAt(i) == '0') 
                    s.replace(i, i + 1, "1");
                 
                else 
                    s.replace(i, i + 1, "0");
            }

            if (s.charAt(i) == '1' && flag == 0) 
                flag = 1;
            i--;
        }
        return s.toString();
    }

    public static String onesComp(String str)
    {
    	// Creates a new StringBuffer to hold the existing string (str)
        StringBuffer s = new StringBuffer(str);
        
        // Checks each element of the stringBuffer (s) until the EOL and 
        // replaces 0s with 1 and 1s with 0 to perfrom 1s Complement
        int i =  str.length()-1;
        while (i>=0)
        {
            if (str.charAt(i) == '0') 
                s.replace(i, i + 1, "1");
            else 
                s.replace(i, i + 1, "0");
            i--;          
        }
        
        // Converts the StringBuffer back to a String and then returns it 
        return s.toString();
    }

    public static String intToBinary(int a,int bits) 
    {
    	// Converts the given integer a into a binary number and then
    	// adds leading 0s to it until the length is eqaul to no. of bits and then
    	// returns the final number of binary as a string value
    	
        String t = Integer.toBinaryString(a);
        
        for ( ; t.length() != bits; )
            t = "0" + t;
    
        return t;
    }

    public static String[] Shift_Right(String AC, String multiplier, String qm1, int count) 
    {
        qm1 = "" + multiplier.charAt(multiplier.length() - 1);

        int ac = Integer.parseInt(AC, 2);
        ac = ac >> 1;
        String ac1 = intToBinary(ac, count);
        StringBuffer AC1 = new StringBuffer(ac1);

        int m = Integer.parseInt(multiplier, 2);
        m = m >> 1;
        String m1 = intToBinary(m, count);
        StringBuffer multiplier1 = new StringBuffer(m1);

        multiplier1.replace(0, 1, "" + AC.charAt(AC.length() - 1));

        if (AC.length() > 1) {
            AC1.replace(0, 1, "" + AC1.charAt(1));
        }

        String r[] = {AC1.toString(), multiplier1.toString(), qm1};
        return r;

    }

}