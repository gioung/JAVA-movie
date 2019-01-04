import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
//�󿵰����� �����ϴ� class
public class MovieTheater {
     private ArrayList<MovieList> list = new ArrayList<>();//�󿵰� ����Ʈ 
     //�ε��� 0 - 1�󿵰� , �ε���1 - 2�󿵰� ,�ε���2 -3�󿵰�
     String Filename = "movie.txt"; //��ȭ���� ����()
     
     public MovieTheater() { //������ �󿵰��� 3���� ���� �� ����
    	 for(int i=0;i<3;i++)
    	 list.add(new MovieList());
    }
    //setter �� getter
	public ArrayList<MovieList> getList() {
		return list;
	}

	public void setList(ArrayList<MovieList> list) {
		this.list = list;
	}
	
	public void saveData() {//��ȭ������ ����(movie.txt)�� ����
		try {//���� ����
		BufferedWriter writer = new BufferedWriter(new FileWriter(Filename));
		
		for(int i=0;i<list.size();i++) {
			MovieList movlist=list.get(i); //���񸮽�Ʈ ��ü(1�󿵰����� 3�󿵰����� �ݺ�)
			int size=movlist.getList().size();
			for(int j=0;j<size;j++) {//�ش� �󿵰��� ��ȭ�� �����Ѵٸ� �ݺ�
			int number = movlist.getList().get(j).getNumber(); //��ȭ�� �󿵰���ȣ
			String name = movlist.getList().get(j).getName(); //��ȭ�� ����
			String director = movlist.getList().get(j).getDirector(); //����
			String genre = movlist.getList().get(j).getGenre();//�帣
			int running_time = movlist.getList().get(j).getRunning_time();//��Ÿ��
			//���Ͽ� ����. �� ��ȭ������ , �� �����ؼ� ����.
			writer.write(number+","+name+","+director+","+genre+","+running_time);
			writer.newLine(); //�� ��ȭ������ ���� �ٹٲ�
			}
		}
		writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadData() { //���Ͽ� �����ϴ� ��ȭ������ ����Ʈ�� �ҷ��´�.
		try {
			String line;
			BufferedReader buf = new BufferedReader(new FileReader(Filename));
    		while((line=buf.readLine())!=null) { //�ٴ����� �д´�.
    			if(line!="") {//������� �������� �����Ƿ� �����ϱ� ���� ���ǹ�
    				// �� ���� ,�� �������� ��ū�� ������.
    				StringTokenizer token = new StringTokenizer(line,",");
    				int number = Integer.parseInt(token.nextToken()); 
    				String name = token.nextToken();
    				String director = token.nextToken();
    				String genre = token.nextToken();
    				int rt = Integer.parseInt(token.nextToken());
    				//����Ʈ�� �߰�
    				list.get(number-1).add(new Movie(number,name,director,genre,rt));
    			}
    		}
    		buf.close();//���� �ݱ�
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
     
}
