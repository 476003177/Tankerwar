//测试键盘、鼠标窗口监听

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
		
		//注册监听
		this.addKeyListener(mp);                        //添加键盘监听
		this.addMouseListener(mp);
		this.addMouseMotionListener(mp);
		this.addWindowListener(mp);
		
		this.setTitle("测试键盘、鼠标窗口监听");                      //设置窗体标题
		this.setSize(400, 300);                         //以像素为单位设置窗体长宽	
		this.setLocation(500,400);                      //设置初试横纵位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭窗口即退出
		this.setResizable(false);                       //禁止用户改变窗体大小
		this.setVisible(true);
	}


}


class MyPanel extends JPanel implements KeyListener , MouseListener, java.awt.event.MouseMotionListener, WindowListener{    
	int x=10;
	int y=10;
	public void paint(Graphics g)                       //覆盖JPanel的paint方法，屏幕显示时候自动调用一次，Graphics是绘图的重要类，相当于一只画笔
	{
		//调用父类函数完成初始化   
		super.paint(g);                                 //不能少
		g.fillOval(x, y, 10, 10);
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {                  //键的一个值被输出
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {                //按下
		// TODO Auto-generated method stub
//		System.out.println("按下"+(char)e.getKeyCode());
		System.out.println(e.getKeyChar()+"被按下");
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
	public void keyReleased(KeyEvent e) {               //释放
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {            //鼠标点击
		// TODO Auto-generated method stub
		System.out.println("鼠标点击面板"+e.getX()+","+e.getY()+"；   绝对坐标为"+e.getXOnScreen()+","+e.getYOnScreen());
	}

	@Override
	public void mousePressed(MouseEvent e) {            //鼠标按下
		// TODO Auto-generated method stub
		System.out.println("鼠标按下");
	}

	@Override
	public void mouseReleased(MouseEvent e) {           //鼠标释放
		// TODO Auto-generated method stub
		System.out.println("鼠标释放");
	}

	@Override
	public void mouseEntered(MouseEvent e) {            //鼠标进入到MyPanel
		// TODO Auto-generated method stub
		System.out.println("鼠标进入到面板");
	}

	@Override
	public void mouseExited(MouseEvent e) {             //鼠标离开了MyPanel
		// TODO Auto-generated method stub
		System.out.println("鼠标离开了面板");
	}

	@Override
	public void mouseDragged(MouseEvent e) {            //鼠标拖拽
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {              //鼠标移动
		// TODO Auto-generated method stub
		System.out.println("鼠标当前坐标为：x="+e.getX()+" , y="+e.getY());
	}

	@Override
	public void windowOpened(WindowEvent e) {           //窗口打开了
		// TODO Auto-generated method stub
		System.out.println("窗口打开了");
	}

	@Override
	public void windowClosing(WindowEvent e) {          //窗口正在关闭
		// TODO Auto-generated method stub
		System.out.println("窗口正在关闭");
	}

	@Override
	public void windowClosed(WindowEvent e) {           //窗口关闭了
		// TODO Auto-generated method stub
		System.out.println("窗口关闭了");
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
	public void windowActivated(WindowEvent e) {        //窗口激活了
		// TODO Auto-generated method stub
		System.out.println("窗口激活了");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {      //窗口最小化了
		// TODO Auto-generated method stub
		System.out.println("窗口最小化了");
	}

}