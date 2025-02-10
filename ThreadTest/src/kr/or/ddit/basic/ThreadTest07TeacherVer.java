package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest07TeacherVer {
	
	public static boolean inputCheck;

	public static void main(String[] args) {
		GameCountDown gcd = new GameCountDown();
		
		// 난수를 이용해서 컴퓨터 가위 바위 보 정하기
		String[] data = {"가위","바위","보"};
		int index = (int)(Math.random()*3);
		String com = data[index];
		
		// 사용자의 가위 바위 보 입력 받기
		gcd.start(); // 카운트 다운 쓰레드 실행
		String man = null;
		do {
		 man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요...");
		}while(!"가위".equals(man) && !"바위".equals(man) && !"보".equals(man));
		
		inputCheck = true;
		
		// 결과 판정
		String result = "";
		if(com.equals(man)) {
			result = "DRAW";
		}else if("가위".equals(com) && "보".equals(man) ||
				 "바위".equals(com) && "가위".equals(man) ||
				 "보".equals(com) && "바위".equals(man)) {
			result ="USER LOSE";
		}else {
			result = "USER WIN";
		}
		
		System.out.println("-- RESULT --");
		System.out.println("COM : " + com);
		System.out.println("USER : " + man);
		System.out.println("RESULT : " + result);
		
		

	}

}

class GameCountDown extends Thread{
	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			
			// 입력이 완료되었는지 여부를 검사해서 입력이 완료되면 쓰레드를 종료시킨다.
			
			if(ThreadTest07TeacherVer.inputCheck==true) {
				return;
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}

		System.out.println("-- RESULT --");
		System.out.println("TIME OVER. \nUSER LOSE.");
		System.exit(0);
	}
}