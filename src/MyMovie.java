//������ ������ ��ȭ class
public class MyMovie {
	
	private int number; //�󿵰���ȣ
	private int index; //��ȭ ����(�ش�󿵰����� �󿵼��� 1���� ���ϸ��� ��)
	private String name; //��ȭ ����
	private int rt;//���� Ÿ��
	//������
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
