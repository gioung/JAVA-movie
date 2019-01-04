import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UserList { //등록한 유저들을 담는 class
    private ArrayList<User> list=new ArrayList<>(); //ArrayList를 이용
    private String Filename; //Filename(output.txt)를 담는변수 
    public UserList(String Filename) {
    	this.Filename=Filename;
    	String line; 
    	try { //등록된 회원정보를 저장한 파일을 읽어온다. 파일열기
    		BufferedReader buf = new BufferedReader(new FileReader(Filename));
    		while((line=buf.readLine())!=null) { //줄단위로 읽어온다
    			if(line!="") { //간혹 공백을 읽는경우가 있으므로 방지하기위한 조건문
    				//StringTokenizer객체가 ,를 기준으로 문자열  line을 토큰으로 나눈다
    				StringTokenizer token = new StringTokenizer(line,",");
    				String uid = token.nextToken(); //맨첫번째 토큰은 아이디
    				String upwd = token.nextToken(); //그다음 토큰은 비밀번호
    				list.add(new User(uid,upwd)); //파일에서 불러온 데이터를 list에저장
    			}
    		}
    		buf.close(); //파일 닫기
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
    
	public void saveData() { //새로 등록된 회원정보를 기존회원정보에 추가해 파일에 쓴다.
		try {
			//파일 열기
			BufferedWriter buf = new BufferedWriter(new FileWriter("output.txt"));
			for(int i=0;i<list.size();i++) {
			//아이디 , 비밀번호 형태로 파일에 쓰기
			buf.write(list.get(i).getId()+","+list.get(i).getPwd()); 
			buf.newLine(); //회원정보를 하나 쓴 후에 다음줄로 넘어간다.
            }
			buf.close(); //파일닫기
			for(int i=0;i<list.size();i++) { 
				//아이디 생성시 그아이디에 해당하는 영화예매내역을 저장하는 파일을 만든다. 파일제목은 아이디
			buf = new BufferedWriter(new FileWriter(list.get(i).getId()+".txt"));}
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
}
