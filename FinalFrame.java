/**
 * @(#)FinalFrame.java
 *
 *
 * @author 
 * @version 1.00 2013/4/29
 */
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class FinalFrame extends JFrame implements ActionListener//makes it a frame and allows it to detect actions
{
	//creates variables
	JTextArea name;
	JTextArea days;
	JTextArea total;
	JButton Surg;
	JButton Med;
	JButton Calc;
	SurgFrame SF;
	MedFrame MF;
	JPanel topPanel = new JPanel();
	
	int med1 = 0;
	int med2 = 0;
	int med3 = 0;
	int med4 = 0;
	int med5 = 0;
	
	int surg1 = 0;
	int surg2 = 0;
	int surg3 = 0;
	int surg4 = 0;
	int surg5 = 0;
	
    public FinalFrame()
    {
	 	topPanel.setLayout(new FlowLayout());
	 	//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes program on exit
    	//set text areas locations and sizes
    	name = new JTextArea("Name");
    	name.setAlignmentX(0);
    	name.setAlignmentY(0);
    	name.setSize(100,20);
    	days = new JTextArea("Days in Hospital");
    	days.setLocation(0,40);
    	days.setSize(100,20);
    	total = new JTextArea("");
    	total.setLocation(300,0);
    	total.setSize(100,100);
    	total.setEditable(false);
    	name.setEditable(true);
    	days.setEditable(true);
    	//Set Buttons locatiosn and sizes
    	Surg = new JButton("Surgery");
    	Surg.setLocation(150,0);
    	Surg.setSize(100,20);
    	Surg.setActionCommand("Surg");
    	Med = new JButton("Meds");
    	Med.setLocation(150,20);
    	Med.setSize(100,20);
    	Med.setActionCommand("Meds");
    	Calc = new JButton("Calculate");
    	Calc.setLocation(150,40);
    	Calc.setSize(100,20);
    	Calc.setActionCommand("Calc");
    	Surg.addActionListener(this);
    	Med.addActionListener(this);
    	Calc.addActionListener(this);
    	this.setSize(400,100);
    	//creates the selection frames, but hides them
    	SF = new SurgFrame();
    	MF = new MedFrame();
    	//adds things to the frame
    	topPanel.add(name);
    	topPanel.add(days);
    	topPanel.add(total);
    	topPanel.add(Surg);
    	topPanel.add(Med);
    	topPanel.add(Calc);
		add(topPanel);
    	//makes it visible
    	//this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)//detects and reacts to button presses
    	{
        if ("Surg".equals(e.getActionCommand()))
        	{
            	SF = new SurgFrame(SF.get1(),SF.get2(),SF.get3(),SF.get4(),SF.get5());
            	SF.Visible();
        	}
        else if ("Meds".equals(e.getActionCommand()))
        	{
				MF = new MedFrame(MF.get1(),MF.get2(),MF.get3(),MF.get4(),MF.get5());
            	MF.Visible();
       		}
       	else if ("Calc".equals(e.getActionCommand()))
       	{
       		Calc();
       	}
    }
    public void Calc()//calculates and changes the total text area with final cost, can be recalled
    {
    	double totalC = SF.get1()*100;
    	totalC += SF.get2()*200;
    	totalC += SF.get3()*300;
    	totalC += SF.get4()*400;
    	totalC += SF.get5()*500;
    	
    	totalC += MF.get2()*10;
    	totalC += MF.get2()*20;
    	totalC += MF.get2()*30;
    	totalC += MF.get2()*40;
    	totalC += MF.get2()*50;
    	
    	totalC += Integer.parseInt(days.getText())*100;
    	total.setText(""+totalC);
    	
    }
	 
	 public JPanel getTopPanel()
	 {
	 	return topPanel;
	 }
}