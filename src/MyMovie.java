//유저가 예매한 영화 class
public class MyMovie {
	
	private int number; //상영관번호
	private int index; //영화 순번(해당상영관내의 상영순서 1번이 제일먼저 상영)
	private String name; //영화 제목
	private int rt;//러닝 타임
	//생성자
	public MyMovie(int number, int index, String name, int rt) {
		super();
		
		this.number = number;
		this.index = index;
		this.name = name;
		this.rt = rt;
	}
	
	
   //setter & getter
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRt() {
		return rt;
	}
	public void setRt(int rt) {
		this.rt = rt;
	}
	@Override
	public String toString() {
		return "MyMovie [number=" + number + ", index=" + index + ", name=" + name + ", rt=" + rt + "]";
	}
}
