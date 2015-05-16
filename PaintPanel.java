// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------

// Class PaintPanel is an extension of JPanel, with the added functionality to paint graphical objects
class PaintPanel extends JPanel				
{
	Graphics gBuffer;
	Image tempImage;
	String problem;
	boolean draw = false;

	public boolean getDraw()
	{
		return draw;
	}
	
	public Image getImage()
	{
		return tempImage;
	}
	
	public String getDescription()
	{
		return problem;
	}

	protected void setDraw(boolean b) {draw=b;}
	protected void setImage(Image i) {tempImage=i;}

}
