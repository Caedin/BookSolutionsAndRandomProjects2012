

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------



// --------  RoomDimension ----- ///

class RoomDimension
{
	double length;
	double width;
	
	public RoomDimension(double length, double width)
	{
		this.length = length;
		this.width = width;
		
	}
	
	public double getArea()
	{
		return length*width;
	}
	
	public double getLength() {return length;}
	public double getWidth() {return width;}
}

/// ------     RoomCarpet ------- //
class RoomCarpet 
{
	RoomDimension size;
	double carpetCost;
	
	public RoomCarpet(RoomDimension size, double cost)
	{
		this.size=size;
		carpetCost = cost;
	}
	
	public double getTotalCost()
	{
		double totalCost;
		totalCost = size.getArea() * carpetCost;
		return totalCost;
	}

	public double getArea()
	{
		return size.getArea();
	}
	
	public String toString()
	{
		String x;
		x = "Room size is " + size.getArea() + " square feet.";
		x = x + "\nTotal cost is : $"+getTotalCost();
		return x;
	}
}


class Problem9_3  extends PaintPanel
{
	PaintFrame topPanel = new PaintFrame();
	JPanel panel = new JPanel();
	JLabel imageLabel = new JLabel();
	
	Point p1 = new Point(0,0);
	Point p2 = new Point(0,0);
	
	JTextField totalField;
	JTextField totalCostField;
	JTextField carpetWidthInput;
	JTextField carpetLengthInput;
	
	boolean clicked = false;
	

	@Override
	public String getDescription()
	{
		return "3. Carpet Calculator"
+"\nThe Westfield Carpet Company has asked you to write an application that calculates the price of carpeting for rectangular rooms. To calculate the price, you multiply the area of the \nfloor (width times length) by the price per square foot of carpet. For example, the area of floor that is 12 feet long and 10 feet \nwide is 120 square feet. To cover that floor with carpet that costs $8 per square foot would cost $960. (12  10  8 = 960.)"
+"\nFirst, you should create a class named RoomDimension that has two fields: one for the length of the room and one for the width. The RoomDimension class should have a method that returns \nthe area of the room. (The area of the room is the rooms length multiplied by the rooms width.)"
+"\nNext you should create a RoomCarpet class that has a RoomDimension object as a field. It should also have a field for the cost of the carpet per square foot. The RoomCarpet class should have a method that returns the total cost of the carpet."
+"\nFigure 9-21 is a UML diagram that shows possible class designs and the relationships among the classes. Once you have written these classes, use them in an application that asks the user \nto enter the dimensions of a room and the price per square foot of the desired carpeting. The application should display the total cost of the carpet.";
	}
	
	public Problem9_3()
	{
		int WINDOW_WIDTH = 1000;
		int WINDOW_HEIGHT = 1000;
		
		topPanel.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		
		buildPanel();
		setVisible(true);
	}
	
