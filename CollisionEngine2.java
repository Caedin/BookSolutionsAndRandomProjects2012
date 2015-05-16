

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------

public class CollisionEngine2 extends PaintApplet
{
	
	Image virtualMem;
	Graphics gBuffer;
	int appletWidth;		
	int appletHeight;		
	boolean gravity = false;
	boolean wallBounce = false;
	boolean wallCollision = false;
	boolean ballCollision = true;
	boolean orbit = true;
	boolean spiral = false;
	boolean higherSpeed = false;
	boolean lowerSpeed = false;
	boolean testMode = false;
	boolean load = true;
	boolean friction = true;
	double startFrame;
	double endFrame;
	double fpsAvg = 0;
	double Temp = 0;
	double avgTemp = 0;
	boolean display = false;
	double currentFps;
	double loadTime;
	int maxCircles;
	double avgEnergy = 0;
	double frictionCoefficient = 0.9999;
	double frictionValue = 1;
	Font title = new Font("Arial",Font.BOLD,20);
	Font labels = new Font("Arial",Font.BOLD,14);
	
	int gainTempCounter = 0;
	int preRenderCounter = 0;

	
	int length = 200;
	int width = 200;
	int size = 100;
	
	int xPixels = 0;
	int yPixels = 0;
	int xPixelsMax = 1000;
	int yPixelsMax = 1000;
	Pixel[][] pixels = new Pixel[xPixelsMax+1][yPixelsMax+1];
	
	ArrayList<Circle> circles = new ArrayList();
	ArrayList<Double> fps = new ArrayList();
	ArrayList<Double> energy = new ArrayList();
	ArrayList<Image> preRender = new ArrayList();

	double sumOfDistances = 0;
	int centerOfMassX = 0;
	int centerOfMassY = 0;
	
	Point p1 = new Point(0,0);
	Point p2 = new Point(0,0);
	
	boolean resize = false;
	boolean complete = false;
	
	int counter = 0;
	
	Rectangle[] buttons = new Rectangle[11];
	
	public void init()
	{
		setSize(800,700);
		appletWidth = 800;
		appletHeight = 700;	
		virtualMem = createImage(appletWidth,appletHeight);
		gBuffer = virtualMem.getGraphics();
		
		for(int i = 0; i<11; i++)
		{
			buttons[i]=new Rectangle(670,50+50*i,100,50);
		}
	}
	
	@Override
	public String getDescription()
	{
		return "This is a program that demonstrates a collision engine for objects in 2D. "
									+"\n\n Wall Bounce = Balls bounce off the walls."
									+"\n Wall Collision = Balls break into smaller balls when hitting the walls."
									+"\n Ball Collision = Balls bounce off of each other."
									+"\n Gravity Mode = Gives all balls a constant falling acceleration"
									+"\n Orbit = Balls will orbit the center of the viewing field."
									+"\n Clear = removes all the balls from the screen."
									+"\n Speed Up = Increase all balls current speed."
									+"\n Slow Down = Slow down all balls current speed."
									+"\n Spiral = Clears the screen and causes new balls to spiral away into the center."
									+"\n Starburst = Clears the screen and creates an explosion of balls from the center."
									+"\n Friction = Causes all the balls to slowly lose velocity over time, and on collisions."
									+"\n Temperature = Balls get hotter when they collide, their color indicates their temperature, hot balls can fuse or explode.";
	}
	

	public void paint(Graphics g)
	{	
		String loadingString = "0%";
		if(load)
		{
			startFrame = System.nanoTime();
			while(load)
			{
						pixels[xPixels][yPixels] = new Pixel(false);
						
						if (xPixelsMax==xPixels && yPixelsMax==yPixels)
						{
							load=false;
						}
						else
						{
							if(yPixelsMax==yPixels) 
							{
								xPixels++; 
								yPixels=0;
							} 
							else yPixels++;
						}
						if(xPixels%100==0 && yPixels == 0)
						{
							gBuffer.setColor(Color.WHITE);
							gBuffer.fillRect(0,0,800,700);
							gBuffer.setColor(Color.BLACK);
							loadingString = "Loading  :  "+xPixels/10+"%";
							gBuffer.setColor(Color.BLACK);
							gBuffer.setFont(new Font("Arial",Font.BOLD, 30));
							gBuffer.drawString(loadingString,280,350);
							g.drawImage(virtualMem,0,0,this);
						}
						repaint();
						
			}
			circles.clear();
			endFrame = System.nanoTime()-startFrame;
			loadTime = endFrame/1000000000;
			if(testMode) System.out.println(loadTime);
			maxCircles = (int)Math.round(6000/loadTime);
			
			if(testMode) System.out.println("Max Circles:"+maxCircles);
			starBurst();
		}
			
			
			startFrame = System.nanoTime();
			gBuffer.setColor(Color.BLACK);
			gBuffer.fillRect(0,0,appletWidth,appletHeight);
			
			
			draw();
			
			
			gBuffer.setColor(Color.GRAY);
			gBuffer.fillRect(650,0,300,800);
			gBuffer.setColor(Color.WHITE);
			gBuffer.setFont(title);
			gBuffer.drawString("Ball Options",670,25);
			if(wallBounce==false) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);
			gBuffer.fillRect(670,50,100,50);
			gBuffer.setColor(Color.WHITE);
			gBuffer.setFont(labels);
			gBuffer.drawString("Wall Bounce",675,75);
			
