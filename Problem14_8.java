

// ---------- Imported Packages ------------------
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.image.BufferedImage;
// -----------------------------------------------

public class Problem14_8 extends PaintPanel
{
	int appletWidth;		
	int appletHeight;	
	JTextField time = new JTextField(10);
	boolean timer = false;
	double timeElapsed = 0;
	Timer timeObject;
	
	

	
	@Override
	public String getDescription()
	{
		return "8. Stopwatch Applet"
+"\n\nWrite an applet that simulates a stopwatch. It should have a Start button and a Stop button. When the Start button is clicked the applet should count the seconds that pass." 
+"\nWhen the Stop button is clicked, the applet should stop counting seconds.";
	}
	

	public Problem14_8()
	{	
		final DecimalFormat format = new DecimalFormat("0.00");
		setLayout(new BorderLayout());
		
		JPanel header = new JPanel();
		header.add(KDUtil.wrap(new JLabel("StopWatch Program")));
		
	

		add(header, BorderLayout.NORTH);
		add(topPanel(), BorderLayout.CENTER);
		add(bottomPanel(), BorderLayout.SOUTH);
		
		timeObject = new Timer(10,null);
		timeObject.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				timeElapsed = timeElapsed + 0.01;
				time.setText(format.format(timeElapsed));
			}
		});
		
		time.setEditable(false);
		
	}
	
	public JPanel topPanel()
	{
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Timer:"));
		topPanel.add(time);
		return topPanel;
	}
	
	public JPanel bottomPanel()
	{
		JPanel topPanel = new JPanel();
		
		JButton start = new JButton("Start");
		JButton stop = new JButton("Stop");
		
		start.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				timeObject.start();
			}
		});
		stop.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				timeObject.stop();
			}
		});
		
		
		topPanel.add(KDUtil.wrap(start));
		topPanel.add(KDUtil.wrap(stop));
		
		return topPanel;
	}
	
		
}
						
	