


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
public class Problem13_5 extends PaintPanel
{
	JLabel label = new JLabel();
	ArrayList<CashRegister> registers = new ArrayList();
	double[] priceList;
	String[] bookListRoot;
	String[] bookListNoPrice;
	JList bookList;
	JList cart = new JList();

	
	JTextArea output = new JTextArea(20,60);
	
	public Problem13_5()
	{
		try
		{
		try{RandomAccessFile test = new RandomAccessFile("test.txt","rw");test.close();}catch(IOException e){}

		setLayout(new BorderLayout());
		add(KDUtil.wrap(topPanel()), BorderLayout.NORTH);
		add(KDUtil.wrap(centerPanel()), BorderLayout.CENTER);
		add(KDUtil.wrap(bottomPanel()), BorderLayout.SOUTH);
		}
		catch(java.security.AccessControlException k)
		{
			removeAll();
			add(new JLabel("ERROR: Applets cannot read or write files"));
		}	

	}
	
	public JPanel topPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		JLabel header = new JLabel("Shopping Cart Program");
		
		populateList();
		
		bookList.addListSelectionListener(new ListListener());
		cart.addListSelectionListener(new ListListener());
		
		JScrollPane books = new JScrollPane(bookList);
		JScrollPane carts = new JScrollPane(cart);
		
		topPanel.add(KDUtil.wrap(books), BorderLayout.WEST);
		topPanel.add(KDUtil.wrap(carts), BorderLayout.EAST);
		topPanel.add(KDUtil.wrap(header), BorderLayout.NORTH);
	
		return topPanel;
	}
	
	private class ListListener implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			updateList();
		}
	}
	
	
	public void populateList()
	{
		ArrayList<String> books = new ArrayList();
		
		try
		{

			File inputFile = new File("Books.txt");
			Scanner inputScanner = new Scanner(inputFile);
					
			while(inputScanner.hasNext())
			{
				String output = inputScanner.nextLine();
				books.add(output);
			}
			
			inputScanner.close();
		}
		catch(IOException e){}
		
		String[] tempBookList = new String[books.size()];
		double[] tempBookPrice = new double[books.size()];
		bookListNoPrice = new String[books.size()];
		
		for(int i = 0; i<books.size(); i++)
		{
			int pivot = -1;
			for(int k = 0; k<books.get(i).length(); k++)
				if(books.get(i).charAt(k)==',') pivot = k;
			
			String name = books.get(i).substring(0,pivot);
			String price = books.get(i).substring(pivot+1);
			
			bookListNoPrice[i] = name;
			tempBookPrice[i] = Double.parseDouble(price);
			tempBookList[i] = books.get(i);
		}
		
		bookList = new JList(tempBookList);
		bookListRoot = tempBookList;
		priceList = tempBookPrice;
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
				updateList();
				registers.clear();
				int[] prices = bookList.getSelectedIndices();
				
				RetailItem item = new RetailItem();
				CashRegister register = new CashRegister();
				for(int i = 0; i<prices.length; i++)
				{
					item = new RetailItem(priceList[prices[i]], bookListNoPrice[prices[i]]);
					register = new CashRegister(item, 1);
					registers.add(register);
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
	
	private void updateList()
	{
		int[] boughtBooks = bookList.getSelectedIndices();
		String[] cartList = new String[boughtBooks.length];
		
		for(int i = 0; i<boughtBooks.length; i++)
		{
			cartList[i] = bookListRoot[boughtBooks[i]]; 
		}
		
		cart.setListData(cartList);
	}


	@Override
	public String getDescription()
	{
		return "5. Shopping Cart System"
+"\n\nCreate an application that works like a shopping cart system for a book" 
+"\nstore. In this book’s source code, available at www.pearsonhighered.com/gaddis," 
+"\nyou will find a file named BookPrices.txt. This file contains the names and "
+"\nprices of various books, formatted in the following fashion:"

+"\n\nI Did It Your Way, 11.95"
+"\nThe History of Scotland, 14.50"
+"\nLearn Calculus in One Day, 29.95"
+"\nFeel the Stress, 18.50"
 
+"\n\nEach line in the file contains the name of a book, followed by a comma, "
+"\nfollowed by the book’s retail price. When your application begins execution, it "
+"\nshould read the contents of the file and store the book titles in a list component."
+"\nThe user should be able to select a title from the list and add it to a shopping "
+"\ncart, which is simply another list component. The application should have buttons"
+"\nor menu items that allow the user to remove items from the shopping cart, clear "
+"\nthe shopping cart of all selections, and check out. When the user checks out, "
+"\nthe application should calculate and display the subtotal of all the books in "
+"\nthe shopping cart, the sales tax (which is 6 percent of the subtotal), and the "
+"\ntotal.";
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

