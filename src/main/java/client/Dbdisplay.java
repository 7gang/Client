package client;

import javax.swing.*;
import java.awt.*;


public class Dbdisplay extends JPanel{
	BorderLayout layout = new BorderLayout();
	JLabel text = new JLabel("Test");
	Font font = new Font("Helvetica", Font.BOLD,50);

	
	Dbdisplay(){
		setPreferredSize(new Dimension(1020,338));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setLayout(layout);
		text.setHorizontalTextPosition(text.CENTER);
		text.setHorizontalAlignment(text.CENTER);
		text.setFont(font);
		add(text,BorderLayout.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void setQuote(String input) {
		text.setText(input);
	}
}
