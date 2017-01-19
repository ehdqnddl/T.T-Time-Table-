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

public class HomeView extends JPanel implements ActionListener{

	// 이부분은 클래스 내에서 사용할 변수선언을 하는 부분.

	private static final String HomeView = null;
	private JLabel Label_Time;
	private JLabel Label_Today;
	private JLabel Label_Table;
	private JLabel Label_Notice;
	private JLabel Label_Title_Memo;
	private JLabel Label_Memo;
	private JTextArea TextField_Memo;
	private JTextField TextField_Memo_Title;
	private JButton Button_Add;
	private JButton[] Button_Memo = new JButton[20];
	private ImageIcon Image_Memo = new ImageIcon("data/image/Memo.png");
	private int Count_Button = 0;
	
	public HomeView() {
		this.setLayout(null);
		// 이부분을 편집하면 프로그램의 우리가 원하는 부분이 편집됨.
		Label_Time = new JLabel("<HTML>"
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
				+ "</HTML>");
	//	Label_Time.setOpaque(true); 
	//	Label_Time.setBackground(Color.WHITE);
		Label_Time.setFont(GlobalValue.Font_Number);
		Label_Time.setBounds(10,10,60,650);  
		this.add(Label_Time);
		
		Calendar today = Calendar.getInstance();
		int Today_Month = today.get(Calendar.MONTH) + 1;
		int Today_Date = today.get(Calendar.DATE);
		Label_Today = new JLabel(String.valueOf(Today_Month)
				+"월 " + String.valueOf(Today_Date)+"일");
		Label_Today.setFont(GlobalValue.Font_String);
		Label_Today.setBounds(80,20,60,15);  
		this.add(Label_Today);
		
		Label_Title_Memo = new JLabel("To-do");
		Label_Title_Memo.setBounds(900,20,100,15);
		Label_Title_Memo.setFont(GlobalValue.Font_String);
		this.add(Label_Title_Memo);

		TextField_Memo = new JTextArea();
		TextField_Memo.setLineWrap(true);
		TextField_Memo.setFont(GlobalValue.Font_String);
		JScrollPane Panel_Scroll = new JScrollPane(TextField_Memo);
		Panel_Scroll.setBounds(900,100,300,200);
		this.add(Panel_Scroll);
		
		TextField_Memo_Title = new JTextField();
		TextField_Memo_Title.setFont(GlobalValue.Font_String);
		TextField_Memo_Title.setBackground(Color.WHITE);
		TextField_Memo_Title.setBounds(900,60,300,30);
		this.add(TextField_Memo_Title);
		
		Button_Add = new JButton("추가");
		Button_Add.setBounds(1140,310, 60, 30);
		Button_Add.setBackground(Color.BLACK);
		Button_Add.setForeground(Color.WHITE);
		Button_Add.addActionListener(this);
		this.add(Button_Add);
		
		//jbtn[i] = new JButton();
		//jbtn[i].setBounds(i*100,i*100,100,100);
		//this.add(jbtn[i]);
	}
	
	public void actionPerformed(ActionEvent event) {
		String type = event.getActionCommand();
		if(type == "추가") {
			//System.out.println(Count_Button);
			Button_Memo[Count_Button] = new JButton();
			//Button_Memo[Count_Button].setIcon(Image_Memo);
			Button_Memo[Count_Button].setBounds(350 + Count_Button*130,60,100,100);
			Button_Memo[Count_Button].setBackground(Color.YELLOW);
			Button_Memo[Count_Button].setFont(GlobalValue.Font_String);
			Button_Memo[Count_Button].setBorderPainted(true);
			Button_Memo[Count_Button].addActionListener(this);
			String str_Title = TextField_Memo_Title.getText();
			String str_Contents = TextField_Memo.getText();
			str_Contents.replaceAll("\r\n","<br>");
			Button_Memo[Count_Button].setText(str_Title);
			this.add(Button_Memo[Count_Button]);
			this.repaint();
			
			try {
				PrintWriter pw = new PrintWriter
						(new BufferedWriter(new FileWriter("data/memo/"+str_Title+".txt")));
				pw.println(str_Contents);
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Count_Button ++;	
		}
		else {
			int n=0;
			System.out.println(type);
			String Contents = null;
			String Str = null;
			//Contents ContentsFrame = new Contents();
			final Frame fs = new Frame("Memo : "+ type);
			fs.setVisible(true);
			fs.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					fs.setVisible(false);
					fs.dispose();
				}
			});
			fs.setSize(500, 500);
			fs.setLocation(200, 200);
			JTextArea SubTextArea = new JTextArea();
			SubTextArea.setFont(GlobalValue.Font_String);
			JScrollPane Panel_Scroll2 = new JScrollPane(SubTextArea);
			
			try {
				BufferedReader br = 
						new BufferedReader(new FileReader("data/memo/"+type+".txt"));
				Contents = br.readLine();
				Contents += "\r\n";
				while((Str = br.readLine()) != null) {
					Contents += Str + "\r\n";
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SubTextArea.setText(Contents);
			SubTextArea.setEditable(false);
			SubTextArea.setCaretPosition(0);
			fs.add(Panel_Scroll2);
		}
	}
	

}
