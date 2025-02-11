package kr.or.ddit.basic;

public class ThreadTest19 {

	public static void main(String[] args) {

		DataBox box = new DataBox(); // 공통 사용 객체

		ProducerThread pth = new ProducerThread(box);
		ConsumerThread cth = new ConsumerThread(box);

		pth.start();
		cth.start();
	}

}

// 데이터를 넣어주는 쓰레드 (setValue() 메서드를 호출하는 쓰레드)
class ProducerThread extends Thread {
	private DataBox box;

	public ProducerThread(DataBox box) {
		super();
		this.box = box;
	}

	@Override
	public void run() {
		String[] nameArr = { "홍길동", "일지매", "이순신", "강감찬" };
		for (int i = 0; i < 4; i++) {
			box.setValue(nameArr[i]);
		}
	}
}

//데이터를 꺼내서 사용하는 쓰레드 (getValue()메서드를 호출하는 쓰레드)
class ConsumerThread extends Thread {
	private DataBox box;

	public ConsumerThread(DataBox box) {
		super();
		this.box = box;
	}

	@Override
	public void run() {
		for (int i = 0; i < 4; i++) {
			String data = box.getValue();
		}
	}

}

// 데이터를 공통으로 사용하는 클래스
class DataBox {
	private String value;

	// value값이 null 이면 value 변수에 문자열이 채워질 때까지 기다린다.
	// value변수에 값이 있으면 해당 문자열을 반환한다.
	// 값을 반환한 후에는 value변수 값이 null로 만든다.
	public synchronized String getValue() { // 메서드 동기화 처리
		if (value == null) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}

		String returnValue = value;

		System.out.println("쓰레드가 읽은 데이터 : " + returnValue);
		value = null;

		notify();
		return returnValue;
	}

	// value 변수에 값이 있으면 value 변수가 null이 될 때까지 기다린다.
	// value가 null이 되면 새로운 데이터를 저장한다.
	public synchronized void setValue(String value) {
		if (this.value != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}

		this.value = value;
		System.out.println("Thread에서 새로 저장한 데이터 : " + value);

		notify();
	}

}
