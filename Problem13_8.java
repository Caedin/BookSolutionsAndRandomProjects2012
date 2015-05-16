


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
public class Problem13_8 extends PaintPanel
{
	JLabel label = new JLabel();
	ArrayList<CashRegister> registers = new ArrayList();
	JRadioButtonMenuItem studentButton = new JRadioButtonMenuItem("Student Registration");
	JRadioButtonMenuItem regularButton	= new JRadioButtonMenuItem("Regular Registration");
	ButtonGroup statusGroup = new ButtonGroup();
	JCheckBoxMenuItem dinner = new JCheckBoxMenuItem("Opening Night Dinner");
	JCheckBoxMenuItem commerce = new JCheckBoxMenuItem("Introduction to E-Commerce");
	JCheckBoxMenuItem web = new JCheckBoxMenuItem("The Future of the Web");
	JCheckBoxMenuItem java = new JCheckBoxMenuItem("Advanced Java Programming");
	JCheckBoxMenuItem network = new JCheckBoxMenuItem("Network Security");

	JTextArea output = new JTextArea(20,40);
	
	public Problem13_8()
	{

		setLayout(new BorderLayout());
		add(KDUtil.wrap(topPanel()), BorderLayout.NORTH);
		add(KDUtil.wrap(centerPanel()), BorderLayout.CENTER);
		add(KDUtil.wrap(bottomPanel()), BorderLayout.SOUTH);

	}
	
	public JPanel topPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		JLabel header = new JLabel("Conference Registration System");
		
		
		JMenuBar menuBar = new JMenuBar();
		
		
		statusGroup.add(studentButton);
		statusGroup.add(regularButton);

		VerticalMenuBar optionsBar = new VerticalMenuBar();

		
		menuBar.add(regularButton);
		menuBar.add(studentButton);
		
		optionsBar.add(dinner);
		optionsBar.add(commerce);
		optionsBar.add(web);
		optionsBar.add(java);
		optionsBar.add(network);
		
		
					
		topPanel.add(KDUtil.wrap(menuBar), BorderLayout.CENTER);		
		topPanel.add(KDUtil.wrap(header), BorderLayout.NORTH);	
		topPanel.add(KDUtil.wrap(optionsBar), BorderLayout.SOUTH);				
		
	
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
		
		if(studentButton.isSelected()==true)
		{
			item = new RetailItem(495, "Student Registration");
			register = new CashRegister(item, 1);
			
			boolean contain = false;
			for(int i = 0; i<registers.size(); i++)
			{
				if(registers.get(i).getItem().getName().equals("Student Registration"))
				{
					registers.get(i).incQuantity();
					contain = true;
				}
			}
			
			if(contain == false)
				registers.add(register);
		} 
		if(regularButton.isSelected()==true)
		{
			item = new RetailItem(895, "Regular Registration");
			register = new CashRegister(item, 1);
			
			boolean contain = false;
			for(int i = 0; i<registers.size(); i++)
			{
				if(registers.get(i).getItem().getName().equals("Regular Registration"))
				{
					registers.get(i).incQuantity();
					contain = true;
				}
			}
			
			if(contain == false)
				registers.add(register);
		} 
	
		
		if(dinner.isSelected()==true)
		{
			item = new RetailItem(30, "Opening Night Dinner");
			register = new CashRegister(item, 1);
			boolean contain = false;
			for(int i = 0; i<registers.size(); i++)
			{
				if(registers.get(i).getItem().getName().equals("Opening Night Dinner"))
				{
					registers.get(i).incQuantity();
					contain = true;
				}
			}
			
			if(contain == false)
				registers.add(register);
		}
		
		if(commerce.isSelected()==true)
		{
			item = new RetailItem(295, "Introduction to E-Commerce");
			register = new CashRegister(item, 1);
			boolean contain = false;
			for(int i = 0; i<registers.size(); i++)
			{
				if(registers.get(i).getItem().getName().equals("Introduction to E-Commerce"))
				{
					registers.get(i).incQuantity();
					contain = true;
				}
			}
			
			if(contain == false)
				registers.add(register);
		}
		
		
		if(web.isSelected()==true)
		{
			item = new RetailItem(295, "The Future of the Web");
			register = new CashRegister(item, 1);
			boolean contain = false;
			for(int i = 0; i<registers.size(); i++)
			{
				if(registers.get(i).getItem().getName().equals("The Future of the Web"))
				{
					registers.get(i).incQuantity();
					contain = true;
				}
			}
			
			if(contain == false)
				registers.add(register);
		}
		
		
		if(java.isSelected()==true)
		{
			item = new RetailItem(395, "Advanced Java Programming");
			register = new CashRegister(item, 1);
			boolean contain = false;
			for(int i = 0; i<registers.size(); i++)
			{
				if(registers.get(i).getItem().getName().equals("Advanced Java Programming"))
				{
					registers.get(i).incQuantity();
					contain = true;
				}
			}
			
			if(contain == false)
				registers.add(register);
		}
		
		
		if(network.isSelected()==true)
		{
			item = new RetailItem(395, "Network Security");
			register = new CashRegister(item, 1);
			boolean contain = false;
			for(int i = 0; i<registers.size(); i++)
			{
				if(registers.get(i).getItem().getName().equals("Network Security"))
				{
					registers.get(i).incQuantity();
					contain = true;
				}
			}
			
			if(contain == false)
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
		return "8. Conference Registration System"
+"\n\nCreate an application that calculates the registration fees for a conference. "
+"\nThe general conference registration fee is $895 per person, and student "
+"\nregistration is $495 per person. There is also an optional opening night dinner "
+"\nwith a keynote speech for $30 per person. In addition, the optional preconference"
+"\n workshops listed in Table 13-3 are available."
+"\nTable 13-3 Optional preconference workshops"
 



 
+"\n\nThe application should allow the user to select the registration type, "
+"\nthe optional opening night dinner and keynote speech, and as many preconference "
+"\nworkshops as desired. The total cost should be displayed.";
	}
		

private class VerticalMenuBar extends JMenuBar {
  private final LayoutManager grid = new GridLayout(0,1);
  public VerticalMenuBar() {
    setLayout(grid);
  }
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
	
	public void incQuantity()
	{
		number++;
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

