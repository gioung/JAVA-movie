//��ȭ Ŭ����
public class Movie {
    private String name; //��ȭ����
    private String director; //����
    private String genre; //�帣
    private int running_time; //����Ÿ�� (�� ����)
    private int number; //�󿵰� ��ȣ (�󿵰��� 1~3���� �ִ�.)
	//������
    public Movie(int number,String name, String director, String genre, int running_time) {
    	super();
		this.number = number;
		this.name = name;
		this.director = director;
		this.genre = genre;
		this.running_time = running_time;
	}

    //setter �� getter
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
