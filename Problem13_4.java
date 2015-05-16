


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


// ---------- Main Class -------------------------
public class Problem13_4 extends PaintPanel
{
	JLabel label = new JLabel();
	ArrayList<CashRegister> registers = new ArrayList();

	String[] deckList = {"The Master Thrasher $60","The Dictator $45", "The Street King $50"};
	final JComboBox deckBox = new JComboBox(deckList);

	String[] assembliesList = {"7.75 inch axel $35", "8 inch axel $40", "8.5 inch axel $45"};
	final JComboBox assemblieBox = new JComboBox(assembliesList);
		
	String[] wheelList = {"51 mm $20", "55 mm $22", "58 mm $24", "61 mm $28"};
	final JComboBox wheelBox = new JComboBox(wheelList);


	String[] accList = {"Grip Tape: $10", "Bearings: $30", "Riser pads: $2", "Nuts & bolts kit: $3"};
	final JList accListComp = new JList(accList);
	
	JTextArea output = new JTextArea(20,30);
	
	public Problem13_4()
	{
		setLayout(new BorderLayout());
		add(wrap(topPanel()), BorderLayout.NORTH);
		add(wrap(centerPanel()), BorderLayout.CENTER);
		add(wrap(bottomPanel()), BorderLayout.SOUTH);
	}
	
	public JPanel topPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		JLabel header = new JLabel("Skateboard Purchase Program");
		
		// Decks
		deckBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculateCost();
			}
		});
		
		// Truck Assemblies
		assemblieBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculateCost();
			}
		});

		// Wheels
		wheelBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculateCost();
			}
		});

		// Accessories
		accListComp.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		accListComp.addListSelectionListener(new ListListener());
		
		JScrollPane scroll = new JScrollPane(accListComp);
		
		JPanel wrapper = new JPanel(new GridLayout(3,2,5,5));
		wrapper.add(KDUtil.wrap(new JLabel("Deck")));
		wrapper.add(KDUtil.wrap(deckBox));
		wrapper.add(KDUtil.wrap(new JLabel("Assemblies")));
		wrapper.add(KDUtil.wrap(assemblieBox));
		wrapper.add(KDUtil.wrap(new JLabel("Wheels")));
		wrapper.add(KDUtil.wrap(wheelBox));
		topPanel.add(KDUtil.wrap(wrapper), BorderLayout.WEST);
		
		wrapper = new JPanel(new BorderLayout());
		wrapper.add(KDUtil.wrap(new JLabel("Accessories")), BorderLayout.NORTH);
		wrapper.add(KDUtil.wrap(scroll), BorderLayout.CENTER);
		topPanel.add(KDUtil.wrap(header), BorderLayout.NORTH);
		topPanel.add(KDUtil.wrap(wrapper), BorderLayout.EAST);
	
		return topPanel;
	}
	
	private class ListListener implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			calculateCost();
		}
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
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		JPanel buttonWrapper = new JPanel();

		
		JButton purchase = new JButton("Purchase");
		buttonWrapper.add(KDUtil.wrap(purchase));
		
		purchase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				calculateCost();
				
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

	
	public JPanel wrap(JComponent panel)
	{
		JPanel wrapper = new JPanel();
		wrapper.add(panel);
		return wrapper;
	}
	
	private void calculateCost()
	{
		registers.clear();
		
		int deck = deckBox.getSelectedIndex();
		int ass = assemblieBox.getSelectedIndex();
		int wheel = wheelBox.getSelectedIndex();
		
		RetailItem item = new RetailItem();
		CashRegister register = new CashRegister();
		
		if(deck==0) 
		{
			item = new RetailItem(60,"Thrasher");
			register = new CashRegister(item,1);
			registers.add(register);
		} else if(deck==1)
		{
			item = new RetailItem(45,"The Dictator");
			register = new CashRegister(item,1);
			registers.add(register);
		} else if(deck==2)
		{
			item = new RetailItem(50,"The Street King");
			register = new CashRegister(item,1);
			registers.add(register);
		}
		
		if(ass == 0)
		{
			item = new RetailItem(35,"7.75 inch axel");
			register = new CashRegister(item,1);
			registers.add(register);
		} else if (ass == 1)
		{
			item = new RetailItem(40,"8 inch axel");
			register = new CashRegister(item,1);
			registers.add(register);
		} else if (ass == 2)
		{
			item = new RetailItem(45,"8.5 inch axel");
			register = new CashRegister(item,1);
			registers.add(register);
		}
		
			
		if(wheel == 0)
		{
			item = new RetailItem(20,"51 mm");
			register = new CashRegister(item,1);
			registers.add(register);
		} else if (wheel == 1)
		{
			item = new RetailItem(22,"55 mm");
			register = new CashRegister(item,1);
			registers.add(register);
		} else if (wheel == 2)
		{
			item = new RetailItem(24,"58 mm");
			register = new CashRegister(item,1);
			registers.add(register);
		} else if (wheel == 3)
		{
			item = new RetailItem(28,"61 mm");
			register = new CashRegister(item,1);
			registers.add(register);
		} 
		
		int[] accessories;
		accessories = accListComp.getSelectedIndices();
		
		for(int i = 0; i<accessories.length; i++)
		{
			if(accessories[i] == 0)
			{
				item = new RetailItem(10,"Grip tape");
				register = new CashRegister(item,1);
				registers.add(register);
			}
			if(accessories[i] == 1)
			{
				item = new RetailItem(30,"Bearings");
				register = new CashRegister(item,1);
				registers.add(register);
			}
			if(accessories[i] == 2)
			{
				item = new RetailItem(2,"Riser pads");
				register = new CashRegister(item,1);
				registers.add(register);
			}
			if(accessories[i] == 3)
			{
				item = new RetailItem(3,"Nuts & bolts kit");
				register = new CashRegister(item,1);
				registers.add(register);
			}
		}
		
	}


	@Override
	public String getDescription()
	{
		return "The Skate Shop sells the skateboard products listed in Table 13-2."

+"\n\nIn addition, the Skate Shop sells the following miscellaneous products and services:"
+"\nGrip tape: $10"
+"\nBearings: $30"
+"\nRiser pads: $2"
+"\nNuts & bolts kit: $3 "
+"\n\nCreate an application that allows the user to select one deck, one truck assembly, and one wheel set from "
+"\neither list components or combo boxes. The application should also have a list component that allows the user to select "
+"\nmultiple miscellaneous products. The application should display the subtotal, the amount of sales tax (at 6 percent), and the total "
+"\nof the order.";
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

