import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
//영화예매 화면
public class rsvPanel extends JPanel implements ActionListener{
    //필드 선언
	//패널
	private JPanel toppanel = new JPanel(); 
	private JPanel bottompanel = new JPanel(); 
	private JPanel[] panels = new JPanel[5]; 
	//주의사항 레이블
	private JLabel label = new JLabel("주의사항 : 총 예매 할 수 있는 영화갯수는 5개입니다.");
	//상영관 선택 라디오버튼
	private JRadioButton[] buttons = new JRadioButton[3];
	private JLabel[] info = new JLabel[6]; //밑에 문자열들을 나타내는 레이블
	private String[] infostr = {"선택","순번","제목","감독","장르","러닝타임"}; //예매가능영화 정보항목
	private JLabel[] labels = new JLabel[25]; //각 영화하나당 5개의 라벨을 이용해서 영화정보표시(총5개 영화표시)
	private JCheckBox[] checkboxes = new JCheckBox[5];//영화 선택을 위한 체크박스
	private JButton button = new JButton("예매하기");//예매하기 버튼
	private JButton button2 = new JButton("영화삭제(관리자용)");//영화삭제 버튼
	private JLabel state = new JLabel();//상태메시지 레이블
	private ButtonGroup group = new ButtonGroup();//라디오버튼을 위한 버튼그룹
	private MovieTheater m;//영화 삭제시 파일에 쓰기 위한 객체
	private MyList mylist;//영화 예매시 해당 아이디 파일명에 예매된영화 추가(예매내역)
	private listPanel listpanel; //예매내역 패널
	public rsvPanel(MovieTheater m,MyList mylist,listPanel listpanel) {
		this.listpanel=listpanel;
		this.m=m; 
		this.mylist=mylist;
		
		for(int i=0;i<panels.length;i++) { //패널 생성
			panels[i] = new JPanel();
	    }
		setLayout(new BorderLayout()); //보더 레이아웃 설정
		//탑패널에 패널0,1추가
		toppanel.add(panels[0]); 
		toppanel.add(panels[1]);
		toppanel.setLayout(new GridLayout(2,1));//탑패널 그리드레이아웃
		add(toppanel,BorderLayout.NORTH); //탑패널 위에 보더레이아웃 배치
		
		panels[0].add(label); //주의사항메시지 패널0에 추가
		for(int i=0;i<buttons.length;i++) { //상영관을 선택할수 있는 라디오버튼 생성
  		  buttons[i]=new JRadioButton((i+1)+"상영관");
  		  group.add(buttons[i]);
  		  panels[1].add(buttons[i]);
  		  buttons[i].addActionListener(this); //각 라디오버튼에 이벤트 추가
  	  }
		panels[2].setLayout(new GridLayout(6,6)); 
		for(int i=0;i<info.length;i++) { //영화정보 항목을 나타내는 레이블 생성
			info[i]=new JLabel(infostr[i]);
			info[i].setFont(new Font("Serif",Font.BOLD,15));
			panels[2].add(info[i]);
		}
		int a=0; //체크박스한개에 레이블5개가 영화 한개에 해당 이를 생성하기 위한 변수
		for(int i=0;i<checkboxes.length;i++) {//체크박스 초기화
			checkboxes[i] = new JCheckBox();  //체크박스생성
			checkboxes[i].setEnabled(false); //체크박스 이용불가
		    panels[2].add(checkboxes[i]); //패널2에 체크박스 추가
		for(int j=a;j<a+5;j++) { //레이블 초기화
			labels[j]=new JLabel("입력"); //레이블의 초기텍스트 "입력" 설정
			labels[j].setEnabled(false);//이용불가
			panels[2].add(labels[j]); //패널2에 추가
		}a+=5;}//그다음 줄에 레이블생성을 위한것
	    
		bottompanel.add(panels[3]); //바텀패널에 패널3,4추가
		bottompanel.add(panels[4]);
		bottompanel.setLayout(new GridLayout(2,1));
		panels[3].add(button); //예매버튼 추가
		panels[3].add(button2); //영화삭제버튼 추가
		buttons[0].setSelected(true); //디폴트로 상영관 1선택
		panels[4].add(state);//상태메시지 패널4에추가
		add(panels[2],BorderLayout.CENTER); //패널2는 중앙에 추가
		add(bottompanel,BorderLayout.SOUTH);//바텀패널은 바텀에 추가
		this.upload_Data();//등록된 영화를 화면에 표시
		button.addActionListener(this);  //예매버튼에 이벤트추가
		button2.addActionListener(this); //영화삭제버튼에 이벤트 추가
	}
	@Override
	//이벤트발생시
	public void actionPerformed(ActionEvent e) {
		int c=0;//영화예매 성공여부 0이면 실패 1이면 성공
		if(e.getSource()!=button && e.getSource()!=button2) {//라디오버튼클릭시
			upload_Data(); //해당 상영관에 등록된 영화들을 표시
			for(int i=0;i<checkboxes.length;i++) 
				checkboxes[i].setSelected(false); //체크박스 체크를 전부해제
		}
		else if(e.getSource()==button) { //영화 예매버튼 클릭시
			int number=0; //현재 상영관 번호를 저장하는 변수
			for(int i=0;i<buttons.length;i++) {
				if(buttons[i].isSelected()==true) //현재 상영관 번호를 받아온다.
					number=i;
			}
			MovieList list = m.getList().get(number); //해당상영관 객체
			
			if(mylist.getList().size()>4) //현재 예매한 영화가 5개이상일경우
				state.setText("영화를 더 이상 예매할 수 없습니다!");//영화예매 불가메시지
			else {
			int d=0;	//중복체크 변수 0이면 중복없음 1이면 중복있음
			for(int i=0;i<checkboxes.length;i++) {
				
				if(checkboxes[i].isSelected()==true) {//체크된 체크박스가 있을시
					for(int j=0; j<mylist.getList().size();j++) {
						//현재예매할 영화와 유저가 예매한 영화 하나하나를 비교
						MyMovie m= mylist.getList().get(j);
						//현재 체크박스에 체크한영화가 이미 예매한 영화라면
						if(m.getName().equals(list.getList().get(i).getName())==true) {
							d++;//중복변수 1증가
							state.setText("중복되는 영화가 존재합니다!");//에러메시지
							break;
						}
					}//중복된영화가 없고 현재예매한 영화가 4개이하면
					if(d==0 && mylist.getList().size()<=4) {
					   mylist.add(list.getList().get(i),i+1);//예매내역리스트에 추가
					mylist.saveData();//예매내역리스트에서 해당유저 파일로 예매내역 쓰기
					c++;}//c증가
					else if(d!=0)
						break;
				}
			}}
			if(c!=0) //예매성공시
				state.setText("영화가 예매되었습니다!");//메시지 출력
			for(int i=0;i<checkboxes.length;i++)
				checkboxes[i].setSelected(false);//체크된 체크박스 체크 해제
			
			listpanel.upload_Data(); //예매내역 패널에 예매된 영화 추가
		}
		else { //영화 삭제버튼을 누를시
			int x=0;//영화 삭제가 성공적이면 1 아니면 0
			int number=0;//현재 상영관 저장 변수(상영관번호는 넘버+1)
			for(int i=0;i<buttons.length;i++) {//현재상영관정보를 받는다.
				if(buttons[i].isSelected()==true)
					number=i;
			}
			MovieList list = m.getList().get(number);//상영관 객체
			for(int i=checkboxes.length-1;i>=0;i--) {//영화삭제시 제일큰 체크박스부터해야 인덱스 오류가 안납니다.
				if(checkboxes[i].isSelected()==true) {//선택된 체크박스에 대해
					list.getList().remove(i); //해당 영화삭제
					x++; //x증가
					checkboxes[i].setSelected(false);//해당 체크박스해제
				}
			}
			if(x>0) { //삭제가 성공적이면
		    m.saveData(); //삭제된 영화를 파일에서 지운다.(정확히는 리스트에서 영화가 없어지고 리스트를 다시 파일에쓴다.)
			this.upload_Data();}//영화가 삭제된 화면을 보여준다.
		}
		
	}
	public void upload_Data() { //패널화면에서 컴포넌트가 변경될시(영화가 삭제되거나하는) 변경된 화면을 보여주기위한 메소드
		int number=0; //상영관 번호를 얻기위한 변수
		for(int i=0;i<buttons.length;i++) {
			if(buttons[i].isSelected()==true) //현재 선택된 상영관을 number에 저장
				number=i;
		}
		MovieList list = m.getList().get(number); //해당 상영관(MovieList)객체
		
		int b=0; //체크박스한개와 레이블5개는 하나의 영화정보를 표현
		for(int i=0;i<list.getList().size();i++) { //해당 상영관에 영화가 존재할시
			checkboxes[i].setEnabled(true); //체크박스 이용가능
			Movie m =list.getList().get(i); //해당상영관의 해당번호(i) 영화객체 
			 labels[b].setText((i+1)+""); //영화의 순번표시
			 labels[b].setEnabled(true);  //레이블 enable,레이블의 초기화는 false다. 
			 labels[b+1].setText(m.getName()); //영화제목 표시
			 labels[b+1].setEnabled(true);
			 labels[b+2].setText(m.getDirector());//영화 감독표시
			 labels[b+2].setEnabled(true);
			 labels[b+3].setText(m.getGenre());//영화 장르 표시
			 labels[b+3].setEnabled(true);
			 labels[b+4].setText(m.getRunning_time()+"");//러닝타임 표시
			 labels[b+4].setEnabled(true);
		b+=5;	
		}
		for(int i=b;i<labels.length;i++) {//영화가 표시되지 않는 레이블에대해서
			if(i%5==0) 
				checkboxes[i/5].setEnabled(false);//체크박스와 레이블 모두 enable불가
			labels[i].setText("입력");
			labels[i].setEnabled(false);}
			
		this.revalidate(); //컴포넌트 변경사항을 업데이트하여 패널에 보여준다.
		
	}
	 
}
