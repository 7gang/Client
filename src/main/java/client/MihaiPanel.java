package client;
import java.awt.*;
import javax.swing.*;

/**
 * Class responsible for drawing the panel containing the 4 buttons of the interface.
 */

public class MihaiPanel extends JPanel {
	
	// initialization of the 4 buttons
	static JButton b1 = new JButton("Add");
	static JButton b2 = new JButton("Edit");
	static JButton b3 = new JButton("Remove");
	static JButton b4 = new JButton("Refresh");
	// initialization of the button sizes and the distance between the buttons.
	int bDimX = 150;
	int bDimY = 60;
	int bDist = 15;
	// initialization of the font to be used for the button text.
	Font font = new Font("Helvetica", Font.PLAIN,20);

	
		MihaiPanel(){
			// change the Java look and feel to the standard Windows 10 look and feel.
			try {
		            // Set System L&F
		        UIManager.setLookAndFeel(
		            UIManager.getSystemLookAndFeelClassName());
		    } 
		    catch (UnsupportedLookAndFeelException e) {
		       // handle exception
		    }
		    catch (ClassNotFoundException e) {
		       // handle exception
		    }
		    catch (InstantiationException e) {
		       // handle exception
		    }
		    catch (IllegalAccessException e) {
		       // handle exception
		    }
			
			// set the button font to the previously initialized one.
			b1.setFont(font);
			b2.setFont(font);
			b3.setFont(font);
			b4.setFont(font);
			
			// set the preffered, maximum and minimum size of the buttons.
			setPreferredSize(new Dimension(150,338));
			setMaximumSize(getPreferredSize());
			setMinimumSize(getPreferredSize());
			
			// set the visibility of the panel to true, and its background color to white.
			setVisible(true);
			setBackground(Color.WHITE);
			
			// create a border around the panel and set the layout to a vertical BoxLayout.
			setBorder(BorderFactory.createLineBorder(Color.black));
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			// add the buttons, the rigid area between the buttons for space 
			// and a vertical glue that centers the buttons vertically.
			add(Box.createVerticalGlue());
			add(b1);
			add(Box.createRigidArea(new Dimension(1,bDist)));
			add(b2);
			add(Box.createRigidArea(new Dimension(1,bDist)));
			add(b3);
			add(Box.createRigidArea(new Dimension(1,bDist)));
			add(b4);
			
			// set the preffered size and the minimum size of the buttons.
			b1.setPreferredSize(new Dimension(bDimX, bDimY));
			b2.setPreferredSize(new Dimension(bDimX, bDimY));
			b3.setPreferredSize(new Dimension(bDimX, bDimY));
			b4.setPreferredSize(new Dimension(bDimX, bDimY));
			
			b1.setMinimumSize(getPreferredSize());
			b2.setMinimumSize(getPreferredSize());
			b3.setMinimumSize(getPreferredSize());
			b4.setMinimumSize(getPreferredSize());
			
			b1.setAlignmentX(CENTER_ALIGNMENT);
			b2.setAlignmentX(CENTER_ALIGNMENT);
			b3.setAlignmentX(CENTER_ALIGNMENT);
			b4.setAlignmentX(CENTER_ALIGNMENT);
			
			add(Box.createVerticalGlue());
			
			b1.setVisible(true);
			b2.setVisible(true);
			b3.setVisible(true);
			b4.setVisible(true);
			
			
		}
}
