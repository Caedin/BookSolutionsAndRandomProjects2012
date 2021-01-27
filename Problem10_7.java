



// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// -----------------------------------------------


class Problem10_7  extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel(new GridLayout(3,0));
	JPanel outputPanel = new JPanel(new GridLayout(1,0));
	JLabel inputLabel = new JLabel("Input:");
	JLabel outputLabel = new JLabel("");
	JTextField inputName = new JTextField(40);
	JTextField inputValue = new JTextField(40);
	JTextField inputDate = new JTextField(40);
	final Check outputCheck = new Check();

	
	
	public Problem10_7()
	{
		buildPanel();		
		add(topPanel);
		setVisible(true);
	}
	
		
	
	@Override
	public String getDescription()
	{
		return "7. Check Writer"
		
		+"\nWrite a program that displays a simulated paycheck. The program should ask the user to enter the date, the payees name, and the amount of the check. It should then display a simulated" 
		+"\ncheck with the dollar amount spelled out.";
	}
	
	
	public void buildPanel()
	{
		
		inputName.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) 
				{
					outputPanel.removeAll();
					outputCheck.setName(inputName.getText());
					outputPanel.add(outputCheck.getPanel());
					repaint();
					revalidate();
            }

            @Override
            public void removeUpdate(DocumentEvent de) 
				{
					outputPanel.removeAll();
					outputCheck.setName(inputName.getText());
					outputPanel.add(outputCheck.getPanel());
					repaint();
					revalidate();
            }

            @Override
            public void changedUpdate(DocumentEvent de) 
				{
					outputPanel.removeAll();
					outputCheck.setName(inputName.getText());
					outputPanel.add(outputCheck.getPanel());
					repaint();
					revalidate();
            }
        });
		  
		 inputDate.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) 
				{
					outputPanel.removeAll();
					outputCheck.setDate(inputDate.getText());
					outputPanel.add(outputCheck.getPanel());
					repaint();
					revalidate();
            }

            @Override
            public void removeUpdate(DocumentEvent de) 
				{
					outputPanel.removeAll();
					outputCheck.setDate(inputDate.getText());
					outputPanel.add(outputCheck.getPanel());
					repaint();
					revalidate();
            }

            @Override
            public void changedUpdate(DocumentEvent de) 
				{
					outputPanel.removeAll();
					outputCheck.setDate(inputDate.getText());
					outputPanel.add(outputCheck.getPanel());
					repaint();
					revalidate();
            }
        });
		  
		  
		 inputValue.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) 
				{
					outputPanel.removeAll();
					outputCheck.setValue(KDUtil.moneyInputValidation(inputValue.getText()));
					outputPanel.add(outputCheck.getPanel());
					repaint();
					revalidate();
            }

            @Override
            public void removeUpdate(DocumentEvent de) 
				{
					outputPanel.removeAll();
					outputCheck.setValue(KDUtil.moneyInputValidation(inputValue.getText()));
					outputPanel.add(outputCheck.getPanel());
					repaint();
					revalidate();
            }

            @Override
            public void changedUpdate(DocumentEvent de) 
				{
					outputPanel.removeAll();
					outputCheck.setValue(KDUtil.moneyInputValidation(inputValue.getText()));
					outputPanel.add(outputCheck.getPanel());
					repaint();
					revalidate();
            }
        });
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Automated - Check Writer");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		
		JPanel temp = new JPanel();
		temp.add(KDUtil.wrap(new JLabel("Date:")));
		temp.add(KDUtil.wrap(inputDate));
		inputPanel.add(temp);
		
		temp = new JPanel();
		temp.add(KDUtil.wrap(new JLabel("Name:")));
		temp.add(KDUtil.wrap(inputName));
		inputPanel.add(temp);
		
		temp = new JPanel();
		temp.add(KDUtil.wrap(new JLabel("Value:")));
		temp.add(KDUtil.wrap(inputValue));
		inputPanel.add(temp);
		
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		
		outputPanel.add(outputCheck.getPanel());
		mainPanel.add(outputPanel, BorderLayout.SOUTH);




		topPanel.add(mainPanel);
	
	}
		
	
	
}

class Check
{
	String payee;
	String value;
	double valueMoney;
	String valueWords;
	CheckDate date;
	
