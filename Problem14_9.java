

// ---------- Imported Packages ------------------
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
// -----------------------------------------------

public class Problem14_9 extends PaintPanel
{
	int appletWidth;		
	int appletHeight;	
	ArrayList<ImageIcon> images = new ArrayList();
	Timer timeDelay = new Timer(10000,null);
	JLabel slides = new JLabel();
	int index = 0;
	double delay = 10000;
	
	

	
	@Override
	public String getDescription()
	{
		return "9. Slideshow Application"
+"\n\nWrite an application that displays a slideshow of images, one after the other, with a time delay between each image. The user should be able to select up to 10 images for the "
+"\nslide show and specify the time delay in seconds.";
	}
	

	public Problem14_9()
	{	
		setLayout(new BorderLayout());
		add(topPanel(), BorderLayout.NORTH);
		add(slides,BorderLayout.CENTER);
		
		timeDelay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				if(images.size()>0)
				{
					if(index==images.size()) index = 0;
					slides.setIcon(images.get(index));
					index++;
				}
			}			
		});
		
		timeDelay.start();
		
	}
	
	public BufferedImage scaleImage(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		
		double scaleX = 800/w;
		double scaleY = 600/h;
		
		
		BufferedImage resized = new BufferedImage(800, 600, image.getType());
  		Graphics2D g = resized.createGraphics();
   	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
   	g.drawImage(image, 0, 0, 800, 600, 0, 0, image.getWidth(), image.getHeight(), null);
   	g.dispose();
		
		image = resized;
		
		return image;
	}


	
	public JPanel topPanel()
	{
		JPanel topPanel = new JPanel();
		
		JButton open = new JButton("Add Picture");
		open.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int status;
				JFileChooser fileChooser = new JFileChooser();
				status = fileChooser.showOpenDialog(null);
				if(status == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = fileChooser.getSelectedFile();
					String fileName = selectedFile.getPath();
					
					BufferedImage background =  new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
					try{background = ImageIO.read(selectedFile);}catch(IOException e){}
					
					background = scaleImage(background);
					
					ImageIcon image = new ImageIcon(background);
					images.add(image);
				}
			}			
		});
		JButton delays = new JButton("Set Delay");
		delays.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String inputValue = JOptionPane.showInputDialog("Please enter a delay in seconds.");
				delay = 1000*Double.parseDouble(inputValue);
				timeDelay.setDelay((int)delay);
			}			
		});

		topPanel.add(open);
		topPanel.add(delays);
		
		return topPanel;
	}
	
	public JPanel bottomPanel()
	{
		JPanel topPanel = new JPanel();
				
		return topPanel;
	}
	
		
}
						
	