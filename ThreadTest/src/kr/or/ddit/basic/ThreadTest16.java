package kr.or.ddit.basic;
/*
 	은행의 입출금을 쓰레드로 처리하는 예제
 	(synchronized를 이용한 동기화 처리 예제)
 */

public class ThreadTest16 {

	private int balance; // 잔액이 저장될 변수

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금을 처리하는 메서드
	public void deposit(int money) {
		balance += money;
	}

	// 출금하는 메서드 ( 성공 시 : true , 실패 : false 반환)
	// ==> 동기화 처리를 통해서 출금이 정상적으로 이루어지도록 한다.
	// public synchronized boolean withdraw(int money) { // 메서드 동기화 방식
	public boolean withdraw(int money) {
		synchronized (this) {	// 동기화 블럭으로 동기화 설정
			if (balance >= money) {
				// 시간 지연용 반복문...
				for (int i = 1; i <= 100_000_000; i++) {
					for (int j = 1; j <= 2; j++) {
						int a = i + j;
					}
				}
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());

				return true;
			} else {
				return false;
			}

		}

	}

	public static void main(String[] args) {
		ThreadTest16 acount = new ThreadTest16();
		acount.setBalance(10000);

		// 익명 구현체로 쓰레드 구현

		Runnable r = new Runnable() {
			@Override
			public void run() {
				boolean result = acount.withdraw(6000);
				System.out.println("쓰레드에서 result = " + result + ", balance = " + acount.getBalance());
			}
		};
		// --------------------------------
		Thread th1 = new Thread(r);
		Thread th2 = new Thread(r);

		th1.start();
		th2.start();
	}
}
