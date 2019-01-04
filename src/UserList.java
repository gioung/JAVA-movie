import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UserList { //����� �������� ��� class
    private ArrayList<User> list=new ArrayList<>(); //ArrayList�� �̿�
    private String Filename; //Filename(output.txt)�� ��º��� 
    public UserList(String Filename) {
    	this.Filename=Filename;
    	String line; 
    	try { //��ϵ� ȸ�������� ������ ������ �о�´�. ���Ͽ���
    		BufferedReader buf = new BufferedReader(new FileReader(Filename));
    		while((line=buf.readLine())!=null) { //�ٴ����� �о�´�
    			if(line!="") { //��Ȥ ������ �д°�찡 �����Ƿ� �����ϱ����� ���ǹ�
    				//StringTokenizer��ü�� ,�� �������� ���ڿ�  line�� ��ū���� ������
    				StringTokenizer token = new StringTokenizer(line,",");
    				String uid = token.nextToken(); //��ù��° ��ū�� ���̵�
    				String upwd = token.nextToken(); //�״��� ��ū�� ��й�ȣ
    				list.add(new User(uid,upwd)); //���Ͽ��� �ҷ��� �����͸� list������
    			}
    		}
    		buf.close(); //���� �ݱ�
    	}catch(IOException e) {
    		  e.printStackTrace();
    	}
    }
	public ArrayList<User> getList() {
		return list;
	}
	public void setList(ArrayList<User> list) {
		this.list = list;
	}
    
	public void saveData() { //���� ��ϵ� ȸ�������� ����ȸ�������� �߰��� ���Ͽ� ����.
		try {
			//���� ����
			BufferedWriter buf = new BufferedWriter(new FileWriter("output.txt"));
			for(int i=0;i<list.size();i++) {
			//���̵� , ��й�ȣ ���·� ���Ͽ� ����
			buf.write(list.get(i).getId()+","+list.get(i).getPwd()); 
			buf.newLine(); //ȸ�������� �ϳ� �� �Ŀ� �����ٷ� �Ѿ��.
            }
			buf.close(); //���ϴݱ�
			for(int i=0;i<list.size();i++) { 
				//���̵� ������ �׾��̵� �ش��ϴ� ��ȭ���ų����� �����ϴ� ������ �����. ���������� ���̵�
			buf = new BufferedWriter(new FileWriter(list.get(i).getId()+".txt"));}
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
}
