package Main.Frame.Body.View;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TimeTableView extends JPanel {
	private DefaultListModel<String> listModel;
	private JList<String> list;
	private JScrollPane listScroll;
	private JTextField txtField;
	private JButton butAdd;
	private int nNum;
	
	public TimeTableView() {
		nNum = 1;
		this.setLayout(new BorderLayout());
		
		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		listScroll = new JScrollPane(list);
		list.setAutoscrolls(true);
		this.add(listScroll,"Center");

		JPanel rPanel = new JPanel();
		rPanel.setLayout(new BorderLayout());
		txtField = new JTextField();
		txtField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtField.getText().length()>0)
				{
					listModel.addElement(nNum+" - "+txtField.getText());
					nNum++;
					txtField.setText("");
				}
			}
		});
		rPanel.add(txtField,"Center");
		butAdd = new JButton("Add");
		butAdd.setPreferredSize(new Dimension(70, 20));
		butAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtField.getText().length()>0)
				{
					listModel.addElement(nNum+" - "+txtField.getText());
					nNum++;
					txtField.setText("");
				}
			}
		});
		rPanel.add(butAdd,"East");
		this.add(rPanel,"South");
		
	}
}
