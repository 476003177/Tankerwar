//���Լ��̡���괰�ڼ���

package Test3;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Test3 extends JFrame{
	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test3 test3=new Test3();
	}
	public Test3()
	{
		mp=new MyPanel();
		
		this.add(mp);
		
		//ע�����
		this.addKeyListener(mp);                        //��Ӽ��̼���
		this.addMouseListener(mp);
		this.addMouseMotionListener(mp);
		this.addWindowListener(mp);
		
		this.setTitle("���Լ��̡���괰�ڼ���");                      //���ô������
		this.setSize(400, 300);                         //������Ϊ��λ���ô��峤��	
		this.setLocation(500,400);                      //���ó��Ժ���λ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùرմ��ڼ��˳�
		this.setResizable(false);                       //��ֹ�û��ı䴰���С
		this.setVisible(true);
	}


}


class MyPanel extends JPanel implements KeyListener , MouseListener, java.awt.event.MouseMotionListener, WindowListener{    
	int x=10;
	int y=10;
	public void paint(Graphics g)                       //����JPanel��paint��������Ļ��ʾʱ���Զ�����һ�Σ�Graphics�ǻ�ͼ����Ҫ�࣬�൱��һֻ����
	{
		//���ø��ຯ����ɳ�ʼ��   
		super.paint(g);                                 //������
		g.fillOval(x, y, 10, 10);
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {                  //����һ��ֵ�����
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {                //����
		// TODO Auto-generated method stub
//		System.out.println("����"+(char)e.getKeyCode());
		System.out.println(e.getKeyChar()+"������");
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			y++;
		}else if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			y--;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			x--;
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			x++;
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {               //�ͷ�
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {            //�����
		// TODO Auto-generated method stub
		System.out.println("��������"+e.getX()+","+e.getY()+"��   ��������Ϊ"+e.getXOnScreen()+","+e.getYOnScreen());
	}

	@Override
	public void mousePressed(MouseEvent e) {            //��갴��
		// TODO Auto-generated method stub
		System.out.println("��갴��");
	}

	@Override
	public void mouseReleased(MouseEvent e) {           //����ͷ�
		// TODO Auto-generated method stub
		System.out.println("����ͷ�");
	}

	@Override
	public void mouseEntered(MouseEvent e) {            //�����뵽MyPanel
		// TODO Auto-generated method stub
		System.out.println("�����뵽���");
	}

	@Override
	public void mouseExited(MouseEvent e) {             //����뿪��MyPanel
		// TODO Auto-generated method stub
		System.out.println("����뿪�����");
	}

	@Override
	public void mouseDragged(MouseEvent e) {            //�����ק
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {              //����ƶ�
		// TODO Auto-generated method stub
		System.out.println("��굱ǰ����Ϊ��x="+e.getX()+" , y="+e.getY());
	}

	@Override
	public void windowOpened(WindowEvent e) {           //���ڴ���
		// TODO Auto-generated method stub
		System.out.println("���ڴ���");
	}

	@Override
	public void windowClosing(WindowEvent e) {          //�������ڹر�
		// TODO Auto-generated method stub
		System.out.println("�������ڹر�");
	}

	@Override
	public void windowClosed(WindowEvent e) {           //���ڹر���
		// TODO Auto-generated method stub
		System.out.println("���ڹر���");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {        //���ڼ�����
		// TODO Auto-generated method stub
		System.out.println("���ڼ�����");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {      //������С����
		// TODO Auto-generated method stub
		System.out.println("������С����");
	}

}