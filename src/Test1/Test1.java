package Test1;
import java.awt.*;
import javax.swing.*;
public class Test1 extends JFrame{
	
	MyPanel mp=null;                                     //�������
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Test1 test=new Test1();
	}
	
	//���캯��
	public Test1()
	{
		mp=new MyPanel();
		this.add(mp);                                   //�������뵽JFrame����
		
		
		//��ʼ��
		this.setTitle("̹�˴�ս");                         //���ô������
		this.setSize(400, 300);                         //������Ϊ��λ���ô��峤��	
		this.setLocation(500,400);                      //���ó��Ժ���λ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùرմ��ڼ��˳�
		this.setResizable(false);                       //��ֹ�û��ı䴰���С
		this.setVisible(true);                          //��ʾ
		
	}
}

class MyPanel extends JPanel
{
	public void paint(Graphics g)                       //����JPanel��paint��������Ļ��ʾʱ���Զ�����һ�Σ�Graphics�ǻ�ͼ����Ҫ�࣬�൱��һֻ����
	{
		//���ø��ຯ����ɳ�ʼ��   
		super.paint(g);                                 //������
		g.setColor(Color.blue);                         //���û�����ɫ,�ɶ������
		g.drawOval(10, 10, 30, 30);                     //��Բ����Բ���߿�
//		g.drawLine(10, 10, 40, 40);                     //��ֱ��
//		g.drawRect(10, 10, 46, 60);                     //�����α߿�
//		g.fillRect(10, 10, 40, 60);                     //��������
//		g.fillOval(10, 10, 30, 30);                     //�������Բ
//		Image im=Toolkit.getDefaultToolkit().getImage
//				(Panel.class.getResource("/caocao.jpg"));
//		g.drawImage(im, 90, 90, 200, 150, this);        //��ͼƬ
//		g.setFont(new Font("���Ĳ���",Font.BOLD,50));      //��������Ϊ���Ĳ��ƣ����壬��С30
//		g.drawString("�������", 100, 100);                //����
		
		
	}
}