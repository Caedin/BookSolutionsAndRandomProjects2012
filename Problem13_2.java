


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------


// ---------- Main Class -------------------------
public class Problem13_2 extends PaintPanel
{
	final JLabel imageLabel = new JLabel();
	
	public Problem13_2()
	{
		try
		{
		try{RandomAccessFile test = new RandomAccessFile("test.txt","rw");test.close();}catch(IOException e){}

		setLayout(new BorderLayout());
		add(KDUtil.wrap(topPanel()), BorderLayout.NORTH);
		add(KDUtil.wrap(centerPanel()), BorderLayout.CENTER);
		add(KDUtil.wrap(bottomPanel()), BorderLayout.SOUTH);
		}
		catch(java.security.AccessControlException k)
		{
			removeAll();
			add(new JLabel("ERROR: Applets cannot read or write files"));
		}	
		
	}
	
	public JPanel topPanel()
	{
		JPanel topPanel = new JPanel();
		JButton open = new JButton("Open Picture");
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
					
					ImageIcon image = new ImageIcon(fileName);
					imageLabel.setIcon(image);
				}
			}			
		});

		topPanel.add(KDUtil.wrap(open));
		return topPanel;
	}
	
	public JPanel bottomPanel()
	{
		JPanel bottomPanel = new JPanel();
	
		
		return bottomPanel;
	}
	
	public JPanel centerPanel()
	{
		JPanel centerPanel = new JPanel();
		centerPanel.add(imageLabel);
		
		return centerPanel;
		
	}



	@Override
	public String getDescription()
	{
		return "2. Image Viewer"
+"\n\nWrite an application that allows the user to view image files. The application"
+"\nshould use either a button or a menu item that displays a file chooser. When the"
+"\nuser selects an image file, it should be loaded and displayed.";
	}
		

}