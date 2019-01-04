//영화 클래스
public class Movie {
    private String name; //영화제목
    private String director; //감독
    private String genre; //장르
    private int running_time; //러닝타임 (분 단위)
    private int number; //상영관 번호 (상영관은 1~3까지 있다.)
	//생성자
    public Movie(int number,String name, String director, String genre, int running_time) {
    	super();
		this.number = number;
		this.name = name;
		this.director = director;
		this.genre = genre;
		this.running_time = running_time;
	}

    //setter 및 getter
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getRunning_time() {
		return running_time;
	}

	public void setRunning_time(int running_time) {
		this.running_time = running_time;
	}
    
    
    
    
}