	public Check(String payee, String value, String date)
	{
		this.payee=payee;
		this.value=value;
		setValue(value);
		createValueWords();
		createDate(date);
	}
	
	public Check()
	{
		payee = "";
		value= "";
		date = new CheckDate();
	}
	
	public void setDate(String date)	{createDate(date);}
	public void setName(String name)	{payee=name;}
	public void setValue(String val)
	{
		if(KDUtil.moneyInputValidation(val)!="Input Error" && KDUtil.moneyInputValidation(val).isEmpty()==false)
		{
		value = val;
		valueMoney = Double.parseDouble(KDUtil.moneyInputValidation(value));
		createValueWords();
		}
	}
	
	public void createValueWords()
	{
		valueWords = "";
		double num = valueMoney*100;
		int k = 14;
		int[] places = new int[15];
		
		while(k>=0)
		{
			places[k] = (int)(num/Math.pow(10,k));	
			num=num%Math.pow(10,k);
			k=k-1;
		}
		
			switch(14)
			{
				case(14): 
						if(places[14]>100) {valueWords = "OVERFLOW ERROR"; break;}
						if(places[14]>9) valueWords = valueWords + places[14]+" trillion ";
						else
						if(places[14]!=0) valueWords = valueWords + numToWord(places[14])+" trillion ";
				case(13): if(places[13]!=0) valueWords = valueWords + numToWord(places[13])+" hundred ";
				case(12): 
				
				if(places[12]!=0)
					{
						if(places[12]!=1)
						{
							if(places[11]==0)
							{
								valueWords = valueWords + numToWord(places[12]*10)+" billion ";
							}
							else
							valueWords = valueWords + numToWord(places[12]*10)+"-";
						}
						else
						{
							valueWords = valueWords + numToWord(places[12]*10+places[11])+" billion ";
						}
					} 


				case(11): if(places[11]!=0 && places[12]!=1) valueWords = valueWords + numToWord(places[11])+" billion ";
				else
				if(valueMoney>	1000000000)
				{
					if(valueWords.contains("billion")==false && valueWords.contains("trillion")==false) valueWords = valueWords + " billion ";
				}
				
				case(10): if(places[10]!=0) valueWords = valueWords + numToWord(places[10])+" hundred ";
				case(9): 
				
					if(places[9]!=0)
					{
						if(places[9]!=1)
						{
							if(places[8]==0)
							{
								valueWords = valueWords + numToWord(places[9]*10)+" million ";
							}
							else
							valueWords = valueWords + numToWord(places[9]*10)+"-";
						}
						else
						{
							valueWords = valueWords + numToWord(places[9]*10+places[8])+" million ";
						}
					} 

					
					
				case(8): if(places[8]!=0 && places[9]!=1) valueWords = valueWords + numToWord(places[8])+" million ";
				else
				if(valueMoney>	1000000)
				{
					if(valueWords.contains("million")==false && valueWords.contains("billion")==false && valueWords.contains("trillion")==false) valueWords = valueWords + " million ";
				}
				
				case(7): if(places[7]!=0) valueWords = valueWords + numToWord(places[7])+" hundred ";
				case(6):
					
					if(places[6]!=0)
					{
						if(places[6]!=1)
						{
							if(places[5]==0)
							{
								valueWords = valueWords + numToWord(places[6]*10)+" thousand ";
							}
							else
							valueWords = valueWords + numToWord(places[6]*10)+"-";
						}
						else
						{
							valueWords = valueWords + numToWord(places[6]*10+places[5])+" thousand ";
						}
					} 

					
					
				case(5): 
				if(places[5]!=0 && places[6]!=1) valueWords = valueWords + numToWord(places[5])+" thousand ";
				else
				if(valueMoney>	1000)
				{
					if(valueWords.contains("thousand")==false && valueWords.contains("million")==false && valueWords.contains("billion")==false && valueWords.contains("trillion")==false) valueWords = valueWords + " thousand ";
				}
				case(4): if(places[4]!=0) valueWords = valueWords + numToWord(places[4])+" hundred ";
				case(3): 
					
					if(places[3]!=0)
					{
						if(places[3]!=1)
						{
							if(places[2]==0)
							{
								valueWords = valueWords + numToWord(places[3]*10)+" dollars ";
							}
							else
							valueWords = valueWords + numToWord(places[3]*10)+"-";
						}
						else
						{
							valueWords = valueWords + numToWord(places[3]*10+places[2])+" dollars ";
						}
					} 
					
					
				case(2): 
				if(valueMoney>=2)
				{
					if(places[2]!=0 && places[3]!=1) valueWords = valueWords + numToWord(places[2])+" dollars ";
					else if(valueWords.contains("dollars")==false) valueWords = valueWords + " dollars ";
				}
				else
				if(valueMoney>=1)
				{
					if(places[2]!=0 && places[3]!=1) valueWords = valueWords + numToWord(places[2])+" dollar ";
					else if(valueWords.contains("dollar")==false) valueWords = valueWords + " dollar ";
				}
				

				
				case(1): 
				if(valueMoney>=1)
				{
					if(places[0]==0 && places[1]!=0) valueWords = valueWords + "and " +(places[1]*10)+" cents ";
				}
				else	if(places[0]==0 && places[1]!=0) valueWords = valueWords +(places[1]*10)+" cents ";
				
				case(0): 
				if(valueMoney>=1)
				{
					if(places[0]!=0) valueWords = valueWords + "and " +(places[1]*10+places[0])+" cents ";
				}
				else if(places[0]!=0) valueWords = valueWords +(places[1]*10+places[0])+" cents ";
				
				default: break;
			}
		
		valueWords = KDUtil.capitalizeWords(valueWords);
	}
	
