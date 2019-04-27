package client;

import javax.swing.*;
import java.awt.*;
/**
 * Class for the container that gives the user input into the database
 */
public class Dbinput extends JPanel {
	BorderLayout layout = new BorderLayout();//Border layout used to center the label component
	static JTextField input = new JTextField(20);//JTextField component to input text into
	
	Dbinput(){
		setPreferredSize(new Dimension(1020,338));//Setting the size of the container
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setLayout(layout);//Setting the layout for the container
		add(input, BorderLayout.CENTER);//Adding the JLabel to the container
		input.setSize(400,40);//Setting the size for the JTextField
		input.setHorizontalAlignment(JTextField.CENTER);//Centering the text for the JTextField
		setBorder(BorderFactory.createLineBorder(Color.black));//Creating a border around the container
	}

}
