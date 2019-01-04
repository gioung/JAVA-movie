import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//��ȭ ���ȭ��
public class rgsPanel extends JPanel implements ActionListener{
	//�� ������Ʈ ����
        private JRadioButton[] buttons = new JRadioButton[3];//�󿵰��� �����ϴ� ������ư
	    private ButtonGroup group = new ButtonGroup(); //������ư�� �������� ��ư�׷�
	    private JPanel[] panels = new JPanel[7]; //�г� 7��
	    //�Է¶� �󺧸޽���
	    private String[] property = {"��ȭ ���� : ","  ����      : ","  �帣      :","���� Ÿ�� : "};
	    private JLabel[] labels = new JLabel[4]; //�� �޽����� ǥ���� ���̺�
	    private JTextField[] tf = new JTextField[4]; //�Է������� �ؽ�Ʈ�ʵ�
	    private JButton button = new JButton("����ϱ�"); //��ȭ��Ϲ�ư
	    private JLabel state = new JLabel(); //���¸޽���
	    private MovieTheater m; //��ȭ�߰��� ����Ʈ,���Ͽ� �����ϱ����� MovieTheater��ü
	    private rsvPanel rsv;//��ȭ �߰��� ���ų���ȭ���� ������Ʈ�ϱ����� �ʿ�
	    //������ �Ű�������  MovieTheater��ü�� rsvPanel��ü�� �޴´�. 
        public rgsPanel(MovieTheater m,rsvPanel rsv) {
        	this.m =m; this.rsv=rsv;
        	//�г� ����
        	 for(int i=0;i<panels.length;i++) {
        		panels[i]=new JPanel();
        		add(panels[i]);
        	}
        	 //�гε��� �׸��巹�̾ƿ�(�յ��ϰ�) ��ġ
        	 setLayout(new GridLayout(7,1));
        	 //1~3�󿵰��� ������ư���� ������ �� �ִ�.
        	  for(int i=0;i<buttons.length;i++) {
        		  buttons[i]=new JRadioButton((i+1)+"�󿵰�");
        		  group.add(buttons[i]);
        		  panels[0].add(buttons[i]); //�гο� �߰�
        		  
        	  }
        	  buttons[0].setSelected(true); //1�󿵰��� ���õǵ��� �ʱ�ȭ
        	 for(int i=0;i<labels.length;i++) { //�Է¶� �� ����
        		 labels[i]=new JLabel(property[i]);
        		 panels[i+1].add(labels[i]);
        		 tf[i] = new JTextField(15);
        		 panels[i+1].add(tf[i]);
        	 }
        	 button = new JButton("����ϱ�"); //��ȭ ����ϱ� ��ư
        	 button.addActionListener(this); //����ư�� �̺�Ʈ �߰�
        	
        	 panels[5].add(button);  //�гο� ��ư�߰�
        	 panels[6].add(state); //���¸޽��� �߰�
        	
        }
		@Override
		//��ȭ ����̺�Ʈ
		public void actionPerformed(ActionEvent e) {
			int c=0; //c�� 0�̸� ��ȭ��ϰ��� 1�̸� �ߺ��� ��ȭ�� ����.
			int number=0; //���缱�õ� �󿵰�
			for(int i=0;i<buttons.length;i++) {
				//���õ� �󿵰� ������ ��´�.
				if(buttons[i].isSelected()==true)
					number=i; //�󿵰� ��ȣ�� number�� ���� (number�� 0�̸� 1�󿵰� ,1�̸� 2�󿵰�)
			}
			//�� �Է¶��� �Է��� ��ȭ�������� ��ȯ
			String name = tf[0].getText(); 
			String director = tf[1].getText();
			String genre = tf[2].getText();
			int running_time=0;
			if(Integer.parseInt(tf[3].getText())>0)//����Ÿ���� �����̹Ƿ� Integer.parseInt������.
			running_time = Integer.parseInt(tf[3].getText());
			MovieList list = m.getList().get(number); 
			for(int i=0;i<list.getList().size();i++) { 
				//����Ϸ��� ��ȭ�� ���� �����ϴ� ��ȭ���� �ش�󿵰�(MovieList)�� �ִ� ��ȭ�� �̸���
				if(list.getList().get(i).getName().equals(name)) {//�̸��� ������ ��ȭ�� ������
					state.setText("��ȭ�� �̹� ��ϵǾ��ֽ��ϴ�!");//�ߺ��޽��� ���
					c++; //c����
					break;}
			}
			if(list.getList().size()>4) { //�ش翵ȭ���� ��ȭ��5���̻��ϰ��
				state.setText("��ȭ�� �󿵰��� �ִ� 5����� �����մϴ�!");//�����޽���
			}
			else if(c==0 && list.getList().size()<=4) {//�ߺ��ȿ�ȭ�� ���� ��ϵȿ�ȭ�� 4�������ϰ��
			list.add(new Movie(number+1,name,director,genre,running_time));//����Ʈ�� �߰�
			m.saveData();//����Ʈ���� ���Ϸ� ����
			state.setText("��ȭ�� ��ϵǾ����ϴ�!");//��ϸ޽���
			rsv.upload_Data();}//��ȭ ���� �гο��� ��ϵ� ��ȭ ���̰��ϱ�
			
		}
        
}