	public String numToWord(int x)
	{
		switch(x)
		{
			case(1): return "one";
			case(2): return "two";
			case(3): return "three";
			case(4): return "four";
			case(5): return "five";
			case(6): return "six";
			case(7): return "seven";
			case(8): return "eight";
			case(9): return "nine";
			case(10): return "ten";
			case(11): return "eleven";
			case(12): return "twelve";
			case(13): return "thirteen";
			case(14): return "fourteen";
			case(15): return "fifteen";
			case(16): return "sixteen";
			case(17): return "seventeen";
			case(18): return "eighteen";
			case(19): return "nineteen";
			case(20): return "twenty";
			case(30): return "thirty";
			case(40): return "forty";
			case(50): return "fifty";
			case(60): return "sixty";
			case(70): return "seventy";
			case(80): return "eighty";
			case(90): return "ninety";
			default: return "unknown";
			
		}
	}
	
	
	public void createDate(String input)
	{
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

		
		
		date = new CheckDate(year,month,day);

		
		
	}
	
	public JPanel getPanel()
	{
		DecimalFormat money = new DecimalFormat("$#,##0.00");
		
		JPanel top = new JPanel(new BorderLayout());
		top.setBorder(BorderFactory.createTitledBorder("Check"));
		
		JPanel topRight = new JPanel(new GridLayout(0,2));
		topRight.add(KDUtil.wrap(new JLabel("Date:")));
		topRight.add(KDUtil.wrap(new JLabel(date.toString())));
		
		JPanel rightAlign = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		rightAlign.add(topRight);
		
		top.add(rightAlign, BorderLayout.NORTH);
		
		JPanel center = new JPanel(new BorderLayout());
		center.add(KDUtil.wrap(new JLabel("Pay to the Order of:")), BorderLayout.WEST);
		center.add(KDUtil.wrap(new JLabel(money.format(valueMoney))), BorderLayout.EAST);
		center.add(KDUtil.wrap(new JLabel(KDUtil.capitalizeWords(payee))), BorderLayout.CENTER);
		
		top.add(center, BorderLayout.CENTER);
		
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottom.add(KDUtil.wrap(new JLabel(valueWords)));
		top.add(bottom, BorderLayout.SOUTH);
		

		return top;
	}
	
	private class CheckDate
	{
		int year;
		int month;
		int day;
		
		public CheckDate(int year, int month, int day)
		{
			this.year=year;
			this.month=month;
			this.day=day;
		}
		
		public CheckDate()
		{
			this.year=0;
			this.month=0;
			this.day=0;
		}
		
		public String toString()
		{
			return month+"/"+day+"/"+year;
		}
	}
	
}