			if(wallCollision==false) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);
			gBuffer.fillRect(670,100,100,50);
			gBuffer.setColor(Color.WHITE);
			gBuffer.drawString("Wall Collision",675,125);
			
			if(load==false)
			{
				if(ballCollision==false) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);		gBuffer.fillRect(670,150,100,50);
				gBuffer.setColor(Color.WHITE);
				gBuffer.drawString("Ball Collision",675,175);
			}
			else
			{
				if(ballCollision==false) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);		gBuffer.fillRect(670,150,100,50);
				gBuffer.setColor(Color.WHITE);
				gBuffer.drawString("Loading  :  "+xPixels/20+"%",675,172);
			}
			
			if(gravity==false) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);
			gBuffer.fillRect(670,200,100,50);
			gBuffer.setColor(Color.WHITE);
			gBuffer.drawString("Gravity Mode",675,225);
			
			if(orbit==false) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);
			gBuffer.fillRect(670,250,100,50);
			gBuffer.setColor(Color.WHITE);
			gBuffer.drawString("Orbit",675,275);
			
			gBuffer.setColor(Color.BLACK);
			gBuffer.fillRect(670,300,100,50);
			gBuffer.setColor(Color.WHITE);
			gBuffer.drawString("Clear",675,325);
			
			if(higherSpeed==false) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);
			gBuffer.fillRect(670,350,100,50);
			gBuffer.setColor(Color.WHITE);
			gBuffer.drawString("Speed Up",675,375);
			
			if(lowerSpeed==false) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);
			gBuffer.fillRect(670,400,100,50);
			gBuffer.setColor(Color.WHITE);
			gBuffer.drawString("Slow Down",675,425);
			
			if(spiral==false) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);
			gBuffer.fillRect(670,450,100,50);
			gBuffer.setColor(Color.WHITE);
			gBuffer.drawString("Spiral",675,475);
			
			gBuffer.setColor(Color.BLACK);
			gBuffer.fillRect(670,500,100,50);
			gBuffer.setColor(Color.WHITE);
			gBuffer.drawString("Star Burst",675,525);
			
			if(friction==false) gBuffer.setColor(Color.BLACK); else gBuffer.setColor(Color.RED);
			gBuffer.fillRect(670,550,100,50);
			gBuffer.setColor(Color.WHITE);
			gBuffer.drawString("Friction",675,575);
			
			
			gBuffer.setColor(Color.BLACK);
			gBuffer.fillRect(650,600,150,125);
			gBuffer.setColor(Color.WHITE);
			
			int tempPower = 0;
			while(Temp>=100000) 
			{
				Temp = Temp/10;
				tempPower++;
			}
			
			int avgPower = 0;
			while(avgTemp>=100000) 
			{
				avgTemp = avgTemp/10;
				avgPower++;
			}
			
			int enrPower = 0;
			while(avgEnergy>=100000) 
			{
				avgEnergy = avgEnergy/10;
				enrPower++;
			}
			
			gBuffer.drawString("MaxTemp: "+Math.round(Temp)+" x10^"+tempPower,630,625);
			gBuffer.drawString("AvgTemp: "+Math.round(avgTemp)+" x10^"+avgPower,630,645);
			gBuffer.drawString("Energy: "+Math.round(avgEnergy)+" x10^"+enrPower,630,665);
		
			if(testMode) gBuffer.drawString("FPS: "+Math.round(fpsAvg),675,685);
			
			
			gBuffer.setColor(Color.BLUE);
			
			delay(1000/60);
			g.drawImage(virtualMem,0,0,this);
			
			lowerSpeed = false;
			higherSpeed = false;
			
			
			endFrame = System.nanoTime() - startFrame;
			currentFps = (double)Math.round((17.5/(endFrame/1000000))*60);
			fps.add(currentFps);
			if(fps.size()>30) fps.remove(0);
			for(int  i = 0; i <fps.size(); i++) fpsAvg = fpsAvg + fps.get(i);
			fpsAvg = fpsAvg / fps.size();
			
			repaint();
	}
	
	public void draw()
	{
		gainTempCounter++;
		Temp = 0;
		avgTemp = 0;
		avgEnergy = 0;
		energy.clear();
		sumOfDistances = 0;
		centerOfMassX = 0;
		centerOfMassY = 0;
		
		for(int i = 0; i < circles.size(); i++)
		{
			if(circles.get(i).getTemperature()> Temp) Temp = circles.get(i).getTemperature();
			if(friction) circles.get(i).setFrictionValue(circles.get(i).getFrictionValue()*frictionCoefficient);
			avgTemp = avgTemp + circles.get(i).getTemperature();
			energy.add(circles.get(i).getEnergy());
			centerOfMassX = centerOfMassX + (int)circles.get(i).getX();
			centerOfMassY = centerOfMassY + (int)circles.get(i).getY();
			circles.get(i).draw(circles, fpsAvg, gBuffer,currentFps);
		}
		avgTemp = avgTemp/circles.size();
		Collections.sort(energy);
		
		if(energy.size()>0) avgEnergy= energy.get(energy.size()/2);
		if(circles.size()>1)
		{
			centerOfMassX = centerOfMassX/circles.size();
			centerOfMassY = centerOfMassY/circles.size();
		}
			
		for(int i = 0; i<circles.size(); i++)
		{
			circles.get(i).setCenter(centerOfMassX,centerOfMassY);
			if(gainTempCounter>60) circles.get(i).gainTemp=true;
			/*if(Temp>40000) 
			{
				circles.get(i).explosions=true;
				circles.get(i).fusions=false;
			} 
			
			if(Temp<100) 
			{
				circles.get(i).explosions=false;
				circles.get(i).fusions=true;
			} */
		
		}
		
		
		
		
		
		
			
		if(testMode)
		{
			gBuffer.setColor(Color.RED);
			gBuffer.fillRect(centerOfMassX,centerOfMassY, 5,5);
		}

	}
	
	public boolean mouseMove(Event e, int x, int y)
	{
		return true;
	}	
	
	public boolean mouseDrag(Event e, int x, int y)
	{
			if(x<600 && y<650  && circles.size()<maxCircles/2 && ballCollision==false)
			{
				Circle c = new Circle(x-size/4,y-size/4,gBuffer,size,true,pixels);
				c.setWallBounce(wallBounce);
				c.setWallCollision(wallCollision);
				c.setBallCollision(ballCollision);
				c.setVortex(orbit);
				c.setSpiral(spiral);
				c.setMaxCircles(maxCircles);
				circles.add(c);
			}
			
			if(buttons[6].contains(x,y)) 
			{
				higherSpeed =true;
				for(int i = 0; i<circles.size(); i++)
				{
					circles.get(i).setVelocityX(circles.get(i).getVelocityX()*1.1);
					circles.get(i).setVelocityY(circles.get(i).getVelocityY()*1.1);
				}
	
			}
			
			if(buttons[7].contains(x,y)) 
			{
				lowerSpeed = true;
				for(int i = 0; i<circles.size(); i++)
				{
					circles.get(i).setVelocityX(circles.get(i).getVelocityX()*.9);
					circles.get(i).setVelocityY(circles.get(i).getVelocityY()*.9);
				}
			}
	
	
		repaint();
		return true;
	}
	
	public boolean mouseDown(Event e, int x, int y)
	{
		if(x<600 && y<650  && circles.size()<maxCircles)
			{
			if(gravity==true)
			{
				Circle c = new Circle(x-size/4,y-size/4,gBuffer,size,true,pixels);
				c.setVelocity(0,0);
				c.setAccel(0,0.030);
				c.setWallBounce(wallBounce);
				c.setWallCollision(wallCollision);
				c.setBallCollision(ballCollision);
				c.setVortex(orbit);
				c.setMaxCircles(maxCircles);
				circles.add(c);
			}else
			{
			
				Circle c = new Circle(x-size/4,y-size/4,gBuffer,size,true,pixels);
				c.setWallBounce(wallBounce);
				c.setWallCollision(wallCollision);
				c.setBallCollision(ballCollision);
				c.setVortex(orbit);
				c.setMaxCircles(maxCircles);
				circles.add(c);
				
			}
		}
		
		if(buttons[0].contains(x,y)) 
		{
		
			wallBounce=!wallBounce;
			if(wallCollision && wallBounce) wallCollision = false;
			//circles = new ArrayList();
		}
		
		if(buttons[1].contains(x,y)) 
		{
		
			wallCollision=!wallCollision;
			if(wallCollision && wallBounce) wallBounce = false;
		}
		
		if(load==false)
		if(buttons[2].contains(x,y)) 
		{
		
			ballCollision=!ballCollision;
		}
		
		if(buttons[3].contains(x,y)) 
		{
		
			gravity=!gravity;
		}
		
		if(buttons[4].contains(x,y)) 
		{
		
			orbit=!orbit;
		}
		
		if(buttons[5].contains(x,y)) 
		{
			if(circles.size()>=1)
			{
				for(int i = 0; i<circles.size(); i++)
				{
					circles.get(i).clearPixels();
				}
				for(int i = 0; i<900; i++)
					for(int k = 0; k<700; k++)
						if(pixels[i][k].getValue()) 
						{
							//System.out.println(pixels[i][k].getValue() + "("+i+","+k+")");
							pixels[i][k].setValue(false);
						}
						
				circles = new ArrayList();
			}
		}
		
		if(buttons[9].contains(x,y)) 
		{
			starBurst();
		}
		
		if(buttons[6].contains(x,y)) 
		{
			higherSpeed =true;
			for(int i = 0; i<circles.size(); i++)
			{
				circles.get(i).setVelocityX(circles.get(i).getVelocityX()*1.1);
				circles.get(i).setVelocityY(circles.get(i).getVelocityY()*1.1);
			}

		}
		
		if(buttons[7].contains(x,y)) 
		{
			lowerSpeed = true;
			for(int i = 0; i<circles.size(); i++)
			{
				circles.get(i).setVelocityX(circles.get(i).getVelocityX()*.9);
				circles.get(i).setVelocityY(circles.get(i).getVelocityY()*.9);
			}
		}
		
		if(buttons[8].contains(x,y)) 
		{
			for(int i = 0; i<circles.size(); i++)
			{
				circles.get(i).clearPixels();
			}
			circles = new ArrayList();
			spiral = !spiral;
		}
		
		if(buttons[10].contains(x,y)) 
		{
			
			friction=!friction;
		}

		if(circles.size()>=1)
		for(int i = 0; i<circles.size(); i++)
		{
				circles.get(i).setWallBounce(wallBounce);
				circles.get(i).setWallCollision(wallCollision);
				circles.get(i).setBallCollision(ballCollision);
				circles.get(i).setVortex(orbit);
				circles.get(i).setSpiral(spiral);
				circles.get(i).setFriction(friction);
				if(gravity) 
				{
					circles.get(i).setAccel(0,0.030);
				}
				else circles.get(i).setAccel(0,0);
		}
		
		
		
		repaint();
		return true;
	}
	
	public boolean mouseUp(Event e, int x, int y)
	{
		
		
		repaint();
		return true;
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}

	public void delay(int n)
	{
		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.currentTimeMillis();	
	}
	
	public void starBurst()
	{
		for(int i = 0; i<circles.size(); i++)
		{
			circles.get(0).clearPixels();
		}
		
		circles = new ArrayList();
		for(int i = 0; i<900; i++)
			for(int k = 0; k<700; k++)
				if(pixels[i][k].getValue()) 
				{
					//System.out.println(pixels[i][k].getValue() + "("+i+","+k+")");
					pixels[i][k].setValue(false);
				}
		
		

			
		while(circles.size()<(maxCircles/4))
		{
			Circle c = new Circle(325,350,gBuffer,(int)(Math.pow(Math.random()*5,Math.random()*2)+5),false,pixels);
			c.setWallBounce(false);
			c.setWallCollision(false);
			c.setBallCollision(true);
			c.setVortex(true);
			c.setSpiral(false);
			c.setFriction(friction);
			c.setFrictionValue(1.0);
			c.setMaxCircles(maxCircles);
			circles.add(c);
		}
	gainTempCounter = 0;
		
	}

}



