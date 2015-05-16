

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// -----------------------------------------------


class KDUtil				// This program provides useful methods I wrote originally as excercises, but found them to be useful for the other problems as I went along.
{
	public static JPanel wrap(Container c)
	{
		JPanel wrapper = new JPanel();
		wrapper.add(c);
		return wrapper;
	}
	
	public static JPanel wrap(JComponent c)
	{
		JPanel wrapper = new JPanel();
		wrapper.add(c);
		return wrapper;
	}
	
	public static String doubleInputValidation(String input)
	{
		boolean inputValidation = true;
		char[] inputArray = input.toCharArray();
								
		String newInput = "";
								
		for(int i = 0; i<input.length(); i++)
		{
			switch (inputArray[i])
				{
					case '0': newInput = newInput+ inputArray[i]; break;
					case '1': newInput = newInput+ inputArray[i]; break;
					case '2': newInput = newInput+ inputArray[i]; break;
					case '3': newInput = newInput+ inputArray[i]; break;
					case '4': newInput = newInput+ inputArray[i]; break;
					case '5': newInput = newInput+ inputArray[i]; break;
					case '6': newInput = newInput+ inputArray[i]; break;
					case '7': newInput = newInput+ inputArray[i]; break;
					case '8': newInput = newInput+ inputArray[i]; break;
					case '9': newInput = newInput+ inputArray[i]; break;
					case '.': newInput = newInput+ inputArray[i]; break;
					case '-': if(i==0) {break;} else {inputValidation = false; break;}
					default: inputValidation = false; break;
				}
			
		}
		if(inputValidation) return newInput; else return "Input Error";
	}
	
	public static String moneyInputValidation(String input)
	{
		boolean inputValidation = true;
		char[] inputArray = input.toCharArray();
								
		String newInput = "";
								
		for(int i = 0; i<input.length(); i++)
		{
			switch (inputArray[i])
				{
					case '0': newInput = newInput+ inputArray[i]; break;
					case '1': newInput = newInput+ inputArray[i]; break;
					case '2': newInput = newInput+ inputArray[i]; break;
					case '3': newInput = newInput+ inputArray[i]; break;
					case '4': newInput = newInput+ inputArray[i]; break;
					case '5': newInput = newInput+ inputArray[i]; break;
					case '6': newInput = newInput+ inputArray[i]; break;
					case '7': newInput = newInput+ inputArray[i]; break;
					case '8': newInput = newInput+ inputArray[i]; break;
					case '9': newInput = newInput+ inputArray[i]; break;
					case '.': newInput = newInput+ inputArray[i]; break;
					case '-': if(i==0) {break;} else {inputValidation = false; break;}
					case '$': break; 	
					case ',': break; 						
					default: inputValidation = false; break;
				}
			
		}
		if(inputValidation) return newInput; else return "Input Error";
	}

	
	public static String intInputValidation(String input)
	{
		boolean inputValidation = true;
		char[] inputArray = input.toCharArray();
								
		String newInput = "";
								
		for(int i = 0; i<input.length(); i++)
		{
			switch (inputArray[i])
				{
					case '0': newInput = newInput+ inputArray[i]; break;
					case '1': newInput = newInput+ inputArray[i]; break;
					case '2': newInput = newInput+ inputArray[i]; break;
					case '3': newInput = newInput+ inputArray[i]; break;
					case '4': newInput = newInput+ inputArray[i]; break;
					case '5': newInput = newInput+ inputArray[i]; break;
					case '6': newInput = newInput+ inputArray[i]; break;
					case '7': newInput = newInput+ inputArray[i]; break;
					case '8': newInput = newInput+ inputArray[i]; break;
					case '9': newInput = newInput+ inputArray[i]; break;
					case '-': if(i==0) {break;} else {inputValidation = false; break;}
					default: inputValidation = false; break;
				}
			
		}
		if(inputValidation) return newInput; else return "Input Error";
	}
	
	public static String capitalizeWords(String input)
	{
		char[] chars = new char[input.length()];
		boolean capitalizeNext = false;
		
		String output = "";
		
		for(int i = 0; i<chars.length; i++)
			chars[i] = input.charAt(i);
			
		for(int i = 0; i<chars.length; i++)
		{
			if(i==0) if((int)chars[i]>=97) chars[i] = Character.toString((char)((int)(chars[i])-32)).charAt(0);
			
			if(chars[i] != ' ' && capitalizeNext==true )
			{
				if((int)chars[i]>=97 && (int)chars[i]<=122) 
				{
					chars[i] = Character.toString((char)((int)(chars[i])-32)).charAt(0);
					capitalizeNext = false;
				}
				
				if((int)chars[i]>=65 && (int)chars[i]<=90) capitalizeNext = false;
			}
			
			if(chars[i] == '.' || chars[i] == '?' || chars[i] == '!') capitalizeNext = true;
		}
		
		for(int i = 0; i<chars.length; i++)
			output = output + chars[i];
			
		return output;
	}
	
