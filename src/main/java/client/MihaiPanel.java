package client;
import java.awt.*;
import javax.swing.*;

public class MihaiPanel extends JPanel {
	
	static JButton b1 = new JButton("Add");
	static JButton b2 = new JButton("Edit");
	static JButton b3 = new JButton("Remove");
	static JButton b4 = new JButton("Refresh");
	int bDim = 80;
	int bDist = 15;
	Font font = new Font("Helvetica", Font.PLAIN,20);

	
		MihaiPanel(){
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
			
			b1.setFont(font);
			b2.setFont(font);
			b3.setFont(font);
			b4.setFont(font);

			setPreferredSize(new Dimension(150,338));
			setMaximumSize(getPreferredSize());
			setMinimumSize(getPreferredSize());
			setVisible(true);
			setOpaque(true);
			setBackground(Color.WHITE);
			setBorder(BorderFactory.createLineBorder(Color.black));
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			add(Box.createVerticalGlue());
			add(b1);
			add(Box.createRigidArea(new Dimension(1,bDist)));
			add(b2);
			add(Box.createRigidArea(new Dimension(1,bDist)));
			add(b3);
			add(Box.createRigidArea(new Dimension(1,bDist)));
			add(b4);
			
			b1.setPreferredSize(new Dimension(150, 60));
			b2.setPreferredSize(new Dimension(150, 60));
			b3.setPreferredSize(new Dimension(150, 60));
			b4.setPreferredSize(new Dimension(150, 60));
			
			b1.setMinimumSize(getPreferredSize());
			b2.setMinimumSize(getPreferredSize());
			b3.setMinimumSize(getPreferredSize());
			b4.setMinimumSize(getPreferredSize());
			
			setVisible(true);
			
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
