import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
//��ȭ���� ȭ��
public class rsvPanel extends JPanel implements ActionListener{
    //�ʵ� ����
	//�г�
	private JPanel toppanel = new JPanel(); 
	private JPanel bottompanel = new JPanel(); 
	private JPanel[] panels = new JPanel[5]; 
	//���ǻ��� ���̺�
	private JLabel label = new JLabel("���ǻ��� : �� ���� �� �� �ִ� ��ȭ������ 5���Դϴ�.");
	//�󿵰� ���� ������ư
	private JRadioButton[] buttons = new JRadioButton[3];
	private JLabel[] info = new JLabel[6]; //�ؿ� ���ڿ����� ��Ÿ���� ���̺�
	private String[] infostr = {"����","����","����","����","�帣","����Ÿ��"}; //���Ű��ɿ�ȭ �����׸�
	private JLabel[] labels = new JLabel[25]; //�� ��ȭ�ϳ��� 5���� ���� �̿��ؼ� ��ȭ����ǥ��(��5�� ��ȭǥ��)
	private JCheckBox[] checkboxes = new JCheckBox[5];//��ȭ ������ ���� üũ�ڽ�
	private JButton button = new JButton("�����ϱ�");//�����ϱ� ��ư
	private JButton button2 = new JButton("��ȭ����(�����ڿ�)");//��ȭ���� ��ư
	private JLabel state = new JLabel();//���¸޽��� ���̺�
	private ButtonGroup group = new ButtonGroup();//������ư�� ���� ��ư�׷�
	private MovieTheater m;//��ȭ ������ ���Ͽ� ���� ���� ��ü
	private MyList mylist;//��ȭ ���Ž� �ش� ���̵� ���ϸ� ���ŵȿ�ȭ �߰�(���ų���)
	private listPanel listpanel; //���ų��� �г�
	public rsvPanel(MovieTheater m,MyList mylist,listPanel listpanel) {
		this.listpanel=listpanel;
		this.m=m; 
		this.mylist=mylist;
		
		for(int i=0;i<panels.length;i++) { //�г� ����
			panels[i] = new JPanel();
	    }
		setLayout(new BorderLayout()); //���� ���̾ƿ� ����
		//ž�гο� �г�0,1�߰�
		toppanel.add(panels[0]); 
		toppanel.add(panels[1]);
		toppanel.setLayout(new GridLayout(2,1));//ž�г� �׸��巹�̾ƿ�
		add(toppanel,BorderLayout.NORTH); //ž�г� ���� �������̾ƿ� ��ġ
		
		panels[0].add(label); //���ǻ��׸޽��� �г�0�� �߰�
		for(int i=0;i<buttons.length;i++) { //�󿵰��� �����Ҽ� �ִ� ������ư ����
  		  buttons[i]=new JRadioButton((i+1)+"�󿵰�");
  		  group.add(buttons[i]);
  		  panels[1].add(buttons[i]);
  		  buttons[i].addActionListener(this); //�� ������ư�� �̺�Ʈ �߰�
  	  }
		panels[2].setLayout(new GridLayout(6,6)); 
		for(int i=0;i<info.length;i++) { //��ȭ���� �׸��� ��Ÿ���� ���̺� ����
			info[i]=new JLabel(infostr[i]);
			info[i].setFont(new Font("Serif",Font.BOLD,15));
			panels[2].add(info[i]);
		}
		int a=0; //üũ�ڽ��Ѱ��� ���̺�5���� ��ȭ �Ѱ��� �ش� �̸� �����ϱ� ���� ����
		for(int i=0;i<checkboxes.length;i++) {//üũ�ڽ� �ʱ�ȭ
			checkboxes[i] = new JCheckBox();  //üũ�ڽ�����
			checkboxes[i].setEnabled(false); //üũ�ڽ� �̿�Ұ�
		    panels[2].add(checkboxes[i]); //�г�2�� üũ�ڽ� �߰�
		for(int j=a;j<a+5;j++) { //���̺� �ʱ�ȭ
			labels[j]=new JLabel("�Է�"); //���̺��� �ʱ��ؽ�Ʈ "�Է�" ����
			labels[j].setEnabled(false);//�̿�Ұ�
			panels[2].add(labels[j]); //�г�2�� �߰�
		}a+=5;}//�״��� �ٿ� ���̺������ ���Ѱ�
	    
		bottompanel.add(panels[3]); //�����гο� �г�3,4�߰�
		bottompanel.add(panels[4]);
		bottompanel.setLayout(new GridLayout(2,1));
		panels[3].add(button); //���Ź�ư �߰�
		panels[3].add(button2); //��ȭ������ư �߰�
		buttons[0].setSelected(true); //����Ʈ�� �󿵰� 1����
		panels[4].add(state);//���¸޽��� �г�4���߰�
		add(panels[2],BorderLayout.CENTER); //�г�2�� �߾ӿ� �߰�
		add(bottompanel,BorderLayout.SOUTH);//�����г��� ���ҿ� �߰�
		this.upload_Data();//��ϵ� ��ȭ�� ȭ�鿡 ǥ��
		button.addActionListener(this);  //���Ź�ư�� �̺�Ʈ�߰�
		button2.addActionListener(this); //��ȭ������ư�� �̺�Ʈ �߰�
	}
	@Override
	//�̺�Ʈ�߻���
	public void actionPerformed(ActionEvent e) {
		int c=0;//��ȭ���� �������� 0�̸� ���� 1�̸� ����
		if(e.getSource()!=button && e.getSource()!=button2) {//������ưŬ����
			upload_Data(); //�ش� �󿵰��� ��ϵ� ��ȭ���� ǥ��
			for(int i=0;i<checkboxes.length;i++) 
				checkboxes[i].setSelected(false); //üũ�ڽ� üũ�� ��������
		}
		else if(e.getSource()==button) { //��ȭ ���Ź�ư Ŭ����
			int number=0; //���� �󿵰� ��ȣ�� �����ϴ� ����
			for(int i=0;i<buttons.length;i++) {
				if(buttons[i].isSelected()==true) //���� �󿵰� ��ȣ�� �޾ƿ´�.
					number=i;
			}
			MovieList list = m.getList().get(number); //�ش�󿵰� ��ü
			
			if(mylist.getList().size()>4) //���� ������ ��ȭ�� 5���̻��ϰ��
				state.setText("��ȭ�� �� �̻� ������ �� �����ϴ�!");//��ȭ���� �Ұ��޽���
			else {
			int d=0;	//�ߺ�üũ ���� 0�̸� �ߺ����� 1�̸� �ߺ�����
			for(int i=0;i<checkboxes.length;i++) {
				
				if(checkboxes[i].isSelected()==true) {//üũ�� üũ�ڽ��� ������
					for(int j=0; j<mylist.getList().size();j++) {
						//���翹���� ��ȭ�� ������ ������ ��ȭ �ϳ��ϳ��� ��
						MyMovie m= mylist.getList().get(j);
						//���� üũ�ڽ��� üũ�ѿ�ȭ�� �̹� ������ ��ȭ���
						if(m.getName().equals(list.getList().get(i).getName())==true) {
							d++;//�ߺ����� 1����
							state.setText("�ߺ��Ǵ� ��ȭ�� �����մϴ�!");//�����޽���
							break;
						}
					}//�ߺ��ȿ�ȭ�� ���� ���翹���� ��ȭ�� 4�����ϸ�
					if(d==0 && mylist.getList().size()<=4) {
					   mylist.add(list.getList().get(i),i+1);//���ų�������Ʈ�� �߰�
					mylist.saveData();//���ų�������Ʈ���� �ش����� ���Ϸ� ���ų��� ����
					c++;}//c����
					else if(d!=0)
						break;
				}
			}}
			if(c!=0) //���ż�����
				state.setText("��ȭ�� ���ŵǾ����ϴ�!");//�޽��� ���
			for(int i=0;i<checkboxes.length;i++)
				checkboxes[i].setSelected(false);//üũ�� üũ�ڽ� üũ ����
			
			listpanel.upload_Data(); //���ų��� �гο� ���ŵ� ��ȭ �߰�
		}
		else { //��ȭ ������ư�� ������
			int x=0;//��ȭ ������ �������̸� 1 �ƴϸ� 0
			int number=0;//���� �󿵰� ���� ����(�󿵰���ȣ�� �ѹ�+1)
			for(int i=0;i<buttons.length;i++) {//����󿵰������� �޴´�.
				if(buttons[i].isSelected()==true)
					number=i;
			}
			MovieList list = m.getList().get(number);//�󿵰� ��ü
			for(int i=checkboxes.length-1;i>=0;i--) {//��ȭ������ ����ū üũ�ڽ������ؾ� �ε��� ������ �ȳ��ϴ�.
				if(checkboxes[i].isSelected()==true) {//���õ� üũ�ڽ��� ����
					list.getList().remove(i); //�ش� ��ȭ����
					x++; //x����
					checkboxes[i].setSelected(false);//�ش� üũ�ڽ�����
				}
			}
			if(x>0) { //������ �������̸�
		    m.saveData(); //������ ��ȭ�� ���Ͽ��� �����.(��Ȯ���� ����Ʈ���� ��ȭ�� �������� ����Ʈ�� �ٽ� ���Ͽ�����.)
			this.upload_Data();}//��ȭ�� ������ ȭ���� �����ش�.
		}
		
	}
	public void upload_Data() { //�г�ȭ�鿡�� ������Ʈ�� ����ɽ�(��ȭ�� �����ǰų��ϴ�) ����� ȭ���� �����ֱ����� �޼ҵ�
		int number=0; //�󿵰� ��ȣ�� ������� ����
		for(int i=0;i<buttons.length;i++) {
			if(buttons[i].isSelected()==true) //���� ���õ� �󿵰��� number�� ����
				number=i;
		}
		MovieList list = m.getList().get(number); //�ش� �󿵰�(MovieList)��ü
		
		int b=0; //üũ�ڽ��Ѱ��� ���̺�5���� �ϳ��� ��ȭ������ ǥ��
		for(int i=0;i<list.getList().size();i++) { //�ش� �󿵰��� ��ȭ�� �����ҽ�
			checkboxes[i].setEnabled(true); //üũ�ڽ� �̿밡��
			Movie m =list.getList().get(i); //�ش�󿵰��� �ش��ȣ(i) ��ȭ��ü 
			 labels[b].setText((i+1)+""); //��ȭ�� ����ǥ��
			 labels[b].setEnabled(true);  //���̺� enable,���̺��� �ʱ�ȭ�� false��. 
			 labels[b+1].setText(m.getName()); //��ȭ���� ǥ��
			 labels[b+1].setEnabled(true);
			 labels[b+2].setText(m.getDirector());//��ȭ ����ǥ��
			 labels[b+2].setEnabled(true);
			 labels[b+3].setText(m.getGenre());//��ȭ �帣 ǥ��
			 labels[b+3].setEnabled(true);
			 labels[b+4].setText(m.getRunning_time()+"");//����Ÿ�� ǥ��
			 labels[b+4].setEnabled(true);
		b+=5;	
		}
		for(int i=b;i<labels.length;i++) {//��ȭ�� ǥ�õ��� �ʴ� ���̺����ؼ�
			if(i%5==0) 
				checkboxes[i/5].setEnabled(false);//üũ�ڽ��� ���̺� ��� enable�Ұ�
			labels[i].setText("�Է�");
			labels[i].setEnabled(false);}
			
		this.revalidate(); //������Ʈ ��������� ������Ʈ�Ͽ� �гο� �����ش�.
		
	}
	 
}
