package client;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
	Dbtable table = new Dbtable();
	Dbdisplay display = new Dbdisplay();
	Dbinput inputfield = new Dbinput();
	JPanel side = new MihaiPanel();
	JPanel side2 = new JPanel();
	JPanel top = new JPanel();
	JPanel bottom = table;
	GroupLayout layout = new GroupLayout(getContentPane());
	CardLayout cl = new CardLayout();
	String q;
	String k;
	int selector = 0;
	int block = 0;
	
	Gui(){
		block = 0;
		top.setLayout(cl);
		top.add(display);
		top.add(inputfield);
		cl.first(top);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
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
    	
		side.setBackground(Color.WHITE);
		side2.setBackground(Color.WHITE);
		top.setBackground(Color.WHITE);
		bottom.setBackground(Color.WHITE);
		
		setLayout(layout);
		setSize(1200,690);
		setResizable(false);
		setBackground(Color.GRAY);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		table.list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				switch(block) {
				case 0:	System.out.println(table.list.getSelectedIndex());
						table.r = table.listModel.getElementAt(table.list.getSelectedIndex());
						System.out.println(table.r);
						table.selection = table.list.getSelectedIndex();
				break;
				case 1: break;		
				}
				k = table.r;
				display.text.setText(table.r);
			}
		});
		display.text.setText(table.r);
		
		MihaiPanel.b1.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				displayInput();
				selector = 1;
			}
		});
		
		MihaiPanel.b2.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				displayInput();
				selector = 2;
			}
		});
		
		MihaiPanel.b3.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				block = 1;
				table.removeItem(k);
				block = 0;
			}
		});
		
		MihaiPanel.b4.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				block = 1;
				table.updateItems();
				block = 0;
			}
		});
		
		Dbinput.input.addActionListener(new java.awt.event.ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  block = 1;
			  q =Dbinput.input.getText();
			  switch(selector) {
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
	
	void displayInput() {
		cl.last(top);
	}
	void displayText(){
		cl.first(top);
	}
}