class Circle
{
	Graphics g;
	double x,y;
	double radius;
	double velocityX = 0;
	double velocityY = 0;
	double accelX = 0;
	double accelY = 0;
	double distance;
	double energy;
	int orbitCounter = 0;
	double currentDistance;
	boolean wallCollision = false;
	boolean wallBounce = true;
	boolean ballCollision = false;
	boolean vortex = false;
	boolean spiral = false;
	boolean gainTemp = false;
	boolean friction = false;
	double avgFps = 0;
	int maxCircles = 100;
	int offset = 50;
	Pixel[][] pixels = new Pixel[1000][1000];
	ArrayList<Point> currentPixels = new ArrayList();
	ArrayList<Integer> centerX = new ArrayList();
	ArrayList<Integer> centerY = new ArrayList();
	
	int centerOfMassX;
	int centerOfMassY;
	
	int[] xCoord;
	int[] yCoord;
	
	double counter = 0;
	double counter2 = 0;
	double frictionValue = 1;
	double forceX;
	double forceY;
	double explosionCounter = 0;
	
	boolean explosions = true;
	boolean fusions = true;
	
	Color c;
	
	double sides = 36;					// Variable for number of sides comparison
	double mass, mass2, temp1, temp2;		// Variables for collision reactions
	
	// Variables for setPixels()  
	boolean collision = false;
	double currentX;
	double currentY;
	double twoPI = 2*Math.PI;
	//
	
