package kr.or.ddit.basic;

import java.lang.reflect.Array;
import java.util.Arrays;

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
		
		Horse[] horseArr = new Horse[] {
				new Horse("태수"),	
				new Horse("철민"),	
				new Horse("학범"),	
				new Horse("상윤"),	
				new Horse("성화"),	
				new Horse("아린"),	
				new Horse("성운"),	
				new Horse("현식"),	
				new Horse("현준"),	
				new Horse("윤석")	
			};
		
		GameState gs = new GameState(horseArr);
		for(Horse h : horseArr) {
			h.start();
		}
		
		gs.start();
		
		for(Horse h : horseArr) {
			try {
				h.join();
			} catch (InterruptedException e) {
			}
			try {
				gs.join();
			} catch (InterruptedException e) {
			}
			
			System.out.println();
			System.out.println("경기 끝");
			System.out.println();
			
			//등수의 오름차순으로 정렬
			Arrays.sort(horseArr);	// 배열 정렬하기
		}
		
		for(Horse h : horseArr) {
			System.out.println(h);
		}
			
		}

	}


// 경주마 클래스 작성
class Horse extends Thread implements Comparable<Horse>{
	
	public static int currentRank = 0;	// 말의 등수를 구할 때 사용하는 변수
	
	private String horesName;		// 말이름
	private int rank;				// 등수
	private int location;			// 위치
	
	public Horse(String horesName) {
		this.horesName = horesName;
	}

	public String getHoresName() {
		return horesName;
	}

	public void setHoresName(String horesName) {
		this.horesName = horesName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return  horesName + "은(는)" + rank + "등 입니다.";
	}
	
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(rank, horse.rank);
	}
	
	@Override
	public void run() {
		for(int i=1; i<=50; i++) {
			location = i;	// 현재 말의 위치
			try {
				Thread.sleep((int)(Math.random()*700));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		// 말의 등수를 구하는곳
		// 한 마리의 말이 경주가 끝나면 등수를 구해서 설정한다.
		currentRank++;
		rank = currentRank;
	}
	
	
}

// 경기 중 중간에 각 말드의 위치를 나타내야함.
// 경기중 말의 현재 위치를 나타내는 쓰레드

class GameState extends Thread{
	private Horse[] horseArr;		// 경주에 참여한 경주마가 저장된 배열

	// 생성자
	public GameState(Horse[] horseArr) {
		this.horseArr = horseArr;
	}
	
	@Override
	public void run() {
		while(true) {
			// 모든 말들의 경기가 종료되었는지 여부 검사
			if(Horse.currentRank == horseArr.length) {
				break;
			}
			for(int i=1; i<10; i++) {
				System.out.println();
			}
			
			for (int i=0; i<horseArr.length; i++) {
				System.out.print(horseArr[i].getHoresName() + " : ");
				for (int j=1; j<=50; j++) {
					// 말의 현재 위치를 검사한다.
					if(horseArr[i].getLocation()==j) {
						System.out.print("🏃‍♂️‍➡️");
					}else {
						System.out.print("-");						
					}
				}
				System.out.println(); // 줄바꿈용
			}
		}
	}
	
	
	
}
