

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import java.io.*;
import java.awt.image.AffineTransformOp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;
import javax.imageio.ImageIO;
// -----------------------------------------------



public class LOSDemo extends PaintPanel
{

	LOSDisplay LOS = new LOSDisplay();
	BufferedImage background;
	PaintPanel LOSPanel = this;
	JMenuBar menuBar = new JMenuBar();
	JMenuBar subMenu = new JMenuBar();
	JMenu options = new JMenu("Settings");
	JMenu modes = new JMenu("Layers");



	public LOSDemo()
	{
		setPreferredSize(new Dimension(700,600));
		setLayout(new BorderLayout());
		
		add(topPanel(), BorderLayout.NORTH);
		add(LOS, BorderLayout.CENTER);
		LOS.addMouseMotionListener(LOS);
		LOS.addMouseListener(LOS);
				
		this.repaint();
		this.revalidate();
	}
	

	
	
	public JPanel topPanel()
	{
		JPanel topPanel = new JPanel(new BorderLayout());

		
		final JMenuItem drawMap = new JMenuItem("Shapes");
		drawMap.setToolTipText("Allows you to create a map");
		final JMenuItem addLights = new JMenuItem("Lights");
		addLights.setToolTipText("Allows you to add Light Sources");
		
		final JMenuItem detail = new JMenuItem("Details");
		detail.setToolTipText("Change quality of image");
		final JMenuItem addShape = new JMenuItem("New Shape");
		addShape.setToolTipText("Creates a new shape");
		final JMenuItem clearLights = new JMenuItem("Remove Lights");
		
		final JCheckBoxMenuItem followLight = new JCheckBoxMenuItem("Mouse Light");
		followLight.setState(true);
		followLight.setToolTipText("Turns your mouse into a light source.");
		final JMenuItem loadBackground = new JMenuItem("Load Image");
		loadBackground.setToolTipText("Load a background image");
		final JMenuItem clearBackground = new JMenuItem("Clear Image");
		clearBackground.setToolTipText("Clear a background image");
		final JMenuItem colorChooser = new JMenuItem("Choose Color");
		colorChooser.setToolTipText("Choose a color for your new shape");
		
		final JCheckBoxMenuItem whiteBackGround = new JCheckBoxMenuItem("Background");
		colorChooser.setToolTipText("Turns on or off a white background.");
		
		
		final JPanel colorChooserPanel = new JPanel();
		final JLabel currentColor = new JLabel();
		
		colorChooserPanel.add(currentColor);
		colorChooserPanel.add(colorChooser);
		BufferedImage virtualMem;
		Graphics gBuffer;
		virtualMem = new BufferedImage(10,10,BufferedImage.TYPE_INT_ARGB);
		gBuffer = virtualMem.getGraphics();
		gBuffer.setColor(Color.BLACK);
		gBuffer.fillRect(0,0,40,20);
		currentColor.setIcon(new ImageIcon(virtualMem));
		
		
		final JMenuBar shapeChooserBar = new JMenuBar();
		final JMenu shapeChooser = new JMenu("Choose Drawing Method");
		shapeChooserBar.add(shapeChooser);
		shapeChooser.setToolTipText("Select a method of drawing");
		final JPanel shapeChooserPanel = new JPanel();
		final JLabel currentShape = new JLabel("Free-Draw");
		
		final JMenuItem drawModeRectangle = new JMenuItem("Rectangle");
		final JMenuItem drawModeTriangle = new JMenuItem("Triangle");
		final JMenuItem drawModeFree = new JMenuItem("Free-Draw");
		
		shapeChooser.add(drawModeRectangle);
		//shapeChooser.add(drawModeTriangle);
		shapeChooser.add(drawModeFree);
		
		shapeChooserPanel.add(currentShape);
		shapeChooserPanel.add(shapeChooserBar);
		
		final JMenu file = new JMenu("File");
		final JMenuItem save = new JMenuItem("Save");
		final JMenuItem load = new JMenuItem("Load");


		
		file.add(save);
		file.add(load);
		
		menuBar.add(file);
		menuBar.add(options);
		menuBar.add(modes);
		
		options.add(detail);
		options.add(clearBackground);
		options.add(loadBackground);
		
		subMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		drawMap.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				LOS.layer("Map");
				subMenu.removeAll();
				subMenu.add(colorChooserPanel);
				subMenu.add(shapeChooserPanel);
				subMenu.add(whiteBackGround);
				subMenu.revalidate();
			}			
		});
		addLights.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				LOS.layer("Light");
				subMenu.removeAll();
				subMenu.add(followLight);
				subMenu.add(clearLights);
				subMenu.revalidate();

			}			
		});
		
		detail.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				options();
			}			
		});
		
		followLight.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				LOS.followLight();
			}			
		});
		
		drawModeRectangle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				currentShape.setText("Rectangle");
				LOS.setDrawType("Rectangle");
			}			
		});
		
		drawModeTriangle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				currentShape.setText("Triangle");
				LOS.setDrawType("Triangle");
			}			
		});
		
		drawModeFree.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				currentShape.setText("Free-Draw");
				LOS.setDrawType("Free-Draw");
			}			
		});

		addShape.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				LOS.addShape();
			}			
		});
		clearLights.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				LOS.clearLights();
			}			
		});
		
		loadBackground.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try{
				int status;
				JFileChooser fileChooser = new JFileChooser();
				status = fileChooser.showOpenDialog(null);
				if(status == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = fileChooser.getSelectedFile();
					String fileName = selectedFile.getPath();
					
					try{background = ImageIO.read(selectedFile);}catch(IOException e){}
					
					LOS.setBackgroundImage(background);
				}
				}catch(java.security.AccessControlException e){JOptionPane.showMessageDialog(null, "Error Reading File", "Error Reading File", JOptionPane.ERROR_MESSAGE);};
			}			
		});
		clearBackground.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				LOS.clearImage();
			}			
		});
		colorChooser.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Color c = JColorChooser.showDialog(null,"Select a Color", Color.RED);
				LOS.setSelectedColor(c);
				
				BufferedImage virtualMem;
				Graphics gBuffer;
				virtualMem = new BufferedImage(10,10,BufferedImage.TYPE_INT_ARGB);
				gBuffer = virtualMem.getGraphics();
				gBuffer.setColor(c);
				gBuffer.fillRect(0,0,40,20);
				
				currentColor.setIcon(new ImageIcon(virtualMem));
			}			
		});
		whiteBackGround.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				LOS.setBackground(whiteBackGround.isSelected());
			}			
		});
		

		LOS.layer("Map");
		subMenu.removeAll();
		subMenu.add(colorChooserPanel);
		subMenu.add(shapeChooserPanel);
		subMenu.add(whiteBackGround);
		subMenu.revalidate();

		
		modes.add(drawMap);
		modes.add(addLights);
		
		topPanel.add(menuBar, BorderLayout.NORTH);
		topPanel.add(subMenu, BorderLayout.CENTER);
		
		return topPanel;
	}

	public JPanel bottomPanel()
	{
		JPanel centerPanel = new JPanel(new BorderLayout());

		
		return centerPanel;
	}
	
	public JPanel centerPanel()
	{
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		return centerPanel;
	}
	
	public void options()
	{
		final JFrame input = new JFrame();
		
		
		final JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel header = new JPanel();
		header.add(KDUtil.wrap(new JLabel("Select Options")));
		
		topPanel.add(header, BorderLayout.NORTH);
		
		JLabel brightness = new JLabel("Brightness");
		JLabel resolution = new JLabel("Detail");
		final JSlider brightSlider = new JSlider(JSlider.HORIZONTAL, 1,18,LOS.brightness);
		brightSlider.setMajorTickSpacing(4);
		brightSlider.setMinorTickSpacing(1);
		brightSlider.setPaintTicks(true);
		brightSlider.setPaintLabels(true);
		
		final JSlider detailSlider = new JSlider(JSlider.HORIZONTAL, 25,400,LOS.resolution);
		detailSlider.setMajorTickSpacing(100);
		detailSlider.setMinorTickSpacing(25);
		detailSlider.setPaintTicks(true);
		detailSlider.setPaintLabels(true);
		
		JLabel shadows = new JLabel("Shadow Detail");
		final JSlider shadowSlider = new JSlider(JSlider.HORIZONTAL, 0,1,LOS.accuracy);
		shadowSlider.setMajorTickSpacing(1);
		shadowSlider.setPaintTicks(true);
		shadowSlider.setPaintLabels(true);
		
		JPanel centerPanel = new JPanel(new GridLayout(3,2,5,5));
		centerPanel.add(KDUtil.wrap(brightness));
		centerPanel.add(KDUtil.wrap(brightSlider));
		centerPanel.add(KDUtil.wrap(resolution));
		centerPanel.add(KDUtil.wrap(detailSlider));
		centerPanel.add(KDUtil.wrap(shadows));
		centerPanel.add(KDUtil.wrap(shadowSlider));
		
		topPanel.add(centerPanel,BorderLayout.CENTER);
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				LOS.setBrightness(brightSlider.getValue());
				LOS.setResolution(detailSlider.getValue());
				LOS.setAccuracy(shadowSlider.getValue());
				input.setVisible(false);
			}			
		});
		
		topPanel.add(KDUtil.wrap(save), BorderLayout.SOUTH);
		
		input.add(topPanel);
		input.pack();
		input.setVisible(true);
	}
	

	
	

	@Override
	public String getDescription()
	{
		return "This program demonstrates shadows and line of sight using light sources.";
	}





