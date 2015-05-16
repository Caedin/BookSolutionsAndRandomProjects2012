/**
 * @(#)MedFrame.java
 *
 *
 * @author 
 * @version 1.00 2013/4/29
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class MedFrame extends JFrame//makes it a frame
{
	//makes multi-selctable check boxes
	JCheckBox CB1;
	JCheckBox CB2;
	JCheckBox CB3;
	JCheckBox CB4;
	JCheckBox CB5;
	//JButton	close;
    public MedFrame()
    {
    	this.setLayout(null);//removes silly java layout restrictions
    	
    	//creates labaled check boxes, sets their location and size
    	CB1 = new JCheckBox("Med. 1");
    	CB2 = new JCheckBox("Med. 2");
    	CB3 = new JCheckBox("Med. 3");
    	CB4 = new JCheckBox("Med. 4");
    	CB5 = new JCheckBox("Med. 5");
    	
    	CB1.setSize(100,50);
    	CB2.setSize(100,50);
    	CB3.setSize(100,50);
    	CB4.setSize(100,50);
    	CB5.setSize(100,50);
    	
    	CB1.setLocation(0,50);
    	CB2.setLocation(100,50);
    	CB3.setLocation(200,50);
    	CB4.setLocation(300,50);
    	CB5.setLocation(400,50);
    	
    	//close = new JButton("Close");
    	//close.setSize(150,150);
    	//close.setLocation(300,300);
    	//adds buttons to object
    	this.add(CB1);
    	this.add(CB2);
    	this.add(CB3);
    	this.add(CB4);
      	this.add(CB5);
      	//this.add(close);
      	
      	//makes the window invisible till called
    	this.setSize(500,500);
    	this.setVisible(false);
    }
    //creates a preset surgery window with different surgeries selected
    public MedFrame(int a, int b, int c, int d, int e)
    {
    	this.setLayout(null);
    	
    	CB1 = new JCheckBox("Med. 1");
    	CB2 = new JCheckBox("Med. 2");
    	CB3 = new JCheckBox("Med. 3");
    	CB4 = new JCheckBox("Med. 4");
    	CB5 = new JCheckBox("Med. 5");
    	
    	CB1.setSize(100,50);
    	CB2.setSize(100,50);
    	CB3.setSize(100,50);
    	CB4.setSize(100,50);
    	CB5.setSize(100,50);
    	
    	CB1.setLocation(0,50);
    	CB2.setLocation(100,50);
    	CB3.setLocation(200,50);
    	CB4.setLocation(300,50);
    	CB5.setLocation(400,50);
    	
    	if (a == 1)
    	{
    		CB1.setSelected(true);
    	}
    	if (b == 1)
    	{
    		CB2.setSelected(true);
    	}
    	if (c == 1)
    	{
    		CB3.setSelected(true);
    	}
    	if (d == 1)
    	{
    		CB4.setSelected(true);
    	}
    	if (e == 1)
    	{
    		CB5.setSelected(true);
    	}
    	
    	//close = new JButton("Close");
    	//close.setSize(150,150);
    	//close.setLocation(300,300);
    	
    	this.add(CB1);
    	this.add(CB2);
    	this.add(CB3);
    	this.add(CB4);
      	this.add(CB5);
      	//this.add(close);
      	
      	
    	this.setSize(500,500);
    	this.setVisible(false);
    }
    public void Visible()//makes frame visible
    {
    	this.setVisible(true);
    }
    //accesses check boxes
    public int get1()
    {
    	if (CB1.isSelected())
    		return 1;
    	else
    		return 0;
    }
    public int get2()
    {
    	if (CB2.isSelected())
    		return 1;
    	else
    		return 0;
    }
    public int get3()
    {
    	if (CB3.isSelected())
    		return 1;
    	else
    		return 0;
    }
    public int get4()
    {
    	if (CB4.isSelected())
    		return 1;
    	else
    		return 0;
    }
    public int get5()
    {
    	if (CB5.isSelected())
    		return 1;
    	else
    		return 0;
    }
}