package Main.Frame.Title;
//자바의 그래픽 라이브러리 awt, swing
import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import GlobalValue.GlobalValue;
import Main.Frame.Body.Body;

import java.awt.event.*;

//메뉴패널 클래스 : JPanel을 상속받고 ActionListener(오브젝트 이벤트를 받음)를 사용할수있음
public class Title extends JPanel implements ActionListener {
	private JButton btnBack;
	private JButton btnSetting;
	private JLabel lblTitle;
	private Body cTask;			//작업패널의 함수를 사용하기위해 선언
	private GridLayout gridLayout;
	public Title() {
		// TODO Auto-generated constructor stub
		gridLayout = new GridLayout(1, 8);
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(GlobalValue.nFrameWidth,GlobalValue.nTitleHeight));
		this.setBackground(new Color(159,159,159));
		
		btnBack = new JButton("B");
		btnBack.setBackground(Color.BLACK);	//버튼색 설정
		btnBack.setForeground(Color.WHITE);	//버튼 텍스트 색 설정
		btnBack.setPreferredSize(new Dimension(GlobalValue.nTitleHeight, GlobalValue.nTitleHeight));
		btnBack.addActionListener(this);		//버튼이벤트를 읽을 리스너를 설정 , 현재 클래스에서 ActionListener 인터페이스를 사용하여 this로 접근
		this.add(btnBack,"West");
		
		lblTitle = new JLabel("설현내꺼");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblTitle,"Center");
		
		btnSetting = new JButton("S");
		btnSetting.setBackground(Color.BLACK);	//버튼색 설정
		btnSetting.setForeground(Color.WHITE);	//버튼 텍스트 색 설정
		btnSetting.setPreferredSize(new Dimension(GlobalValue.nTitleHeight, GlobalValue.nTitleHeight));
		btnSetting.addActionListener(this);		//버튼이벤트를 읽을 리스너를 설정 , 현재 클래스에서 ActionListener 인터페이스를 사용하여 this로 접근
		this.add(btnSetting,"East");
		
	}
	
	public void setBody(Body task)
	{
		cTask = task;
	}

	//ActionListner 인터페이스를 사용하여 선언된 함수, 이벤트 발생시 이벤트에 대한 정보를 넘겨받을 수 있음
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JButton but = (JButton)e.getSource();
		cTask.changeScene(but.getText());
		
	}
}