private class LOSDisplay extends JComponent implements MouseMotionListener, MouseListener
{
	BufferedImage virtualMem;
	Graphics gBuffer;
	
	int appletWidth;		
	int appletHeight;	
	int resolution = 250;
	int brightness = 2;
	int accuracy = 1;
	BufferedImage image;
	boolean presetImage = false;
	boolean followLight = true;
	
	
	boolean layerLight = true;
	boolean layerMap = false;
	
	Obstruction background = new Obstruction();
	
	String drawType = "Free-Draw";
	
	
	Shader[][] colors;
	
	Point oldLight = new Point(-1,0);
	
	ArrayList<Obstruction> walls = new ArrayList();
	Obstruction temp = new Obstruction();
	
	boolean updateDisplay = true;
	
	Color selectedColor = new Color(0,0,0);
	Color lightColor = Color.WHITE;
	
	public void setSelectedColor(Color c)
	{
		selectedColor = c;
	}
	
	public void setLightColor(Color c)
	{
		lightColor = c;
	}
	
	public void setDrawType(String s)
	{
		drawType = s;
	}
	
	public void setBackground(boolean b)
	{
		if(b==true) 
		{
			background.fillObstruction();
			for(int i = 0; i<walls.size(); i++)
				walls.get(i).fillObstruction();
			//walls.add(background);
		}
		if(b==false) 
		{
			background.clearObstruction();
			for(int i = 0; i<walls.size(); i++)
				walls.get(i).fillObstruction();
			//walls.remove(background);
		}
	}
	
	
	
	
	public void setBrightness(int x)
	{
		brightness = x;
		clearLights();
		
		updateDisplay = true;
	}
	public void setResolution(int x)
	{
		resolution = x;
		
		for(int i = 0; i<walls.size(); i++)
			walls.get(i).setResolution(resolution/10);
			
		updateDisplay = true;
	}
	
