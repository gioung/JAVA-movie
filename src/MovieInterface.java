import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
//메인화면 , 각기능을 실행할 수 있으며 기능으로는 영화등록및삭제,영화예매및 취소 , 로그아웃이 있다.
	public class MovieInterface extends JFrame implements ActionListener{
		//필드 선언
		private JButton logout,rsv_button;    //로그아웃 버튼,예매 버튼
		private JButton list_button,mov_rgs;  //예매내역 버튼 , 영화등록 버튼
		private JPanel toppanel,bottompanel,centerpanel; 
		//borderlayout으로 레이아웃하기위한 패널들 3개
		private JPanel top_subpanel1,top_subpanel2; //top패널을 이루는 패널
		private JLabel top_label,bottom_label; //각패널에 속하는 라벨
		private rgsPanel rgspanel; //영화 등록 패널
		private rsvPanel rsvpanel; //영화 예매 패널
		private listPanel listpanel; //예매 내역 패널
		//위에 3개 패널은 위에서 선언된 버튼을 누를시 해당되는 패널이  중앙패널에 출력된다.
		private Login l; //로그아웃시 다시 로그인객체로 돌아오기위해 선언
		private MovieTheater mov_theater = new MovieTheater(); //영화관 객체 MovieTheater class참고.
		private MyList mylist; //현재 사용자가 예매한 영화들을 담는 객체 MyList class 참고
	      public MovieInterface(String input_id,Login l) { //입력된아이디,로그인 객체를 매개변수로 받는다.
	    	  mov_theater.loadData(); //파일로부터 등록된영화정보를 읽고 리스트에 저장한다.
	    	  String id = input_id; //입력된아이디
	    	  this.l=l; //로그인 객체
	    	  //위에서 선언한 참조변수에 객체를 넣어줍니다.
	    	  rsv_button=new  JButton("영화 예매");
	    	  list_button=new JButton("예매 내역");
	    	  mov_rgs=new JButton("영화등록(관리자용)");
	    	  logout=new JButton("로그 아웃");
	    	  toppanel = new JPanel();
	    	  bottompanel =new JPanel();
	    	  centerpanel = new JPanel();
	    	  top_subpanel1 = new JPanel();
	    	  top_subpanel2 = new JPanel();
	    	  top_label = new JLabel("안녕하세요! 저희 영화관에 오신것을 환영합니다!");
	    	  top_label.setFont(new Font("Serif",Font.PLAIN,15));
	    	  bottom_label = new JLabel("로그인된 아이디 : "+id);
	    	  mylist = new MyList(id);
	    	  listpanel = new listPanel(mylist);
	    	  rsvpanel = new rsvPanel(mov_theater,mylist,listpanel);
	    	  rgspanel = new rgsPanel(mov_theater,rsvpanel);
	    	  mov_theater = new MovieTheater();
	    	  
	    	 BufferedImage myPicture; //이미지 파일을 읽기위한 class
			try {
				myPicture = ImageIO.read(new File("movie_theater2.jpg")); 
				//메인화면에 표시될 이미지를 읽어옵니다
				JLabel pic1 = new JLabel(new ImageIcon(myPicture));
				centerpanel.add(pic1); //중앙패널에 이미지 추가
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
             //패널 부착
			  toppanel.setLayout(new GridLayout(2,1));
			  toppanel.add(top_subpanel2);
			  toppanel.add(top_subpanel1);
			  top_subpanel1.add(mov_rgs);
			  top_subpanel1.add(rsv_button);
			  top_subpanel1.add(list_button);
			  top_subpanel1.add(logout);
			  top_subpanel1.setLayout(new GridLayout(1,4));
			  top_subpanel2.add(top_label);
			  bottompanel.add(bottom_label);
	    	  
	    	  setLayout(new BorderLayout()); //레이아웃은 보더레이아웃
	    	  add(toppanel,BorderLayout.NORTH);
	    	  add(bottompanel,BorderLayout.SOUTH);
	    	  add(centerpanel,BorderLayout.CENTER);
	    	  //프레임 기본설정
	    	  setTitle("HI! Movie");
	    	  setSize(700,600);
	    	  setResizable(false);
	    	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	  setVisible(true);
	    	  this.setLocationRelativeTo(null);
	    	  //등록버튼,예매버튼,예매내역버튼,로그아웃 버튼에 액션리스너이벤트 추가
	    	  mov_rgs.addActionListener(this);
	    	  rsv_button.addActionListener(this);
	    	  list_button.addActionListener(this);
	    	  logout.addActionListener(this);
	      }
		@Override
		//이벤트 함수
		public void actionPerformed(ActionEvent e) {
			centerpanel.setLayout(new BorderLayout()); 
			//예매버튼을 누를시
			if(e.getSource()==rsv_button) {
				centerpanel.removeAll(); //모든 컴포넌트 및 이미지를 지운다.
				centerpanel.add(rsvpanel); //중앙패널에 rsvpanel을 추가
				revalidate(); //변경된 컴포넌트 적용
				repaint(); //다시 그리기
			}
			else if(e.getSource()==mov_rgs) { //등록버튼을 누를시
		    centerpanel.removeAll();
		    centerpanel.add(rgspanel); //중앙패널에 rgspanel 추가
			revalidate();
			repaint();
			}
			else if(e.getSource()==list_button) { //예매내역버튼을 누를시
			    centerpanel.removeAll();   
			    centerpanel.add(listpanel); //중앙패널에 listpanel 추가
				revalidate();
				repaint();
			}
			else if(e.getSource()==logout) { //로그아웃버튼을 누를시
				this.setVisible(false); //현재 MovieInterface객체를 보이지않게한다.
				l.setVisible(true); //로그인 객체를 다시 보이게한다.
			}
			
		}
}



