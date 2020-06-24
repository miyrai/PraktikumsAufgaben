package de.hfu;

import java.util.Scanner;

/**
* formats input into upperCase
*/
public class App 
{
    public static void main( String[] args )
    {
    	Scanner scanner = new Scanner(System.in);
    	String eingabe = null;

		do {
			eingabe = scanner.nextLine();
		} while (eingabe.equals(""));

		System.out.println(eingabe.toUpperCase());

    }
}
