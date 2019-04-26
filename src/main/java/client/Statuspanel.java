package client;

import javax.swing.*;
import java.awt.*;

public class Statuspanel extends JPanel {
	String status;
	boolean connection;
	JLabel text = new JLabel();
	
	Statuspanel(){
		setPreferredSize(new Dimension(150,292));
		setMaximumSize(getPreferredSize());	
		setMaximumSize(getPreferredSize());
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		text.setText("Server Status: ");
		add(text);
		
		
	}
	void connected() {
		if(connection = true) {
			status = "Connected";
			text.setText("Server Status: "+status);
		}
		else {
			status = "No connection";
			text.setText("Server Status: "+status);
		}
	}
	
}
