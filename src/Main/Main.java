package Main;

import Main.Frame.MainFrame;

//main클래스 c언어의 main문과 같음
public class Main {
	//static - 선언 시 static 메모리를 할당 , 전역변수로 사용가능하지만 많이사용하면 효율적이지 못함 
	//final - define과 같음
	
	//접근제어자(밑으로 갈수록 접근이 자유로워짐)
	//private - 선언한 해당클래스에서만 사용가능, 다른 클래스에서 접근할수 없음
	//protected - 선언한 해당클래스 뿐만아니라 해당클래스를 상속한 클래스에서도 사용가능
	//public - 객체가 선언되면 어디에서든 접근가능, 안정성이 떨어짐
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//java에서 프린트 함수, c언어에서는 printf랑 비슷함
		System.out.println("print 함수");
		
		MainFrame mainFrame = new MainFrame();

	}

}
