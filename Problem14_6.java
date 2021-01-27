

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------

public class Problem14_6 extends PaintApplet implements MouseMotionListener, MouseListener
{
	Image virtualMem;
	Graphics gBuffer;
	int appletWidth;		
	int appletHeight;	
	int size = 4;
	Rectangle[][] squares = new Rectangle[size][size];
	boolean[][] draw = new boolean[size][size];
	
	
	public void init()
	{
		appletWidth = 800;
		appletHeight = 700;	
		addMouseMotionListener(this);
		addMouseListener(this);
		
		virtualMem = createImage(appletWidth,appletHeight);
		gBuffer = virtualMem.getGraphics();
		
		for(int i = 0; i<size; i++)
			for(int k = 0; k<size; k++)
			squares[i][k] = new Rectangle((appletWidth*i)/size,(appletHeight*k)/size,appletWidth/size,appletHeight/size);
	}
	
	@Override
	public String getDescription()
	{
		return "6. GridFiller Applet"
+"\n\nWrite an applet that displays a 44 grid. When the user clicks on a square in the grid, the applet should draw a filled circle in it. If the square already has a circle," 
+"\nclicking on it should cause the circle to disappear.";
	}
	

	public void paint(Graphics g)
	{	
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(0,0,appletWidth,appletHeight);
		
	
		gBuffer.setColor(Color.BLACK);
		for(int i = 0; i<size; i++)
			for(int k = 0; k<size; k++)
			{
				gBuffer.drawRect((appletWidth*i)/size,(appletHeight*k)/size,appletWidth/size,appletHeight/size);
				if(draw[i][k] == true) gBuffer.fillOval((appletWidth*i)/size,(appletHeight*k)/size,appletWidth/size,appletHeight/size);
			}
		
		
		
		
		
		
		g.drawImage(virtualMem,0,0,this);
		repaint();
		
		
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}

	public void mousePressed(MouseEvent e) 
 	{
		int x = e.getX();
		int y = e.getY();
		
		for(int i = 0; i<size; i++)
			for(int k = 0; k<size; k++)
			{
				if(squares[i][k].contains(x,y)==true)
					draw[i][k] = !draw[i][k];
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
						
	