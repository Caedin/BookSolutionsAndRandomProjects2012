


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// -----------------------------------------------



class Problem10_14 extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Input :");
	JTextArea outputLabel = new JTextArea(30,20);
	JTextField input = new JTextField(40);
	
	
	public Problem10_14()
	{
		buildPanel();
				
				
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "14. Word Separator"
+"\n\nWrite a program that accepts as input a sentence in which all of the words are run together, but the first character of each word is uppercase. Convert the sentence to a string in which "
+"\nthe words are separated by spaces and only the first word starts with an uppercase letter. For example, the string “StopAndSmellTheRoses.” would be converted to “Stop and smell the roses.”";
	}
	
	
	public void buildPanel()
	{
		input.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
				outputLabel.setText("");
				outputLabel.append(wordSeperate(input.getText()));
				outputLabel.setEditable(false);
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
				outputLabel.setText("");
				outputLabel.append(wordSeperate(input.getText()));
				outputLabel.setEditable(false);
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
				outputLabel.setText("");
				outputLabel.append(wordSeperate(input.getText()));
				outputLabel.setEditable(false);
            }
        });
				
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Word Separator");
		JPanel headerFrame = new JPanel();
		headerFrame.add(header);
		mainPanel.add(headerFrame, BorderLayout.NORTH);
		
		inputPanel.add(inputLabel);
		inputPanel.add(input);
		mainPanel.add(inputPanel, BorderLayout.CENTER);

		
		JScrollPane scroll = new JScrollPane(outputLabel);
		outputPanel.add(scroll);
		mainPanel.add(scroll, BorderLayout.SOUTH);

		topPanel.add(mainPanel);
	
	}
	
	private String wordSeperate(String s)
	{
		String output = "";
		
		for(int i = 0; i<s.length(); i++)
		{
			switch(s.charAt(i))
			{
				case 'A': output = output + " a"; break;
				case 'B': output = output + " b"; break;
				case 'C': output = output + " c"; break;
				case 'D': output = output + " d"; break;
				case 'E': output = output + " e"; break;
				case 'F': output = output + " f"; break;
				case 'G': output = output + " g"; break;
				case 'H': output = output + " h"; break;
				case 'I': output = output + " i"; break;
				case 'J': output = output + " j"; break;
				case 'K': output = output + " k"; break;
				case 'L': output = output + " l"; break;
				case 'M': output = output + " m"; break;
				case 'N': output = output + " n"; break;
				case 'O': output = output + " o"; break;
				case 'P': output = output + " p"; break;
				case 'Q': output = output + " q"; break;
				case 'R': output = output + " r"; break;
				case 'S': output = output + " s"; break;
				case 'T': output = output + " t"; break;
				case 'U': output = output + " u"; break;
				case 'V': output = output + " v"; break;
				case 'W': output = output + " w"; break;
				case 'X': output = output + " x"; break;
				case 'Y': output = output + " y"; break;
				case 'Z': output = output + " z"; break;
				
				default: output = output + s.charAt(i); break;
			}

		}
		String temp = "";
		if(output.isEmpty()==false)
		while(output.charAt(0)==' ')
		{
			for(int i = 1; i<output.length(); i++)
			{
				temp = temp+output.charAt(i);
			}
			output=temp;
		}
		output = KDUtil.capitalizeWords(output);
		
		
		
		return output;
	}
	


	
		
	
}
				