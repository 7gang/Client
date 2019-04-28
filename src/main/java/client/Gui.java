package client;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *Main GUI class that acts as the frame that other container classes are added to
 */
public class Gui extends JFrame {
	Dbtable table = new Dbtable();//
	Dbdisplay display = new Dbdisplay();
	Dbinput inputfield = new Dbinput();
	JPanel side = new ButtonPanel();//JPanels used to set up the layout 
	JPanel side2 = new JPanel();
	JPanel top = new JPanel();
	JPanel bottom = table;
	GroupLayout layout = new GroupLayout(getContentPane());//GroupLayout used on the frame to layout the various containers
	CardLayout cl = new CardLayout();//CardLayout used the top JPanel to switch between the Dbdisplay and Dbinput containers
	String q;
	String k;
	int selector = 0;
	int block = 0;
	
	Gui(){
		block = 0;
		top.setLayout(cl);//Setting the layout of the top JPanel 
		top.add(display);//Adding the containers to the top JPanel
		top.add(inputfield);
		cl.first(top);//Setting the first card in the card layout to be shown on startup
		layout.setAutoCreateGaps(true);//Setting the group layout to automatically create gaps between JPanels 
		layout.setAutoCreateContainerGaps(true);
		
		//Setting up the groups for the group layout
		layout.setHorizontalGroup(
    			layout.createSequentialGroup()
    			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(side)
						.addComponent(side2))
    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    						.addComponent(top)
    						.addComponent(bottom)));
    	
    	layout.setVerticalGroup(
    			layout.createSequentialGroup()
    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    						.addComponent(side)
    						.addComponent(top))
    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    						.addComponent(side2)
    						.addComponent(bottom)));
    	//Setting the background color of the JPanels
		side.setBackground(Color.WHITE);
		side2.setBackground(Color.WHITE);
		top.setBackground(Color.WHITE);
		bottom.setBackground(Color.WHITE);
		
		setLayout(layout);//Setting the layout for the frame
		setSize(1200,690);//Setting the size of the frame
		setResizable(false);//Setting the frame to not be resizable
		setBackground(Color.GRAY);//Setting the frame background color
		setDefaultCloseOperation(EXIT_ON_CLOSE);//Setting the default close operation so the program shuts down when the window is closed
		setVisible(true);//Setting the frame to be visible
		
		Dbtable.list.addListSelectionListener(new ListSelectionListener() {//Adding a selection listener to the Dbtable list
			@Override
			public void valueChanged(ListSelectionEvent arg0) {//Method for the listener that is called whenever the selection in the list is changed
				switch(block) {//Switch case that is used stop code from being called when the list contents are changing 
				case 0:	k = Dbtable.listModel.getElementAt(Dbtable.list.getSelectedIndex());//Retrieving text entry from the list
				break;
				case 1: break;//doing noting to protect the code from errors
				}
				display.text.setText(k);//Setting the text in the Dbdisplay JLabel
			}
		});
		display.text.setText(k);
		//Action listeners for buttons in the JPanel MihaiPanel
		ButtonPanel.b1.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e) 
			{
				displayInput();
				selector = 1;
			}
		});
		
		ButtonPanel.b2.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				displayInput();
				selector = 2;
			}
		});
		
		ButtonPanel.b3.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				block = 1;//Changing the int block to protect the code from errors when changes are happening to the list
				table.removeItem(k);
				block = 0;
			}
		});
		
		ButtonPanel.b4.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				block = 1;//Changing the int block to protect the code from errors when changes are happening to the list
				table.updateItems();
				block = 0;
			}
		});
		
		Dbinput.input.addActionListener(new java.awt.event.ActionListener() {//ActionListener for the Dbinput JTextField
		  public void actionPerformed(ActionEvent e) {
			  block = 1;//Changing the int block to protect the code from errors when changes are happening to the list
			  q =Dbinput.input.getText();
			  switch(selector) {//Switch to determine what method the text input should be applied to
				  case 1: table.addItem(q);		
				  break;
				  case 2: table.editItem(k,q);	
				  break;
				  }
			  block = 0;	
			  displayText();
		  }
		});
	}
	void displayText(){//method to change the top JPanel to be the first card
		cl.first(top);
	}
	void displayInput() {//method to change the top JPanel to be the last card
		cl.last(top);
	}	
}
