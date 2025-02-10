package kr.or.ddit.basic;

public class ThreadTest15 {

	public static void main(String[] args) {
		// 공통으로 사용할 객체 생성
		ShareObject sObj = new ShareObject();
		
		TestThread th1 = new TestThread("TEST1", sObj);
		TestThread th2 = new TestThread("TEST2", sObj);
		
		th1.start();
		th2.start();
	}

}

class TestThread extends Thread{
	
	private ShareObject sObj;
	
	public TestThread(String name, ShareObject sObj) {
		super(name);	// 쓰레드의 name 설정
		this.sObj = sObj;
		
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			sObj.add();
		}
	}
}



// 공통 클래스
class ShareObject {
	private int sum;

	// 동기화 처리하기
	public void add() {
		// public synchronized void add() { // 방법1 ==> 메서드에 synchronized 동기화 설정을 한다.
		synchronized (this){

			int n = sum;

			n += 10;

			sum = n;

			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);

		}

	}
}