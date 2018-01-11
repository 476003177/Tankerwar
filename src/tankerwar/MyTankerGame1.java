package tankerwar;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

import java.io.*;

public class MyTankerGame1 extends JFrame implements ActionListener{
	int mbx=Recorder.mbx;
	int mby=Recorder.mby;
	int px=Recorder.px;
	int py=Recorder.py;
	public static int smb=1;                             //开始面板显示
	public static MyPanel mp=null;                       //定义面板
	StartPanel sp=null;                                  //定义开始面板
	
	JMenuBar jmb=null;
	JMenu jm1=null;
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
	JMenuItem jmi3=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankerGame1 mytankergamer1=new MyTankerGame1();
		AePlayWave apw=new AePlayWave("tank.wav");
		apw.start();
	}
	
	public MyTankerGame1()
	{
		
		MyPanel mp=null;
		sp=new StartPanel();
		
		jmb=new JMenuBar();
		jm1=new JMenu("游戏(G)");
		jm1.setMnemonic('G');
		jmi1=new JMenuItem("开始新游戏(N)");
		jmi1.setMnemonic('N');
		jmi2=new JMenuItem("继续(c)");
		jmi2.setMnemonic('C');
		jmi3=new JMenuItem("保存退出(E)");
		jmi3.setMnemonic('E');

		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jmb.add(jm1);	
		this.setJMenuBar(jmb);

		jmi1.addActionListener(this);
		jmi1.setActionCommand("newgame");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("continue");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("exit");
		
		this.add(sp);
		Thread t=new Thread(sp);
		t.start();
		//初始化
		this.setTitle("坦克大战");                         //设置窗体标题
		this.setSize(px, py);                         //以像素为单位设置窗体长宽	
		this.setLocation(200,200);                          //设置初试横纵位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭窗口即退出
//		this.setResizable(false);                       //禁止用户改变窗体大小
		this.setVisible(true);                          //显示
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("newgame")||e.getActionCommand().equals("continue"))
		{
			this.remove(sp);                            //清除旧面板
			smb=0;
			if(e.getActionCommand().equals("newgame"))
			{
				mp.recovery=false;
				Recorder.setMyLife(Recorder.fimyLife);
			}else{
				mp.recovery=true;
			} 
			if(this.mp!=null)
			{
				mp.mytank=null;
				mp.ets.clear();
				mp.edts.clear();
				this.mp.xc=false;
			}
			mp=new MyPanel();
			this.add(mp);                               //将面板加入到JFrame窗体
			this.setVisible(true);                      //显示新面板
			this.addKeyListener(mp);
			Thread t=new Thread(mp);
			t.start();
		}else if(e.getActionCommand().equals("exit"))
		{
			Recorder.keep();
			System.exit(0);
		}
	}
}

class StartPanel extends JPanel implements Runnable                         //开始面板，提示作用
{
	boolean paint=true;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, Recorder.mbx, Recorder.mby);
		g.setColor(Color.yellow);
		Font myFont=new Font("华文新魏",Font.BOLD,30);
		g.setFont(myFont);                             //设置字体
		if(paint==true)
		{
			g.drawString("stage:1", Recorder.mbx/3, Recorder.mby/3);
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			if (MyTankerGame1.smb==1)
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				paint=!paint;
				this.repaint();
			}else if (MyTankerGame1.smb==0)break;
		}
	}
}