	public void setAccuracy(int x)
	{
		accuracy = x;
		updateDisplay = true;
	}
	
	public void layer(String s)
	{
		if(s.equals("Light"))
		{
			layerLight = true;
			layerMap = false;
		}
		if(s.equals("Map"))
		{
			layerLight = false;
			layerMap = true;
		}
		
		clearLights();
		updateDisplay = true;
	}

	public void setBackgroundImage(BufferedImage image)
	{
		presetImage = true;
		this.image = image;
		scaleImage();
		drawBackground();	
		walls.clear();
	}
	
	public void clearImage()
	{
		walls.clear();
		presetImage = false;
		drawBackground();
		updateDisplay =true;
	}
	
	public void followLight()
	{
		clearLights();
		followLight = !followLight;
	}
	
	public void drawBackground()
	{
		if(presetImage==false)
		{
			for(int i = 0; i<appletWidth; i++)
			{
				for(int k = 0; k<appletHeight; k++)
				{
					colors[i][k] = new Shader(new Color(255,255,255));
				}
			}
		}
		else
		{
			int w = image.getWidth();
	   	int h = image.getHeight();
	   	
			if(w>=appletWidth) w=appletWidth-1;
			if(h>=appletHeight) h=appletHeight-1;
			
		   for (int i = 0; i < w; i++) 
		   for (int j = 0; j < h; j++) 
		  	{
				colors[i][j] = new Shader(new Color(image.getRGB(i,j)));
		  	}

		}
		
	}
	