	public static Date createDate(String input)				// Returns a date, accepts a string as input.   Written originally for problem 10.7.
	{
		try{
		if(input.isEmpty())
			return new Date();
		}catch(NullPointerException e){return new Date();}
		
		int month = 0;
		int day = 0;
		int year = 0;
				
		char[] inputArray = new char[input.length()];
		for(int i = 0; i<input.length(); i++)
		{
			inputArray[i] = input.charAt(i);
		}
		
		int place = 0;
		String[] strings = new String[inputArray.length+5];
		
		for(int i = 0; i<inputArray.length+5; i++)
		{
			strings[i] = "";
		}
		
		for(int i = 0; i<inputArray.length; i++)
		{
			switch(inputArray[i])
			{
				case '0': strings[place] = strings[place]+inputArray[i]; break;
				case '1': strings[place] = strings[place]+inputArray[i]; break;
				case '2': strings[place] = strings[place]+inputArray[i]; break;
				case '3': strings[place] = strings[place]+inputArray[i]; break;
				case '4': strings[place] = strings[place]+inputArray[i]; break;
				case '5': strings[place] = strings[place]+inputArray[i]; break;
				case '6': strings[place] = strings[place]+inputArray[i]; break;
				case '7': strings[place] = strings[place]+inputArray[i]; break;
				case '8': strings[place] = strings[place]+inputArray[i]; break;
				case '9': strings[place] = strings[place]+inputArray[i]; break;
				default: place++; break;
			}		
				
		}
		if(input.contains("January") || input.contains("january") || input.contains("JANUARY") || input.contains("jan") || input.contains("Jan") || input.contains("JAN")) month = 1;
		if(input.contains("February") || input.contains("february")|| input.contains("FEBRUARY") || input.contains("feb") || input.contains("Feb") || input.contains("FEB")) month = 2;
		if(input.contains("March") || input.contains("march")|| input.contains("MARCH") || input.contains("mar") || input.contains("Mar") || input.contains("MAR")) month = 3;
		if(input.contains("April") || input.contains("april")|| input.contains("APRIL") || input.contains("apr") || input.contains("Apr") || input.contains("APR")) month = 4;
		if(input.contains("May") || input.contains("may")|| input.contains("MAY") || input.contains("may") || input.contains("May") || input.contains("MAY")) month = 5;
		if(input.contains("June") || input.contains("june")|| input.contains("JUNE") || input.contains("jun") || input.contains("Jun") || input.contains("JUN")) month = 6;
		if(input.contains("July") || input.contains("july")|| input.contains("JULY") || input.contains("jul") || input.contains("Jul") || input.contains("JUL")) month = 7;
		if(input.contains("August") || input.contains("august")|| input.contains("AUGUST") || input.contains("aug") || input.contains("Aug") || input.contains("AUG")) month = 8;
		if(input.contains("September") || input.contains("september")|| input.contains("SEPTEMBER") || input.contains("sep") || input.contains("Sep") || input.contains("SEP")) month = 9;
		if(input.contains("October") || input.contains("october")|| input.contains("OCTOBER") || input.contains("oct") || input.contains("Oct") || input.contains("OCT")) month = 10;
		if(input.contains("November") || input.contains("november")|| input.contains("NOVEMBER") || input.contains("nov") || input.contains("Nov") || input.contains("NOV")) month = 11;
		if(input.contains("December") || input.contains("december")|| input.contains("DECEMBER") || input.contains("dec") || input.contains("Dec") || input.contains("DEC")) month = 12;
		
		int num = 0;
		String m = "";
		String d = "";
		String y = "";
		
		for(int i = 0; i<=place; i++)
		{
			if(strings[i].isEmpty()==false &&strings[i].length()<6)
			{
				//System.out.print(strings[i]);
				num = Integer.parseInt(strings[i]);
				
				if(m.isEmpty() && num<=12 && month==0) m = strings[i];
				
				if(d.isEmpty() && num<=12 && ((m==strings[i])==false)) d = strings[i];
				
				if(num>12 && num<=31) d = strings[i];
				
				if(num>31) y = strings[i];
			}
		}
		if(m.isEmpty()==false)
		month = Integer.parseInt(m);
		if(d.isEmpty()==false)
		day = Integer.parseInt(d);
		if(y.isEmpty()==false)
		year = Integer.parseInt(y);

		
		
		Date date = new Date(year-1900,month-1,day);
		return date;

		
		
	}
	
	//Attempts to open and close a file, if it fails it returns an error frame, if it succeeds it returns the original frame, without changes.
	public static JPanel fileHandleTest(JPanel topPanel)
	{
		try
		{
		try{RandomAccessFile test = new RandomAccessFile("test.txt","rw");test.close();}catch(IOException e){}

		}
		catch(java.security.AccessControlException k)
		{
			topPanel.removeAll();
			topPanel.add(new JLabel("ERROR: Applets cannot read or write files"));
		}
		
		return topPanel;
	}




}