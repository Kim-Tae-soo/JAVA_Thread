package kr.or.ddit.basic;

/*
 	Thread의 stop() 메서드를 호출하면 쓰레드가 바로 멈춘다.
 	이 때 사용하던 자원을 정리하지 못하고 쓰레드가 종료되어 다른 쓰레드에 영향을 줄 수 있다.
 	그래서 stop()메서드는 비추천으로 되어 있다.
 	
 */

public class ThreadTest11 {

	public static void main(String[] args) {
		/*
		ThreadStopTest01 th1 = new ThreadStopTest01(); // 객체 생성
		th1.start();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		
		}
		*/
		//th1.stop << 이건 버전업 되고서 실행 안됌
		//th1.setStop(true); // 이걸써야함
		
		
		// interrupt() 메서드를 이용한 쓰레드 멈추기
		ThreadStopTest02 th2 = new ThreadStopTest02();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		th2.interrupt();
		
	}

}

// interrupt()메서드를 이용하여 쓰레드를 멈추게 하는 연습용 쓰레드
class ThreadStopTest02 extends Thread{
	
	/*
		// 방법1 ==> interrupt()메서드와 sleep메서드를 이용하는 방법
	@Override
	public void run() {
		try {
			while(true) {
				System.out.println("쓰레드 실행 중...");
				
				Thread.sleep(1); // 일시 정지 상태에서 interrup() 메서드가 실행되면 
								 // InterruptedException이 발생한다.
			}
		} catch (InterruptedException e) {
			
		}
		
		System.out.println("자원 정리중...");
		System.out.println("쓰레드 종료...");
	}
	*/
	
	// 방법2 ==> interrupt() 메서드가 호출되었는지 직접 검사하는 방법
	@Override
	public void run() {
		while(true) {
			System.out.println("Thread run...");
			/*
			// 검사방법1 ==> 쓰레드의 인스턴스 메서드인 isInterrupted() 이용하기
			//		isInterrupted()메서드는 interript()메서드가 호출되면 true를 반환한다.
			
			if(this.isInterrupted()) {
				break;
			}
			*/
			
			// 검사방법2 ==> 쓰레드의 정적 메서드인 interrupted() 이용하기
			//		interrupted() 메서드는 interrupt() 메서드가 호출되면 true를 반환한다.
			if(Thread.interrupted()) {
				break;
			}
			
		}
		System.out.println("clearing...");
		System.out.println("Thread Stop...");

		

	}
	
	
}


class ThreadStopTest01 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
		
	}
	
	@Override
		public void run() {
			while(!stop) {
				System.out.println("쓰레드가 처리할 내용 실행 중...");
			}
			
			System.out.println("자원 정리중 ...");
			System.out.println("쓰레드 종료...");
		}
}