	public void scaleImage()
	{

		int w = image.getWidth();
		int h = image.getHeight();
		
		double scaleX = appletWidth/w;
		double scaleY = appletHeight/h;
		
		
		BufferedImage resized = new BufferedImage(appletWidth, appletHeight, image.getType());
  		Graphics2D g = resized.createGraphics();
   	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
   	g.drawImage(image, 0, 0, appletWidth, appletHeight, 0, 0, image.getWidth(), image.getHeight(), null);
   	g.dispose();
		
		image = resized;
	}

	
	public LOSDisplay()
	{
		setSize(700,600);
		appletWidth = 700;
		appletHeight = 600;	
		
		background.addPoint(0,0);
		background.addPoint(0,appletHeight);
		background.addPoint(appletWidth,appletHeight);
		background.addPoint(appletWidth,0);
		background.setColor(Color.WHITE);
		
		virtualMem = new BufferedImage(appletWidth,appletHeight,BufferedImage.TYPE_INT_ARGB);
		gBuffer = virtualMem.getGraphics();
		
		colors = new Shader[appletWidth][appletHeight];
		
		//new wall
		clearShapes();

		
		
	}
	public void clearLights()
	{
		for(int i = 0; i<appletWidth; i=i+appletWidth/resolution)
			{
				for(int k = 0; k<appletHeight; k=k+appletHeight/resolution)
				{		
					colors[i][k].clearLight();
				}
			}
				
		oldLight = new Point(-1,-1);
		updateDisplay =true;
	}
	
	public void clearShapes()
	{
		walls.clear();
		drawBackground();	
		updateDisplay =true;
	}
	
	public void addShape()
	{
		for(int i = 0; i < walls.size(); i++)
		{
			walls.get(i).setResolution(resolution/10);
			walls.get(i).fillObstruction();
		}
		updateDisplay = true;
	}
	
