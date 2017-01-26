package Main.Frame;


import GlobalValue.GlobalValue;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Font;
import java.io.*;

import javax.swing.*;
// java.awt 랑 javax.swing은 그래픽 라이브러리

public class MainFrame extends JFrame implements ActionListener,MouseListener{
	private int nWidth;
	private int nHeight;
	private JLabel Label_ID,Label_Password, Label_Image, Label_PName;
	private JLabel Label_Subtitle;
	private ImageIcon Label_ImageIcon;
	private JTextField TextField_ID,TextField_Password;
	private JPanel LoginPanel;
	private JButton Button1;
	private String Input_ID;
	private String Input_Password;
	
	
	public MainFrame() {
		this.setTitle("Log-In");
		this.setPreferredSize(new Dimension(GlobalValue.nLoginFrameWidth,GlobalValue.nLoginFrameHeight));
		this.setBackground(Color.black);
		this.setVisible(true);
		this.setResizable(false);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 프레임의 크기를 설정
		// 프레임의 배경색을 설정
		// 프레임을 보일껀지 안보일껀지 설정
		// 프레임의 x버튼을 누르면 어떻게 할껀지 설정
		
		
		LoginPanel = new JPanel();
		LoginPanel.setLayout(null);
		LoginPanel.setBackground(Color.WHITE);
		//프레임위에서 사용할 패널을 정의한다.
		Label_ImageIcon = new ImageIcon("image/logo.jpg");
		Label_Image = new JLabel(Label_ImageIcon);
		Label_Image.setBounds(GlobalValue.nButtonx+25,10,250,250);
		LoginPanel.add(Label_Image);
		
		Label_PName = new JLabel("T.T-Time-Table");
		Label_PName.setFont(GlobalValue.Font_PName);
		Label_PName.setBounds(GlobalValue.nButtonx+60,GlobalValue.nButtony-160,200,50);
		LoginPanel.add(Label_PName);
		
		Label_ID = new JLabel("아이디(학번)");
		Label_ID.setFont(GlobalValue.Font_Label);
		Label_ID.setBounds(GlobalValue.nButtonx,GlobalValue.nButtony-100,100,50);
		LoginPanel.add(Label_ID);
		
		TextField_ID = new JTextField(10);
	//	TextField_ID.addMouseListener(this);
		TextField_ID.setBounds(GlobalValue.nButtonx + 100,GlobalValue.nButtony-100,200,50);
		LoginPanel.add(TextField_ID);
		//텍스트영역을 만들어서 패널에 추가한다.

		
		Label_Password = new JLabel("비밀번호");
		Label_Password.setFont(GlobalValue.Font_Label);
		Label_Password.setBounds(GlobalValue.nButtonx+30,GlobalValue.nButtony-50,100,50);
		LoginPanel.add(Label_Password);
		
		TextField_Password = new JPasswordField(10);
		TextField_Password.setBounds(GlobalValue.nButtonx+100,GlobalValue.nButtony-50,200,50);
		LoginPanel.add(TextField_Password);
		
		//텍스트영역을 만들어서 패널에 추가한다.
		
		Button1 = new JButton("로그인");
		Button1.setBounds(GlobalValue.nButtonx,GlobalValue.nButtony+20,300,50);
		Button1.addActionListener(this);
		Button1.setFont(GlobalValue.Font_Button);
		Button1.setBackground(Color.DARK_GRAY);
		Button1.setForeground(Color.WHITE);
		LoginPanel.add(Button1);
		
		Label_Subtitle = new JLabel("By 설현내꺼");
		Label_Subtitle.setFont(GlobalValue.Font_PName);
		Label_Subtitle.setBounds(300,670,300,50);
		LoginPanel.add(Label_Subtitle);
		this.add(LoginPanel);
		//패널을 프레임에 추가한다.
			
		this.pack();
	}
	public void actionPerformed(ActionEvent event) {
		Input_ID = TextField_ID.getText();
		Input_Password = TextField_Password.getText();
		int check = 0;
		try {
			BufferedReader idpw = new BufferedReader(new FileReader("idpw.txt"));
			String temp;
			while((temp = idpw.readLine()) != null) {
				String[] split = temp.split(" ");
				if(split[0].equals(Input_ID) && split[1].equals(Input_Password))
				{
					System.out.println(Input_ID+" 사용자가 로그인했습니다.");
					check = 1;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check == 0)
		{
			JOptionPane.showMessageDialog(this, "아아디 / 비밀번호를 확인해주세요.");
		}
		if(check == 1)
		{
			File dir = new File("user/"+Input_ID);
			File dir_data = new File(dir+"/data");
			File dir_memo = new File(dir_data + "/memo");
			File dir_image = new File(dir_data + "/image");
			if(!dir.exists()) {
				dir.mkdirs();
			}
			if(!dir_data.exists()) {
				dir_data.mkdirs();
			}
			if(!dir_memo.exists()) {
				dir_memo.mkdirs();
			}
			if(!dir_image.exists()) {
				dir_image.mkdirs();
			}
			JOptionPane.showMessageDialog(this, "로그인 확인. 프로그램을 실행합니다.");
		}
		//윗부분에 로그인인증알고리즘을 추가요망.
		TextField_ID.setText(null);
		TextField_Password.setText(null);
	}
	public void mouseDragged(MouseEvent event) {}
	public void mouseMoved(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	public void mouseClicked(MouseEvent event) {}

	
}