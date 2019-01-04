import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
//�α��� Ŭ������ �α��� ȭ�� �� �α��� ������� 
public class Login extends JFrame implements ActionListener{
    
	//�ʵ� ����
	private JPanel panel;  //�г�
	private JTextField idtf; //id�� �Է��� textfield
	private JPasswordField pwdtf; //pwd�� �Է��� textfield
	private JLabel label,idlabel,pwdlabel; //����ǥ�� , id��ǥ�� , pwd�� ǥ��
	private JButton rgbutton,Loginbutton;//���̵� ��Ϲ�ư , �α��ι�ư
	private UserList list; //UserList��ü�� ������� �ʵ�
	private ArrayList<User> userlist;  //ArrayList<User> ��ü�� ������� �ʵ�
	private MovieInterface mi; //�α��ν� MovieInterface ��ü�� ������� �ʵ�
	
	//������
	public Login(String Filename) {
		
		list = new UserList(Filename); //UserList ������ �����ϼ���.
		userlist = list.getList(); 
		
		//�� �ʵ忡 ��ü ����
		idlabel=new JLabel("id : ");    
		pwdlabel = new JLabel("pwd : ");
		label=new JLabel("");
		panel = new JPanel();
		idtf = new JTextField(15);
		pwdtf=new JPasswordField(15);
		rgbutton = new JButton("����ϱ�");
		Loginbutton = new JButton("�α���");
		//�⺻���� frame����
		setLocation(100,100);
		setSize(500,150);
		setTitle("�α��� â");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		this.setLocationRelativeTo(null);
		
		//�̺�Ʈ �߰�
		rgbutton.addActionListener(this);
		Loginbutton.addActionListener(this);
		
		//�г� - ������Ʈ �߰�
		panel.add(idlabel);
		panel.add(idtf);
		panel.add(pwdlabel);
		panel.add(pwdtf);
		panel.add(rgbutton);
		panel.add(Loginbutton);
		panel.add(label);
		
		//�г� �߰�
		add(panel);
	    
	}

	@Override
	//�̺�Ʈ �߻�ó�� �Լ�
	public void actionPerformed(ActionEvent event) {
		
		String input_id=idtf.getText(); //idtf�� �Էµ� �ؽ�Ʈ �� ����
		String input_pwd=pwdtf.getText();
		//pwdtf�� �Էµ� �ؽ�Ʈ ������ -ǥ�ô� �ش� �ʵ尡 password�ؽ�Ʈ�ʵ�� �׷�,�۵��� ���������ϴ�.
		//��� ��ư�� ������
		if(event.getSource()==rgbutton) {
			String uid;  int c=0; //uid�� ���� output���Ͽ� ����Ǿ��ִ� ���̵� ������Ѻ���
			                      //c�� ���� �ԷµȾ��̵� �����ϸ� 1 , �ƴϸ� 0�� ǥ���ϴ� ����
			if(userlist.size()>0) { //���� output���Ͽ� ����Ǿ��ִ� ȸ�������� �������� ���� ������ �ٷ� ���.
			int size=userlist.size();
			for(int i=0;i<size;i++) { //����  �����ϴ� ȸ�����̵� �ԷµȾ��̵�ͺ�
				uid=userlist.get(i).getId(); 
				if(uid.equals(input_id)) { //���� �����ϴ� ȸ�����̵�� �Է¾��̵� �������
					label.setText("���̵� �̹� �����մϴ�!");//�ߺ��޽��� ���
					c++; //c�� 1����
					break; 
				}
			}}
			if(c==0) { //c�� 0�ϰ�� (�ߺ��̾��ų� ��ϵȾ��̵� �ϳ����������) ���̵����
				userlist.add(new User(input_id,input_pwd));
				label.setText("���̵� �����Ϸ�"); //���̵� �����޽���
				list.saveData(); //��ϵ� ȸ������ output���Ͽ� ����
			}}
		
		else if(event.getSource()==Loginbutton) { //�α��� ��ư
			int c=0; //c�� 0�̸� �α��κҰ� c�� 1�̸� �α��ΰ���
			for(User u:userlist) { //for - each�� ��ϵ� ȸ��������� ���� ���̵�,��й�ȣ ��
				if(u.getId().equals(input_id) && u.getPwd().equals(input_pwd)) {
					//��ġ�Ұ��
					c++; //c�� 1����
					this.setVisible(false); //�α���â ����
					mi=new MovieInterface(input_id,this); //����ȭ�� ����.
					break;
					
					
				}
			}
			if(c==0) { //��ġ�ϴ� ������ �������
				label.setText("���̵� �Ǵ� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�!"); //�޽��� ���
			}
			
		}
	
	
	
	
	
	}

}
