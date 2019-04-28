package client;

import javax.swing.*;
import java.awt.*;

/**
 * Class for the container used to show a list of database entries
 */
public class Dbtable extends JPanel{
	static DefaultListModel<String> listModel = new DefaultListModel<>();//ListModel used to add entries into the Jlist
	static JList<String> list = new JList<>(listModel);//JList component used to show the database to the user
	static JScrollPane listScroller = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//JScrollPane component used with the JList to make it Scrollable
	private static Font font = new Font("Helvetica", Font.PLAIN,20);//Font used in the list 

	Dbtable(){
		setPreferredSize(new Dimension(1020,292));//Setting the size for the container
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		add(listScroller);//Adding the JScrollpane to the container
		list.setVisible(true);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//Setting the selection mode of the list so that only one entry in the list can be selected at once
		list.setLayoutOrientation(JList.VERTICAL);//Setting the layout of the list so that it does no wrap horizontally
		list.setVisibleRowCount(-1);//Setting the visible row count to be -1 so that all rows are visible at once
		listScroller.setPreferredSize(new Dimension(1013, 281));//Setting the size of the Scrollpane
		list.setFont(font);//Setting the font used for the list
		setBorder(BorderFactory.createLineBorder(Color.black));//Creating a border around the container
	}

	/**
	 * Replace all Dbtable entries with the most recent dataset from the Server
	 */
	private void reformList() {
		// clear the list model
		listModel.clear();
		// add the new entries one-by-one
		for (String quote : Server.getQuotes())
			listModel.addElement(quote);
	}
	
	void addItem(String quote) {
		if (!Server.addQuote(quote)) 
			System.out.println("Could not add quote \"" + quote + "\"!"); // display an error message if operation failed
		else reformList(); // assume operation was successful through and update the table
	}
	
	void editItem(String targetQuote, String newQuote) {
		if (!Server.editQuote(targetQuote, newQuote))
			System.out.println("Could not change quote \"" + targetQuote + "\" to \"" + newQuote + "\"!"); // display an error message if operation failed
		else reformList(); // assume operation was successful and update the table
	}
	
	void removeItem(String quote) {
		if (!Server.deleteQuote(quote))
			System.out.println("Could not delete quote \"" + quote + "\"!"); // display an error message if operation failed
		else reformList(); // assume operation was successful and update the table
	}
	
	void updateItems() {
		reformList();
	}
	
}
