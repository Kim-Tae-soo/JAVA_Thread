package kr.or.ddit.basic;

/*
 	3개의 쓰레드가 각각 알파벳 A ~ Z까지를 출력하는데 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기
 */

public class ThreadTest12 {

	public static void main(String[] args) {
		DisplayCharacter[] dispArr = new DisplayCharacter[] {
			new DisplayCharacter("김태수 "),	
			new DisplayCharacter("김철민 "),	
			new DisplayCharacter("이성화 ")	
		};
		
		for(DisplayCharacter dc : dispArr) {
			dc.start();
		}
		for(DisplayCharacter dc : dispArr) {
			try {
				dc.join();
			} catch (Exception e) {
				
			}
		}
		
		System.out.println();
		System.out.println("result");
		System.err.println("RANK : " + DisplayCharacter.setRank);

	}

}

// A ~ Z 까지 출력하는 쓰레드
class DisplayCharacter extends Thread {
	public static String setRank = ""; // 출력을 끝낸 순서대로 이름이 저장될 변수
	private String name; // 쓰레드 이름이 저장될 변수

	// 생성자
	public DisplayCharacter(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + " User result : " + ch);
			try {
				// 지연 시간을 난수를 이용하여 처리한다.
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
				
			}
		}
		
		System.out.println(name + " User End...");
		
		//출력을 끝낸 순서대로 setRank변수에 추가한다.
		setRank += name + "";
	}
}