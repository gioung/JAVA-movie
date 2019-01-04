import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
//����ڰ� ������ ��ȭ(MyMovie)���� ��� class
public class MyList {
    private ArrayList<MyMovie> list = new ArrayList<>();//MyMovie����Ʈ ����
    
    private String line;//������ ���پ� ������ ������ �����ϱ� ���� ����
    private String userid;//���� �α����� ������ ��ȭ���ų����� �����ϱ����� ���̵�,���ϸ� ���̵��Դϴ�.(Login���� ��Ͻ� ���ϻ���)
   //������
    public MyList(String id) {
    	
    	userid = id; //id
    	try {//������ ��ȭ���ų����� ����Ǿ��ִ� ������ �о�´�.
			BufferedReader reader = new BufferedReader(new FileReader(userid+".txt"));
			while((line=reader.readLine())!=null)//���پ� �д´�.
				if(line!="") {//������ �������� �����Ƿ� ������ �����ϱ����� ���
    				StringTokenizer token = new StringTokenizer(line,",");
    				int number = Integer.parseInt(token.nextToken()); //�󿵰���ȣ
    				int index =  Integer.parseInt(token.nextToken()); //��ȭ ����
    				String name = token.nextToken();//��ȭ����
    				int rt = Integer.parseInt(token.nextToken());//����Ÿ��
    				list.add(new MyMovie(number,index,name,rt));//����Ʈ�� �߰�
    				
    		}
			reader.close();//���� �ݱ�
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

//setter & getter
	public ArrayList<MyMovie> getList() {
		return list;
	}
    
	public void setList(ArrayList<MyMovie> list) {
		this.list = list;
	}
	//���� ���ŵ� ��ȭ�� list�� �߰�
	public void add(Movie m,int index) {//��ȭ��ü�� ������ �Ű������ι޴´�
		
		int number = m.getNumber();
		int i =  index;
		String name = m.getName();
		int rt = m.getRunning_time();
		list.add(new MyMovie(number,i,name,rt)); //�󿵰���ȣ,����,����,����Ÿ��(����������)
		
	}
	public void saveData() { //���ų����� ����Ʈ�κ��� ����(�ش��������̵��̸����� ���ִ�����)�� �����Ѵ�
		try {//���Ͽ���
			BufferedWriter writer = new BufferedWriter(new FileWriter(userid+".txt"));
			for(int i=0;i<list.size();i++) {
				MyMovie m = list.get(i);
				int number = m.getNumber();
				int index =  m.getIndex();
				String name = m.getName();
				int rt = m.getRt();
				writer.write(+number+","+index+","+name+","+rt);//���Ͼ���
				writer.newLine();
			}
			writer.close();//���� �ݱ�
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
