// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------

class Problem9_9  extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel panel = new JPanel();
	
	JLabel[] labels = new JLabel[10];
	JTextField[] fields = new JTextField[10];
	JTextField[] fieldsOut = new JTextField[10];
	JButton[] calculate = new JButton[10];
	JPanel[] fieldPanel = new JPanel[10];

	JLabel image = new JLabel();
	
	Image tempImage;
	Graphics gBuffer;

	public Problem9_9()
	{
		panel.setLayout(new BorderLayout());
		buildPanel();
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "9. Geometry Calculator\n"+
"Design a Geometry class with the following methods:\n"
+" A static method that accepts the radius of a circle and returns the area of the circle. Use the following formula:\n"
+"Area = (pi)r2\n"
+"Use Math.PI for pi and the radius of the circle for r.\n"
+" A static method that accepts the length and width of a rectangle and returns the area of the rectangle. Use the following formula:\n"
+"Area = Length  Width\n"
+" A static method that accepts the length of a triangles base and the triangles height. The method should return the area of the triangle. Use the following formula:\n"
+"Area = Base  Height  0.5\n"
+"The methods should display an error message if negative values are used for the circles radius, the rectangles length or width, or the triangles base or height.\n";
	}
	
	
	
	private void drawImage(int x, int y, String shape)
	{
			// Prevents overflow type errors
			if(x>4000) x=4000;
			if(y>4000) y=4000;
		
			// Graphics objects for drawing our object
			tempImage = createImage(x,y);
			gBuffer = tempImage.getGraphics();
			
			// This makes the edges of our shape blend in with the JPanel background
			gBuffer.setColor(new Color(237,237,237));
			gBuffer.fillRect(0,0,x,y);
			
			if(shape.equals("Triangle"))
			{
				gBuffer.setColor(Color.GREEN);
				
				Polygon poly = new Polygon();
				poly.addPoint(x/2,0);
				poly.addPoint(x,y);
				poly.addPoint(0,y);
				
				gBuffer.fillPolygon(poly);
			}
			
			if(shape.equals("Circle"))
			{
				gBuffer.setColor(Color.BLUE);
				gBuffer.fillOval(0,0,x,y);
				
			}
			
			if(shape.equals("Rectangle"))
			{
				gBuffer.setColor(Color.RED);
				gBuffer.fillRect(0,0,x,y);
				
			}
			
	}

	
	private void buildPanel()
	{
				JPanel header = new JPanel();
				final JPanel shapePanel = new JPanel();
				shapePanel.setLayout(new GridLayout(0,3));
				
				JLabel head = new JLabel("Welcome to the Geometry Class Demonstration");
				header.add(head);
				JPanel grid = new JPanel();
				grid.setLayout(new GridLayout(4,0));
				
				final DecimalFormat format = new DecimalFormat("0.0");
				
					
				
					labels[0] = new JLabel("Calculate the area of a circle ");
					fields[0] = new JTextField(10);
					fieldsOut[0] = new JTextField(10);
					calculate[0] = new JButton("Calculate");
					fieldPanel[0] = new JPanel();
					
					fieldPanel[0].add(labels[0]);
					fieldPanel[0].add(fields[0]);
					fieldPanel[0].add(calculate[0]);
					fieldPanel[0].add(fieldsOut[0]);
					grid.add(fieldPanel[0]);
					
					calculate[0].addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent arg0) 
						{
							String s = fields[0].getText();
							if(s.length()!=0) 
							{
								double x = Double.parseDouble(s);
								
								if(x>0) // Prevents errors with negative input
								{
									panel.remove(image);				// Clear current image
									drawImage((int)(2*x),(int)(2*x),"Circle");			// Draw new image
									fieldsOut[0].setText(format.format(Geometry.circle(x)));			// Calculate area
									ImageIcon icon = new ImageIcon(tempImage);		// Add new image to panel
									image = new JLabel(icon);
									panel.add(image, BorderLayout.SOUTH);
								}
								
								topPanel.revalidate();
								topPanel.repaint();
								
							}
							
						}
					});					
					
					
					
					labels[1] = new JLabel("Calculate the area of a Rectangle ");
					fields[1] = new JTextField(10);
					fields[2] = new JTextField(10);
					fieldsOut[1] = new JTextField(10);
					calculate[1] = new JButton("Calculate");
					fieldPanel[1] = new JPanel();
					
					fieldPanel[1].add(labels[1]);
					JPanel temp = new JPanel();
					temp.setLayout(new GridLayout(2,0));
					temp.add(fields[1]);
					temp.add(fields[2]);
					fieldPanel[1].add(temp);
					fieldPanel[1].add(calculate[1]);
					fieldPanel[1].add(fieldsOut[1]);
					grid.add(fieldPanel[1]);
					
					calculate[1].addActionListener(new ActionListener() 
					{
							public void actionPerformed(ActionEvent arg0) 
						{
							String s = fields[1].getText();
							String s1 = fields[2].getText();
							if(s.length()!=0 && s1.length()!=0) 
							{
								double x = Double.parseDouble(s);
								double y = Double.parseDouble(s1);
								
								if(x>0 && y>0)
								{
									panel.remove(image);
									drawImage((int)(x),(int)(y),"Rectangle");
									fieldsOut[1].setText(format.format(Geometry.rectangle(x,y)));
									ImageIcon icon = new ImageIcon(tempImage);
									image = new JLabel(icon);
									panel.add(image, BorderLayout.SOUTH);
								}
								
								topPanel.revalidate();
								topPanel.repaint();
								
							}
							
						}
					});	
					
					labels[2] = new JLabel("Calculate the area of a Triangle ");
					fields[3] = new JTextField(10);
					fields[4] = new JTextField(10);
					fieldsOut[2] = new JTextField(10);
					calculate[2] = new JButton("Calculate");
					fieldPanel[2] = new JPanel();
					
					fieldPanel[2].add(labels[2]);
					temp = new JPanel();
					temp.setLayout(new GridLayout(2,0));
					temp.add(fields[3]);
					temp.add(fields[4]);
					fieldPanel[2].add(temp);
					fieldPanel[2].add(calculate[2]);
					fieldPanel[2].add(fieldsOut[2]);
					grid.add(fieldPanel[2]);
					
					calculate[2].addActionListener(new ActionListener() {
					
							public void actionPerformed(ActionEvent arg0) 
						{
							String s = fields[3].getText();
							String s1 = fields[4].getText();
							if(s.length()!=0 && s1.length()!=0) 
							{
								double x = Double.parseDouble(s);
								double y = Double.parseDouble(s1);
								
								if(x>0 && y>0)
								{
									panel.remove(image);
									drawImage((int)(x),(int)(y),"Triangle");
									fieldsOut[2].setText(format.format(Geometry.triangle(x,y)));
									ImageIcon icon = new ImageIcon(tempImage);
									image = new JLabel(icon);
									panel.add(image, BorderLayout.SOUTH);
								}
								
								topPanel.revalidate();
								topPanel.repaint();
								
							}
							
						}
					});			
							
					
				
				JPanel wrapper = new JPanel(new BorderLayout());
				wrapper.add(header, BorderLayout.NORTH);
				wrapper.add(grid, BorderLayout.CENTER);
				
				JPanel wrapperLeft = new JPanel(new FlowLayout(FlowLayout.LEFT,125,0));
				wrapperLeft.add(wrapper);
				
				panel.add(wrapperLeft, BorderLayout.CENTER);
				topPanel.add(panel);
				
	}
	
	static private class Geometry				// Geometry class returns area values.
	{
		static public double circle(double r)
		{
			return Math.PI*Math.pow(r,2);
		}
		
		static public double rectangle(double r, double s)
		{
			return r*s;
		}
		
		static public double triangle(double r, double s)
		{
			return (r*s)/2;
		}
	}
	
}
