/* ========================================================================== */
/* PROGRAM: Simple Algorithm Trader
    AUTHOR: Megan Camp
    COURSE NUMBER: CIS 210
    COURSE SECTION NUMBER: 02
    INSTRUCTOR NAME: Dr.Tian
    PROJECT NUMBER: 8
    DUE DATE: 11/07/2017
SUMMARY

INPUT

OUTPUT

ASSUMPTIONS

/* MAIN FUNCTION */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SimpleAlgorithmTradingPlatform{
	public static void main(String[] args) throws IOException {
		Scanner keyboard= new Scanner(System.in);
		System.out.print("Enter the name of the input file: ");
		String inputFileName=keyboard.nextLine();
		File file=new File(inputFileName);
		
		
		if (file.exists()) {
			AlgorithmTrader trade=new AlgorithmTrader();
			trade.Run(inputFileName);
		}
		else {
			System.out.println("Error: File does not exist");
		}//end of outside else
		
	}
}