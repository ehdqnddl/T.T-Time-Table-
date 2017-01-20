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
	private JTextArea SubTextArea;
	private JTextField SubTextArea_Title;
	private JFrame fs;
	
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
		Label_Today.setBounds(110,20,60,15);  
		this.add(Label_Today);
		
		Label_Title_Memo = new JLabel("To-do");
		Label_Title_Memo.setBounds(570,20,100,15);
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
		
		File path = new File("data/memo");
		File arr[] = path.listFiles();
		int sizex =0;
		int sizey= 0;
		for(int i=0; i<arr.length;i++) {
			String name = arr[i].getName();
			sizex = i * 130;
			sizey = sizex /520;
			sizex = sizex % 520;
			name = name.replaceAll(".txt", "");
			Button_Memo[i] = new JButton();
			//Button_Memo[Count_Button].setIcon(Image_Memo);
			Button_Memo[i].setBounds(350 +sizex,60 + (sizey *70),100,56);
			Button_Memo[i].setBackground(Color.YELLOW);
			Button_Memo[i].setFont(GlobalValue.Font_String);
			Button_Memo[i].setBorderPainted(true);
			Button_Memo[i].addActionListener(this);
			Button_Memo[i].setText(name);
			this.add(Button_Memo[i]);
		}
		Count_Button = arr.length;
		URL_Call();
	}
	
	public void actionPerformed(ActionEvent event) {
		int sizex = 0;
		int sizey = 0;
		String type = event.getActionCommand();
		if(type == "추가") {
			//System.out.println(Count_Button);
			int check = 0;
			
			String str_Title = TextField_Memo_Title.getText();
			String str_Contents = TextField_Memo.getText();
			for(int i=0;i<Count_Button;i++) {
				if(Button_Memo[i].getText().equals(str_Title)) {
					check=1;
					JOptionPane.showMessageDialog(this, "같은 제목의 메모가 존재합니다.");
					
				}
			}
			if(GlobalValue.MAX_BUTTON_COUNT == Count_Button &&check==0)
			{
				JOptionPane.showMessageDialog(this, "더 이상 추가할 수 없습니다.");
				check = 1;
			} 
			if(check==0){
				Button_Memo[Count_Button] = new JButton();
				sizex = Count_Button * 130;
				sizey = sizex /520;
				sizex = sizex % 520;
				//Button_Memo[Count_Button].setIcon(Image_Memo);
				Button_Memo[Count_Button].setBounds(350 + sizex,60 + (sizey * 70),100,56);
				Button_Memo[Count_Button].setBackground(Color.YELLOW);
				Button_Memo[Count_Button].setFont(GlobalValue.Font_String);
				Button_Memo[Count_Button].setBorderPainted(true);
				Button_Memo[Count_Button].addActionListener(this);
	
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
		}
		else if(type == "수정"){
			String edit_Contents = SubTextArea.getText();
			String edit_Title = SubTextArea_Title.getText();
			edit_Contents.replaceAll("\r\n","<br>");
			
			try {
				PrintWriter pw = new PrintWriter
						(new BufferedWriter(new FileWriter("data/memo/"+edit_Title+".txt")));
				pw.println(edit_Contents);
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fs.dispose();
		}	
		else if(type == "삭제"){
			String Delete_Title = SubTextArea_Title.getText();
			File f = new File("data/memo/"+ Delete_Title + ".txt");
		    f.delete();
		    fs.dispose();
		    for(int i=0;i< Count_Button;i++) {
		    	System.out.println(Button_Memo[i].getText());
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
				Button_Memo[i] = new JButton();
				//Button_Memo[Count_Button].setIcon(Image_Memo);
				Button_Memo[i].setBounds(350 +sizex,60 + (sizey *70),100,56);
				Button_Memo[i].setBackground(Color.YELLOW);
				Button_Memo[i].setFont(GlobalValue.Font_String);
				Button_Memo[i].setBorderPainted(true);
				Button_Memo[i].addActionListener(this);
				Button_Memo[i].setText(name);
				this.add(Button_Memo[i]);
			}
			Count_Button = arr.length;
		}
		else {
			int n=0;
			System.out.println(type);
			String Contents = null;
			String Str = null;
			//Contents ContentsFrame = new Contents();
			fs = new JFrame("Memo : "+ type);
			fs.setVisible(true);
			fs.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					fs.setVisible(false);
					fs.dispose();
				}
			});
			fs.setSize(500, 530);
			fs.setBackground(Color.orange);
			//fs.setLocation(200, 200);
			fs.setLayout(null);
			SubTextArea = new JTextArea();
			SubTextArea.setFont(GlobalValue.Font_String);
			JScrollPane Panel_Scroll2 = new JScrollPane(SubTextArea);
			Panel_Scroll2.setBounds(20,50,440,370);
			
			try {
				BufferedReader br = 
						new BufferedReader(new FileReader("data/memo/"+type+".txt"));
				Contents = br.readLine();
				Contents += "\r\n";
				while((Str = br.readLine()) != null) {
					Contents += Str + "\r\n";
				}
				br.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SubTextArea.setText(Contents);
			//SubTextArea.setEditable(false);
			SubTextArea.setCaretPosition(0);
			fs.add(Panel_Scroll2);
			
			SubTextArea_Title = new JTextField(type);
			SubTextArea_Title.setFont(GlobalValue.Font_String);
			SubTextArea_Title.setEditable(false);
			SubTextArea_Title.setBounds(20, 20, 440, 20);
			fs.add(SubTextArea_Title);
			
			JButton Button_delete = new JButton("삭제");
			Button_delete.setBounds(390, 430, 70, 30);
			Button_delete.setBackground(Color.BLACK);
			Button_delete.setFont(GlobalValue.Font_String);
			Button_delete.setForeground(Color.WHITE);
			Button_delete.addActionListener(this);
			fs.add(Button_delete);	
			
			JButton Button_edit = new JButton("수정");
			Button_edit.setBounds(310, 430, 70, 30);
			Button_edit.setBackground(Color.BLACK);
			Button_edit.setFont(GlobalValue.Font_String);
			Button_edit.setForeground(Color.WHITE);
			Button_edit.addActionListener(this);
			fs.add(Button_edit);
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
