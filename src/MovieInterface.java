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
//����ȭ�� , ������� ������ �� ������ ������δ� ��ȭ��Ϲ׻���,��ȭ���Ź� ��� , �α׾ƿ��� �ִ�.
	public class MovieInterface extends JFrame implements ActionListener{
		//�ʵ� ����
		private JButton logout,rsv_button;    //�α׾ƿ� ��ư,���� ��ư
		private JButton list_button,mov_rgs;  //���ų��� ��ư , ��ȭ��� ��ư
		private JPanel toppanel,bottompanel,centerpanel; 
		//borderlayout���� ���̾ƿ��ϱ����� �гε� 3��
		private JPanel top_subpanel1,top_subpanel2; //top�г��� �̷�� �г�
		private JLabel top_label,bottom_label; //���гο� ���ϴ� ��
		private rgsPanel rgspanel; //��ȭ ��� �г�
		private rsvPanel rsvpanel; //��ȭ ���� �г�
		private listPanel listpanel; //���� ���� �г�
		//���� 3�� �г��� ������ ����� ��ư�� ������ �ش�Ǵ� �г���  �߾��гο� ��µȴ�.
		private Login l; //�α׾ƿ��� �ٽ� �α��ΰ�ü�� ���ƿ������� ����
		private MovieTheater mov_theater = new MovieTheater(); //��ȭ�� ��ü MovieTheater class����.
		private MyList mylist; //���� ����ڰ� ������ ��ȭ���� ��� ��ü MyList class ����
	      public MovieInterface(String input_id,Login l) { //�ԷµȾ��̵�,�α��� ��ü�� �Ű������� �޴´�.
	    	  mov_theater.loadData(); //���Ϸκ��� ��ϵȿ�ȭ������ �а� ����Ʈ�� �����Ѵ�.
	    	  String id = input_id; //�ԷµȾ��̵�
	    	  this.l=l; //�α��� ��ü
	    	  //������ ������ ���������� ��ü�� �־��ݴϴ�.
	    	  rsv_button=new  JButton("��ȭ ����");
	    	  list_button=new JButton("���� ����");
	    	  mov_rgs=new JButton("��ȭ���(�����ڿ�)");
	    	  logout=new JButton("�α� �ƿ�");
	    	  toppanel = new JPanel();
	    	  bottompanel =new JPanel();
	    	  centerpanel = new JPanel();
	    	  top_subpanel1 = new JPanel();
	    	  top_subpanel2 = new JPanel();
	    	  top_label = new JLabel("�ȳ��ϼ���! ���� ��ȭ���� ���Ű��� ȯ���մϴ�!");
	    	  top_label.setFont(new Font("Serif",Font.PLAIN,15));
	    	  bottom_label = new JLabel("�α��ε� ���̵� : "+id);
	    	  mylist = new MyList(id);
	    	  listpanel = new listPanel(mylist);
	    	  rsvpanel = new rsvPanel(mov_theater,mylist,listpanel);
	    	  rgspanel = new rgsPanel(mov_theater,rsvpanel);
	    	  mov_theater = new MovieTheater();
	    	  
	    	 BufferedImage myPicture; //�̹��� ������ �б����� class
			try {
				myPicture = ImageIO.read(new File("movie_theater2.jpg")); 
				//����ȭ�鿡 ǥ�õ� �̹����� �о�ɴϴ�
				JLabel pic1 = new JLabel(new ImageIcon(myPicture));
				centerpanel.add(pic1); //�߾��гο� �̹��� �߰�
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
             //�г� ����
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
	    	  
	    	  setLayout(new BorderLayout()); //���̾ƿ��� �������̾ƿ�
	    	  add(toppanel,BorderLayout.NORTH);
	    	  add(bottompanel,BorderLayout.SOUTH);
	    	  add(centerpanel,BorderLayout.CENTER);
	    	  //������ �⺻����
	    	  setTitle("HI! Movie");
	    	  setSize(700,600);
	    	  setResizable(false);
	    	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	  setVisible(true);
	    	  this.setLocationRelativeTo(null);
	    	  //��Ϲ�ư,���Ź�ư,���ų�����ư,�α׾ƿ� ��ư�� �׼Ǹ������̺�Ʈ �߰�
	    	  mov_rgs.addActionListener(this);
	    	  rsv_button.addActionListener(this);
	    	  list_button.addActionListener(this);
	    	  logout.addActionListener(this);
	      }
		@Override
		//�̺�Ʈ �Լ�
		public void actionPerformed(ActionEvent e) {
			centerpanel.setLayout(new BorderLayout()); 
			//���Ź�ư�� ������
			if(e.getSource()==rsv_button) {
				centerpanel.removeAll(); //��� ������Ʈ �� �̹����� �����.
				centerpanel.add(rsvpanel); //�߾��гο� rsvpanel�� �߰�
				revalidate(); //����� ������Ʈ ����
				repaint(); //�ٽ� �׸���
			}
			else if(e.getSource()==mov_rgs) { //��Ϲ�ư�� ������
		    centerpanel.removeAll();
		    centerpanel.add(rgspanel); //�߾��гο� rgspanel �߰�
			revalidate();
			repaint();
			}
			else if(e.getSource()==list_button) { //���ų�����ư�� ������
			    centerpanel.removeAll();   
			    centerpanel.add(listpanel); //�߾��гο� listpanel �߰�
				revalidate();
				repaint();
			}
			else if(e.getSource()==logout) { //�α׾ƿ���ư�� ������
				this.setVisible(false); //���� MovieInterface��ü�� �������ʰ��Ѵ�.
				l.setVisible(true); //�α��� ��ü�� �ٽ� ���̰��Ѵ�.
			}
			
		}
}



