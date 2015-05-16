import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class HopitalAppletDemo extends JApplet
{
	hospitalDemo f = new hospitalDemo();
	public void init()
		{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
            public void run()
            {
               f = new hospitalDemo();
					f.setVisible(false);
            }
        });
	}
	
	public JPanel getTopPanel()
	{
		return f.getTopPanel();
	}


}