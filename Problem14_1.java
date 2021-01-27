

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------

public class Problem14_1 extends PaintApplet implements MouseMotionListener, MouseListener
{
	Image virtualMem;
	Graphics gBuffer;
	int appletWidth;		
	int appletHeight;	
	
	int x = appletWidth/2;
	int y = appletHeight/2;
	
	public void init()
	{
		appletWidth = 800;
		appletHeight = 700;	
		addMouseMotionListener(this);
		addMouseListener(this);
		
		virtualMem = createImage(appletWidth,appletHeight);
		gBuffer = virtualMem.getGraphics();
	}
	
	@Override
	public String getDescription()
	{
		return "1. FollowMe Applet"
+"\n\nWrite an applet that initially displays the word Hello in the center of a window. The word should follow the mouse cursor when it is moved inside the window.";
	}
	

	public void paint(Graphics g)
	{	
		gBuffer.setColor(new Color(237,237,237));
		gBuffer.fillRect(0,0,appletWidth,appletHeight);
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawString("Hello",x,y);
	
		
		g.drawImage(virtualMem,0,0,this);
		repaint();
		
		
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}

	public void mousePressed(MouseEvent e) 
 	{
		
		
	}
 	public void mouseReleased(MouseEvent e)  
  	{
		
   }
   public void mouseEntered(MouseEvent e)  
	{
		this.x = e.getX();
		this.y = e.getY();
   }
   public void mouseExited(MouseEvent e) 
	{
		x = 350;
		y = 350;
   }

   public void mouseClicked(MouseEvent e) 
	{
	
   }
	
	public void mouseMoved(MouseEvent e) 
	{
		this.x = e.getX();
		this.y = e.getY();
   }
	
	public void mouseDragged(MouseEvent e) 
	{
		this.x = e.getX();
		this.y = e.getY();
	}

	
}
						
	