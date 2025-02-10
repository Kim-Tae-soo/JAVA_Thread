package kr.or.ddit.basic;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

/*
 	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 	
 	컴퓨터의 가위 바위 보는 난수를 이용해서 구하고
 	사용자의 입력은 showInputDialog()메서드를 이용해서 입력 받는다.
 	
 	사용자의 입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
 	5초 안에 입력이 없으면 게임에 진것으로 처리한다.
 	5초 안에 입력이 있으면 승패의 결과를 구해서 출력한다.
 	
 	결과예시)
 	
 	1) 5초 안에 입력이 완료되었을 때
 		-- 결 과 --
 	컴퓨터 : 가위
 	사용자 : 바위
 	결 과 : 당신이 이겼습니다.
 	
 	2) 5초 안에 입력이 완료되지 않았을 때
 		-- 결 과 --
 	시간 초과로 당신이 패배하였습니다.
 */

public class ThreadTest07 {

	public static void main(String[] args) {
		userInput th1 = new userInput();
		timeOut th2 = new timeOut();
		th1.start();
		th2.start();
	}
}

class userInput extends Thread {
	// 입력의 완료 여부를 확인하기 위한 변수 선언
	// 이 변수는 쓰레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false;

	@Override
	public void run() {
		String[] choices = { "가위", "바위", "보" };
		Random random = new Random();
		int cc = random.nextInt(3); // 컴퓨터 난수 설정 (3) 인 이유 0, 1, 2 3가지

		String str = JOptionPane.showInputDialog("가위 바위 보 입력");
		System.out.println("User : " + str);
		System.out.println("Com : " + choices[cc]);
		if (str.equals(choices[cc])) {
			System.out.println("-- RESULT --");
			System.out.println("DRAW!");
		} else if ((str.equals("가위") && choices[cc].equals("보")) || (str.equals("바위") && choices[cc].equals("가위"))
				|| (str.equals("보") && choices[cc].equals("바위"))) {
			System.out.println("-- RESULT --");
			System.out.println("USER WIN!");
		} else {
			System.out.println("-- RESULT --");
			System.out.println("COM WIN!");
		}

		inputCheck = true; // 입력이 온료되면 inputCheck를 true로 변경

	}
}

// 카운트 다운을 처리하는 쓰레드
class timeOut extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if (userInput.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("-- 결 과 --");
		System.out.println("경과시간이 초과했습니다. \n니가 졌음.");
		System.exit(0);
	}
}
