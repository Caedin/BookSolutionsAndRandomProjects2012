


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


// ---------- Main Class -------------------------
public class Problem13_7 extends PaintPanel
{
	JLabel label = new JLabel();
	ArrayList<CashRegister> registers = new ArrayList();
	
	JRadioButtonMenuItem regular = new JRadioButtonMenuItem("Regular: $0");
	JRadioButtonMenuItem folding = new JRadioButtonMenuItem("Folding: $10");
	JRadioButtonMenuItem roman = new JRadioButtonMenuItem("Roman: $15");
	JRadioButtonMenuItem size1 = new JRadioButtonMenuItem("25 inches wide: $0");
	JRadioButtonMenuItem size2 = new JRadioButtonMenuItem("27 inches wide: $2");
	JRadioButtonMenuItem size3 = new JRadioButtonMenuItem("32 inches wide: $4");
	JRadioButtonMenuItem size4 = new JRadioButtonMenuItem("40 inches wide: $6");
	JRadioButtonMenuItem natural = new JRadioButtonMenuItem("Natural: $5");
	JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Blue: $0");
	JRadioButtonMenuItem teal = new JRadioButtonMenuItem("Teal: $0");
	JRadioButtonMenuItem red = new JRadioButtonMenuItem("Red: $0");
	JRadioButtonMenuItem green = new JRadioButtonMenuItem("Green: $0");

	JTextArea output = new JTextArea(20,40);
	
	public Problem13_7()
	{

		setLayout(new BorderLayout());
		add(KDUtil.wrap(topPanel()), BorderLayout.NORTH);
		add(KDUtil.wrap(centerPanel()), BorderLayout.CENTER);
		add(KDUtil.wrap(bottomPanel()), BorderLayout.SOUTH);

	}
	
	public JPanel topPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		JLabel header = new JLabel("Shade Designer");
		
		JMenuBar menuBar = new JMenuBar();
		JMenu styles = new JMenu("Styles");
			 
		ButtonGroup stylesGroup = new ButtonGroup();
		stylesGroup.add(regular);
		stylesGroup.add(folding);
		stylesGroup.add(roman);
		
		styles.add(regular);
		styles.add(folding);
		styles.add(roman);
		
			
			
		 JMenu sizes = new JMenu("Sizes");
			 
			 
		ButtonGroup sizeGroups = new ButtonGroup();
		
		sizeGroups.add(size1);
		sizeGroups.add(size2);
		sizeGroups.add(size3);
		sizeGroups.add(size4);
		
		sizes.add(size1);
		sizes.add(size2);
		sizes.add(size3);
		sizes.add(size4);
			
			
		 JMenu colors = new JMenu("Colors");
			 
		ButtonGroup colorGroups = new ButtonGroup();
			 
		
		colorGroups.add(natural);
		colorGroups.add(blue);
		colorGroups.add(teal);
		colorGroups.add(red);
		colorGroups.add(green);
		
		
		colors.add(natural);
		colors.add(blue);
		colors.add(teal);
		colors.add(red);
		colors.add(green);
		
