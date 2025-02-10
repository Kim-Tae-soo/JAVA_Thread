package kr.or.ddit.basic;

/*
	10마리의 말들이 경주하는 경마 크로그램 작성하기
	
	말은 Hores라는 이름의 쓰레드 클래스로 작성하기
	이 클래스의 말 이름(String), 등수(int), 현재 위치(int)를 멤버 변수로 갖는다.
	그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내주 정렬 기준이 있다.
		(Complarable 인터페이스 구현)
	경기 구간은 1~50구간으로 되어있다.
	
	경기 중 중간에 각 말들의 위치를 나타낸다.
	예)
	01번말 ------->--------------------------
	02번발 -->-------------------------------
	...
	10번말 ------------>---------------------
	
	경기가 끝나면 등수 수능로 출력한다.
 */

public class ThreadTest13 {

	public static void main(String[] args) {
		
		Horse[] HorseArr = new Horse[] {
				new Horse("태수馬 "),	
				new Horse("철민馬 "),	
				new Horse("학범馬 "),	
				new Horse("상윤馬 "),	
				new Horse("성화馬 "),	
				new Horse("아린馬 "),	
				new Horse("성운馬 "),	
				new Horse("현식馬 "),	
				new Horse("현준馬 "),	
				new Horse("윤석馬 ")	
			};
			
			for(Horse dc : HorseArr) {
				dc.start();
			}
			for(Horse dc : HorseArr) {
				try {
					dc.join();
				} catch (Exception e) {
					
				}
			}
			
			System.out.println();
			System.out.println("result");
			System.err.println("TOTAL RANK\n" + Horse.setRank);

		}

	}



class Horse extends Thread{
	public static String setRank = ""; // 출력을 끝낸 순서대로 이름이 저장될 변수
	private String name; // 쓰레드 이름이 저장될 변수
	private int location;
	
	
	
	public Horse(String name) {
		super();
		this.name = name;
	}



	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			System.out.println(name + " Horse result : " + i);
			try {
				// 지연 시간을 난수를 이용하여 처리한다.
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
				
			}
		}
		
		System.out.println(name + " Horse End...");
		
		//출력을 끝낸 순서대로 setRank변수에 추가한다.
		setRank += name + "";
	}
}
