package Main.Frame.Body;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import Main.Frame.Body.View.*;

public class ViewManager extends JPanel {
	private JTabbedPane tabBar;
	
	private HomeView cHomeView;
	private TimeTableView cTimetable;
	private CampusView cCampus;
	private CommunityView cCommunity;
	private HistoryView cHistory;
	private CurriculumView cCurriculum;
	
	public ViewManager()
	{
		this.setBackground(Color.gray);
		this.setLayout(new BorderLayout());

		tabBar = new JTabbedPane();
		tabBar.setBackground(new Color(81, 81, 81));
		tabBar.setForeground(new Color(214, 214, 214));
		
		cHomeView = new HomeView();
		tabBar.add(cHomeView,"Campus         ");
		
		cCampus = new CampusView();
		tabBar.add(cCampus,"Campus         ");
		
		cTimetable = new TimeTableView();
		tabBar.add(cTimetable,"Timetable      ");
		
		cHistory = new HistoryView();
		tabBar.add(cHistory,"History        ");
		
		cCurriculum = new CurriculumView();
		tabBar.add(cCurriculum,"Curriculum     ");

		cCommunity = new CommunityView();
		tabBar.add(cCommunity,"Community      ");

		tabBar.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<tabBar.getTabCount();i++)
				{
					tabBar.setForegroundAt(i,new Color(214, 214, 214));
					tabBar.setBackgroundAt(i,new Color(78,78,78));
				}
				tabBar.setForegroundAt(tabBar.getSelectedIndex(),new Color(0, 0, 0));
				tabBar.setBackgroundAt(tabBar.getSelectedIndex(),new Color(81, 81, 81));
				System.out.println(tabBar.getSelectedIndex());
				tabBar.repaint();
			}
		});

		tabBar.setForegroundAt(0,new Color(0, 0, 0));
		this.add(tabBar,"Center");
		
	}

}
