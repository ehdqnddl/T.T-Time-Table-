package Main;

import Main.Frame.MainFrame;

//mainŬ���� c����� main���� ����
public class Main {
	//static - ���� �� static �޸𸮸� �Ҵ� , ���������� ��밡�������� ���̻���ϸ� ȿ�������� ���� 
	//final - define�� ����
	
	//����������(������ ������ ������ �����ο���)
	//private - ������ �ش�Ŭ���������� ��밡��, �ٸ� Ŭ�������� �����Ҽ� ����
	//protected - ������ �ش�Ŭ���� �Ӹ��ƴ϶� �ش�Ŭ������ ����� Ŭ���������� ��밡��
	//public - ��ü�� ����Ǹ� ��𿡼��� ���ٰ���, �������� ������
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		MainFrame mainFrame = null;
		System.out.println("���α׷��� ����˴ϴ�.");
		mainFrame = new MainFrame();
		if(mainFrame == null) {
			System.out.println("���α׷��� ������� �ʾҽ��ϴ�.");
		}
	}

}
