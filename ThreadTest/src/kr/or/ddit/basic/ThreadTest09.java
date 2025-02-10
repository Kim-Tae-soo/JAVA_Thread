package kr.or.ddit.basic;

// 쓰레드의 상태를 출력하는 예제

public class ThreadTest09 {

	public static void main(String[] args) {
//		TargetThread tar = new TargetThread();
//		StatePrintThread th = new StatePrintThread(tar);
		
		StatePrintThread th = new StatePrintThread(new TargetThread());
		th.start();
	}

}

// TagetThread의 상태를 읽어와 출력하는 쓰레드
class StatePrintThread extends Thread {
	private TargetThread target;

	public StatePrintThread(TargetThread target) {
		this.target = target;

	}

	@Override
	public void run() {
		while (true) {
			// 쓰레드의 상태값 구하기 == > getState() 메서드 이용
			Thread.State state = target.getState(); // 검사 대상 쓰레드의 상태값 구하기

			System.out.println("TargetThread의 상태값 : " + state);

			if (state == Thread.State.NEW) { // TargetThread가 NEW 상태이면...
				target.start();
			}

			if (state == Thread.State.TERMINATED) { // TargetThread가 종료 상태이면...
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				
			}

		}
	}
}

// 쓰레드 상태의 검사 대상이 되는 쓰레드
class TargetThread extends Thread {
	@Override
	public void run() {
		for (long i = 1L; i <= 2_000_000_000L; i++) {
			for(long j=1; j<=5; j++ ) {
				long a = i + j;
			}
		}

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		for (long i = 1L; i <= 2_000_000_000L; i++) {
			for(long j=1; j<=5; j++ ) {
				long a = i + j;
			}
		}
	}
}