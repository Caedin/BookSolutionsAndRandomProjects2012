// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// -----------------------------------------------

// Class PaintPanel is an extension of JPanel, with the added functionality to paint graphical objects
class PaintApplet extends java.applet.Applet				
{
	String problem;
	
	public String getDescription()
	{
		return problem;
	}
}
