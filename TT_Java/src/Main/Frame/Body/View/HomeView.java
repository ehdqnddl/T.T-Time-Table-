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

	// �̺κ��� Ŭ���� ������ ����� ���������� �ϴ� �κ�.
	HomeView_Method Method = new HomeView_Method();
	private static final String HomeView = null;
	private JLabel Label_Time,Label_Today,Label_Title_Memo; //����ϴ� ��
	private JTextArea TextField_Memo,SubTextArea; //����ϴ� �ؽ�Ʈ�����
	private JTextField TextField_Memo_Title,SubTextArea_Title; //����ϴ� �ؽ�Ʈ�ʵ�
	private JButton Button_delete, Button_edit,Button_Add; //����ϴ� ��ư
	private JButton[] Button_Memo = new JButton[20]; //�迭�� �����ϴ� ��ư
	private int Count_Button = 0; //��ư�� ī��Ʈ�� ���� ����
	private int Check_str = 0; //���ڿ� �˻� �ñ׳�
	private JFrame SubFrame; //���ο�â�� ���� ����
	private JScrollPane ScrollPanel_Main,ScrollPanel_Sub; //����ϴ� �г�
	
	

	public HomeView() {
		//Ȩ���� ���̾ƿ��� ���ش�.(���� ���̾ƿ��� �����ϱ����ؼ� ���)
	    
		this.setBackground(GlobalValue.Color_Frame);
		this.setLayout(null);
		
		//���� �����. ���� �гο� �߰��Ѵ�.
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
		
		//�޷��� ����ϱ� ���ؼ� �޷��� �����Ѵ�.
		Calendar today = Calendar.getInstance();
		int Today_Month = today.get(Calendar.MONTH) + 1;
		int Today_Date = today.get(Calendar.DATE);
		
		//���� �����. ���� �гο� �߰��Ѵ�.
		Label_Today = Method.Make_Label(String.valueOf(Today_Month)
				+"�� " + String.valueOf(Today_Date)+"��", GlobalValue.Font_String, 110,20,60,15);  
		this.add(Label_Today);
		
		//���� �����. ���� �гο� �߰��Ѵ�.
		Label_Title_Memo = Method.Make_Label("To-do",GlobalValue.Font_String,570,20,100,15);
		this.add(Label_Title_Memo);

		//�ؽ�Ʈ���� �����.
		TextField_Memo = Method.Make_TextArea(TextField_Memo, "");
		//��ũ���г��� ���� �ؽ�Ʈ���� �߰��Ѵ�.
		ScrollPanel_Main = Method.Make_ScrollPanel(TextField_Memo,900,100,300,200);
		//��ũ���г��� �гο� ���δ�.
		this.add(ScrollPanel_Main);
		
		//�ؽ�Ʈ�ʵ带 �����. �ؽ�Ʈ�ʵ带 �гο� �߰��Ѵ�.
		TextField_Memo_Title = Method.Make_Textfield("", GlobalValue.Font_String, 900,60,300,30, true);
		this.add(TextField_Memo_Title);
		
		//��ư�� �����. ��ư�� �гο� �߰��Ѵ�.
		Button_Add = Method.Make_Button(Button_Add, "�߰�", 
				GlobalValue.Font_String, 1140,305, 60, 30, GlobalValue.Color_Button, GlobalValue.Color_Font);
		Button_Add.addActionListener(this);
		this.add(Button_Add);
		
		//���� IO�� ���ؼ� ������ ��θ� �����Ѵ�.
		File path = new File("data/memo");
		File arr[] = path.listFiles();
		
		//�߰��� ��ư�迭�� ��ġ�� �����ϴ� ������ �����Ѵ�.
		int sizex =0;
		int sizey= 0;
		//����Ǿ��ִ� �޸� ���õ� ������������ �ҷ��´�. �ҷ��� ������ ������ �̿��� ��ư�� �����. ��ư�� �гο� �߰��Ѵ�.
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
		
		//�о�� ���� ������ ������ �����ϴ� ����
		Count_Button = arr.length;
		
		//URLâ�� ��Ÿ���� �żҵ�.
		URL_Call();
	}
	
	public void actionPerformed(ActionEvent event) {
		
		//����� ���ؼ� ���� ������ �ʱ�ȭ�Ѵ�.
		int sizex = 0;
		int sizey = 0;
		//���� ��ư�� Ÿ���� �����Ѵ�.
		String type = event.getActionCommand();
		//��ư�� "�߰�"��ư�϶�,
		if(type == "�߰�") {
			//(1)�߰��Ϸ��� �޸��� ������ �̿��ؼ� ���� �޸� �ִ��� �˻��Ѵ�.
			//�ñ׳� ������ ����
			int check = 0;
			//�߰��� ������ str_contents�� ����
			String str_Contents = TextField_Memo.getText();
			//���Ͱ� �̻��ϰ� �Ǳ⶧���� �׺κ��� �����Ѵ�.
			str_Contents.replaceAll("\r\n","<br>");
			//�߰��� ����� ������ �޾ƿ´�.
			String str_Title= TextField_Memo_Title.getText();
			
			//��� �Ұ����� ���� üũ
			if(Method.CheckString(str_Title) == 1){
				JOptionPane.showMessageDialog(this, "����� �� ���� ���ڰ� ���ԵǾ��ֽ��ϴ�.(\\:?|/><*\")");
				check = 1;
			}
			else{
				for(int i=0;i<Count_Button;i++) {
					if(Button_Memo[i].getText().equals(str_Title)) {
					//�����޸� �����ϴ��� ã�´�.
						check=1;
						JOptionPane.showMessageDialog(this, "���� ������ �޸� �����մϴ�.");
					}
				}
				if(GlobalValue.MAX_BUTTON_COUNT == Count_Button &&check==0)
				{
					JOptionPane.showMessageDialog(this, "�� �̻� �߰��� �� �����ϴ�.");
					check = 1;
				}
			}
			//(2)���� ������ �޸� ������, �ش� �������� ��ư�� ����
			if(check==0){
				//���ڸ� �޾ƿԱ� ������ ȭ���� �ؽ�Ʈ�Է�â�� �ʱ�ȭ�Ѵ�.
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
				//�ռ����� ��ư�� ǥ���ϱ� ���ؼ� repaint�� �̿��ؼ� ���ΰ�ħ
				this.repaint();
				//��ư�� ������ �����ο� ������ �ؽ�Ʈ�������·� ����Ѵ�.
				Method.Make_File("data/memo/",str_Title+".txt", str_Contents);
				Count_Button ++;	
			}
		}
		//������ư�� ������ ���
		else if(type == "����"){
			//�ش������� ����� ���θ���� ��ü�� �ҷ��´�.
			Method.Make_File("data/memo/",SubTextArea_Title.getText()+".txt", SubTextArea.getText());
			SubFrame.dispose();
		}	
		//������ư�� ������ ���
		else if(type == "����"){
			//������ ����� ��ü�� �ҷ��´�.
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
		//�׹ۿ� Ŭ�� = �ش� �޸� ��ư�� ��������
		else {
			//���ο� �������� ���鼭 ��ư�� �ش��ϴ� ������ �̿��ؼ� ������ �����ӿ� ����ش�.
			int n=0;
			SubFrame = Method.Make_Frame("Memo : "+type, 500, 530, Color.ORANGE);
			
			SubTextArea = Method.Make_TextArea(SubTextArea, Method.Read_File("data/memo/", type+".txt"));
			
			ScrollPanel_Sub = Method.Make_ScrollPanel(SubTextArea,20,50,440,370);
			SubFrame.add(ScrollPanel_Sub);
			
			SubTextArea_Title = Method.Make_Textfield(type,GlobalValue.Font_String,20,20,440,20,false);
			SubFrame.add(SubTextArea_Title);
			
			Button_delete = Method.Make_Button(Button_delete, "����", GlobalValue.Font_String, 390, 430, 70, 30, GlobalValue.Color_Button, GlobalValue.Color_Font);
			Button_delete.addActionListener(this);
			SubFrame.add(Button_delete);
			
			Button_edit = Method.Make_Button(Button_edit, "����", GlobalValue.Font_String, 310, 430, 70, 30, GlobalValue.Color_Button, GlobalValue.Color_Font);
			Button_edit.addActionListener(this);
			SubFrame.add(Button_edit);
		}

	}
	
	public  JComponent createContent() {
        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        webBrowserPanel.setBorder(BorderFactory.createTitledBorder("��������"));
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
