package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램
		 
		/*
		 	Thread를 사용하는 방법
		 	
		 	방법1)
		 		Thread 클래스를 상속한 class를 작성한 후 이 class의 인스턴스(객체)를 생성하고
		 		이 인스턴스의 start()메서드를 호출해서 실행한다. 
		 	
		 */
		
		MyThread1 th1 = new MyThread1(); // 인스턴스 생성
		th1.start();
//		th1.run();
		
		/*
		 	방법2)
		 		Runnable 인터페이스를 구현한 class를 작성한 후 이 class의 인스턴스를 생성한다.
		 		이 인스턴스를 Thread객체를 생성할 때 생성자의 인수값으로 넣어서 생성한다.
		 		생성된 Thread의 인스턴스의 start() 메서드로 호출해서 실행한다.
		 */
		MyRunner1 r1 = new MyRunner1(); // Runnable을 구현한 class의 인스턴스 생성 
		Thread th2 = new Thread(r1);		// Thread의 인스턴스(객체) 생성
		th2.start();
//		th2.run();
		
		// 방법3) ==> Runnable 인터페이스를 이용한 익명구현체를 작성하여 사용하는 방법
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for(int i=1; i<=200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
				
			}
		};
		Thread th3 = new Thread(r2);
		th3.start();
//		th3.run();
		
		System.out.println();
		System.out.println("main메서드 끝..");
	}

}

// 방법1) ==> Thread 클래스를 상속 할 class 작성

class MyThread1 extends Thread {
	// run() 메서드를 재정의 해준다.
	// 이 run() 메서드는 쓰레드에 처리할 내용을 작성한다
	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간)메서드는 주어진 '시간'동안 작업을 잠시 멈춘다.
				// '시간'은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
	}
}

// 방법2) ==> Runnable 인터페이스를 구현한 class 작성

class MyRunner1 implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}

	}
}