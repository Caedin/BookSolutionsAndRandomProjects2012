


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


// ---------- Main Class -------------------------

class Problem9_2  extends PaintPanel
{
	ArrayList<BankAccount> banks = new ArrayList();
	JPanel westPanel = new JPanel();
	JPanel eastPanel = new JPanel();
	JPanel wrapper = new JPanel();
	
	public Problem9_2()
	{
		buildPanel();
	}
	
	private void buildPanel()
	{
		buildEastPanel();
		buildWestPanel();
		
		setLayout(new BorderLayout());
		
		wrapper = new JPanel();
		wrapper.add(eastPanel);
		add(wrapper, BorderLayout.WEST);
		
		wrapper = new JPanel();
		wrapper.add(westPanel);
		add(wrapper, BorderLayout.EAST);
	}
	
	private void buildEastPanel()
	{
		eastPanel.setLayout(new GridLayout(5,0));
		
		JLabel name = new JLabel("New Bank Name:");
		wrapper = new JPanel();
		wrapper.add(name);
		eastPanel.add(wrapper);
		
		final JTextField input = new JTextField(20);
		wrapper = new JPanel();
		wrapper.add(input);
		eastPanel.add(wrapper);
		
		name = new JLabel("Starting Balance:");
		wrapper = new JPanel();
		wrapper.add(name);
		eastPanel.add(wrapper);
		
		final JTextField input2 = new JTextField(20);
		wrapper = new JPanel();
		wrapper.add(input2);
		eastPanel.add(wrapper);
		
		JButton newBank = new JButton("Create New BankAccount");
		wrapper = new JPanel();
		wrapper.add(newBank);
		eastPanel.add(wrapper);
		
		newBank.addActionListener(new ActionListener() 
			{

	         public void actionPerformed(ActionEvent arg0)
				{
					String balance = inputValidation(input2.getText());
					String name = input.getText();
					double bal = 0;
					
					if(balance.isEmpty()==false && balance.equals("Input Error")==false) bal = Double.parseDouble(balance);
					
					BankAccount b = new BankAccount(bal, name, banks);
					banks.add(b);
					buildWestPanel();
				}
				
				private String inputValidation(String input)
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

         });
	}
	
	private void buildWestPanel()
	{
		westPanel.removeAll();
		
		if(banks.size()>0)westPanel.setLayout(new GridLayout(banks.size(),0));
		else westPanel.setLayout(new GridLayout(1,0));
		
		for(int i = 0; i<banks.size(); i++)
		{
			westPanel.add(banks.get(i).output());
		}
		
		repaint();
		revalidate();
		
		
	}
	
	
	@Override
	public String getDescription()
	{
		return "2. BankAccount Class Copy Constructor"
+"\nAdd a copy constructor to the BankAccount class. This constructor should accept a BankAccount object as an argument. It should assign to the balance field the value in the "
+"\nargument’s balance field. As a result, the new object will be a copy of the argument object.";
	}
	
	private class BankAccount
	{
		double balance;
		String name;
		DecimalFormat format = new DecimalFormat("$0.00");
		JPanel bank = new JPanel();
		ArrayList<BankAccount> accounts = new ArrayList();
		BankAccount thisAccount;
		
		public BankAccount(double b, String name, ArrayList<BankAccount> accounts)
		{
			balance = b;
			this.name=name;
			this.accounts=accounts;
			thisAccount=this;
		}
		public BankAccount()
		{
			balance = 0;
		}
		
		public BankAccount(BankAccount b)
		{
			balance = b.getBalance();
			this.name=b.name;
			this.accounts=b.accounts;
			thisAccount=this;
		}
		
		public double getBalance() {return balance;}
		
		public JPanel output()
		{
			bank.removeAll();
			bank.setBorder(BorderFactory.createTitledBorder(name));
			bank.setLayout(new GridLayout(0,4));
			
			JLabel label = new JLabel("Balance: "+format.format(balance));
			
			JPanel wrapper = new JPanel();
			wrapper.add(label);
			bank.add(wrapper);
			
	
			bank.add(new JPanel());
			
			
			JButton copy = new JButton("Copy");
			wrapper = new JPanel();
			wrapper.add(copy);
			bank.add(wrapper);
	
			JButton delete = new JButton("Delete");
			wrapper = new JPanel();
			wrapper.add(delete);
			bank.add(wrapper);
			
			copy.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						BankAccount b = new BankAccount(thisAccount);
						accounts.add(b);
						buildWestPanel();
					}
	
	      	});
				
			delete.addActionListener(new ActionListener() 
				{
	
		         public void actionPerformed(ActionEvent arg0)
					{
						accounts.remove(thisAccount);
						buildWestPanel();
					}
	
	      	});
			
			
			return bank;
		}
		
	}

		
}
