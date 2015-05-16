

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------

public class Problem14_2 extends PaintApplet
{
	Image virtualMem;
	Graphics gBuffer;
	int appletWidth;		
	int appletHeight;	
	
	boolean leftWindow = false;
	boolean rightWindow = false;
	boolean door = false;
	

	
	public void init()
	{
		appletWidth = 800;
		appletHeight = 700;	
		
		virtualMem = createImage(appletWidth,appletHeight);
		gBuffer = virtualMem.getGraphics();
	}
	
	@Override
	public String getDescription()
	{
		return "2. House Applet"
+"\n\nWrite an applet that draws the house shown on the left in Figure 14-33. When the user clicks on the door or windows, they should close. "
+"\nThe figure on the right shows the house with its door and windows closed.";
	}
	

	public void paint(Graphics g)
	{	
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(0,0,appletWidth,appletHeight);
		gBuffer.setColor(Color.BLACK);
		

		Polygon roof = new Polygon();
		roof.addPoint(appletWidth/2+25,0);
		roof.addPoint(25,150);
		roof.addPoint(appletWidth-25,150);
		
		gBuffer.drawRect(35,150,appletWidth-70,200);
		
		
		if(leftWindow)
			drawWindow(100,200, 50);
		else
			fillWindow(100,200,50);
			
		if(rightWindow)
			drawWindow(appletWidth-200,200, 50);
		else
			fillWindow(appletWidth-200,200, 50);
			
		
		if(door)
		{
			gBuffer.drawRect((appletWidth-35)/2-20,175,100,175);
			gBuffer.fillOval((appletWidth-35)/2+50,275,10,10);
		}
		else
		{
			gBuffer.fillRect((appletWidth-35)/2-20,175,100,175);
		}
		
		
		gBuffer.drawPolygon(roof);
	
		
		g.drawImage(virtualMem,0,0,this);
		repaint();
		
		
	}
	
	public void drawWindow(int x, int y, int z)
	{
		int size = z;
		
		gBuffer.drawRect(x,y,size,size);
		gBuffer.drawRect(x+size,y,size,size);
		gBuffer.drawRect(x,y+size,size,size);
		gBuffer.drawRect(x+size,y+size,size,size);
	}
	
	public void fillWindow(int x, int y, int z)
	{
		int size = z;
		
		gBuffer.fillRect(x,y,size,size);
		gBuffer.fillRect(x+size,y,size,size);
		gBuffer.fillRect(x,y+size,size,size);
		gBuffer.fillRect(x+size,y+size,size,size);
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public boolean mouseDown(Event e, int x, int y)
	{
		if(x>100 && x<200 && y>200 && y<300)
			leftWindow = !leftWindow;
		if(x>600 && x<700 && y>200 && y<300)
			rightWindow = !rightWindow;
		if(x>(appletWidth-35)/2-20 && x<(appletWidth-35)/2+80 && y>175 && y<350)
			door = !door;


			
		
		return true;
	}
	
}
						
	