

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------

public class ConnectFour extends PaintApplet implements MouseMotionListener, MouseListener
{
	Image virtualMem;
	Graphics gBuffer;
	int appletWidth;		
	int appletHeight;	
	int size = 7;
	int j = 0;
	Rectangle[][] squares = new Rectangle[size][size];
	boolean[][] draw = new boolean[size][size];
	
	boolean[][] color = new boolean[size][size];
	boolean black = true;
	boolean update = true;
	
	boolean winner = false;
	boolean winningColor = false;
	boolean playable = true;
	
	ArrayList<Point> animate = new ArrayList();
	
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
		return "Connect Four Game";
	}
	

	public void paint(Graphics g)
	{	
			if(update)
			{
				gBuffer.setColor(Color.WHITE);
				gBuffer.fillRect(0,0,appletWidth,appletHeight);
				
				gBuffer.setColor(Color.BLACK);
				
				for(int i = 0; i<size; i++)
					for(int k = 0; k<size; k++)
					{
						gBuffer.drawRect((appletWidth*i)/size,(appletHeight*k)/size,appletWidth/size,appletHeight/size);
					}
					
				for(int i = 0; i<size; i++)
					for(int k = 0; k<size; k++)
					{
						if(draw[i][k] == true) 
						{
							if(color[i][k]==true)
								gBuffer.setColor(Color.BLACK);
							else
								gBuffer.setColor(Color.RED);
							
							gBuffer.fillOval((appletWidth*i)/size,(appletHeight*k)/size,appletWidth/size,appletHeight/size);
						}
					}
				
				
				if(winner==true)
				{
				if(winningColor==true) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);
				Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
				gBuffer.setFont(font);
				gBuffer.drawString("WINNER!", appletWidth/2,100);
				}
				
				update=false;
			}
			if(animate.size()>0)
			{
				if(black==false) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);
				if(j<(animate.get(0).getY()*appletHeight)/(size*3))
					{
						gBuffer.fillOval((appletWidth*(int)animate.get(0).getX())/size,j*3,appletWidth/size,appletHeight/size);
						j++;
						update=true;
					}
				else 
				{
					draw[(int)animate.get(0).getX()][(int)animate.get(0).getY()] = true;
					animate.remove(0);
					checkWinner();
					playable = true;
					update=true;
				}
			}

				
		
		
		
		
		
		g.drawImage(virtualMem,0,0,this);
		repaint();
		
		
	}
	
	public void reloadGame()
	{
		for(int i = 0; i<size; i++)
		{
			for(int k = 0; k<size;k++)
			{
				color[i][k]=false;
				draw[i][k]=false;
				squares[i][k] = new Rectangle((appletWidth*i)/size,(appletHeight*k)/size,appletWidth/size,appletHeight/size);
			}
		}
		winner = false;
	}
	
	public void checkWinner()
	{
		int blackScore = 0;
		int redScore = 0;
		for(int i = 0; i<size; i++)
			for(int k = 0; k<size; k++)
			{
				if(draw[i][k]==true && winner==false) checkAdjacent(i,k,color[i][k]);
			}
	}
	
	public void checkAdjacent(int i, int k, boolean color)				
	{
		int c = 0;
		int score = 0;
		try{
		while(draw[i+c][k+c]==true && this.color[i+c][k+c]==color)
		{
			score=score+1;
			c++;	
			if(score>=4)	winner = true;
		}
		
		}catch(java.lang.ArrayIndexOutOfBoundsException e){}
		c = 0;
		score = 0;
		
		try{
		while(draw[i+c][k-c]==true && this.color[i+c][k-c]==color)
		{
			score=score+1;
			c++;	
			if(score>=4)	winner = true;
		}
		}catch(java.lang.ArrayIndexOutOfBoundsException e){}
		
		try{
		c = 0;
		score = 0;
		while(draw[i-c][k+c]==true && this.color[i-c][k+c]==color)
		{
			score=score+1;
			c++;	
			if(score>=4)	winner = true;
		}
		}catch(java.lang.ArrayIndexOutOfBoundsException e){}


		try{
		c = 0;
		score = 0;
		while(draw[i+c][k+c]==true && this.color[i+c][k+c]==color)
		{
			score=score+1;
			c--;	
			if(score>=4)	winner = true;
		}
		}catch(java.lang.ArrayIndexOutOfBoundsException e){}
		try{
		c = 0;
		score = 0;
		while(draw[i][k+c]==true && this.color[i][k+c]==color)
		{
			score=score+1;
			c++;	
			if(score>=4)	winner = true;
		}
		}catch(java.lang.ArrayIndexOutOfBoundsException e){}
		try{
		c = 0;
		score = 0;
		while(draw[i][k+c]==true && this.color[i][k+c]==color)
		{
			score=score+1;
			c--;	
			if(score>=4)	winner = true;
		}
		}catch(java.lang.ArrayIndexOutOfBoundsException e){}
		try{
		c = 0;
		score = 0;
		while(draw[i+c][k]==true && this.color[i+c][k]==color)
		{
			score=score+1;
			c++;	
			if(score>=4)	winner = true;
		}
		}catch(java.lang.ArrayIndexOutOfBoundsException e){}
		try{
		c = 0;
		score = 0;
		while(draw[i+c][k]==true && this.color[i+c][k]==color)
		{
			score=score+1;
			c--;	
			if(score>=4)	winner = true;
		}
		}catch(java.lang.ArrayIndexOutOfBoundsException e){}
		
		if(winner ==true) winningColor = color;
		
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public int checkBelow(int i, int k)
	{
		while(k<size-1) 
		{
			if(draw[i][k+1]==true) return k;
			k=k+1;
		}
		
		return k;
	}



	public void mousePressed(MouseEvent e) 
 	{
		int x = e.getX();
		int y = e.getY();
		
		if(winner==false && playable == true)
		for(int i = 0; i<size; i++)
			for(int k = 0; k<size; k++)
			{
				if(squares[i][k].contains(x,y)==true && draw[i][k]==false)
				{
					int c = checkBelow(i,k);
					
					//draw[i][c] = true;
					color[i][c] = black;
					black=!black;
					animate.add(new Point(i,c));
					j=0;
					playable = false;
					
					break;
				}
			}
		if(winner==true)
		{
			reloadGame();
		}
		update=true;

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
						
	