	private void buildPanel()
	{
		JLabel header = new JLabel("Welcome to Carpet Cost Calculator!");
		JPanel headerPanel = new JPanel();
		headerPanel.add(header);
		panel.add(headerPanel, BorderLayout.NORTH);
		
		
		JPanel fields = new JPanel();
		JPanel fields2 = new JPanel();
		
		fields.setLayout(new GridLayout(4,2));
		
		JLabel carpetWidth = new JLabel("Carpet Width:");
		carpetWidthInput = new JTextField();
		carpetWidthInput.setText("200");
		fields.add(carpetWidth);
		fields.add(carpetWidthInput);
		JLabel carpetLength = new JLabel("Carpet Length:");
		carpetLengthInput = new JTextField();
		carpetLengthInput.setText("200");
		fields.add(carpetLength);
		fields.add(carpetLengthInput);
		JLabel carpetCost = new JLabel("Carpet Cost/Square Foot:");
		final JTextField carpetCostInput = new JTextField();
		carpetCostInput.setText("5");
		
		fields.add(carpetCost);
		fields.add(carpetCostInput);
		
		
		JButton calculate = new JButton("Calculate");
		calculate.addActionListener(new ActionListener() 
			{

	         public void actionPerformed(ActionEvent arg0) 
				{
					double length = Double.parseDouble(carpetLengthInput.getText());
					double width = Double.parseDouble(carpetWidthInput.getText());
					double cost = Double.parseDouble(carpetCostInput.getText());
					
					calculate(length, width, cost);
				}
         });
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(calculate);
		
		panel.add(buttonPanel, BorderLayout.CENTER);
		
		fields2.add(fields);
		
		
		
		
		
		panel.add(fields2, BorderLayout.WEST);
		
		
		
		
		JPanel fields3 = new JPanel();
		JPanel fields4 = new JPanel();
		
		fields3.setLayout(new GridLayout(3,2));
		
		JLabel total = new JLabel("Total Area:");
		totalField = new JTextField(10);
		fields3.add(total);
		fields3.add(totalField);
		JLabel totalCost = new JLabel("Total Cost:");
		totalCostField = new JTextField(10);
		fields3.add(totalCost);
		fields3.add(totalCostField);

		
		fields4.add(fields3);
		panel.add(fields4, BorderLayout.EAST);
		
		JLabel drag = new JLabel("Drag to resize carpet!");
		JPanel dragPanel = new JPanel();
		dragPanel.add(drag);
		panel.add(dragPanel,BorderLayout.SOUTH);
		
		JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT,80,0));
		wrapper.add(panel);
		topPanel.add(wrapper, BorderLayout.NORTH);
		
		wrapper = new JPanel();
		wrapper.add(imageLabel);
		topPanel.add(wrapper, BorderLayout.CENTER);
		  
		imageLabel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) 
				{
                p2 = new Point(e.getX(),e.getY());
		
						
							if(p2.x>p1.x) topPanel.room1.length=topPanel.room1.length*1.01; else if(p2.x<p1.x) topPanel.room1.length=topPanel.room1.length*.99;
							if(p2.y>p1.y) topPanel.room1.width=topPanel.room1.width*1.01; else if(p2.y<p1.y) topPanel.room1.width=topPanel.room1.width*.99;

							imageLabel.setIcon(topPanel.drawRoom());
							calculateAll();
						
						
						p1.x=p2.x;
						p1.y=p2.y;
						
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
        });
		  
		topPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(topPanel);
		
	}
	
	
	private void calculate(double x, double y, double c)
	{
		RoomDimension room1 = new RoomDimension(x,y);
		RoomCarpet carpet1 = new RoomCarpet(room1,c);
		DecimalFormat money = new DecimalFormat("$0.00");
		
		totalField.setText(String.valueOf(carpet1.getArea()));
		totalCostField.setText(money.format(carpet1.getTotalCost()));
		
		topPanel.createRoom(room1,carpet1);
		
		imageLabel.setIcon(topPanel.drawRoom());
	}
	
	private void calculateAll()
	{
		DecimalFormat numbers = new DecimalFormat("0");
		DecimalFormat money = new DecimalFormat("$0.00");
		
		totalField.setText(numbers.format(topPanel.carpet1.getArea()));
		totalCostField.setText(money.format(topPanel.carpet1.getTotalCost()));
		carpetWidthInput.setText(numbers.format(topPanel.room1.width));
		carpetLengthInput.setText(numbers.format(topPanel.room1.length));
	}

	
	private class PaintFrame extends JPanel
	{
		RoomDimension room1;
		RoomCarpet carpet1;
		boolean room = false;
		Image roomImage;
		Graphics gBuffer;
		Rectangle rect = new Rectangle(0,0,0,0);
		
		public PaintFrame()
		{
			
		}
		
		public PaintFrame(JPanel x, boolean y)
		{
			this.add(x);
			room=y;
			
		}
		
		public Rectangle getRect()
		{
			return rect;
		}
		
		public void createRoom(RoomDimension x, RoomCarpet y)
		{
			room1=x;
			carpet1=y;
			room = true;
			
		}
		
		public ImageIcon drawRoom()
		{
			
			roomImage = createImage((int)room1.getLength()+200,(int)room1.getWidth()+200);
			gBuffer = roomImage.getGraphics();
			gBuffer.setColor(new Color(237,237,237));
			gBuffer.fillRect(0,0,(int)room1.getLength()+200,(int)room1.getWidth()+200);
			gBuffer.setColor(Color.BLACK);
			gBuffer.fillRect(100,100,(int)room1.getLength(),(int)room1.getWidth());
			rect = new Rectangle(100,100,(int)room1.getLength(),(int)room1.getWidth());
			
			gBuffer.drawString("Carpet",(int)room1.getLength()/2+100,(int)room1.getWidth()+115);
			gBuffer.drawString("Length = " + (int)room1.getLength(),(int)room1.getLength()/2+100,90);
			gBuffer.drawString("Width = " + (int)room1.getWidth(),(int)room1.getLength()+110,(int)room1.getWidth()/2+100);
			
			ImageIcon image = new ImageIcon(roomImage);
			return image;
			
			
		}
		
		/*@Override  
	    protected void paintComponent(Graphics g)  
	   {  
	        super.paintComponent(g); 
			
			  if(room) g.drawImage(roomImage,500-((int)room1.getLength()/2)-100,500-((int)room1.getWidth()/2)-100,this);
			  
	   }*/
		
		public void update(Graphics g)
		{
		paint(g);
		}
		
		
	
	}
		
}

