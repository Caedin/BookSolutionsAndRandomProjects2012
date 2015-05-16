

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
// -----------------------------------------------

public class Problem14_7 extends PaintPanel implements MouseMotionListener, MouseListener
{
	int appletWidth;		
	int appletHeight;	
	BufferedImage virtualMem;
	Graphics gBuffer;
	
	JTextField moneyGiven = new JTextField(10);
	JTextField cost = new JTextField(10);
	JLabel drinks = new JLabel();
	
	JTextField change = new JTextField(10);
	
	Rectangle cola,lime,grape,rootbeer,water;
	
	int[] supply = new int[5];
	
	
	public Problem14_7()
	{
		setLayout(new BorderLayout());
		addMouseMotionListener(this);
		addMouseListener(this);
		
		for(int i = 0; i<5; i++)
		{
			supply[i] = 20;
		}
		
		add(topPanel(), BorderLayout.NORTH);
		add(drinks, BorderLayout.CENTER);
		add(bottomPanel(), BorderLayout.SOUTH);
		
		drinks.setIcon(new ImageIcon(drawImage()));
		
	}
	
	
	public JPanel topPanel()
	{
		JPanel panel = new JPanel(new GridLayout(2,2,0,0));
		panel.add(new JLabel("Money Given"));
		panel.add(moneyGiven);
		panel.add(new JLabel("Cost"));
		panel.add(cost);
		cost.setText("$0.75");
		cost.setEditable(false);
		
		return panel;
		
	}
	
	public JPanel bottomPanel()
	{
		JPanel panel = new JPanel(new GridLayout(2,2,0,0));
		panel.add(new JLabel("Change"));
		panel.add(change);
		change.setEditable(false);
		
		return panel;
		
	}
	
	@Override
	public String getDescription()
	{
		return "7. DrinkMachine Applet"
+"\n\nWrite an applet that simulates a soft drink vending machine. The simulated machine dispenses the following soft drinks: cola, lemon-lime soda, grape soda, root beer, and bottled water." 
+"\nThese drinks cost $0.75 each to purchase."
+"\nWhen the applet starts, the drink machine should have a supply of 20 of each of the drinks. The applet should have a text field where the user can enter the amount of money he or she is "
+"\ngiving the machine. The user can then click on a button to select a drink to dispense. The applet should also display the amount of change it is giving back to the user. The applet should"
+"\nkeep track of its inventory of drinks and inform the user if he or she has selected a drink that is out of stock. Be sure to handle operator errors such as selecting a drink with no money" 
+"\nentered and selecting a drink with an inadequate amount of money entered.";
	}
	
	public Image drawImage()
	{
		virtualMem = new BufferedImage(300,400,BufferedImage.TYPE_INT_RGB);
		gBuffer = virtualMem.getGraphics();
		gBuffer.setColor(new Color(237,237,237));
		gBuffer.fillRect(0,0,300,400);
		
		Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		gBuffer.setFont(font);
		
		gBuffer.setColor(Color.RED);
		gBuffer.fillRect(150,25,125,40);
		 cola = new Rectangle(150,65,125,40);
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawString("Cola",165,50);
		gBuffer.drawString("("+supply[0]+")",100,50);
		
		gBuffer.setColor(Color.GREEN);
		gBuffer.fillRect(150,75,125,40);
		 lime = new Rectangle(150,115,125,40);
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawString("Lime Soda",150,100);
		gBuffer.drawString("("+supply[1]+")",100,100);
		
		gBuffer.setColor(Color.MAGENTA);
		gBuffer.fillRect(150,125,125,40);
		 grape = new Rectangle(150,165,125,40);
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawString("Grape",165,150);
		gBuffer.drawString("("+supply[2]+")",100,150);
		
		gBuffer.setColor(Color.GRAY);
		gBuffer.fillRect(150,175,125,40);
		 rootbeer = new Rectangle(150,215,125,40);
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawString("Root Beer",150,200);
		gBuffer.drawString("("+supply[3]+")",100,200);
		
		gBuffer.setColor(Color.BLUE);
		gBuffer.fillRect(150,225,125,40);
		 water = new Rectangle(150,265,125,40);
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawString("Water",165,250);
		gBuffer.drawString("("+supply[4]+")",100,250);
		
		
		
		return virtualMem;
	}
	
	public void transaction(int i)
	{
		String moneyEnter = moneyGiven.getText();
		double value = 0;
		
		if(moneyEnter.isEmpty()==false && moneyEnter.equals("Input Error")==false && moneyEnter.charAt(0)!='-')
		{
			moneyEnter = KDUtil.moneyInputValidation(moneyEnter);
			value = Double.parseDouble(moneyEnter);
		}
		
			
		if(supply[i]>0 && value>=0.75)
		{
			supply[i]--;
			change.setText("$"+(value-0.75));
			moneyGiven.setText("$"+(value-0.75));
			
		}
		else
			change.setText("ERROR");
			
		drinks.setIcon(new ImageIcon(drawImage()));
	}

	

	public void mousePressed(MouseEvent e) 
 	{			
		if(cola.contains(e.getX(),e.getY()))
		{
			transaction(0);
		}
		if(lime.contains(e.getX(),e.getY()))
		{
			transaction(1);
		}
		if(grape.contains(e.getX(),e.getY()))
		{
			transaction(2);
		}
		if(rootbeer.contains(e.getX(),e.getY()))
		{
			transaction(3);
		}
		if(water.contains(e.getX(),e.getY()))
		{
			transaction(4);
		}
	}
 	public void mouseReleased(MouseEvent e)  
  	{
		
   }
   public void mouseEntered(MouseEvent e)  
	{
		
   }
   public void mouseExited(MouseEvent e) 
	{

   }

   public void mouseClicked(MouseEvent e) 
	{

   }
	
	public void mouseMoved(MouseEvent e) 
	{

   }
	
	public void mouseDragged(MouseEvent e) 
	{

	}

	
}
						
	