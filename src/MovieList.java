import java.util.ArrayList;
//영화들을 저장하는 상영관 class MovieList 한개는 상영관 1개
public class MovieList {
     private ArrayList<Movie> list = new ArrayList<>();//상영관의 영화리스트
     
     public void add(Movie m) { //영화 객체를 리스트에 추가
    	 list.add(m);
     }
     
     public void delete(String name) { //영화 삭제메소드
    	 for(int i=0; i<list.size();i++) { //이름을 매개변수로 받아서 동일한 영화가 있으면 삭제
    	 if(list.get(i).getName().equals(name)) {
    		 list.remove(i);
    	 }}
     }
     
    //setter 및 getter
	public ArrayList<Movie> getList() {
		return list;
	}

	public void setList(ArrayList<Movie> list) {
		this.list = list;
	}
}