	Circle lastCollide;
	Rectangle area = new Rectangle(0,0,600,650);
	Rectangle area2 = new Rectangle(-45,-45,900,900);
	
	public Circle(int x, int y, Graphics g, int radius, boolean c, Pixel[][] pixels)
	{
		this.x=(int)x;
		this.y=(int)y;
		this.g=g;
		this.radius=(int)radius;
		if(c) 
		this.c = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
		this.pixels=pixels;
		
		while(velocityX==0 && velocityY==0)
		{
			velocityX = Math.random()*16-8;
			velocityY = Math.random()*16-8;
		}
		
		distance = Math.sqrt(Math.pow((this.x-350),2)+Math.pow((this.y-350),2));
		currentDistance = distance;
		double angle = Math.acos((x-350)/distance)*57.3;
		if(y<350) angle = -angle;
		counter =  (int)angle;

		//accelX = Math.random()-.5;
		//accelY = Math.random()-.5;
	}
	
	
	

	public void setMaxCircles(int i)	{maxCircles=i;}
	public void setWallCollision(boolean b) {wallCollision = b;}
	public void setBallCollision(boolean b) {ballCollision = b;}
	public void setColor(Color color) {c=color;}
	public void setWallBounce(boolean b) {wallBounce = b;}
	public void setVortex(boolean b) {vortex = b;}
	public void setSpiral(boolean b) {spiral = b;}
	
