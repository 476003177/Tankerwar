//测试按钮监听

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
		jb1=new JButton("黑色");
		jb2=new JButton("红色");
		
		this.add(jb1,BorderLayout.NORTH);
		this.add(mp);
		this.add(jb2,BorderLayout.SOUTH);
		
		//注册监听
		Cat cat=new Cat();
		jb1.addActionListener(this);
		jb1.addActionListener(cat);
		jb2.addActionListener(this);
		jb2.addActionListener(cat);
		//指定action命令
		jb1.setActionCommand("黑色");
		jb2.setActionCommand("红色");
		
		this.setTitle("测试按钮监听");                         //设置窗体标题
		this.setSize(200, 150);                         //以像素为单位设置窗体长宽	
		this.setLocation(500,400);                      //设置初试横纵位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭窗口即退出
		this.setResizable(false);                       //禁止用户改变窗体大小
		this.setVisible(true);
	}
	//事件处理方法
	public void actionPerformed(ActionEvent e) {
		//判断哪个按钮被点击
		if(e.getActionCommand().equals("黑色"))
		{
			System.out.println("黑色");
			mp.setBackground(Color.black);
		}else if(e.getActionCommand().equals("红色"))
		{
			System.out.println("红色");
			mp.setBackground(Color.red);
		}else{
			System.out.println("无法识别");
		}
		
	}

}


class MyPanel extends JPanel
{
	public void paint(Graphics g)                       //覆盖JPanel的paint方法，屏幕显示时候自动调用一次，Graphics是绘图的重要类，相当于一只画笔
	{
		//调用父类函数完成初始化   
		super.paint(g);                                 //不能少
//		g.fillRect(0, 0, 200, 150);
		
		
		
	}
}

class Cat implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("黑色"))
		{
			System.out.println("猫知道黑色");
		}else if(e.getActionCommand().equals("红色"))
		{
			System.out.println("猫知道红色");
		}else{
			System.out.println("猫无法识别无法识别");
		}
	}
	
}