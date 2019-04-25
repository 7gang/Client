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
	JPanel side = new MihaiPanel();
	JPanel side2 = new JPanel();
	//JPanel top = new JPanel();
	JPanel top = display;
	JPanel bottom = table;
	GroupLayout layout = new GroupLayout(getContentPane());
	
	Gui(){
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
		
		
		side2.setPreferredSize(new Dimension(150,292));
		side2.setMaximumSize(getPreferredSize());	
		side2.setMaximumSize(getPreferredSize());
		side2.setBorder(BorderFactory.createLineBorder(Color.black));
		
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
				System.out.println(table.list.getSelectedIndex());
				table.r = table.listModel.getElementAt(table.list.getSelectedIndex());
				System.out.println(table.r);
				display.text.setText(table.r);
				table.selection = table.list.getSelectedIndex();
			}
		});
		display.text.setText(table.r);
		
		MihaiPanel.b1.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				table.addItem("a quote");
			}
		});
		
		MihaiPanel.b2.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				//table.addItem("a quote");
			}
		});
		
		MihaiPanel.b3.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				//System.out.print(" small Penis");
			}
		});
		
		MihaiPanel.b4.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e) 
			{
				table.updateItems();
			}
		});
	}
}