class MyPanel extends JPanel implements KeyListener,Runnable
{
	public static MyTank mytank=null;                   //定义我的坦克
	MyTank mytankyb=null;                               //样板坦克
	EnemyTank etankyb=null;                             //样板敌人坦克
	public static Vector<EnemyTank> ets=new Vector<EnemyTank>();      //定义存活敌人坦克集合
	public static Vector<EnemyTank> edts=new Vector<EnemyTank>();     //定义死亡但仍有子弹敌人坦克集合
	int ensize=20;                                      //敌人坦克数量
	public static boolean recovery=false;               //恢复标志位
	public boolean xc=true;                             //线程标志位
	Image image1=null;                                  //定义图片，三张图片组成一颗炸弹
	Image image2=null;
	Image image3=null;
	Vector<Bomb>bombs=new Vector<Bomb>();
	
	
	public MyPanel()                                    //构造函数
	{
		mytankyb=new MyTank(10,15);
		etankyb=new EnemyTank(10,15);
		if(recovery==true)
		{
			Recorder.get();                             //恢复记录
		}else{
			mytank=new MyTank(10,15);
			for(int i=0;(i<ensize)&&((i+1)*50+20)<=Recorder.mbx;i++)
			{
				EnemyTank et=new EnemyTank((i+1)*50,15);//创建敌人坦克对象
				ets.add(et);                            //将敌人坦克加入到集合
				ets.get(i).setDirect(2);                //初始化敌人坦克方向
				Thread t=new Thread(et);                //启动敌人坦克线程
				t.start();
			}
		}
		Recorder.enNum=ets.size();
		//初始化图片
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb1.gif"));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb2.gif"));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb3.gif"));
		try {
			image1=ImageIO.read(new File("bomb1.gif"));
			image2=ImageIO.read(new File("bomb2.gif"));
			image3=ImageIO.read(new File("bomb3.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g)                       //覆盖JPanel的paint方法，屏幕显示时候自动调用一次，Graphics是绘图的重要类，相当于一只画笔
	{
		//调用父类函数完成初始化   
		
		super.paint(g);                                 //不能少,作用：清空、初始化、重置
		g.fillRect(0, 0, Recorder.mbx, Recorder.mby);   //画背景
		this.drawtip(g);
		if(mytank.islive==true)
			this.drawTank(mytank, g,0);                 //画自己的坦克
		drawbomb(g);                                    //画出炸弹
		drawenemytank(g);                               //画出敌人坦克
		drawmyshot(g);                                  //画出自己子弹，判断攻击
		drawenemyshot(g);                               //画出敌人子弹，判断攻击
	}
		
		
		
	
	public void drawtip(Graphics g)
	{
		this.drawTank(this.etankyb,g,1,50,Recorder.mby+30);//画出样板坦克提示
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNum()+"",80,Recorder.mby+30);
		this.drawTank(this.mytankyb,g,1,130,Recorder.mby+30);//画出自己样板坦克提示
		g.setColor(Color.black);
		g.drawString(Recorder.getMyLife()+"",160,Recorder.mby+30);
	
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNumrc()+"",Recorder.mbx+70,100);
		Font f=new Font("宋体",Font.BOLD,20);
		g.setFont(f);
		g.drawString("您的总成绩是：",Recorder.mbx+5,50);
		this.drawTank(this.etankyb,g,1,Recorder.mbx+20,100);
	}
	
	public void drawbomb(Graphics g)
	{
		for(int i=0;i<bombs.size();i++)                 //画出炸弹
		{
			Bomb b=bombs.get(i);
			if(b.life>6)
			{
				g.drawImage(image1, b.x, b.y, 30, 31, this);
			}else if(b.life>3){
				g.drawImage(image2, b.x, b.y, 30, 31, this);
			}else{
				g.drawImage(image3, b.x, b.y, 30, 31, this);
			}
			b.lifedown();
			if(b.life==0)
			{
				bombs.remove(b);
			}
		}
	}
	
	public void drawmyshot(Graphics g)
	{
		for(int i=0;i<this.mytank.ss.size();i++)        //画出我的子弹
		{
			shot myshot=this.mytank.ss.get(i);
			if(myshot!=null && myshot.islive==true)
			{
				for(int j=0;j<ets.size();j++)
				{
					if(shothittank(myshot,ets.get(j)))  //子弹集中坦克
					{
						ets.get(j).islive=false;
						myshot.islive=false;
						Bomb b=new Bomb(ets.get(j).getZsx(),ets.get(j).getZsy());             //显示炸弹
						bombs.add(b);
						Recorder.deen();;
					}
				}		
				g.setColor(Color.white);
				g.draw3DRect(myshot.x, myshot.y, 1, 1, false);
			}
			if(myshot.islive==false)
			{
				this.mytank.ss.remove(i);               //子弹消失了，从集合中清除
			}
		}
	}
	
	public void drawenemyshot(Graphics g)
	{
		for(int i=0;i<ets.size();i++)                   //画出存活敌人的子弹
		{
			shot myshot=null;
			for(int j=0;j<ets.get(i).ss.size();j++)
			{
				myshot=ets.get(i).ss.get(j);
				if(myshot!=null && myshot.islive==true)
				{
					this.enemytankshot(myshot, i);
					g.setColor(Color.white);
					g.draw3DRect(myshot.x, myshot.y, 1, 1, false);
				}
				if(myshot.islive==false)
				{
					this.ets.get(i).ss.remove(j);       //子弹消失了，从集合中清除
				}			
			}
		}
		
		for(int i=0;i<edts.size();i++)                   //画出死亡敌人仍存子弹
		{
			shot myshot=null;
			if(edts.get(i).ss.size()!=0)
			{
				for(int j=0;j<edts.get(i).ss.size();j++)
				{
					myshot=edts.get(i).ss.get(j);
					if(myshot!=null && myshot.islive==true)
					{
						this.enemytankshot(myshot, -1);
						g.setColor(Color.white);
						g.draw3DRect(myshot.x, myshot.y, 1, 1, false);
					}
					if(myshot.islive==false)
					{
						this.edts.get(i).ss.remove(j);   //子弹消失了，从集合中清除
					}			
				}
			}else{
				edts.remove(i);
			}
			
		}
	}
	
	public void drawenemytank(Graphics g)
	{
		for(int i=0;i<ets.size();i++)
		{
			if(ets.get(i).islive==true)
			{
				this.drawTank(ets.get(i), g,0);           //画敌人的坦克
			}else{
				edts.add(ets.get(i));                   //将已被消灭的坦克移入死亡但仍有子弹坦克集合
				ets.remove(i);                          //将已被消灭的坦克移出存活坦克集合
			}
					              
		}
	}
	
	public void drawTank(Tank tank, Graphics g,int mx,int...zb)
	{
//		Tank tank=null;
		int x=0;
		int y=0;
		if(mx==0)
		{
			x=tank.getX();
			y=tank.getY();
		}else if(mx==1)
		{
			x=zb[0];
			y=zb[1];
		}
		
		switch(tank.type)                               //判断坦克类型
		{
		case 0:
			g.setColor(Color.yellow);
//			tank=new MyTank(int x, int y);		
			break;
		case 1:
			g.setColor(Color.red);
			break;
		}
		switch(tank.getDirect())
		{
		case 0:
			g.fill3DRect(x-10, y-15, 6, 31,false);
			g.fill3DRect(x+5, y-15, 6, 31,false); 	
			g.fillOval(x-5, y-7, 11, 15);
			g.drawLine(x, y-13, x, y);
			break;
		case 2:
			g.fill3DRect(x-10, y-15, 6, 31,false);
			g.fill3DRect(x+5, y-15, 6, 31,false); 	
			g.fillOval(x-5, y-7, 11, 15);
			g.drawLine(x, y+13, x, y);
			break;
		case 1:
			g.fill3DRect(x-15, y-10, 31, 6,false);
			g.fill3DRect(x-15, y+5, 31, 6,false); 	
			g.fillOval(x-7, y-5, 15, 11);
			g.drawLine(x+13, y, x, y);
			break;
		case 3:
			g.fill3DRect(x-15, y-10, 31, 6,false);
			g.fill3DRect(x-15, y+5, 31, 6,false); 	
			g.fillOval(x-7, y-5, 15, 11);
			g.drawLine(x-13, y, x, y);
			break;
		}
		
	}
	//子弹打中坦克
	public boolean shothittank(shot hitshot,Tank tank)
	{
		boolean hit = false;
		if(hitshot.x>=tank.getZsx() &&hitshot.y>=tank.getZsy()
				&&hitshot.x<=tank.getYxx() &&hitshot.y<=tank.getYxy())//子弹集中坦克
		{
			hit=true;
		}
		return hit;
		
	}

	public void enemytankshot(shot myshot,int i)      //i=-1时则不判定自身
	{
		for(int k=0;k<ets.size();k++)
		{
			if(i!=k&&shothittank(myshot,ets.get(k)))  //子弹集中坦克
			{
				myshot.islive=false;
			}
			if(shothittank(myshot,this.mytank)&&mytank.islive==true&&myshot.islive==true)          //子弹集中自己
			{
				myshot.islive=false;
				mytank.delife();;
				Bomb b=new Bomb(mytank.getZsx(),mytank.getZsy());             //显示炸弹
				bombs.add(b);
			}
		}
	}
	//预测坦克撞坦克
	public boolean tankwillhittank(Tank tank1,Tank tank2,int canshu) // canshu=0123代表转方向预测撞，canshu=5代表移动预测
	{
		boolean hit = false;
		int ydirect=tank1.getDirect();
		int yx=tank1.getX();
		int yy=tank1.getY();
		if(canshu==0||canshu==1||canshu==2||canshu==3)
		{
			int direct=canshu;
			tank1.setDirect(direct);
		}else if(canshu==5)
		{
			tank1.move();
		}
		if((tank1.getZsx()>=tank2.getZsx() &&tank1.getZsy()>=tank2.getZsy()
				&&tank1.getZsx()<=tank2.getYxx() &&tank1.getZsy()<=tank2.getYxy())||
				(tank1.getYxx()>=tank2.getZsx() &&tank1.getYxy()>=tank2.getZsy()
						&&tank1.getYxx()<=tank2.getYxx() &&tank1.getYxy()<=tank2.getYxy())||
				(tank1.getYsx()>=tank2.getZsx() &&tank1.getYsy()>=tank2.getZsy()
				&&tank1.getYsx()<=tank2.getYxx() &&tank1.getYsy()<=tank2.getYxy())||
				(tank1.getZxx()>=tank2.getZsx() &&tank1.getZxy()>=tank2.getZsy()
				&&tank1.getZxx()<=tank2.getYxx() &&tank1.getZxy()<=tank2.getYxy()))//子弹集中坦克
		{
			hit=true;
		}
		tank1.setDirect(ydirect);
		tank1.setX(yx);
		tank1.setY(yy);
		return hit;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		boolean hit=false;
		if(this.xc==true)
		{
			int direct=5;
			if(e.getKeyCode()==KeyEvent.VK_SPACE)
			{
				Recorder.zt=!(Recorder.zt);
				if(Recorder.zt==true)                       //暂停，把速度调为0
				{
					Recorder.enemyshotspeed=0;
					Recorder.myshotspeed=0;
					Recorder.mytankspeed=0;
					Recorder.enemytankspeed=0;
				}else{                                      //恢复
					Recorder.enemyshotspeed=Recorder.fienemyshotspeed;
					Recorder.myshotspeed=Recorder.fimyshotspeed;
					Recorder.mytankspeed=Recorder.fimytankspeed;
					Recorder.enemytankspeed=Recorder.fienemytankspeed;
				}
				
			}
			if(Recorder.zt==false)
			{
				if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyChar()=='S' || e.getKeyChar()=='s')
				{
					direct=2;
				}else if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyChar()=='W' || e.getKeyChar()=='w')
				{
					direct=0;
				}else if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyChar()=='A' || e.getKeyChar()=='a')
				{
					direct=3;
				}else if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyChar()=='D' || e.getKeyChar()=='d')
				{
					direct=1;
				}
				if(direct==0||direct==1||direct==2||direct==3)
				{
					for(int j=ets.size()-1;j>=0 && hit==false ;j--)     //判断是否撞敌人坦克
					{
						hit=this.tankwillhittank(this.mytank, ets.get(j), direct);
					}
					if(hit==false)this.mytank.setDirect(direct);
					for(int j=ets.size()-1;j>=0 && hit==false ;j--)     //判断是否撞敌人坦克
					{
						hit=this.tankwillhittank(this.mytank, ets.get(j), 5);
					}
					if(hit==false)this.mytank.move();
				}
				if(e.getKeyCode()==KeyEvent.VK_J)                       //按下J键开火
				{
					if(this.mytank.ss.size()<this.mytank.shotnum)                         //最多只能连发5颗子弹
					{
						this.mytank.shotEnemy();
					}
				}
				this.repaint();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int randnum=0;
		int times[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		for(int i=0;i<ets.size();i++)
		{
			times[i]=rand.nextInt(15)+1;
		}
		while(this.xc)
		{
			while(Recorder.zt)
			{
				System.out.println("暂停了");
			}
			try {
				Thread.sleep(50);
				for(int i=0;i<ets.size();i++)
				{
					times[i]--; 
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//int randNumber = rand.nextInt(MAX - MIN + 1) + MIN 产生MIN 和 MAX 范围内的随机整数
			
			for(int i=0;i<ets.size();i++)                             //敌人坦克移动
			{
				boolean hit=false;
				boolean hit1=false;
				if(times[i]==0)
				{
					randnum = rand.nextInt(4);
					times[i]=rand.nextInt(15)+1;
					for(int j=ets.size()-1;j>=0 && hit==false ;j--)
					{
						if(i!=j)
						{
							hit=this.tankwillhittank(ets.get(i), ets.get(j), randnum);
							hit1=this.tankwillhittank(ets.get(i), this.mytank, randnum);
						}
					}
					if(hit==false&&hit1==false)ets.get(i).setDirect(randnum);
				}else{
					for(int j=ets.size()-1;j>=0 && hit==false ;j--)
					{
						if(i!=j)
						{
							hit=this.tankwillhittank(ets.get(i), ets.get(j), 5);
							hit1=this.tankwillhittank(ets.get(i), this.mytank, 5);
						}
					}
					if(hit==false&&hit1==false)
					{
						ets.get(i).move();
					}
				}
			}
			this.repaint();
		}
	}
}

