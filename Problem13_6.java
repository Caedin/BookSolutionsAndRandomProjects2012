


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
public class Problem13_6 extends PaintPanel
{
	JLabel label = new JLabel();
	ArrayList<CashRegister> registers = new ArrayList();
	double[] priceList;
	String[] bookListRoot;
	String[] bookListNoPrice;
	JList bookList;
	JList cart = new JList();
	
	int[] options = new int[3];
	

	
	JTextArea output = new JTextArea(20,40);
	
	public Problem13_6()
	{
		options[0] = -1;
		options[1] = -1;
		options[2] = -1;
		
		setLayout(new BorderLayout());
		add(KDUtil.wrap(topPanel()), BorderLayout.NORTH);
		add(KDUtil.wrap(centerPanel()), BorderLayout.CENTER);
		add(KDUtil.wrap(bottomPanel()), BorderLayout.SOUTH);

	}
	
	public JPanel topPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		JLabel header = new JLabel("Cell Phone Plan Program");
		
		JMenuBar menuBar = new JMenuBar();
		JMenu phoneMenu = new JMenu("Phone Options");
			 JMenuItem model100 = new JMenuItem("Model 100: $29.95");
			 JMenuItem model110 = new JMenuItem("Model 110: $49.95");
			 JMenuItem model200 = new JMenuItem("Model 200: $99.95");
			
			phoneMenu.add(model100);
			phoneMenu.add(model110);
			phoneMenu.add(model200);
			
		 JMenu planMenu = new JMenu("Plan Options");
			 JMenuItem plan1 = new JMenuItem("300 min/month: $45.00 per month");
			 JMenuItem plan2 = new JMenuItem("800 min/month: $65.00 per month");
			 JMenuItem plan3 = new JMenuItem("1500 min/month: $99.00 per month");
			
			planMenu.add(plan1);
			planMenu.add(plan2);
			planMenu.add(plan3);
			
		 JMenu otherMenu = new JMenu("Additional Services");
			 JMenuItem voicemail = new JMenuItem("Voice mail: $5.00 per month");
			 JMenuItem texts = new JMenuItem("Text messaging: $10.00 per month");
			
			otherMenu.add(voicemail);
			otherMenu.add(texts);
			
			menuBar.add(phoneMenu);
			menuBar.add(planMenu);
			menuBar.add(otherMenu);
			
			
			model100.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					options[0] = 0;
					update();
				}			
			});
			model110.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					options[0] = 1;
					update();
				}			
			});
			model200.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					options[0] = 2;
					update();
				}			
			});
			
			plan1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					options[1] = 0;
					update();
				}			
			});
			plan2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					options[1] = 1;
					update();
				}			
			});
			plan3.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					options[1] = 2;
					update();
				}			
			});
			
			voicemail.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					if(options[2] == 0) options[2] = -1;
					else if(options[2] == 1) options[2] = 2;
					else options[2] = 0;
					

					
					update();
				}			
			});
			texts.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					if(options[2] == 1) options[2] = -1;
					else
					if(options[2] == 0)
						options[2] = 2;
					else options[2] = 1;
					update();
				}			
			});
			
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
				registers.clear();
				
				RetailItem item = new RetailItem();
				CashRegister register = new CashRegister();
				
				if(options[0] == 0)	{item = new RetailItem(29.95, "Model100\t");	register = new CashRegister(item, 1); registers.add(register);}
				if(options[0] == 1)	{item = new RetailItem(49.95, "Model110\t");	register = new CashRegister(item, 1); registers.add(register);}
				if(options[0] == 2)	{item = new RetailItem(99.95, "Model200\t");	register = new CashRegister(item, 1); registers.add(register);}
				
				if(options[1] == 0)	{item = new RetailItem(45, "300 min/month\t");	register = new CashRegister(item, 1); registers.add(register);}
				if(options[1] == 1)	{item = new RetailItem(65, "800 min/month\t");	register = new CashRegister(item, 1); registers.add(register);}
				if(options[1] == 2)	{item = new RetailItem(99, "1500 min/month");	register = new CashRegister(item, 1); registers.add(register);}
				
				if(options[2] == 0)	{item = new RetailItem(5, "Voice mail\t");	register = new CashRegister(item, 1); registers.add(register);}
				if(options[2] == 1)	{item = new RetailItem(10, "Text messaging");	register = new CashRegister(item, 1); registers.add(register);}
				
				if(options[2] == 2)	
				{
					item = new RetailItem(5, "Voice mail\t");	register = new CashRegister(item, 1); registers.add(register);
					item = new RetailItem(10, "Text messaging");	register = new CashRegister(item, 1); registers.add(register);				
				}
				
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
	
	public JPanel centerPanel()
	{
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		JPanel buttonWrapper = new JPanel();

		
		JButton purchase = new JButton("Purchase");
		buttonWrapper.add(KDUtil.wrap(purchase));
		
		purchase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				update();	
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
					
				options[0] = -1;
				options[1] = -1;
				options[2] = -1;
				
				output.setText("");
			}			
		});
		
		centerPanel.add(KDUtil.wrap(buttonWrapper), BorderLayout.SOUTH);
		return centerPanel;
	}


	
	

	@Override
	public String getDescription()
	{
		return "6. Cell Phone Packages"
+"\n\nCell Solutions, a cell phone provider, sells the following packages:"
+"\n300 minutes per month: $45.00 per month"
+"\n800 minutes per month: $65.00 per month"
+"\n1500 minutes per month: $99.00 per month"
 
+"\n\nThe provider sells the following phones (a 6 percent sales tax applies to the "
+"\nsale of a phone):"
 
+"\n\nModel 100: $29.95"
+"\nModel 110: $49.95"
+"\nModel 200: $99.95"
 
+"\n\nCustomers may also select the following options:"
+"\nVoice mail: $5.00 per month"
+"\nText messaging: $10.00 per month"
 
+"\n\nWrite an application that displays a menu system. The menu system should allow "
+"\nthe user to select one package, one phone, and any of the options desired. As "
+"\nthe user selects items from the menu, the application should show the prices of "
+"\nthe items selected.";
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

