import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
//상영관들을 관리하는 class
public class MovieTheater {
     private ArrayList<MovieList> list = new ArrayList<>();//상영관 리스트 
     //인덱스 0 - 1상영관 , 인덱스1 - 2상영관 ,인덱스2 -3상영관
     String Filename = "movie.txt"; //영화저장 파일()
     
     public MovieTheater() { //무조건 상영관은 3개로 시작 및 고정
    	 for(int i=0;i<3;i++)
    	 list.add(new MovieList());
    }
    //setter 및 getter
	public ArrayList<MovieList> getList() {
		return list;
	}

	public void setList(ArrayList<MovieList> list) {
		this.list = list;
	}
	
	public void saveData() {//영화정보를 파일(movie.txt)에 저장
		try {//파일 열기
		BufferedWriter writer = new BufferedWriter(new FileWriter(Filename));
		
		for(int i=0;i<list.size();i++) {
			MovieList movlist=list.get(i); //무비리스트 객체(1상영관부터 3상영관까지 반복)
			int size=movlist.getList().size();
			for(int j=0;j<size;j++) {//해당 상영관에 영화가 존재한다면 반복
			int number = movlist.getList().get(j).getNumber(); //영화의 상영관번호
			String name = movlist.getList().get(j).getName(); //영화의 제목
			String director = movlist.getList().get(j).getDirector(); //감독
			String genre = movlist.getList().get(j).getGenre();//장르
			int running_time = movlist.getList().get(j).getRunning_time();//런타임
			//파일에 쓴다. 각 영화정보는 , 로 구분해서 쓴다.
			writer.write(number+","+name+","+director+","+genre+","+running_time);
			writer.newLine(); //한 영화정보를 쓴후 줄바꿈
			}
		}
		writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadData() { //파일에 존재하는 영화정보를 리스트로 불러온다.
		try {
			String line;
			BufferedReader buf = new BufferedReader(new FileReader(Filename));
    		while((line=buf.readLine())!=null) { //줄단위로 읽는다.
    			if(line!="") {//빈공백을 읽을때도 있으므로 방지하기 위한 조건문
    				// 한 줄을 ,를 기준으로 토큰을 나눈다.
    				StringTokenizer token = new StringTokenizer(line,",");
    				int number = Integer.parseInt(token.nextToken()); 
    				String name = token.nextToken();
    				String director = token.nextToken();
    				String genre = token.nextToken();
    				int rt = Integer.parseInt(token.nextToken());
    				//리스트로 추가
    				list.get(number-1).add(new Movie(number,name,director,genre,rt));
    			}
    		}
    		buf.close();//파일 닫기
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
