


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


// ---------- Main Class -------------------------
public class Problem12_3 extends PaintPanel
{
	JTextField name = new JTextField(10);
	JTextField number = new JTextField(10);
	JTextField price = new JTextField(10);
	JLabel label = new JLabel();
	ArrayList<CashRegister> registers = new ArrayList();
	
	JTextArea output = new JTextArea(20,30);
	
	public Problem12_3()
	{
		setLayout(new BorderLayout());
		add(wrap(topPanel()), BorderLayout.NORTH);
		add(wrap(centerPanel()), BorderLayout.CENTER);
		add(wrap(bottomPanel()), BorderLayout.SOUTH);
	}
	
	public JPanel topPanel()
	{
		JPanel topPanel = new JPanel(new GridLayout(2,3,20,0));
		label = new JLabel("Name of Item");
		topPanel.add(wrap(label));
		label = new JLabel("Price per Item");
		topPanel.add(wrap(label));
		label = new JLabel("Quantity");
		topPanel.add(wrap(label));
		
		topPanel.add(wrap(name));
		topPanel.add(wrap(price));
		topPanel.add(wrap(number));
		
		return topPanel;
	}
	
	public JPanel bottomPanel()
	{
		JPanel centerPanel = new JPanel(new BorderLayout());
		label = new JLabel("Receipt");
		centerPanel.add(wrap(label), BorderLayout.NORTH);
		
		JScrollPane scroll = new JScrollPane(output);
		centerPanel.add(wrap(scroll), BorderLayout.CENTER);

		
		return centerPanel;
	}
	
	public JPanel centerPanel()
	{
		JPanel centerPanel = new JPanel();
		JButton purchase = new JButton("Add");
		centerPanel.add(wrap(purchase));
		purchase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String itemName = name.getText();
				double itemPrice = parseMoney(price.getText());
				int quantity = (int)Math.round(parseMoney(number.getText()));
				
				if(itemName.isEmpty()==true) itemName = "Default Item";
				
				RetailItem item = new RetailItem();
				item = new RetailItem(itemPrice, itemName); 
				
				CashRegister register = new CashRegister();
				register = new CashRegister(item,quantity);
				registers.add(register);
				
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
		centerPanel.add(wrap(clear));
		clear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				registers.clear();
				output.setText("");
			}			
		});
		
		
		return centerPanel;
	}

	
	public JPanel wrap(JComponent panel)
	{
		JPanel wrapper = new JPanel();
		wrapper.add(panel);
		return wrapper;
	}


	@Override
	public String getDescription()
	{
		return "3. RetailItem Exceptions"
+"\n\nProgramming Challenge 4 of Chapter 6 required you to write a RetailItem class that holds data pertaining to a retail item. Write an exception class that can be "
+"\ninstantiated and thrown when a negative number is given for the price. Write another exception class that can be instantiated and thrown when a negative number is "
+"\ngiven for the units on hand. Demonstrate the exception classes in a program.";
	}
		


private class CashRegister
{
	RetailItem item;
	int number;
	double tax;
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
		return item.getPrice()*number*0.06;
	}
	public double getTotal()
	{
		return item.getPrice()*number*1.06;
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