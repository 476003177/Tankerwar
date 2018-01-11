package Test1;
import java.awt.*;
import javax.swing.*;
public class Test1 extends JFrame{
	
	MyPanel mp=null;                                     //定义面板
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Test1 test=new Test1();
	}
	
	//构造函数
	public Test1()
	{
		mp=new MyPanel();
		this.add(mp);                                   //将面板加入到JFrame窗体
		
		
		//初始化
		this.setTitle("坦克大战");                         //设置窗体标题
		this.setSize(400, 300);                         //以像素为单位设置窗体长宽	
		this.setLocation(500,400);                      //设置初试横纵位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭窗口即退出
		this.setResizable(false);                       //禁止用户改变窗体大小
		this.setVisible(true);                          //显示
		
	}
}

class MyPanel extends JPanel
{
	public void paint(Graphics g)                       //覆盖JPanel的paint方法，屏幕显示时候自动调用一次，Graphics是绘图的重要类，相当于一只画笔
	{
		//调用父类函数完成初始化   
		super.paint(g);                                 //不能少
		g.setColor(Color.blue);                         //设置画笔颜色,可多次设置
		g.drawOval(10, 10, 30, 30);                     //画圆（椭圆）边框
//		g.drawLine(10, 10, 40, 40);                     //画直线
//		g.drawRect(10, 10, 46, 60);                     //画矩形边框
//		g.fillRect(10, 10, 40, 60);                     //画填充矩形
//		g.fillOval(10, 10, 30, 30);                     //画填充椭圆
//		Image im=Toolkit.getDefaultToolkit().getImage
//				(Panel.class.getResource("/caocao.jpg"));
//		g.drawImage(im, 90, 90, 200, 150, this);        //画图片
//		g.setFont(new Font("华文彩云",Font.BOLD,50));      //设置字体为华文彩云，粗体，大小30
//		g.drawString("祖国万岁", 100, 100);                //画字
		
		
	}
}