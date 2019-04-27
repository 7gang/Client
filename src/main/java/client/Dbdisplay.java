package client;

import javax.swing.*;
import java.awt.*;

/**
 * Class for the container that displays selected entries from the list in the class Dbtable
 */
public class Dbdisplay extends JPanel{
	BorderLayout layout = new BorderLayout();//Border layout used to center the label component
	JLabel text = new JLabel("Test");//JLabel text component 
	Font font = new Font("Helvetica", Font.BOLD,50);//Font used for the JLabel

	Dbdisplay(){
		setPreferredSize(new Dimension(1020,338));//Setting the size of the container
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setLayout(layout);//Setting the layout for the container
		text.setHorizontalTextPosition(JLabel.CENTER);//Centering the text for the JLabel
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setFont(font);//Setting the font
		add(text,BorderLayout.CENTER);//Adding the JLabel to the container
		setBorder(BorderFactory.createLineBorder(Color.black));//Creating a border around the container
	}
}
