/* 
 Author: Keith Dyer
 Date: March 27, 2013
 Problem Number: 11.9
*/



// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------



class Problem11_9  extends PaintPanel
{
	SavingsAccount acc = new SavingsAccount(0,0);
	
	PaintPanel thisPanel;
	
	public Problem11_9()
	{
		thisPanel = this;
		createAccount();
		
		add(buildPanel());
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "9. BankAccount and SavingsAccount Classes"
+"\n\nDesign an abstract class named BankAccount to hold the following data for a bank account:"
+"\n\n• Balance"
+"\n• Number of deposits this month"
+"\n• Number of withdrawals"
+"\n• Annual interest rate"
+"\n• Monthly service charges"
+"\n\nThe class should have the following methods:"

+"\n\nNext, design a SavingsAccount class that extends the BankAccount class. The SavingsAccount class should have a status field to represent an active or inactive account. If the balance of a savings "
+"\naccount falls below $25, it becomes inactive. (The status field could be a boolean variable.) No more withdrawals may be made until the balance is raised above $25, at which time the account becomes "
+"\nactive again. The savings account class should have the following methods:";
	}	
	
	public JPanel buildPanel()
	{	
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(KDUtil.wrap(buildOutputPanel()), BorderLayout.CENTER);
		topPanel.add(KDUtil.wrap(buildButtonsPanel()), BorderLayout.SOUTH);
		return topPanel;
	}
	

	public JPanel buildOutputPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());
		
		topPanel.add(acc.getPanel(), BorderLayout.CENTER);
		
		
		return topPanel;
	}
	
	public JPanel buildButtonsPanel()
	{
		JPanel topPanel = new JPanel();
		
		JButton deposit = new JButton("Deposit");
		JButton withdrawal = new JButton("Withdrawal");
		JButton process = new JButton("MonthlyProcess");
		
		deposit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				double deposit = 0;
				String temp = new String();
				temp = KDUtil.moneyInputValidation(JOptionPane.showInputDialog("Amount to deposit: "));
					
				if(temp.isEmpty()==false)
					deposit = Double.parseDouble(temp);
				
				acc.deposit(deposit);	
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
			}
		});
		
		withdrawal.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				double with = 0;
				String temp = new String();
				temp = KDUtil.moneyInputValidation(JOptionPane.showInputDialog("Amount to withdrawal: "));
					
				if(temp.isEmpty()==false)
					with = Double.parseDouble(temp);
				
				acc.withdrawal(with);	
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
			}
		});
		
		process.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				acc.monthlyProcess();
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
			}
		});
		
		JPanel tempPanel = new JPanel(new BorderLayout());
		tempPanel.add(KDUtil.wrap(deposit), BorderLayout.WEST);
		tempPanel.add(KDUtil.wrap(withdrawal), BorderLayout.CENTER);
		tempPanel.add(KDUtil.wrap(process), BorderLayout.EAST);
		topPanel.add(KDUtil.wrap(tempPanel), BorderLayout.SOUTH);
		
		return topPanel;

	}
	
	public void createAccount()
	{
		final JFrame input = new JFrame();
		input.setSize(400,400);
		
		JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.add(KDUtil.wrap(new JLabel("Creating a new Savings Account")));
		
		JPanel center = new JPanel(new GridLayout(2,2));
		
		final JTextField bal = new JTextField(10);
		final JTextField rate = new JTextField(10);

		
		center.add(KDUtil.wrap(new JLabel("Starting Balance: ")));
		center.add(KDUtil.wrap(bal));
		
		center.add(KDUtil.wrap(new JLabel("Annual Interest Rate: ")));
		center.add(KDUtil.wrap(rate));

		
		JPanel complete = new JPanel();
		
		JButton enter = new JButton("Done");
		complete.add(KDUtil.wrap(enter));
		
		enter.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				double balance = 0;
				double annualRate = 0;
				
				if(bal.getText().isEmpty()==false)
					balance = Double.parseDouble(KDUtil.doubleInputValidation(bal.getText()));
				
				if(rate.getText().isEmpty()==false)
					 annualRate = Double.parseDouble(KDUtil.doubleInputValidation(rate.getText()));

				acc = new SavingsAccount(balance,annualRate);
				
				input.setVisible(false);
				
				thisPanel.removeAll();
				thisPanel.add(buildPanel());
				thisPanel.revalidate();
				thisPanel.repaint();
			}
		});
		
	topPanel.add(KDUtil.wrap(header), BorderLayout.NORTH);
	topPanel.add(KDUtil.wrap(center), BorderLayout.CENTER);
	topPanel.add(KDUtil.wrap(complete), BorderLayout.SOUTH);
	
	input.add(topPanel);
	input.setVisible(true);
	}


	
}

