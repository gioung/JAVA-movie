import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//예매내역 화면
public class listPanel extends JPanel implements ActionListener{
   private JPanel[] panels = new JPanel[3]; //패널
   private JLabel label = new JLabel("나의 예매 내역");//상단에 표시될 레이블
   private JLabel[] info = new JLabel[5]; //아래 항목을 표시하기 위한 레이블
   private String[] infostr = {"선택","상영관","순번","제목","러닝 타임"};//항목들
   private JCheckBox[] checkboxes = new JCheckBox[5];//예매취소를 할 영화를 선택하기위한 체크박스
   private JLabel[] labels = new JLabel[20];//영화정보들을 나타내기 위한 레이블
   private JButton button = new JButton("예매 취소");//예매 취소버튼
   private MyList mylist;//영화예매내역저장 객체
   
   public listPanel(MyList mylist) { //MovieInterface에서 mylist를 매개변수로 받는다
	   this.mylist = mylist;
	  
	   label.setFont(new Font("Serif",Font.PLAIN,20)); 
	   
	   for(int i=0;i<panels.length;i++) { //패널 생성
		   panels[i]=new JPanel();
	   }
	   setLayout(new BorderLayout()); //보더레이아웃
	   add(panels[0],BorderLayout.NORTH); //패널0 북쪽
	   add(panels[1],BorderLayout.CENTER); //패널1 중앙
	   add(panels[2],BorderLayout.SOUTH);//패널2 남쪽
	   panels[0].add(label); //'나의예매내역' 레이블 패널0에 추가
	   panels[1].setLayout(new GridLayout(6,5));
	   for(int i=0;i<info.length;i++) { //각 항목(위에 참고)을 표시하는 레이블 생성
		   info[i]=new JLabel(infostr[i]);
		   info[i].setFont(new Font("Serif",Font.BOLD,15));
		   panels[1].add(info[i]);
	   }
	   int a=0;
	   for(int i=0;i<checkboxes.length;i++) { //영화하나당 하나의체크박스와 4개의 레이블할당
			//예매취소할 영화선택을 위한 체크박스 생성
			checkboxes[i] = new JCheckBox(); 
			checkboxes[i].setEnabled(false);//디폴트로 Enable false
		    panels[1].add(checkboxes[i]);
		for(int j=a;j<a+4;j++) {
			labels[j]=new JLabel("입력");  //디폴트로 "입력"텍스트 설정
			labels[j].setEnabled(false);  //레이블또한 디폴트로 Enable false
			panels[1].add(labels[j]);
		}a+=4;}
	   panels[2].add(button);
	   button.addActionListener(this); //예매취소 버튼 이벤트 활성화
	   upload_Data(); //예매내역이 있을시 이를 패널에 표시(즉, 초기화후 표시)
   }
   public void upload_Data() { //컴포넌트의 변경(영화를 예매한다던가 영화예매를 취소한다든가)이 있을시 변경된패널화면을 보여준다.
	  int b=0;
	   for(int i=0;i<mylist.getList().size();i++) {//사용자의 예매내역이 있을시 예매내역전부를 표시
		   
		   MyMovie m = mylist.getList().get(i); //MyMovie 객체에 사용자의 예매한 영화 하나하나를 할당
		   int number = m.getNumber(); //상영관번호
		   int index = m.getIndex(); //순번(상영순서)
		   String name = m.getName(); //영화제목
		   int rt = m.getRt(); //러닝타임
		   checkboxes[i].setEnabled(true);//체크박스 활성화
		   labels[b].setText(number+""); //상영관번호 레이블에 출력
		   labels[b+1].setText(index+"");//순번 레이블에 출력
		   labels[b+2].setText(name);//영화제목 레이블에 출력
		   labels[b+3].setText(rt+"");//러닝타임 레이블에 출력
		   for(int j=b;j<b+4;j++)
			   labels[j].setEnabled(true); //영화정보를 출력한 레이블활성화
		   b+=4;
	   }
	   for(int i=b;i<labels.length;i++) {//남는 체크박스와 레이블 비활성화
		   if(i%4==0)
			   checkboxes[i/4].setEnabled(false);
		   labels[i].setText("입력");
		   labels[i].setEnabled(false);
	   }
	   this.revalidate(); //컴포넌트의 변경사항을 패널에 업데이트
   }
@Override
public void actionPerformed(ActionEvent e) { //예매취소버튼 이벤트
	int c=0;//취소가 성공적일경우 1 아닐경우 0
	
	for(int i=checkboxes.length-1;i>=0;i--) {//반복은 높은인덱스에서 낮은인덱스로(인덱스오류를 피하기위함)
		if(checkboxes[i].isSelected()==true) {//현재 체크박스에 체크된 영화
			mylist.getList().remove(i);//체크된 영화삭제
			c++;//c증가
			checkboxes[i].setSelected(false);//체크를 풀어준다
		}
	}
	if(c>0) {
    mylist.saveData(); //유저의 예매내역리스트에서 파일로 변경사항을 쓴다.
	this.upload_Data();}//패널에서 삭제된 영화를 지운 화면을 보여준다.
	
}
   
	 
}

