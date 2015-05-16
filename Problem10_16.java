


// ---------- Imported Packages ------------------
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// -----------------------------------------------



class Problem10_16 extends PaintPanel
{
	JPanel topPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	JPanel outputPanel = new JPanel();
	JLabel inputLabel = new JLabel("Input :");
	JTextArea outputLabel = new JTextArea(30,20);
	JTextField input = new JTextField(40);
	
	
	public Problem10_16()
	{
		buildPanel();
				
				
		add(topPanel);
		setVisible(true);
	}
	
	@Override
	public String getDescription()
	{
		return "16. Morse Code Converter"
+"\nMorse code is a code where each letter of the English alphabet, each digit, and various punctuation characters are represented by a series of dots and dashes. Table 10-14 shows part of the code. "
+"\nWrite a program that asks the user to enter a string, and then converts that string to Morse code. Use hyphens for dashes and periods for dots.";
	}
	
	
	public void buildPanel()
	{
		input.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
				outputLabel.setText("");
				outputLabel.append(morseCode(input.getText()));
				outputLabel.setEditable(false);
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
				outputLabel.setText("");
				outputLabel.append(morseCode(input.getText()));
				outputLabel.setEditable(false);
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
				outputLabel.setText("");
				outputLabel.append(morseCode(input.getText()));
				outputLabel.setEditable(false);
            }
        });
				
					
			
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Morse Code Converter");
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
	
	private String morseCode(String input)
	{
		String output = "";
		
		String inputValid = "";
		
		for(int i = 0; i<input.length(); i++)
		{
			switch(input.charAt(i))
			{
				case 'a': inputValid = inputValid + "A"; break;
				case 'b': inputValid = inputValid + "B"; break;
				case 'c': inputValid = inputValid + "C"; break;
				case 'd': inputValid = inputValid + "D"; break;
				case 'e': inputValid = inputValid + "E"; break;
				case 'f': inputValid = inputValid + "F"; break;
				case 'g': inputValid = inputValid + "G"; break;
				case 'h': inputValid = inputValid + "H"; break;
				case 'i': inputValid = inputValid + "I"; break;
				case 'j': inputValid = inputValid + "J"; break;
				case 'k': inputValid = inputValid + "K"; break;
				case 'l': inputValid = inputValid + "L"; break;
				case 'm': inputValid = inputValid + "M"; break;
				case 'n': inputValid = inputValid + "N"; break;
				case 'o': inputValid = inputValid + "O"; break;
				case 'p': inputValid = inputValid + "P"; break;
				case 'q': inputValid = inputValid + "Q"; break;
				case 'r': inputValid = inputValid + "R"; break;
				case 's': inputValid = inputValid + "S"; break;
				case 't': inputValid = inputValid + "T"; break;
				case 'u': inputValid = inputValid + "U"; break;
				case 'v': inputValid = inputValid + "V"; break;
				case 'w': inputValid = inputValid + "W"; break;
				case 'x': inputValid = inputValid + "X"; break;
				case 'y': inputValid = inputValid + "Y"; break;
				case 'z': inputValid = inputValid + "Z"; break;
				default: inputValid = inputValid + input.charAt(i); break;
			}
		}
		
		for(int i = 0; i<inputValid.length(); i++)
		{
			switch(inputValid.charAt(i))
			{
				case ' ': output = output + " "; break;
				case ',': output = output + "--..--"; break;
				case '.': output = output + ".-.-.-"; break;
				case '?': output = output + "..--.."; break;
				case '0': output = output + "-----"; break;
				case '1': output = output + ".----"; break;
				case '2': output = output + "..---"; break;
				case '3': output = output + "...--"; break;
				case '4': output = output + "....-"; break;
				case '5': output = output + "....."; break;
				case '6': output = output + "-...."; break;
				case '7': output = output + "--..."; break;
				case '8': output = output + "---.."; break;
				case '9': output = output + "----."; break;
				case 'A': output = output + ".-"; break;
				case 'B': output = output + "-..."; break;
				case 'C': output = output + "-.-."; break;
				case 'D': output = output + "-.."; break;
				case 'E': output = output + "."; break;
				case 'F': output = output + "..-."; break;
				case 'G': output = output + "--."; break;
				case 'H': output = output + "...."; break;
				case 'I': output = output + ".."; break;
				case 'J': output = output + ".---"; break;
				case 'K': output = output + "-.-"; break;
				case 'L': output = output + ".-.."; break;
				case 'M': output = output + "--"; break;
				case 'N': output = output + "-."; break;
				case 'O': output = output + "---"; break;
				case 'P': output = output + ".--."; break;
				case 'Q': output = output + "--.-"; break;
				case 'R': output = output + ".-."; break;
				case 'S': output = output + "..."; break;
				case 'T': output = output + "-"; break;
				case 'U': output = output + "..-"; break;
				case 'V': output = output + "...-"; break;
				case 'W': output = output + ".--"; break;
				case 'X': output = output + "-..-"; break;
				case 'Y': output = output + "-.--"; break;
				case 'Z': output = output + "--.."; break;
				default: break;
			}
			if(output.length()%40==0) output = output+ "\n";
		}
		
		return output;
	}
	
		
	
}
				