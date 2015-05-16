/**
 * @(#)FinalApplet.java
 *
 * FinalApplet Applet application
 *
 * @author 
 * @version 1.00 2013/4/29
 */
 
import java.awt.*;
import java.applet.*;
import javax.swing.*;

public class FinalApplet extends JApplet {
	FinalFrame f = new FinalFrame();
	public void init()
		{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
            public void run()
            {
               f = new FinalFrame();
            }
        });
	}
	
	public JPanel getTopPanel()
	{
		return f.getTopPanel();
	}


}