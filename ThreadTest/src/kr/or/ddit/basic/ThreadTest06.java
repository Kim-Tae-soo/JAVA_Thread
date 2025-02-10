package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataInput th1 = new DataInput();
		MyCount th2 = new MyCount();
		
		th1.start();
		th2.start();
	}

}

// 데이터를 입력 받는 쓰레드
class DataInput extends Thread {
	// 입력의 완료 여부를 확인하기 위한 변수 선언
	// 이 변수는 쓰레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false;
	
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요...");
		
		inputCheck = true; // 입력이 온료되면 inputCheck를 true로 변경
		
		System.out.println("입력값 : " + str);
	}
}

// 카운트 다운을 처리하는 쓰레드
class MyCount extends Thread {
	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			
			// 입력이 완료되었는지 여부를 검사해서 입력이 완료되면 쓰레드를 종료시킨다.
			
			if(DataInput.inputCheck==true) {
				return;
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}

		System.out.println("경과시간이 초과했습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}