	public void paintComponent(Graphics g) 
	{
      super.paintComponent(g);
		if(updateDisplay)
		{
			virtualMem = new BufferedImage(appletWidth,appletHeight,BufferedImage.TYPE_INT_ARGB);
			gBuffer = virtualMem.getGraphics();
			
			// DRAWS DISPLAY
			if(layerLight)
				gBuffer.setColor(Color.BLACK);
			if(layerMap)
				gBuffer.setColor(Color.WHITE);
				
			gBuffer.fillRect(0,0,appletWidth,appletHeight);
			
			for(int i = 0; i<appletWidth; i=i+appletWidth/resolution)
			{
				for(int k = 0; k<appletHeight; k=k+appletHeight/resolution)
				{	
					if(colors[i][k].getDraw()==true)
					{
						if(layerLight)
						{
							Color c = (colors[i][k].getColor());
							gBuffer.setColor(c);
						}
						else
						{
							int colorR = (int)(colors[i][k].getRed());
							int colorG = (int)(colors[i][k].getGreen());
							int colorB = (int)(colors[i][k].getBlue());
							
							Color c = (new Color(colorR,colorG,colorB));
							gBuffer.setColor(c);
						}
						
						
						gBuffer.fillRect(i,k,appletWidth/resolution,appletHeight/resolution);
					}
				}
			}	
			

					
			updateDisplay = false;
		}
		
		g.drawImage(virtualMem,0,0,null);
		repaint();
	}
	
	
	public void mousePressed(MouseEvent e) 
 	{
		if(layerLight)
		{
			
			for(int i = 0; i<appletWidth; i=i+appletWidth/resolution)
			{
				for(int k = 0; k<appletHeight; k=k+appletHeight/resolution)
				{	
					if(colors[i][k].getDraw()==true)
					{
						double colorValue;
						double distance = calcDistance(i,k,(int)e.getX(),(int)e.getY());
						double obstruct = calcObstruction(i,k,(int)e.getX(),(int)e.getY());
							
						if(brightness/2>1)
							colorValue = 255*(brightness/2);
						else
							colorValue = 255;
								
						double shadows = 0.99;
						shadows = Math.pow(shadows,distance);
						colorValue = colorValue*shadows;
						if(obstruct>1)
						{
							colorValue = colorValue/obstruct;
						}
							
						if(colorValue>255)	colorValue = 255;
						if(colorValue<0) colorValue = 0;
						
						colors[i][k].addLight((int)colorValue);
					}
				}
			}	

			updateDisplay=true;
		}
		if(layerMap)
		{
			temp = new Obstruction();
			temp.setColor(selectedColor);
			
			if(drawType.equals("Free-Draw"))
			{
				temp.addPoint(e.getX(),e.getY());
				temp.fillObstruction();
				updateDisplay = true;
			}
			if(drawType.equals("Rectangle"))
			{
				temp.addPoint(e.getX(),e.getY());
				temp.fillObstruction();
				updateDisplay = true;
			}
			if(drawType.equals("Triangle"))
			{
				temp.addPoint(e.getX(),e.getY());
				temp.fillObstruction();
				updateDisplay = true;
			}


		}
		
	}
 	public void mouseReleased(MouseEvent e)  
  	{
		if(layerMap)
		{
			if(drawType.equals("Rectangle"))
			{
				Rectangle r = temp.getBounds();
				
				Obstruction rectangle = new Obstruction();
				rectangle.addPoint(r.x,r.y);
				rectangle.addPoint(r.x+r.width,r.y);
				rectangle.addPoint(r.x+r.width,r.y+r.height);
				rectangle.addPoint(r.x,r.y+r.height);
				rectangle.setColor(selectedColor);
				
				temp = rectangle;
				
				temp.fillObstruction();
				updateDisplay = true;
			}
			if(drawType.equals("Triangle"))
			{
				temp.addPoint(e.getX(),e.getY());
				temp.fillObstruction();
				updateDisplay = true;
			}

			temp.setResolution(resolution/10);
			walls.add(temp);
			addShape();
		}

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
		if(layerLight)
		{
			
	      if(followLight)
			{
					if(oldLight.getX()!=-1)
					{
						for(int i = 0; i<appletWidth; i=i+appletWidth/resolution)
						{
							for(int k = 0; k<appletHeight; k=k+appletHeight/resolution)
							{	
								if(colors[i][k].getDraw()==true)
								{
									double colorValue;
									double distance = calcDistance(i,k,(int)oldLight.getX(),(int)oldLight.getY());
									double obstruct = calcObstruction(i,k,(int)oldLight.getX(),(int)oldLight.getY());
										
									if(brightness/2>1)
										colorValue = 255*(brightness/2);
									else
										colorValue = 255;
											
									double shadows = 0.99;
									shadows = Math.pow(shadows,distance);
									colorValue = colorValue*shadows;
									
									if(obstruct>1)
									{
										colorValue = colorValue/obstruct;
									}
										
									if(colorValue>255)	colorValue = 255;
									if(colorValue<0) colorValue = 0;
									
									colors[i][k].subLight((int)colorValue);
								}
							}	
						}
					}
				
				for(int i = 0; i<appletWidth; i=i+appletWidth/resolution)
				{
					for(int k = 0; k<appletHeight; k=k+appletHeight/resolution)
					{	
						if(colors[i][k].getDraw()==true)
						{
							double colorValue;
							double distance = calcDistance(i,k,(int)e.getX(),(int)e.getY());
							double obstruct = calcObstruction(i,k,(int)e.getX(),(int)e.getY());
								
							if(brightness/2>1)
								colorValue = 255*(brightness/2);
							else
								colorValue = 255;
									
							double shadows = 0.99;
							shadows = Math.pow(shadows,distance);
							colorValue = colorValue*shadows;
							if(obstruct>1)
							{
								colorValue = colorValue/obstruct;
							}
								
							if(colorValue>255)	colorValue = 255;
							if(colorValue<0) colorValue = 0;
							
							colors[i][k].addLight((int)colorValue);
						}
					}
				}	
			oldLight = new Point(e.getX(), e.getY());
			

			updateDisplay=true;
			}
		}

   }
	