	public double getX() {return x;}
	public double getY() {return y;}
	public double getVelocityX() {return velocityX;}
	public double getVelocityY() {return velocityY;}
	public double getAccelX() {return accelX;}
	public double getAccelY() {return accelY;}
	public double getFrictionValue() {return frictionValue;}
	public double getEnergy() {return energy;}
	
	public void setFrictionValue(double x) {frictionValue = x;}
	public void setVelocityX(double x) {velocityX = x;}
	public void setVelocityY(double y) {velocityY = y;}
	public void setX(double x) { this.x = x;}
	public void setY(double y) { this.y = y;}
	public double getRadius() {return radius;}
	public void setFriction(boolean b) { friction = b;}
	public double getTemperature() { return explosionCounter;}
	public void addTemperature() { explosionCounter++;}
	public void setTemperature(double temp) { explosionCounter=temp;}
	
	
	public void setVelocity(double x, double y)
	{
		velocityX=x;
		velocityY=y;
	}
	
	public void setAccel(double x, double y)
	{
		accelX=x;
		accelY=y;
	}
	
	public void clearLastCollide()
	{
		lastCollide = null;
	}
	
	
	
	public void clearPixels()
	{
				for( int i = 0; i < currentPixels.size(); i++)
				{
					for(int roundFactor = -1; roundFactor<2; roundFactor++)
						for(int roundFactor2 = -1; roundFactor2<2; roundFactor2++)
							if(pixels[(int)currentPixels.get(i).getX()+roundFactor][(int)currentPixels.get(i).getY()+roundFactor2].getValue()) pixels[(int)currentPixels.get(i).getX()+roundFactor][(int)currentPixels.get(i).getY()+roundFactor2].setValue(false);
				}
				
				currentPixels.clear();

	}
	
