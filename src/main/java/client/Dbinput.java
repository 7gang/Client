package client;

import javax.swing.*;
import java.awt.*;

public class Dbinput extends JPanel {
	BorderLayout layout = new BorderLayout();
	static JTextField input = new JTextField(20);
	
	Dbinput(){
		setPreferredSize(new Dimension(1020,338));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setLayout(layout);
		add(input, BorderLayout.CENTER);
		input.setSize(400,40);
		input.setHorizontalAlignment(input.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

}