		menuBar.add(styles);
		menuBar.add(sizes);
		menuBar.add(colors);
		
			
		topPanel.add(KDUtil.wrap(header), BorderLayout.NORTH);
		topPanel.add(menuBar, BorderLayout.SOUTH);			
		
	
		return topPanel;
	}
	
	


		
	
	public JPanel bottomPanel()
	{
		JPanel centerPanel = new JPanel(new BorderLayout());
		label = new JLabel("Receipt");
		centerPanel.add(KDUtil.wrap(label), BorderLayout.NORTH);
		
		JScrollPane scroll = new JScrollPane(output);
		centerPanel.add(KDUtil.wrap(scroll), BorderLayout.CENTER);

		
		return centerPanel;
	}
	
	public void update()
	{
			CashRegister register = new CashRegister();
			RetailItem item = new RetailItem();
			
			item = new RetailItem(50, "Shade\t");
			register = new CashRegister(item, 1);
			registers.add(register);
			
			if(regular.isSelected()==true)	
			{
				item = new RetailItem(0, "     Regular\t");
				register = new CashRegister(item, 1);
				registers.add(register);
			}else
			if(folding.isSelected()==true)	
			{
				item = new RetailItem(10, "     Folding\t");
				register = new CashRegister(item, 1);
				registers.add(register);
			}else
			if(roman.isSelected()==true)	
			{
				item = new RetailItem(15, "     Roman\t");
				register = new CashRegister(item, 1);
				registers.add(register);
			}
			
			if(size1.isSelected()==true)	
			{
				item = new RetailItem(0, "     Size - 25\"\t");
				register = new CashRegister(item, 1);
				registers.add(register);
			}else
			if(size2.isSelected()==true)	
			{
				item = new RetailItem(2, "     Size - 27\"\t");
				register = new CashRegister(item, 1);
				registers.add(register);
			}else
			if(size3.isSelected()==true)	
			{
				item = new RetailItem(4, "     Size - 32\"\t");
				register = new CashRegister(item, 1);
				registers.add(register);
			} else
			if(size4.isSelected()==true)	
			{
				item = new RetailItem(6, "     Size - 40\"\t");
				register = new CashRegister(item, 1);
				registers.add(register);
			}
			
			if(natural.isSelected()==true)	
			{
				item = new RetailItem(5, "     Color - Natural");
				register = new CashRegister(item, 1);
				registers.add(register);
			}else
			if(blue.isSelected()==true)	
			{
				item = new RetailItem(0, "     Color - Blue\t");
				register = new CashRegister(item, 1);
				registers.add(register);
			}else
			if(teal.isSelected()==true)	
			{
				item = new RetailItem(0, "     Color - Teal\t");
				register = new CashRegister(item, 1);
				registers.add(register);
			}else
			if(red.isSelected()==true)	
			{
				item = new RetailItem(0, "     Color - Red\t");
				register = new CashRegister(item, 1);
				registers.add(register);
			} else
			if(green.isSelected()==true)	
			{
				item = new RetailItem(0, "     Color - Green\t");
				register = new CashRegister(item, 1);
				registers.add(register);
			}

			

	}
	
	public JPanel centerPanel()
	{
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		JPanel buttonWrapper = new JPanel();

		
		JButton purchase = new JButton("Add");
		buttonWrapper.add(KDUtil.wrap(purchase));
		
		purchase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				update();
				
				DecimalFormat format = new DecimalFormat("$0.00");
				output.setText("");
				double subTotal = 0;
				double tax = 0;
				double total = 0;
				
				for(int i = 0; i<registers.size(); i++)
				{
					try
					{
						try
						{
							output.append(registers.get(i).getItem().getName()+"\t"+registers.get(i).getItem().getPriceString()+" x "+registers.get(i).getNumber()+" = "+format.format(registers.get(i).getSubTotal())+"\n");
							subTotal = subTotal + registers.get(i).getSubTotal();
							tax = tax + registers.get(i).getTax();
							total = total + registers.get(i).getTotal();
						}
						catch(NegativePrice e)
						{
							output.append(e.getMessage()+"\n");
						}
					}
					catch(NegativeQuantity e)
					{
						output.append(e.getMessage()+"\n");
					}
				
					
				}
					
				output.append("\n\t\tSubtotal:\t"+format.format(subTotal)+"\n");
				output.append("\t\tTax:\t"+format.format(tax)+"\n");
				output.append("\t\tTotal:\t"+format.format(total)+"\n");
		
			}
			
			private double parseMoney(String input)
			{
				boolean inputValidation = true;
				String newInput = "";
						
				if(input.isEmpty()==false)
				{
					char[] inputArray = input.toCharArray();
											
											
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
							case '$': break;
							default: inputValidation = false; break;
						}
			
					}
				}
				if(inputValidation && newInput.isEmpty()==false) return Double.parseDouble(newInput); else return -1;
			}
			
			
		});
		
		JButton clear = new JButton("Void");
		
		buttonWrapper.add(KDUtil.wrap(clear));
		
		clear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				registers.clear();

				output.setText("");
			}			
		});
		
		centerPanel.add(KDUtil.wrap(buttonWrapper), BorderLayout.SOUTH);
		return centerPanel;
	}


	
	

	@Override
	public String getDescription()
	{
		return "7. Shade Designer"
+"\n\nA custom window shade designer charges a base fee of $50 per shade. In addition," 
+"\ncharges are added for certain styles, sizes, and colors as follows:"
 
+"\n\nStyles:"
+"\nRegular shades: Add $0"
+"\nFolding shades: Add $10"
+"\nRoman shades: Add $15"
 
+"\n\nSizes:"
+"\n25 inches wide: Add $0"
+"\n27 inches wide: Add $2"
+"\n32 inches wide: Add $4"
+"\n40 inches wide: Add $6"
 
+"\n\nColors:"
+"\nNatural: Add $5"
+"\nBlue: Add $0"
+"\nTeal: Add $0"
+"\nRed: Add $0"
+"\nGreen: Add $0"
 
+"\n\nCreate an application that allows the user to select the style, size, color,"
+"\nand number of shades from lists or combo boxes. The total charges should be "
+"\ndisplayed.";
	}
		


private class CashRegister
{
	RetailItem item;
	int number;
	double tax = 0.06;
	DecimalFormat format = new DecimalFormat("$0.00");
	

	public CashRegister(RetailItem i, int num)
	{
		item=i;
		number=num;
	}
	
	public CashRegister()
	{
		item= new RetailItem();
		number=0;
	}
	
	public String getNumber()
	{
			if(number<0)
			{
				throw new NegativeQuantity("Negative Quantity Error");
			}
			else
			{
				return String.valueOf(number);
			}
	}
	
	public RetailItem getItem()
	{
		return item;
	}
	
	public double getSubTotal()
	{
		return item.getPrice()*number;
	}
	public double getTax()
	{
		return item.getPrice()*number*tax;
	}
	public void setTax(double t)
	{
		tax = t;
	}
	public double getTotal()
	{
		return item.getPrice()*number*(1+tax);
	}
}

private class RetailItem
{
	double price;
	String name;
	
	public RetailItem(double p, String n)
	{
		price=p;
		name=n;
	}
	
	public RetailItem()
	{
		price=0;
		name="Default Item";
	}
	
	
	public String getPriceString()
	{
		DecimalFormat format = new DecimalFormat("$0.00");
			if(price<0)
			{
				throw new NegativePrice("Negative Price Error");
			}
			else
			{
				return format.format(price);
			}

	}
	
	public double getPrice()
	{
		return (price);
	}
	public String getName()
	{
		return name;
	}
		
}
private class NegativePrice extends IllegalArgumentException
{
	public NegativePrice(String s)
	{
		super(s);
	}
}

private class NegativeQuantity  extends IllegalArgumentException
{
	public NegativeQuantity(String s)
	{
		super(s);
	}
}
}

