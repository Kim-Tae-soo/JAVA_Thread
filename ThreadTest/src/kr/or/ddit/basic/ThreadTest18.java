package kr.or.ddit.basic;
/*
 	wait(), notify()를 이용하여 두 쓰레드가 번갈아 한번씩 실행되는 예제
 	
 	- wait(), notify(), notifyAll()메서드는 동기화 영역에서만 사용 가능하다.
 */
public class ThreadTest18 {

	public static void main(String[] args) {
		WorkObject obj = new WorkObject();
		
		WorkThreadA thA = new WorkThreadA(obj);
		WorkThreadB thB = new WorkThreadB(obj);
		
		thA.start();
		thB.start();

	}

}

// WorkObject의 methodA() 메서드만 호출하는 쓰레드
class WorkThreadA extends Thread{
	private WorkObject workObj;

	public WorkThreadA(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			workObj.methodA();
		}
		synchronized (workObj) {
			workObj.notify();
		}
	}
}

//WorkObject의 methodB() 메서드만 호출하는 쓰레드
class WorkThreadB extends Thread{
	private WorkObject workObj;

	public WorkThreadB(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			workObj.methodB();
		}
		synchronized (workObj) {
			workObj.notify();
		}
	}
	
}


// 공통으로 사용할 클래스
class WorkObject{
	public synchronized void methodA() {
		System.out.println("methodA() 메서드 실행 중...");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	
	}
	
	public synchronized void methodB() {
		System.out.println("methodB() 메서드 작업 중...");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	
	}
	
}
