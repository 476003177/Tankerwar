//���԰�ť����

package Test2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Test2 extends JFrame implements ActionListener{

	MyPanel mp=null;
	JButton jb1=null;
	JButton jb2=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test2 test2=new Test2();

	}
	public Test2()
	{
		mp=new MyPanel();
		mp.setBackground(Color.black);
		jb1=new JButton("��ɫ");
		jb2=new JButton("��ɫ");
		
		this.add(jb1,BorderLayout.NORTH);
		this.add(mp);
		this.add(jb2,BorderLayout.SOUTH);
		
		//ע�����
		Cat cat=new Cat();
		jb1.addActionListener(this);
		jb1.addActionListener(cat);
		jb2.addActionListener(this);
		jb2.addActionListener(cat);
		//ָ��action����
		jb1.setActionCommand("��ɫ");
		jb2.setActionCommand("��ɫ");
		
		this.setTitle("���԰�ť����");                         //���ô������
		this.setSize(200, 150);                         //������Ϊ��λ���ô��峤��	
		this.setLocation(500,400);                      //���ó��Ժ���λ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùرմ��ڼ��˳�
		this.setResizable(false);                       //��ֹ�û��ı䴰���С
		this.setVisible(true);
	}
	//�¼�������
	public void actionPerformed(ActionEvent e) {
		//�ж��ĸ���ť�����
		if(e.getActionCommand().equals("��ɫ"))
		{
			System.out.println("��ɫ");
			mp.setBackground(Color.black);
		}else if(e.getActionCommand().equals("��ɫ"))
		{
			System.out.println("��ɫ");
			mp.setBackground(Color.red);
		}else{
			System.out.println("�޷�ʶ��");
		}
		
	}

}


class MyPanel extends JPanel
{
	public void paint(Graphics g)                       //����JPanel��paint��������Ļ��ʾʱ���Զ�����һ�Σ�Graphics�ǻ�ͼ����Ҫ�࣬�൱��һֻ����
	{
		//���ø��ຯ����ɳ�ʼ��   
		super.paint(g);                                 //������
//		g.fillRect(0, 0, 200, 150);
		
		
		
	}
}

class Cat implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("��ɫ"))
		{
			System.out.println("è֪����ɫ");
		}else if(e.getActionCommand().equals("��ɫ"))
		{
			System.out.println("è֪����ɫ");
		}else{
			System.out.println("è�޷�ʶ���޷�ʶ��");
		}
	}
	
}