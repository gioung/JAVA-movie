import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
//사용자가 예매한 영화(MyMovie)들을 담는 class
public class MyList {
    private ArrayList<MyMovie> list = new ArrayList<>();//MyMovie리스트 선언
    
    private String line;//파일을 한줄씩 읽을때 한줄을 저장하기 위한 변수
    private String userid;//현재 로그인한 유저의 영화예매내역을 저장하기위한 아이디,파일명도 아이디입니다.(Login에서 등록시 파일생성)
   //생성자
    public MyList(String id) {
    	
    	userid = id; //id
    	try {//유저의 영화예매내역이 저장되어있는 파일을 읽어온다.
			BufferedReader reader = new BufferedReader(new FileReader(userid+".txt"));
			while((line=reader.readLine())!=null)//한줄씩 읽는다.
				if(line!="") {//공백을 읽을때가 있으므로 오류를 방지하기위한 제어문
    				StringTokenizer token = new StringTokenizer(line,",");
    				int number = Integer.parseInt(token.nextToken()); //상영관번호
    				int index =  Integer.parseInt(token.nextToken()); //영화 순번
    				String name = token.nextToken();//영화제목
    				int rt = Integer.parseInt(token.nextToken());//러닝타임
    				list.add(new MyMovie(number,index,name,rt));//리스트로 추가
    				
    		}
			reader.close();//파일 닫기
			
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
	//새로 예매된 영화를 list에 추가
	public void add(Movie m,int index) {//영화객체와 순번을 매개변수로받는다
		
		int number = m.getNumber();
		int i =  index;
		String name = m.getName();
		int rt = m.getRunning_time();
		list.add(new MyMovie(number,i,name,rt)); //상영관번호,순번,제목,러닝타임(생성자참고)
		
	}
	public void saveData() { //예매내역을 리스트로부터 파일(해당유저아이디이름으로 되있는파일)로 저장한다
		try {//파일열기
			BufferedWriter writer = new BufferedWriter(new FileWriter(userid+".txt"));
			for(int i=0;i<list.size();i++) {
				MyMovie m = list.get(i);
				int number = m.getNumber();
				int index =  m.getIndex();
				String name = m.getName();
				int rt = m.getRt();
				writer.write(+number+","+index+","+name+","+rt);//파일쓰기
				writer.newLine();
			}
			writer.close();//파일 닫기
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
