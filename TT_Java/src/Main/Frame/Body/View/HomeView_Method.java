package Main.Frame.Body.View;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.net.URL;
import GlobalValue.GlobalValue;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.io.*;

public class HomeView_Method {
	public JButton Make_Button(JButton Button,String str,Font font,
			int x,int y,int width,int height,Color bgColor,Color strColor) {
		Button = new JButton(str);
		Button.setFont(font);
		Button.setBounds(x,y,width,height);
		Button.setBackground(bgColor);
		Button.setForeground(strColor);
		Button.setBorder(new BevelBorder(BevelBorder.RAISED));
		return Button;
	}
	
	public JFrame Make_Frame(String str, int x, int y, Color bgColor) {
	JFrame frame = new JFrame(str);
			frame.setVisible(true);
			frame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					frame.setVisible(false);
					frame.dispose();
				}
			});
			frame.setSize(x, y);
			frame.setBackground(bgColor);
			frame.setLayout(null);
		return frame;
	}
	
	public JScrollPane Make_ScrollPanel(Component view,int x,int y,int width,int height) {
		JScrollPane ScrollPanel = new JScrollPane(view);
		ScrollPanel.setBounds(x,y,width,height);
		ScrollPanel.setBorder(null);
		return ScrollPanel;
	}
	
	public JLabel Make_Label(String str,Font font,int x,int y,int width,int height) {
		JLabel Label= new JLabel(str);
		Label.setFont(font);
		Label.setForeground(GlobalValue.Color_Label);
		Label.setBounds(x,y,width,height);
		return Label;
	}
	public JTextField Make_Textfield(String str,Font font,
			int x,int y,int width, int height, Boolean true_or_false) {
		JTextField Textfield = new JTextField(str);
		Textfield.setFont(font);
		Textfield.setEditable(true_or_false);
		Textfield.setBounds(x,y,width,height);
		Textfield.setBackground(GlobalValue.Color_Text);
		Border border=new BevelBorder(BevelBorder.LOWERED);
		Textfield.setBorder(border);
		return Textfield;
	}
	
	
	public JTextArea Make_TextArea(JTextArea Textarea,String Contents) {
		Textarea = new JTextArea();
		Textarea.setFont(GlobalValue.Font_String);
		Textarea.setText(Contents);
		Textarea.setCaretPosition(0);
		Textarea.setLineWrap(true);
		Textarea.setBackground(GlobalValue.Color_Text);
		Border border=new BevelBorder(BevelBorder.LOWERED);
		Textarea.setBorder(border);
		return Textarea;
	}
	public void Make_File(String path,String title, String contents) {
		contents.replaceAll("\r\n","<br>");
		try {
			PrintWriter pw = new PrintWriter
					(new BufferedWriter(new FileWriter(path+title)));
			pw.println(contents);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Delete_File(String path) {
		File f = new File(path);
	    f.delete();
	}

	public String Read_File(String path, String title) {
		String Contents = null; //반환되는 최종 문자열(파일 전체)
		String Str = null; // 한줄씩 읽은 문자열
		try {
			BufferedReader br = 
					new BufferedReader(new FileReader(path + title));
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
		return Contents;
		
	}
	
	public int CheckString(String str) {
		int signal = 0;
		if(str.contains("?") || str.contains("|")|| str.contains("\\")|| str.contains("/")|| str.contains("*")
				|| str.contains(":")|| str.contains("<")|| str.contains(">")|| str.contains("\""))
		{
			signal = 1;
		}
		return signal;
	}
}