	public void mouseDragged(MouseEvent e) 
	{
      if(layerMap)
		{
			if(drawType.equals("Free-Draw"))
			{
				temp.addPoint(e.getX(),e.getY());
				temp.fillObstruction();
				updateDisplay = true;
			}
			if(drawType.equals("Rectangle"))
			{
				temp.addPoint(e.getX(),e.getY());
				
				/*Rectangle r = temp.getBounds();
				
				Obstruction rectangle = new Obstruction();
				rectangle.addPoint(r.x,r.y);
				rectangle.addPoint(r.x+r.width,r.y);
				rectangle.addPoint(r.x+r.width,r.y+r.height);
				rectangle.addPoint(r.x,r.y+r.height);
				rectangle.setColor(selectedColor);
				
				temp = rectangle;*/
				
				temp.fillObstruction();
				updateDisplay = true;

			}
			if(drawType.equals("Triangle"))
			{
				temp.addPoint(e.getX(),e.getY());
				temp.fillObstruction();
				updateDisplay = true;
			}


		}
   }
	 
	/*public double calcLight(int i, int k)					// Returns a value between 0 and 1, indicating how bright the location is
	{
		double light = 0;
		double colorSum = 0;
		double colorValue = 0;
		
					if(lightSource.size()>0)
					{
						for(int p = 0; p<lightSource.size(); p++)
						{
							double distance = calcDistance(i,k,(int)lightSource.get(p).getX(),(int)lightSource.get(p).getY());

							double obstruct = calcObstruction(i,k,(int)lightSource.get(p).getX(),(int)lightSource.get(p).getY());
							
							if(brightness/2>1)
								colorValue = 255*(brightness/2);
							else
								colorValue = 255;
								
							double shadows = 0.99;
							shadows = Math.pow(shadows,distance);
							colorValue = colorValue*shadows;
							if(obstruct>1)
								colorValue = Math.pow(colorValue,1/(obstruct-1));
							
							if(colorValue>255)	colorValue = 255;
							if(colorValue<0) colorValue = 0;


							colorSum = colorSum + colorValue;

						}
						
						if(colorSum>255)	colorSum = 255;
						if(colorSum<0) colorSum = 0;
						
					}
		
		light = colorSum/255;
		
		return light;
	}*/
	
	public double calcDistance(int x, int y, int x2, int y2)
	{
		double distance = 0;
		
		distance = Math.abs(Math.sqrt(Math.pow(x2-x,2)+Math.pow(y2-y,2)));
		
		return distance;
	}
	
