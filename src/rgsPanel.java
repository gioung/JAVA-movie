import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//영화 등록화면
public class rgsPanel extends JPanel implements ActionListener{
	//각 컴포넌트 선언
        private JRadioButton[] buttons = new JRadioButton[3];//상영관을 선택하는 라디오버튼
	    private ButtonGroup group = new ButtonGroup(); //라디오버튼을 묶기위한 버튼그룹
	    private JPanel[] panels = new JPanel[7]; //패널 7개
	    //입력란 라벨메시지
	    private String[] property = {"영화 제목 : ","  감독      : ","  장르      :","러닝 타임 : "};
	    private JLabel[] labels = new JLabel[4]; //위 메시지를 표시할 레이블
	    private JTextField[] tf = new JTextField[4]; //입력을위한 텍스트필드
	    private JButton button = new JButton("등록하기"); //영화등록버튼
	    private JLabel state = new JLabel(); //상태메시지
	    private MovieTheater m; //영화추가시 리스트,파일에 저장하기위한 MovieTheater객체
	    private rsvPanel rsv;//영화 추가시 예매내역화면을 업데이트하기위해 필요
	    //생성자 매개변수로  MovieTheater객체와 rsvPanel객체를 받는다. 
        public rgsPanel(MovieTheater m,rsvPanel rsv) {
        	this.m =m; this.rsv=rsv;
        	//패널 생성
        	 for(int i=0;i<panels.length;i++) {
        		panels[i]=new JPanel();
        		add(panels[i]);
        	}
        	 //패널들을 그리드레이아웃(균등하게) 배치
        	 setLayout(new GridLayout(7,1));
        	 //1~3상영관을 라디오버튼으로 선택할 수 있다.
        	  for(int i=0;i<buttons.length;i++) {
        		  buttons[i]=new JRadioButton((i+1)+"상영관");
        		  group.add(buttons[i]);
        		  panels[0].add(buttons[i]); //패널에 추가
        		  
        	  }
        	  buttons[0].setSelected(true); //1상영관이 선택되도록 초기화
        	 for(int i=0;i<labels.length;i++) { //입력란 라벨 생성
        		 labels[i]=new JLabel(property[i]);
        		 panels[i+1].add(labels[i]);
        		 tf[i] = new JTextField(15);
        		 panels[i+1].add(tf[i]);
        	 }
        	 button = new JButton("등록하기"); //영화 등록하기 버튼
        	 button.addActionListener(this); //위버튼에 이벤트 추가
        	
        	 panels[5].add(button);  //패널에 버튼추가
        	 panels[6].add(state); //상태메시지 추가
        	
        }
		@Override
		//영화 등록이벤트
		public void actionPerformed(ActionEvent e) {
			int c=0; //c가 0이면 영화등록가능 1이면 중복된 영화가 있음.
			int number=0; //현재선택된 상영관
			for(int i=0;i<buttons.length;i++) {
				//선택된 상영관 정보를 얻는다.
				if(buttons[i].isSelected()==true)
					number=i; //상영관 번호를 number에 저장 (number가 0이면 1상영관 ,1이면 2상영관)
			}
			//각 입력란에 입력한 영화정보들을 반환
			String name = tf[0].getText(); 
			String director = tf[1].getText();
			String genre = tf[2].getText();
			int running_time=0;
			if(Integer.parseInt(tf[3].getText())>0)//러닝타임은 숫자이므로 Integer.parseInt를쓴다.
			running_time = Integer.parseInt(tf[3].getText());
			MovieList list = m.getList().get(number); 
			for(int i=0;i<list.getList().size();i++) { 
				//등록하려는 영화가 현재 존재하는 영화인지 해당상영관(MovieList)에 있는 영화와 이름비교
				if(list.getList().get(i).getName().equals(name)) {//이름이 동일한 영화가 있을시
					state.setText("영화가 이미 등록되어있습니다!");//중복메시지 출력
					c++; //c증가
					break;}
			}
			if(list.getList().size()>4) { //해당영화관에 영화가5개이상일경우
				state.setText("영화는 상영관당 최대 5개등록 가능합니다!");//에러메시지
			}
			else if(c==0 && list.getList().size()<=4) {//중복된영화가 없고 등록된영화가 4개이하일경우
			list.add(new Movie(number+1,name,director,genre,running_time));//리스트에 추가
			m.saveData();//리스트에서 파일로 쓰기
			state.setText("영화가 등록되었습니다!");//등록메시지
			rsv.upload_Data();}//영화 예매 패널에서 등록된 영화 보이게하기
			
		}
        
}
