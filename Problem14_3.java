

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------

public class Problem14_3 extends PaintApplet implements MouseMotionListener, MouseListener
{
	Image virtualMem;
	Graphics gBuffer;
	int appletWidth;		
	int appletHeight;	
	int x1,x2;
	
	int mouseX;
	int mouseY;
	
	int indexX = 0;
	int indexY = 0;
	
	double theta1, theta2, theta3, theta4;
	
	

	
	public void init()
	{
		appletWidth = 800;
		appletHeight = 700;	
		x1 = appletWidth/2-75;
		x2 = appletWidth/2+25;
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		virtualMem = createImage(appletWidth,appletHeight);
		gBuffer = virtualMem.getGraphics();
	}
	
	@Override
	public String getDescription()
	{
		return "3. WatchMe Applet"
+"\n\nWrite an applet that displays a drawing of two eyes in the center of its window. When the mouse cursor is not inside the window, the eyes should look ahead. "
+"\nWhen the mouse cursor is inside the window, the eyes should follow the cursor.";
	}
	

	public void paint(Graphics g)
	{	
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(0,0,appletWidth,appletHeight);
		gBuffer.setColor(Color.BLACK);
		
		gBuffer.drawOval(appletWidth/2-100,200,100,100);
		gBuffer.drawOval(appletWidth/2,200,100,100);
		
		//Rectangle r = new Rectangle(appletWidth/4,appletHeight/4,appletWidth/2,appletHeight/2);
		
		
		//if(r.contains(indexX,indexY)==true)
		//{
		gBuffer.fillOval(x1+(int)(15*(theta1-1.5)),225+(int)(15*(theta3-1.5)),50,50);
		gBuffer.fillOval(x2+(int)(15*(theta2-1.5)),225+(int)(15*(theta4-1.5)),50,50);
		//}else
		//{
		//gBuffer.fillOval(x1+indexX/15-25,225+indexY/15-25,50,50);
		//gBuffer.fillOval(x2+indexX/15-25,225+indexY/15-25,50,50);
		//}
		
		
				
		g.drawImage(virtualMem,0,0,this);
		repaint();
		
		
	}
	
	public void mousePressed(MouseEvent e) 
 	{

		
	}
 	public void mouseReleased(MouseEvent e)  
  	{
		
   }
   public void mouseEntered(MouseEvent e)  
	{
		mouseX = e.getX();
		mouseY = e.getY();
		

		double distance1 = calcDistance(x1,225,e.getX(),e.getY());
		double distance2 = calcDistance(x2,225,e.getX(),e.getY());
		
		double distanceX1 = x1-e.getX();
		double distanceX2 = x2-e.getX();
		
		theta1 = Math.acos(distanceX1/distance1);
		theta2 = Math.acos(distanceX2/distance2);
		
		distanceX1 = 225-e.getY();
		distanceX2 = 225-e.getY();
		
		theta3 = Math.acos(distanceX1/distance1);
		theta4 = Math.acos(distanceX2/distance2);

		
		
   }
   public void mouseExited(MouseEvent e) 
	{
		mouseX = appletWidth/2-75;
		mouseY = appletWidth/2+25;
		
		theta1 = 1.5;
		theta2 = 1.5;
		theta3 = 1.5;
		theta4 = 1.5;
		indexX = appletWidth/2;
		indexY = appletHeight/2;
   }

   public void mouseClicked(MouseEvent e) 
	{
	
   }
	
	public void mouseMoved(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();
		
		double distance1 = calcDistance(x1,225,e.getX(),e.getY());
		double distance2 = calcDistance(x2,225,e.getX(),e.getY());
		
		double distanceX1 = x1-e.getX();
		double distanceX2 = x2-e.getX();
		
		theta1 = Math.acos(distanceX1/distance1);
		theta2 = Math.acos(distanceX2/distance2);
		
		distanceX1 = 225-e.getY();
		distanceX2 = 225-e.getY();
		
		theta3 = Math.acos(distanceX1/distance1);
		theta4 = Math.acos(distanceX2/distance2);
		
		indexX = mouseX;
		indexY = mouseY;
		
		
		
   }
	
	public void mouseDragged(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

	
		
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public double calcDistance(int x, int y, int x2, int y2)
	{
		double distance = 0;
		
		distance = Math.abs(Math.sqrt(Math.pow(x2-x,2)+Math.pow(y2-y,2)));
		
		return distance;
	}
		
}
						
	