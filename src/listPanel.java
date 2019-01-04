import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//���ų��� ȭ��
public class listPanel extends JPanel implements ActionListener{
   private JPanel[] panels = new JPanel[3]; //�г�
   private JLabel label = new JLabel("���� ���� ����");//��ܿ� ǥ�õ� ���̺�
   private JLabel[] info = new JLabel[5]; //�Ʒ� �׸��� ǥ���ϱ� ���� ���̺�
   private String[] infostr = {"����","�󿵰�","����","����","���� Ÿ��"};//�׸��
   private JCheckBox[] checkboxes = new JCheckBox[5];//������Ҹ� �� ��ȭ�� �����ϱ����� üũ�ڽ�
   private JLabel[] labels = new JLabel[20];//��ȭ�������� ��Ÿ���� ���� ���̺�
   private JButton button = new JButton("���� ���");//���� ��ҹ�ư
   private MyList mylist;//��ȭ���ų������� ��ü
   
   public listPanel(MyList mylist) { //MovieInterface���� mylist�� �Ű������� �޴´�
	   this.mylist = mylist;
	  
	   label.setFont(new Font("Serif",Font.PLAIN,20)); 
	   
	   for(int i=0;i<panels.length;i++) { //�г� ����
		   panels[i]=new JPanel();
	   }
	   setLayout(new BorderLayout()); //�������̾ƿ�
	   add(panels[0],BorderLayout.NORTH); //�г�0 ����
	   add(panels[1],BorderLayout.CENTER); //�г�1 �߾�
	   add(panels[2],BorderLayout.SOUTH);//�г�2 ����
	   panels[0].add(label); //'���ǿ��ų���' ���̺� �г�0�� �߰�
	   panels[1].setLayout(new GridLayout(6,5));
	   for(int i=0;i<info.length;i++) { //�� �׸�(���� ����)�� ǥ���ϴ� ���̺� ����
		   info[i]=new JLabel(infostr[i]);
		   info[i].setFont(new Font("Serif",Font.BOLD,15));
		   panels[1].add(info[i]);
	   }
	   int a=0;
	   for(int i=0;i<checkboxes.length;i++) { //��ȭ�ϳ��� �ϳ���üũ�ڽ��� 4���� ���̺��Ҵ�
			//��������� ��ȭ������ ���� üũ�ڽ� ����
			checkboxes[i] = new JCheckBox(); 
			checkboxes[i].setEnabled(false);//����Ʈ�� Enable false
		    panels[1].add(checkboxes[i]);
		for(int j=a;j<a+4;j++) {
			labels[j]=new JLabel("�Է�");  //����Ʈ�� "�Է�"�ؽ�Ʈ ����
			labels[j].setEnabled(false);  //���̺���� ����Ʈ�� Enable false
			panels[1].add(labels[j]);
		}a+=4;}
	   panels[2].add(button);
	   button.addActionListener(this); //������� ��ư �̺�Ʈ Ȱ��ȭ
	   upload_Data(); //���ų����� ������ �̸� �гο� ǥ��(��, �ʱ�ȭ�� ǥ��)
   }
   public void upload_Data() { //������Ʈ�� ����(��ȭ�� �����Ѵٴ��� ��ȭ���Ÿ� ����Ѵٵ簡)�� ������ ������г�ȭ���� �����ش�.
	  int b=0;
	   for(int i=0;i<mylist.getList().size();i++) {//������� ���ų����� ������ ���ų������θ� ǥ��
		   
		   MyMovie m = mylist.getList().get(i); //MyMovie ��ü�� ������� ������ ��ȭ �ϳ��ϳ��� �Ҵ�
		   int number = m.getNumber(); //�󿵰���ȣ
		   int index = m.getIndex(); //����(�󿵼���)
		   String name = m.getName(); //��ȭ����
		   int rt = m.getRt(); //����Ÿ��
		   checkboxes[i].setEnabled(true);//üũ�ڽ� Ȱ��ȭ
		   labels[b].setText(number+""); //�󿵰���ȣ ���̺� ���
		   labels[b+1].setText(index+"");//���� ���̺� ���
		   labels[b+2].setText(name);//��ȭ���� ���̺� ���
		   labels[b+3].setText(rt+"");//����Ÿ�� ���̺� ���
		   for(int j=b;j<b+4;j++)
			   labels[j].setEnabled(true); //��ȭ������ ����� ���̺�Ȱ��ȭ
		   b+=4;
	   }
	   for(int i=b;i<labels.length;i++) {//���� üũ�ڽ��� ���̺� ��Ȱ��ȭ
		   if(i%4==0)
			   checkboxes[i/4].setEnabled(false);
		   labels[i].setText("�Է�");
		   labels[i].setEnabled(false);
	   }
	   this.revalidate(); //������Ʈ�� ��������� �гο� ������Ʈ
   }
@Override
public void actionPerformed(ActionEvent e) { //������ҹ�ư �̺�Ʈ
	int c=0;//��Ұ� �������ϰ�� 1 �ƴҰ�� 0
	
	for(int i=checkboxes.length-1;i>=0;i--) {//�ݺ��� �����ε������� �����ε�����(�ε��������� ���ϱ�����)
		if(checkboxes[i].isSelected()==true) {//���� üũ�ڽ��� üũ�� ��ȭ
			mylist.getList().remove(i);//üũ�� ��ȭ����
			c++;//c����
			checkboxes[i].setSelected(false);//üũ�� Ǯ���ش�
		}
	}
	if(c>0) {
    mylist.saveData(); //������ ���ų�������Ʈ���� ���Ϸ� ��������� ����.
	this.upload_Data();}//�гο��� ������ ��ȭ�� ���� ȭ���� �����ش�.
	
}
   
	 
}

