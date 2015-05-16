

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------

public class Problem14_5 extends PaintApplet implements MouseMotionListener, MouseListener
{
	Image virtualMem;
	Graphics gBuffer;
	int appletWidth;		
	int appletHeight;	
	
	int x = appletWidth/2;
	int y = appletHeight/2;
	Polygon temp = new Polygon();
	
	ArrayList<Point> points = new ArrayList();
	
	ArrayList<Polygon> polys = new ArrayList();
	
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
		return "5. Polygon Drawer"
+"\n\nWrite an applet that lets the user click on six points. After the sixth point is clicked, the applet should draw a polygon with a vertex at each point the user clicked.";
	}
	

	public void paint(Graphics g)
	{	
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(0,0,appletWidth,appletHeight);
		gBuffer.setColor(Color.BLACK);
		
		gBuffer.setColor(Color.BLUE);
		for(int i = 0; i <polys.size(); i++)
		{
			gBuffer.fillPolygon(polys.get(i));
		}
		//gBuffer.fillPolygon(temp);
		
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawString("Polygon Drawing Program",200,100);
		gBuffer.drawString("("+points.size()%6+"/6)",200,150);
		
		
	
		
		g.drawImage(virtualMem,0,0,this);
		repaint();
		
		
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}

	public void mousePressed(MouseEvent e) 
 	{
		points.add(new Point(e.getX(), e.getY()));
		temp.addPoint(e.getX(), e.getY());
		
		if(points.size()%6==0)
		{
			polys.add(temp);
			temp = new Polygon();
		}
		
		
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
		//points.add(new Point(e.getX(), e.getY()));
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
						
	