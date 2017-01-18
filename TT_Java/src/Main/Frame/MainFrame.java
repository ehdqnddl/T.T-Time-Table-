package Main.Frame;
//자바의 그래픽 라이브러리 awt, swing
import java.awt.*;
import javax.swing.*;

import GlobalValue.GlobalValue;
import Main.Frame.Body.Body;
import Main.Frame.Title.Title;

//JFrame을 상속받은 MainFrame클래스를 생성
//extends - 해당클래스 상속
public class MainFrame extends JFrame {
	private int nWidth;
	private int nHeight;
	private Title cTitle;
	private Body cBody;
	
	//MainFrame 생성자
	public MainFrame(){		
		
		//Frame의 사이즈를 설정
		this.setPreferredSize(new Dimension(GlobalValue.nFrameWidth,GlobalValue.nFrameHeight));
		//Frame의 배경색을 지정
		this.setBackground(Color.black);
		//Frame을 보이게함
		this.setVisible(true);
		//프로그램 닫기를 누르면 프로그램을 종료시킴
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//MainFrame에 메뉴,작업 패널을 설정
		cTitle = new Title();
		this.add(cTitle,"North");
		
		cBody = new Body();
		this.add(cBody,"Center");
		
		cTitle.setBody(cBody);
		
		this.pack();
		
		
	}
}