abstract class BankAccount
{
	protected int age = 0;
	protected double balance = 0;
	protected double startingBalance = 0;
	protected int numDeposits = 0;
	protected int numWithdrawal = 0;
	protected double annualRate = 0;
	protected double mscharge = 0;		// Monthly service charge
	
	public BankAccount(double bal, double rate)
	{
		balance = bal;
		annualRate = rate;
		startingBalance = bal;
		
		
	}
	
	public void deposit(double r)
	{
		balance = balance + r;
		numDeposits++;
	}
	
	public void withdrawal(double w)
	{
		balance = balance - w;
		numWithdrawal++;
		
		mscharge = 0;
		int numCharges = 0;
		if(numWithdrawal>4) 
		{
			numCharges = numWithdrawal-4;
			mscharge = mscharge + numCharges;
		}
	}
	
	public void calcInterest()
	{
		double monthlyRate = (annualRate/12);
		double monthlyInterest = balance*(monthlyRate/100);
		balance = balance + monthlyInterest;
	}
	
	public void monthlyProcess()
	{
		balance = balance - mscharge;
		calcInterest();
		numDeposits = 0;
		numWithdrawal = 0;
		mscharge = 0;
		age++;
	}
}

class SavingsAccount extends BankAccount
{
	boolean active = true;
	
	public SavingsAccount(double bal, double rate)
	{
		super(bal,rate);
	}
	
	public void withdrawal(double w)
	{
		if(balance<25) active = false;
		if(active)
			super.withdrawal(w);
		
	}
	
	public void deposit(double r)
	{
		if(balance+r > 25) active = true;
		if(active)
			super.deposit(r);
	}
	
	public void monthlyProcess()
	{
		int numCharges = 0;
		if(numWithdrawal>4) 
		{
			numCharges = numWithdrawal-4;
			mscharge = mscharge + numCharges;
		}
		
		super.monthlyProcess();
		if(balance<25) active = false;
	}
	
	
	public JPanel getPanel()
	{
		DecimalFormat money = new DecimalFormat("$#,###.00");
	
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(KDUtil.wrap(new JLabel("Savings Account")), BorderLayout.NORTH);
		
				
		
		// Middle pannel, output
		// Starting, current balance, annual rate, age, active
		
		JPanel middle = new JPanel(new GridLayout(6,2,5,5));
		
		middle.add(KDUtil.wrap(new JLabel("Starting Balance:")));
		middle.add(KDUtil.wrap(new JLabel(money.format(startingBalance))));
		
		middle.add(KDUtil.wrap(new JLabel("Current Balance:")));
		middle.add(KDUtil.wrap(new JLabel(money.format(balance))));
		
		middle.add(KDUtil.wrap(new JLabel("Annual Rate:")));
		middle.add(KDUtil.wrap(new JLabel(annualRate+"%")));
		
		middle.add(KDUtil.wrap(new JLabel("Age:")));
		middle.add(KDUtil.wrap(new JLabel(age+" months")));
		
		middle.add(KDUtil.wrap(new JLabel("Active:")));
		if(active)
			middle.add(KDUtil.wrap(new JLabel("true")));
		else
			middle.add(KDUtil.wrap(new JLabel("false")));
			
		middle.add(KDUtil.wrap(new JLabel("Current Monthly Charges:")));
		middle.add(KDUtil.wrap(new JLabel(money.format(mscharge))));
		
		
		
		// end middle pannel
		
		topPanel.add(KDUtil.wrap(middle), BorderLayout.CENTER);
				
		return topPanel;
	}
	
}

