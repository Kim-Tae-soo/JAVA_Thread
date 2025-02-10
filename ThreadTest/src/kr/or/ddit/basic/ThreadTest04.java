package kr.or.ddit.basic;
/*
 	1~20억 까지의 합계를 구하는 프로그램을 하나의 쓰레드가 단독으로 처리할 때와
 	여러개의 쓰렏가 협력해서 처리할 때의 경과시간을 출력해보자.
 */

public class ThreadTest04 {

	public static void main(String[] args) {
		// 단독으로 처리하는 쓰레드 객체 생성
		SumThread sm = new SumThread(1L, 2_000_000_000L);
		long startTime = System.currentTimeMillis();
		
		sm.start();
		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리했을 때 경과시간 : " + (endTime - startTime));
		System.out.println("------------------------------------------------");
		
		// 여러 쓰레드가 협력해서 처리하는 경우
		
//		SumThread sum1 = new SumThread(1L, 500_000_000L);
//		SumThread sum2 = new SumThread(500_000_000L, 1_000_000_000L);
//		SumThread sum1 = new SumThread(1_000_000_000L, 1_500_000_000L);
//		SumThread sum1 = new SumThread(1_500_000_000L, 2_000_000_000L);

		SumThread[] sumArr = new SumThread[] { 
				new SumThread(			  1L, 500_000_000L),
				new SumThread(	500_000_001L, 1_000_000_000L), 
				new SumThread(1_000_000_001L, 1_500_000_000L),
				new SumThread(1_500_000_001L, 2_000_000_000L)

		};
		startTime = System.currentTimeMillis();
		
		for(SumThread sumTh : sumArr) {
			sumTh.start();
		}
		for(SumThread sumTh : sumArr) {
			try {
				sumTh.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("여러 쓰레드가 협력해서 처리한 경과 시간 : " +
							(endTime -startTime));
		

	}

}

// 합계를 구하는 Thread 클래스 작성
class SumThread extends Thread {
	// 합계를 구할 영역의 시작값과 종료값
	private long start,end;

	public SumThread(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		long sum = 0;
		for (long i = start; i <= end; i++) {
			sum += i;
		}
		System.out.println(start + "부터 " + end + "까지의 합계\n합계 : " + sum);
	}
}
