import java.util.ArrayList;
//��ȭ���� �����ϴ� �󿵰� class MovieList �Ѱ��� �󿵰� 1��
public class MovieList {
     private ArrayList<Movie> list = new ArrayList<>();//�󿵰��� ��ȭ����Ʈ
     
     public void add(Movie m) { //��ȭ ��ü�� ����Ʈ�� �߰�
    	 list.add(m);
     }
     
     public void delete(String name) { //��ȭ �����޼ҵ�
    	 for(int i=0; i<list.size();i++) { //�̸��� �Ű������� �޾Ƽ� ������ ��ȭ�� ������ ����
    	 if(list.get(i).getName().equals(name)) {
    		 list.remove(i);
    	 }}
     }
     
    //setter �� getter
	public ArrayList<Movie> getList() {
		return list;
	}

	public void setList(ArrayList<Movie> list) {
		this.list = list;
	}
}
