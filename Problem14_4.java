

// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.image.BufferedImage;
// -----------------------------------------------

public class Problem14_4 extends PaintPanel
{
	BufferedImage virtualMem;
	Graphics gBuffer;
	int appletWidth;		
	int appletHeight;	
	JLabel tempLabel = new JLabel();
	
	int temperature = 50;
	
	

	
	@Override
	public String getDescription()
	{
		return "4. Thermometer Applet"
+"\n\nWrite an applet that displays a thermometer. The user should be able to control the temperature with a slider component. "
+"\nWhen the user moves the slider, the thermometer should show the corresponding temperature.";
	}
	

	public Problem14_4()
	{	
		setLayout(new BorderLayout());
		
		JPanel header = new JPanel();
		header.add(KDUtil.wrap(new JLabel("Thermometer Program")));
		
		ImageIcon tempIcon = new ImageIcon(drawImage());
		tempLabel.setIcon(tempIcon);
		
		add(leftPanel(), BorderLayout.CENTER);
		add(tempLabel, BorderLayout.SOUTH);
		add(header, BorderLayout.NORTH);
		
		
	}
	
	public Image drawImage()
	{
		virtualMem = new BufferedImage(200,400,BufferedImage.TYPE_INT_RGB);
		gBuffer = virtualMem.getGraphics();
		
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(0,0,200,400);
		
		
		gBuffer.setColor(Color.RED);
		gBuffer.fillArc(75,275,50,50,180,180);
		
		gBuffer.setColor(Color.BLACK);
		
		
		gBuffer.drawOval(75,25,50,50);
		gBuffer.drawOval(75,275,50,50);
		
		gBuffer.setColor(Color.BLACK);
		gBuffer.drawRect(75,50,50,250);
		
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(76,50,49,251);
		
		

		for(int  i = 0 ; i<=temperature; i++)
		{
			gBuffer.setColor(Color.RED);
			gBuffer.fillRect(76,300-(int)(i*2.5),50,3);
		}
		
		for(int  i = 0 ; i<10; i++)
		{
			gBuffer.setColor(Color.BLACK);
			gBuffer.drawString(10*i+"",50,300-(25*i));
		}
		

		
		
		
		return virtualMem;
	}
	
	public JPanel leftPanel()
	{
		JPanel panel = new JPanel();
		final JSlider tempSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		tempSlider.setMajorTickSpacing(20);
		tempSlider.setMinorTickSpacing(2);
		tempSlider.setPaintTicks(true);
		tempSlider.setPaintLabels(true);
		
		panel.add(KDUtil.wrap(new JLabel("Temperature")), BorderLayout.NORTH);
		panel.add(tempSlider, BorderLayout.CENTER);
		
		tempSlider.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent e) 
			{
				temperature = tempSlider.getValue();
				ImageIcon tempIcon = new ImageIcon(drawImage());
				tempLabel.setIcon(tempIcon);
			}
		});	
		
		
		return panel;
		
	}	
	
}
						
	