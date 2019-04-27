package client;

import javax.swing.*;

import java.awt.*;

public class Dbtable extends JPanel{
	DefaultListModel<String> listModel = new DefaultListModel<>();
	JList<String> list = new JList<>(listModel);
	JScrollPane listScroller = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	int selection;
	public String r = "";
	Font font = new Font("Helvetica", Font.PLAIN,20);

	Dbtable(){
		setPreferredSize(new Dimension(1020,292));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		add(listScroller);
		list.setVisible(true);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		listScroller.setPreferredSize(new Dimension(1013, 281));
		list.setFont(font);
		setBorder(BorderFactory.createLineBorder(Color.black));
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
