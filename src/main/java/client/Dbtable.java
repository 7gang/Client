package client;

import javax.swing.*;
import java.awt.*;
/**
 * Class for the container used to show a list of database entries
 */
public class Dbtable extends JPanel{
	DefaultListModel<String> listModel = new DefaultListModel<>();//ListModel used to add entries into the Jlist
	JList<String> list = new JList<>(listModel);//JList component used to show the database to the user
	JScrollPane listScroller = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//JScrollPane component used with the JList to make it Scrollable
	Font font = new Font("Helvetica", Font.PLAIN,20);//Font used in the list 

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

	private void reformList() {
		listModel.clear();
		for (String quote : Server.getQuotes()) {
			listModel.addElement(quote);
		}
	}
	
	void addItem(String input) { // requires text field
		Boolean success = Server.addQuote(input);
		System.out.println("add request success: " + success);
		if (!success) 
			return; // display an error message
		reformList();
	}
	
	void editItem(String targetQuote, String newQuote) { // requires text field
		Boolean success = Server.editQuote(targetQuote, newQuote);
		if (!success)
			return; // display an error message
		reformList();
	}
	
	void removeItem(String quote) { // not used
		Boolean success = Server.deleteQuote(quote);
		if (!success)
			return; // display an error message
		reformList();
	}
	
	void updateItems() {
		System.out.println("update request");
		reformList();
	}
	
}