	public int calcObstruction(int x, int y, int x2, int y2)
	{
		if(accuracy == 0) return 0;
		
		int numIntersections = 0;
		
		Line2D.Double line = new Line2D.Double(x,y,x2,y2);
		

		for(int i = 0; i<walls.size(); i++)
		{
			for(int k = 0; k<walls.get(i).npoints; k++)
			{
				if(walls.get(i)==background) break;
				
				if(k==walls.get(i).npoints-1)
				{
					if(line.intersectsLine(new Line2D.Double(walls.get(i).xpoints[k],walls.get(i).ypoints[k],walls.get(i).xpoints[0],walls.get(i).ypoints[0])))
					{
						numIntersections++;
					}
	
				}	else
				if(line.intersectsLine(new Line2D.Double(walls.get(i).xpoints[k],walls.get(i).ypoints[k],walls.get(i).xpoints[k+1],walls.get(i).ypoints[k+1])))
				{
					numIntersections++;
				}
			}
		}

		
		return numIntersections+1;
	}
	
	private class Shader 
	{
		private Color c;
		private int colorValue = 0;
		private double shadeValue = 0;
		boolean draw = false;
		
		public Shader(Color c)
		{
			this.c = c;
			
		}
		
		public Color getColor()
		{
			calcShade();
			
			double CR = c.getRed()*shadeValue;
			double CB = c.getBlue()*shadeValue;
			double CG = c.getGreen()*shadeValue;
			
			if(CR>255) CR = 255;	if (CR<0) CR = 0;
			if(CB>255) CB = 255;	if (CB<0) CB = 0;
			if(CG>255) CG = 255;	if (CR<0) CG = 0;
			
			return new Color((int)CR,(int)CG,(int)CB);
		}
		
		public int getRed() {return c.getRed();}
		public int getGreen() {return c.getGreen();}
		public void setDraw(boolean b) {draw = b;}
		public boolean getDraw() {return draw;}
		public int getBlue() {return c.getBlue();}
		public void setColor(Color c) {this.c = c;}
		
		private void calcShade()
		{
			shadeValue = (double)((double)colorValue/255);
			
			if(shadeValue>1) shadeValue = 1;
			if(shadeValue<0) shadeValue = 0;
			
		}
		
		public void addLight(int r)
		{
			
			colorValue = colorValue+r;
			//if(colorValue>255) colorValue = 255;
			
		}
		public void subLight(int r)
		{
			colorValue = colorValue-r;
			//if(colorValue<0) colorValue = 0;
		}
		
		public void clearLight() {colorValue = 0;}
	}
	
	private class Obstruction extends Polygon
	{
		Color c;
		
		public void setColor(Color c)
		{
			this.c=c;
		}
	
		
		public void setResolution(int d)
		{
			if(d<4) d=4;
			
			Obstruction temp = new Obstruction();
			while(npoints>d)
			{
				for(int i = 0; i<npoints; i=i+2)
				{
					temp.addPoint(xpoints[i],ypoints[i]);	
				}
				reset();
				for(int i = 0; i<temp.npoints; i++)
				{
					//System.out.println(npoints);	
					addPoint(temp.xpoints[i],temp.ypoints[i]);	
				}
				temp = new Obstruction();
				
				
				
			}
		}
		
		public void fillObstruction()
		{
			Rectangle r = this.getBounds();
			Rectangle map = new Rectangle(0,0,appletWidth,appletHeight);
			
			for(double i = r.getX(); i<r.getWidth()+r.getX(); i++)
			{
				for(double k = r.getY(); k<r.getHeight()+r.getY(); k++)
				{
					if(this.contains(i,k) && map.contains(i,k))
					{
						colors[(int)i][(int)k].setColor(c);
						colors[(int)i][(int)k].setDraw(true);
					}
				}
			}
		}
		
		public void clearObstruction()
		{
			Rectangle r = this.getBounds();
			Rectangle map = new Rectangle(0,0,appletWidth,appletHeight);
			
			for(double i = r.getX(); i<r.getWidth()+r.getX(); i++)
			{
				for(double k = r.getY(); k<r.getHeight()+r.getY(); k++)
				{
					if(this.contains(i,k) && map.contains(i,k))
					{
						colors[(int)i][(int)k].setDraw(false);
					}
				}
			}
		}
		
	}
		
}

}
						
	