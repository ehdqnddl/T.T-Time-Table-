package Main.Frame.Body;
import java.awt.*;
import javax.swing.*;

import Main.Frame.Body.View.Setting;

public class Body extends JPanel {
	private CardLayout cardLayout;
	private ViewManager cViewManager;
	private Setting cSetting;
	
	public Body() {
		// TODO Auto-generated constructor stub
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		this.setBackground(Color.gray);
		
		cViewManager = new ViewManager();
		this.add("B",cViewManager);
		
		cSetting = new Setting();
		this.add("S",cSetting);
		
	}
	
	public void changeScene(String str)
	{
		//cSetting.checkThread(str);
		cardLayout.show(this, str);
	}
	
}
