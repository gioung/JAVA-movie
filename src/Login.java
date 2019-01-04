import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
//로그인 클래스는 로그인 화면 및 로그인 기능제공 
public class Login extends JFrame implements ActionListener{
    
	//필드 선언
	private JPanel panel;  //패널
	private JTextField idtf; //id를 입력할 textfield
	private JPasswordField pwdtf; //pwd를 입력할 textfield
	private JLabel label,idlabel,pwdlabel; //상태표시 , id란표시 , pwd란 표시
	private JButton rgbutton,Loginbutton;//아이디 등록버튼 , 로그인버튼
	private UserList list; //UserList객체를 담기위한 필드
	private ArrayList<User> userlist;  //ArrayList<User> 객체를 담기위한 필드
	private MovieInterface mi; //로그인시 MovieInterface 객체를 담기위한 필드
	
	//생성자
	public Login(String Filename) {
		
		list = new UserList(Filename); //UserList 생성자 참고하세요.
		userlist = list.getList(); 
		
		//각 필드에 객체 연결
		idlabel=new JLabel("id : ");    
		pwdlabel = new JLabel("pwd : ");
		label=new JLabel("");
		panel = new JPanel();
		idtf = new JTextField(15);
		pwdtf=new JPasswordField(15);
		rgbutton = new JButton("등록하기");
		Loginbutton = new JButton("로그인");
		//기본적인 frame설정
		setLocation(100,100);
		setSize(500,150);
		setTitle("로그인 창");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		this.setLocationRelativeTo(null);
		
		//이벤트 추가
		rgbutton.addActionListener(this);
		Loginbutton.addActionListener(this);
		
		//패널 - 컴포넌트 추가
		panel.add(idlabel);
		panel.add(idtf);
		panel.add(pwdlabel);
		panel.add(pwdtf);
		panel.add(rgbutton);
		panel.add(Loginbutton);
		panel.add(label);
		
		//패널 추가
		add(panel);
	    
	}

	@Override
	//이벤트 발생처리 함수
	public void actionPerformed(ActionEvent event) {
		
		String input_id=idtf.getText(); //idtf에 입력된 텍스트 값 저장
		String input_pwd=pwdtf.getText();
		//pwdtf에 입력된 텍스트 값저장 -표시는 해당 필드가 password텍스트필드라서 그럼,작동엔 문제없습니다.
		//등록 버튼을 누를시
		if(event.getSource()==rgbutton) {
			String uid;  int c=0; //uid는 현재 output파일에 저장되어있는 아이디를 담기위한변수
			                      //c는 현재 입력된아이디가 존재하면 1 , 아니면 0을 표시하는 변수
			if(userlist.size()>0) { //현재 output파일에 저장되어있는 회원정보가 있으면은 실행 없으면 바로 등록.
			int size=userlist.size();
			for(int i=0;i<size;i++) { //현재  존재하는 회원아이디를 입력된아이디와비교
				uid=userlist.get(i).getId(); 
				if(uid.equals(input_id)) { //현재 존재하는 회원아이디와 입력아이디가 같을경우
					label.setText("아이디가 이미 존재합니다!");//중복메시지 출력
					c++; //c를 1증가
					break; 
				}
			}}
			if(c==0) { //c가 0일경우 (중복이없거나 등록된아이디가 하나도없을경우) 아이디생성
				userlist.add(new User(input_id,input_pwd));
				label.setText("아이디 생성완료"); //아이디 생성메시지
				list.saveData(); //등록된 회원정보 output파일에 쓰기
			}}
		
		else if(event.getSource()==Loginbutton) { //로그인 버튼
			int c=0; //c가 0이면 로그인불가 c가 1이면 로그인가능
			for(User u:userlist) { //for - each문 등록된 회원정보들과 현재 아이디,비밀번호 비교
				if(u.getId().equals(input_id) && u.getPwd().equals(input_pwd)) {
					//일치할경우
					c++; //c를 1증가
					this.setVisible(false); //로그인창 끄기
					mi=new MovieInterface(input_id,this); //메인화면 생성.
					break;
					
					
				}
			}
			if(c==0) { //일치하는 정보가 없을경우
				label.setText("아이디 또는 비밀번호가 올바르지 않습니다!"); //메시지 출력
			}
			
		}
	
	
	
	
	
	}

}