	public boolean setPixels(ArrayList<Circle> circles)
	{
		collision = false;
		twoPI = 2*Math.PI;
		
		
		
		/// -------- This is a Circle based collision algorithm -------- //
		if(area2.contains(x,y))
		{
				sides = 36;
				if(avgFps<sides) sides = avgFps;
				if(avgFps<15) sides=avgFps/2;
				
				for( int r = 0; r<=radius/4; r++)
				for(int i = 0; i<sides; i++)
				{
					currentX =  (Math.round(Math.cos(twoPI * i/sides) * r + (x+offset+radius/4)));
					currentY =  (Math.round(Math.sin(twoPI * i/sides) * r +(y+offset+radius/4)));
					if(collideCheck((int)currentX,(int)currentY, circles) == true) 
					{
						collision = true;
						i=(int)sides;
					}
				}
		}

		/*					/// -------- This is a square based collision algorithm -------- //
		if(area2.contains(x,y))
		{
			for(int i = (int)(x+offset); i<(x+radius/3)+offset; i++)
					for(int k = (int)(y+offset); k<(y+radius/3)+offset; k++)
					{
						if(collideCheck(i,k, circles) == true) 
						{
							collision = true;
							i=(int)(x+radius/2)+offset;
							k=(int)(y+radius/2)+offset;
						}
					}
		}*/
			return collision;

	}
	
	public boolean collideCheck(int i, int k, ArrayList<Circle> circles)
	{
		if(i<1000 && k<1000)
		{
		boolean check = false;
		if(pixels[i][k].getValue()==false)
		{
			pixels[i][k].setValue(true);
			pixels[i][k].setOwner(this);
							
			currentPixels.add(new Point(i,k));
		}
		else
		{
			if(pixels[i][k].getOwner()!=this && circles.contains(pixels[i][k].getOwner() ))
			{
				double minimumDistance = radius/4 + pixels[i][k].getOwner().getRadius()/4;
				double actualDistance = Math.sqrt(Math.pow(x-pixels[i][k].getOwner().getX(),2)+Math.pow(y-pixels[i][k].getOwner().getY(),2));
				
				if(actualDistance<=minimumDistance)
				{
					double angle = Math.acos((-x+pixels[i][k].getOwner().getX())/actualDistance)*57.3;
					if(y>pixels[i][k].getOwner().getY()) angle = -angle;
					
					int newX = (int)(Math.round(Math.cos(twoPI * angle/360) * minimumDistance + x));
					int newY = (int)(Math.round(Math.sin(twoPI * angle/360) * minimumDistance + y));
					
					pixels[i][k].getOwner().clearPixels();
					pixels[i][k].getOwner().setX(newX);
					pixels[i][k].getOwner().setY(newY);
				}
				
				if(gainTemp)
				for(int z = 0; z<circles.size(); z++)
				{
				
					if((x/circles.get(z).getX())>0.9 && (x/circles.get(z).getX())<1.1  && (y/circles.get(z).getY())>0.9 && (y/circles.get(z).getY())<1.1) explosionCounter++;
				}
				
				Circle closest = pixels[i][k].getOwner();
				double chance = 14000+Math.pow(radius+closest.getRadius(),3.75);
								
				if(explosionCounter>chance && fusions && circles.size()>1)				/// FUSIONS
				{
					for(int z = 0; z<circles.size(); z++)
					{
							//circles.get(z).setFrictionValue(1-(1-circles.get(z).getFrictionValue())/5);
							//circles.get(z).setVelocityX(circles.get(z).getVelocityX()*((radius+closest.getRadius())/1)+Math.random()*6-3);
							//circles.get(z).setVelocityY(circles.get(z).getVelocityY()*((radius+closest.getRadius())/1)+Math.random()*6-3);
					}
					
					Circle c = new Circle((int)((x)),(int)((y)),g,(int)(radius+closest.getRadius()/3),false,pixels);
					c.setVelocityX(0);
					c.setVelocityY(0);
					c.setColor(this.c);
					c.setWallBounce(wallBounce);
					c.setFriction(friction);
					c.setWallCollision(wallCollision);
					c.setBallCollision(ballCollision);
					c.setVortex(vortex);
					c.setSpiral(spiral);
					c.setMaxCircles(maxCircles);
					c.setTemperature(explosionCounter+closest.getTemperature());
					
					c.setBallCollision(true);
					circles.add(c);
		
					clearPixels();
					circles.remove(this);
					
					closest.clearPixels();
					circles.remove(closest);
					
					
				}
				
				chance = (double)((double)circles.size()/(double)maxCircles);
				
				if(explosions && chance<=.005  && explosionCounter>1000000)				/// EXPLOSIONS
				{
					int size = circles.size();
					int[] xs = new int[circles.size()];
					int[] ys = new int[circles.size()];
					
					for( int z = 0; z<circles.size(); z++)
					{
						circles.get(z).clearPixels();
						xs[z]=(int)circles.get(z).getX();
						ys[z]=(int)circles.get(z).getY();
					}
					circles.clear();
					
					for(int count = 0; count<size; count++)
					{
						for(int h = 0; h <maxCircles/(size*4); h++)
						{
							Circle c = new Circle((int)(xs[count]+radius/4),(int)(ys[count]+radius/4),g,(int)(Math.pow(Math.random()*5,Math.random()*2)+5),false,pixels);
							c.setVelocityX((Math.random()-.5)*50);
							c.setVelocityY((Math.random()-.5)*50);
							c.setColor(this.c);
							c.setWallBounce(wallBounce);
							c.setFriction(friction);
							c.setWallCollision(wallCollision);
							c.setBallCollision(ballCollision);
							c.setVortex(vortex);
							c.setSpiral(spiral);
							c.setMaxCircles(maxCircles);
							c.setTemperature(0);
								
							c.setBallCollision(true);
							circles.add(c);
						}
					}
				}	
				

		
				
				mass = (radius);
				mass2 = pixels[i][k].getOwner().getRadius();
				
						
				temp1 = (velocityX*(mass/(mass2)));
				temp2 = (velocityY*(mass/(mass2)));
				double temperature = ((pixels[i][k].getOwner().getTemperature()+explosionCounter)/2+1)*1.001;
										
				velocityX = (pixels[i][k].getOwner().getVelocityX())*(mass2/(mass));
				velocityY = (pixels[i][k].getOwner().getVelocityY())*(mass2/(mass));
				explosionCounter = temperature;
				if(explosionCounter<0) explosionCounter=0;
				
				if(friction)
				{
					velocityX=velocityX*frictionValue;
					velocityY=velocityY*frictionValue;
					temp1=temp1*frictionValue;
					temp2=temp2*frictionValue;
					
					// Increase speed based on temperature //
					/*velocityX=velocityX+velocityX*(explosionCounter/160000);
					velocityY=velocityY+velocityY*(explosionCounter/160000);
					
					temp1=temp1+temp1*(pixels[i][k].getOwner().getTemperature()/160000);
					temp2=temp2+temp2*(pixels[i][k].getOwner().getTemperature()/160000);*/
				}
										
				pixels[i][k].getOwner().setVelocityX(temp1);
				pixels[i][k].getOwner().setVelocityY(temp2);
				pixels[i][k].getOwner().setTemperature(explosionCounter);				
				
				
									
				lastCollide = pixels[i][k].getOwner();
					
				lastCollide.clearPixels();
				clearPixels();
				check = true;
			   }
			}
			return check;
		}else return false;

	}
	
