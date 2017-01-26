package Main.Frame.Body.View;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Font;
import java.net.URL;
import GlobalValue.GlobalValue;
import javax.swing.JButton;
import javax.swing.border.Border;
import java.io.*;
import javax.swing.JDialog;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class HomeView extends JPanel implements ActionListener{

	// 이부분은 클래스 내에서 사용할 변수선언을 하는 부분.
	HomeView_Method Method = new HomeView_Method();
	private static final String HomeView = null;
	private JLabel Label_Time,Label_Today,Label_Title_Memo; //사용하는 라벨
	private JTextArea TextField_Memo,SubTextArea; //사용하는 텍스트에어리어
	private JTextField TextField_Memo_Title,SubTextArea_Title; //사용하는 텍스트필드
	private JButton Button_delete, Button_edit,Button_Add; //사용하는 버튼
	private JButton[] Button_Memo = new JButton[20]; //배열로 선언하는 버튼
	private int Count_Button = 0; //버튼의 카운트를 위한 변수
	private int Check_str = 0; //문자열 검사 시그널
	private JFrame SubFrame; //새로운창을 띄우는 변수
	private JScrollPane ScrollPanel_Main,ScrollPanel_Sub; //사용하는 패널
	
	

	public HomeView() {
		//홈뷰의 레이아웃을 없앤다.(각각 레이아웃을 설정하기위해서 사용)
	    
		this.setBackground(GlobalValue.Color_Frame);
		this.setLayout(null);
		
		//라벨을 만든다. 라벨을 패널에 추가한다.
		Label_Time = Method.Make_Label("<HTML>"
				+ " 09:00<br><br><br>"
				+ " 10:00<br><br><br>"
				+ " 11:00<br><br><br>"
				+ " 12:00<br><br><br>"
				+ " 13:00<br><br><br>"
				+ " 14:00<br><br><br>"
				+ " 15:00<br><br><br>"
				+ " 16:00<br><br><br>"
				+ " 17:00<br><br><br>"
				+ " 18:00<br><br><br>"
				+ " 19:00<br><br><br>"
				+ " 20:00<br><br><br>"
				+ "</HTML>",GlobalValue.Font_Number, (int)(GlobalValue.nFrameWidth/128), (int)(GlobalValue.nFrameHeight/72),
				(int)(GlobalValue.nFrameWidth*0.047), (int)(GlobalValue.nFrameHeight*0.9));
		this.add(Label_Time);
		
		//달력을 사용하기 위해서 달력을 정의한다.
		Calendar today = Calendar.getInstance();
		int Today_Month = today.get(Calendar.MONTH) + 1;
		int Today_Date = today.get(Calendar.DATE);
		
		//라벨을 만든다. 라벨을 패널에 추가한다.
		Label_Today = Method.Make_Label(String.valueOf(Today_Month)
				+"월 " + String.valueOf(Today_Date)+"일", GlobalValue.Font_String, 110,20,60,15);  
		this.add(Label_Today);
		
		//라벨을 만든다. 라벨을 패널에 추가한다.
		Label_Title_Memo = Method.Make_Label("To-do",GlobalValue.Font_String,570,20,100,15);
		this.add(Label_Title_Memo);

		//텍스트에어리어를 만든다.
		TextField_Memo = Method.Make_TextArea(TextField_Memo, "");
		//스크롤패널을 만들어서 텍스트에어리어를 추가한다.
		ScrollPanel_Main = Method.Make_ScrollPanel(TextField_Memo,900,100,300,200);
		//스크롤패널을 패널에 붙인다.
		this.add(ScrollPanel_Main);
		
		//텍스트필드를 만든다. 텍스트필드를 패널에 추가한다.
		TextField_Memo_Title = Method.Make_Textfield("", GlobalValue.Font_String, 900,60,300,30, true);
		this.add(TextField_Memo_Title);
		
		//버튼을 만든다. 버튼을 패널에 추가한다.
		Button_Add = Method.Make_Button(Button_Add, "추가", 
				GlobalValue.Font_String, 1140,305, 60, 30, GlobalValue.Color_Button, GlobalValue.Color_Font);
		Button_Add.addActionListener(this);
		this.add(Button_Add);
		
		//파일 IO를 위해서 파일의 경로르 설정한다.
		File path = new File("data/memo");
		File arr[] = path.listFiles();
		
		//추가할 버튼배열의 위치를 정의하는 변수를 설정한다.
		int sizex =0;
		int sizey= 0;
		//저장되어있는 메모에 관련된 데이터파일을 불러온다. 불러온 파일의 제목을 이용해 버튼을 만든다. 버튼을 패널에 추가한다.
		for(int i=0; i<arr.length;i++) {
			String name = arr[i].getName();
			sizex = i * 130;
			sizey = sizex /520;
			sizex = sizex % 520;
			name = name.replaceAll(".txt", "");
			Button_Memo[i] = new JButton();
			Button_Memo[i] = Method.Make_Button(Button_Memo[i],
					name,GlobalValue.Font_String,350 +sizex,60 + (sizey *70),100,56,
					GlobalValue.Color_Memo,GlobalValue.Color_Font);
			Button_Memo[i].addActionListener(this);
			this.add(Button_Memo[i]);
		}
		
		//읽어온 현재 파일의 갯수를 저장하는 변수
		Count_Button = arr.length;
		
		//URL창을 나타내는 매소드.
		URL_Call();
	}
	
	public void actionPerformed(ActionEvent event) {
		
		//계산을 위해서 밑의 변수를 초기화한다.
		int sizex = 0;
		int sizey = 0;
		//누른 버튼의 타입을 정의한다.
		String type = event.getActionCommand();
		//버튼이 "추가"버튼일때,
		if(type == "추가") {
			//(1)추가하려는 메모의 제목을 이용해서 같은 메모가 있는지 검색한다.
			//시그널 변수를 선언
			int check = 0;
			//추가할 내용을 str_contents에 저장
			String str_Contents = TextField_Memo.getText();
			//엔터가 이상하게 되기때문에 그부분을 수정한다.
			str_Contents.replaceAll("\r\n","<br>");
			//추가할 제목과 내용을 받아온다.
			String str_Title= TextField_Memo_Title.getText();
			
			//사용 불가능한 문자 체크
			if(Method.CheckString(str_Title) == 1){
				JOptionPane.showMessageDialog(this, "사용할 수 없는 문자가 포함되어있습니다.(\\:?|/><*\")");
				check = 1;
			}
			else{
				for(int i=0;i<Count_Button;i++) {
					if(Button_Memo[i].getText().equals(str_Title)) {
					//같은메모가 존재하는지 찾는다.
						check=1;
						JOptionPane.showMessageDialog(this, "같은 제목의 메모가 존재합니다.");
					}
				}
				if(GlobalValue.MAX_BUTTON_COUNT == Count_Button &&check==0)
				{
					JOptionPane.showMessageDialog(this, "더 이상 추가할 수 없습니다.");
					check = 1;
				}
			}
			//(2)같은 제목의 메모가 없으면, 해당 제목으로 버튼을 생성
			if(check==0){
				//글자를 받아왔기 때문에 화면의 텍스트입력창을 초기화한다.
				TextField_Memo_Title.setText("");
				TextField_Memo.setText("");
				Button_Memo[Count_Button] = new JButton();
				sizex = Count_Button * 130;
				sizey = sizex /520;
				sizex = sizex % 520;
				Button_Memo[Count_Button] = Method.Make_Button(Button_Memo[Count_Button],
						str_Title,GlobalValue.Font_String,350 +sizex,60 + (sizey *70),100,56,
						GlobalValue.Color_Memo,GlobalValue.Color_Font);
				Button_Memo[Count_Button].addActionListener(this);
				this.add(Button_Memo[Count_Button]);
				//앞서만든 버튼을 표시하기 위해서 repaint를 이용해서 새로고침
				this.repaint();
				//버튼을 만든후 저장경로에 내용을 텍스트파일형태로 기록한다.
				Method.Make_File("data/memo/",str_Title+".txt", str_Contents);
				Count_Button ++;	
			}
		}
		//누른버튼이 수정일 경우
		else if(type == "수정"){
			//해당파일을 지우고 새로만들고 전체를 불러온다.
			Method.Make_File("data/memo/",SubTextArea_Title.getText()+".txt", SubTextArea.getText());
			SubFrame.dispose();
		}	
		//누른버튼이 삭제일 경우
		else if(type == "삭제"){
			//파일을 지우고 전체를 불러온다.
			Method.Delete_File("data/memo/"+ SubTextArea_Title.getText() + ".txt");
			SubFrame.dispose();
		    for(int i=0;i< Count_Button;i++) {
		    	this.remove(Button_Memo[i]);
		    	this.repaint();
		    }
		    File path = new File("data/memo");
			File arr[] = path.listFiles();
			sizex =0;
			sizey= 0;
			for(int i=0; i<arr.length;i++) {
				String name = arr[i].getName();
				sizex = i * 130;
				sizey = sizex /520;
				sizex = sizex % 520;
				name = name.replaceAll(".txt", "");
				Button_Memo[i] = Method.Make_Button(Button_Memo[i], name, GlobalValue.Font_String, 
						350 +sizex,60 + (sizey *70),100,56,GlobalValue.Color_Memo,GlobalValue.Color_Font);
				Button_Memo[i].addActionListener(this);
				this.add(Button_Memo[i]);
			}
			Count_Button = arr.length;
		}
		//그밖에 클릭 = 해당 메모 버튼을 눌렀을때
		else {
			//새로운 프레임을 띄우면서 버튼에 해당하는 제목을 이용해서 내용을 프레임에 띄워준다.
			int n=0;
			SubFrame = Method.Make_Frame("Memo : "+type, 500, 530, Color.ORANGE);
			
			SubTextArea = Method.Make_TextArea(SubTextArea, Method.Read_File("data/memo/", type+".txt"));
			
			ScrollPanel_Sub = Method.Make_ScrollPanel(SubTextArea,20,50,440,370);
			SubFrame.add(ScrollPanel_Sub);
			
			SubTextArea_Title = Method.Make_Textfield(type,GlobalValue.Font_String,20,20,440,20,false);
			SubFrame.add(SubTextArea_Title);
			
			Button_delete = Method.Make_Button(Button_delete, "삭제", GlobalValue.Font_String, 390, 430, 70, 30, GlobalValue.Color_Button, GlobalValue.Color_Font);
			Button_delete.addActionListener(this);
			SubFrame.add(Button_delete);
			
			Button_edit = Method.Make_Button(Button_edit, "수정", GlobalValue.Font_String, 310, 430, 70, 30, GlobalValue.Color_Button, GlobalValue.Color_Font);
			Button_edit.addActionListener(this);
			SubFrame.add(Button_edit);
		}

	}
	
	public  JComponent createContent() {
        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        webBrowserPanel.setBorder(BorderFactory.createTitledBorder("공지사항"));
        JWebBrowser webBrowser = new JWebBrowser();
        webBrowser.navigate("http://board.sejong.ac.kr/boardlist.do?bbsConfigFK=333");
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        webBrowserPanel.setBounds(350, 340, 850, 280);
        this.add(webBrowserPanel, BorderLayout.CENTER);
        // Create an additional bar allowing to show/hide the menu bar of the web browser.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
        JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar", webBrowser.isMenuBarVisible());
        menuBarCheckBox.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            webBrowser.setMenuBarVisible(e.getStateChange() == ItemEvent.SELECTED);
          }
        });
        buttonPanel.add(menuBarCheckBox);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        return contentPane;
    }
	public  void URL_Call() {
		NativeInterface.open();
       //UIUtils.setPreferredLookAndFeel();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createContent();
            }
        });
        NativeInterface.runEventPump();
	}
}