	public void setCenter(int x, int y)
	{
		centerOfMassX=x;
		centerOfMassY=y;
	}

	
	public void draw(ArrayList<Circle> circles, double fps, Graphics g, double currentFps)
	{
	
		area = new Rectangle(0,0,650-(int)(radius/2),700-(int)(radius/2));
		
		
		if(explosionCounter<0) explosionCounter=0;
		if(explosionCounter<20000) g.setColor(new Color((int)(255*(explosionCounter/20000)),(int)(255*(explosionCounter/20000)),255));
		else g.setColor(Color.WHITE);
		clearPixels();
		avgFps = fps;
		if(radius<5) {clearPixels(); circles.remove(this);}
		energy = ((Math.pow(Math.sqrt((Math.pow(velocityX,2)+Math.pow(velocityY,2))),2)*radius))/2+((Math.pow(explosionCounter,2)*radius))/2;
		
		
		//if(x<-50 || x>1050 || y<-50 || y>1050) {clearPixels(); circles.remove(this);}
		
		
		//if(velocityX>50) velocityX = 50;
		//if(velocityX<-50) velocityX = -50;
		//if(velocityY>50) velocityY = 50;
		//if(velocityY<-50) velocityY = -50;
		
		
		
		if(spiral==true)
		{
			double currentX =  (Math.round(Math.cos(twoPI * counter/sides) * (distance-counter2) + 350));
			double currentY =  (Math.round(Math.sin(twoPI * counter/sides) * (distance-counter2) + 350));
			currentDistance = Math.sqrt(Math.pow((350-currentX),2)+Math.pow((350-currentY),2));
			
			//velocityX = ((int)(Math.round(Math.cos(twoPI * counter+1/sides) * Math.sqrt(Math.pow((350-x),2)+Math.pow((350-y),2)) + 350)) - (int)(Math.round(Math.cos(twoPI * counter/sides) * Math.sqrt(Math.pow((350-x),2)+Math.pow((350-y),2)) + 350)))/10;
			//velocityY = ((int)(Math.round(Math.cos(twoPI * counter+1/sides) * Math.sqrt(Math.pow((350-x),2)+Math.pow((350-y),2)) + 350)) - (int)(Math.round(Math.cos(twoPI * counter/sides) * Math.sqrt(Math.pow((350-x),2)+Math.pow((350-y),2)) + 350)))/10;
			
			//currentX=currentX+(int)velocityX;
			//currentY=currentY+(int)velocityY;
			
			int currentRadius = (int)(radius/2*(currentDistance/350));

			g.fillOval((int)currentX,(int)currentY,currentRadius,currentRadius);
			
			if(currentDistance<5) {clearPixels(); circles.remove(this);}
			counter++;
			counter2++;
		}
		
		if(spiral==false)
		{
			g.fillOval((int)x,(int)y,(int)radius/2,(int)radius/2);
			x=x+velocityX;
			y=y+velocityY;
		}
				
		if(ballCollision) 
		{
			if(setPixels(circles)==false && spiral==false) 
			{
				clearLastCollide();
				if(explosionCounter>0)explosionCounter=explosionCounter-1;
			}
			else 
			{
			//explosionCounter=(explosionCounter+1);
			}
		}
		
		if(friction)
		{
			velocityX=velocityX*((frictionValue+1000)/1001);
			velocityY=velocityY*((frictionValue+1000)/1001);
		}
			
		
		
		velocityX = velocityX+accelX;
		velocityY = velocityY+accelY;
		
		
	
		
		if(area.contains((int)x,(int)y)==false && wallCollision == true) 
		{
			radius = radius*.75;
			if(x>600 || x<0+(radius/2)) velocityX=-velocityX;
			if(y>650 || y<0+(radius/2))velocityY=-velocityY;
			
			int xOffset = 0;
			int yOffset = 0;
			
			if(x>650) xOffset = (int)(-radius/2);
			if(x<0+(radius/2)) xOffset = (int)(+radius/2);
			
			if(y>650) yOffset = (int)(-radius/2);
			if(y<0+(radius/2)) yOffset = (int)(+radius/2);
			
			if(circles.size()<maxCircles && avgFps>30)
			{
				Circle c = new Circle((int)x+xOffset,(int)y+yOffset,g,(int)radius,false,pixels);
				if(y>650 || y<0+(radius/2)) c.setVelocityX(velocityX*Math.random()*2-1); else c.setVelocityX(velocityX);
				if(x>600 || x<0+(radius/2)) c.setVelocityY(velocityY*Math.random()*2-1); else c.setVelocityY(velocityY);
				c.setColor(this.c);
				c.setWallBounce(wallBounce);
				c.setWallCollision(wallCollision);
				c.setBallCollision(ballCollision);
				c.setFriction(friction);
				c.setVortex(vortex);
				c.setSpiral(spiral);
				c.setMaxCircles(maxCircles);
				circles.add(c);
			}
			
			
			
		
		}
		
		if(vortex==true)
		{
			centerOfMassX = 300;
			centerOfMassY = 350;
			//if(orbitCounter<398000) orbitCounter=orbitCounter+200;
			forceX = (Math.pow(centerOfMassX-x,2))/(400000-orbitCounter);
			if(x>350) forceX=-forceX;
			forceY = (Math.pow(centerOfMassY-y,2))/(400000-orbitCounter);
			if(y>350) forceY=-forceY;
			
			velocityX = velocityX + forceX;
			velocityY = velocityY +forceY;
			//System.out.println(400000-orbitCounter);
			
		}
		else orbitCounter = 0;
		
		if(spiral==true)
		{
			
			
		}
		
		if(area.contains((int)x,(int)y)==false && wallBounce == true) 
		{
			clearPixels();
			if(x>=650-(radius/2)) { velocityX = -velocityX; x = 645-(radius/2); }
			if(x<=5) { velocityX = -velocityX;  x = 10; }
			if(y>=700-(radius/2)) {velocityY = -velocityY; y = 695-(radius/2);}
			if(y<=5){ velocityY = -velocityY; y = 10;}
			//if(ballCollision) setPixels(circles);
		}
		
		
		
	}
}

class Pixel
{
	boolean used = false;
	boolean hasOwner = false;
	Circle c;
	
	public Pixel(boolean b, Circle c)
	{
		used=b;
		this.c=c;
		hasOwner = true;
	}
	
	public Pixel(boolean b)
	{
		used=b;
	}
	
	public Pixel()
	{
	
	}
	
	public void setValue(boolean b)
	{
		used=b;
	}
	
	public boolean getValue()
	{
		return used;
	}
	
	public Circle getOwner()
	{
		return c;
	}
	
	public void setOwner(Circle c)
	{
		this.c=c;
		hasOwner = true;
	}
	
	public boolean getHasOwner()
	{
		return hasOwner;
	}
	
}
