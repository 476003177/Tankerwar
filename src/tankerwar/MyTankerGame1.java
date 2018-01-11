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
	public static int smb=1;                             //��ʼ�����ʾ
	public static MyPanel mp=null;                       //�������
	StartPanel sp=null;                                  //���忪ʼ���
	
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
		jm1=new JMenu("��Ϸ(G)");
		jm1.setMnemonic('G');
		jmi1=new JMenuItem("��ʼ����Ϸ(N)");
		jmi1.setMnemonic('N');
		jmi2=new JMenuItem("����(c)");
		jmi2.setMnemonic('C');
		jmi3=new JMenuItem("�����˳�(E)");
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
		//��ʼ��
		this.setTitle("̹�˴�ս");                         //���ô������
		this.setSize(px, py);                         //������Ϊ��λ���ô��峤��	
		this.setLocation(200,200);                          //���ó��Ժ���λ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùرմ��ڼ��˳�
//		this.setResizable(false);                       //��ֹ�û��ı䴰���С
		this.setVisible(true);                          //��ʾ
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("newgame")||e.getActionCommand().equals("continue"))
		{
			this.remove(sp);                            //��������
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
			this.add(mp);                               //�������뵽JFrame����
			this.setVisible(true);                      //��ʾ�����
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

class StartPanel extends JPanel implements Runnable                         //��ʼ��壬��ʾ����
{
	boolean paint=true;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, Recorder.mbx, Recorder.mby);
		g.setColor(Color.yellow);
		Font myFont=new Font("������κ",Font.BOLD,30);
		g.setFont(myFont);                             //��������
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
	public static MyTank mytank=null;                   //�����ҵ�̹��
	MyTank mytankyb=null;                               //����̹��
	EnemyTank etankyb=null;                             //�������̹��
	public static Vector<EnemyTank> ets=new Vector<EnemyTank>();      //���������̹�˼���
	public static Vector<EnemyTank> edts=new Vector<EnemyTank>();     //���������������ӵ�����̹�˼���
	int ensize=20;                                      //����̹������
	public static boolean recovery=false;               //�ָ���־λ
	public boolean xc=true;                             //�̱߳�־λ
	Image image1=null;                                  //����ͼƬ������ͼƬ���һ��ը��
	Image image2=null;
	Image image3=null;
	Vector<Bomb>bombs=new Vector<Bomb>();
	
	
	public MyPanel()                                    //���캯��
	{
		mytankyb=new MyTank(10,15);
		etankyb=new EnemyTank(10,15);
		if(recovery==true)
		{
			Recorder.get();                             //�ָ���¼
		}else{
			mytank=new MyTank(10,15);
			for(int i=0;(i<ensize)&&((i+1)*50+20)<=Recorder.mbx;i++)
			{
				EnemyTank et=new EnemyTank((i+1)*50,15);//��������̹�˶���
				ets.add(et);                            //������̹�˼��뵽����
				ets.get(i).setDirect(2);                //��ʼ������̹�˷���
				Thread t=new Thread(et);                //��������̹���߳�
				t.start();
			}
		}
		Recorder.enNum=ets.size();
		//��ʼ��ͼƬ
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
	
	public void paint(Graphics g)                       //����JPanel��paint��������Ļ��ʾʱ���Զ�����һ�Σ�Graphics�ǻ�ͼ����Ҫ�࣬�൱��һֻ����
	{
		//���ø��ຯ����ɳ�ʼ��   
		
		super.paint(g);                                 //������,���ã���ա���ʼ��������
		g.fillRect(0, 0, Recorder.mbx, Recorder.mby);   //������
		this.drawtip(g);
		if(mytank.islive==true)
			this.drawTank(mytank, g,0);                 //���Լ���̹��
		drawbomb(g);                                    //����ը��
		drawenemytank(g);                               //��������̹��
		drawmyshot(g);                                  //�����Լ��ӵ����жϹ���
		drawenemyshot(g);                               //���������ӵ����жϹ���
	}
		
		
		
	
	public void drawtip(Graphics g)
	{
		this.drawTank(this.etankyb,g,1,50,Recorder.mby+30);//��������̹����ʾ
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNum()+"",80,Recorder.mby+30);
		this.drawTank(this.mytankyb,g,1,130,Recorder.mby+30);//�����Լ�����̹����ʾ
		g.setColor(Color.black);
		g.drawString(Recorder.getMyLife()+"",160,Recorder.mby+30);
	
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNumrc()+"",Recorder.mbx+70,100);
		Font f=new Font("����",Font.BOLD,20);
		g.setFont(f);
		g.drawString("�����ܳɼ��ǣ�",Recorder.mbx+5,50);
		this.drawTank(this.etankyb,g,1,Recorder.mbx+20,100);
	}
	
	public void drawbomb(Graphics g)
	{
		for(int i=0;i<bombs.size();i++)                 //����ը��
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
		for(int i=0;i<this.mytank.ss.size();i++)        //�����ҵ��ӵ�
		{
			shot myshot=this.mytank.ss.get(i);
			if(myshot!=null && myshot.islive==true)
			{
				for(int j=0;j<ets.size();j++)
				{
					if(shothittank(myshot,ets.get(j)))  //�ӵ�����̹��
					{
						ets.get(j).islive=false;
						myshot.islive=false;
						Bomb b=new Bomb(ets.get(j).getZsx(),ets.get(j).getZsy());             //��ʾը��
						bombs.add(b);
						Recorder.deen();;
					}
				}		
				g.setColor(Color.white);
				g.draw3DRect(myshot.x, myshot.y, 1, 1, false);
			}
			if(myshot.islive==false)
			{
				this.mytank.ss.remove(i);               //�ӵ���ʧ�ˣ��Ӽ��������
			}
		}
	}
	
	public void drawenemyshot(Graphics g)
	{
		for(int i=0;i<ets.size();i++)                   //���������˵��ӵ�
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
					this.ets.get(i).ss.remove(j);       //�ӵ���ʧ�ˣ��Ӽ��������
				}			
			}
		}
		
		for(int i=0;i<edts.size();i++)                   //�������������Դ��ӵ�
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
						this.edts.get(i).ss.remove(j);   //�ӵ���ʧ�ˣ��Ӽ��������
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
				this.drawTank(ets.get(i), g,0);           //�����˵�̹��
			}else{
				edts.add(ets.get(i));                   //���ѱ������̹�����������������ӵ�̹�˼���
				ets.remove(i);                          //���ѱ������̹���Ƴ����̹�˼���
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
		
		switch(tank.type)                               //�ж�̹������
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
	//�ӵ�����̹��
	public boolean shothittank(shot hitshot,Tank tank)
	{
		boolean hit = false;
		if(hitshot.x>=tank.getZsx() &&hitshot.y>=tank.getZsy()
				&&hitshot.x<=tank.getYxx() &&hitshot.y<=tank.getYxy())//�ӵ�����̹��
		{
			hit=true;
		}
		return hit;
		
	}

	public void enemytankshot(shot myshot,int i)      //i=-1ʱ���ж�����
	{
		for(int k=0;k<ets.size();k++)
		{
			if(i!=k&&shothittank(myshot,ets.get(k)))  //�ӵ�����̹��
			{
				myshot.islive=false;
			}
			if(shothittank(myshot,this.mytank)&&mytank.islive==true&&myshot.islive==true)          //�ӵ������Լ�
			{
				myshot.islive=false;
				mytank.delife();;
				Bomb b=new Bomb(mytank.getZsx(),mytank.getZsy());             //��ʾը��
				bombs.add(b);
			}
		}
	}
	//Ԥ��̹��ײ̹��
	public boolean tankwillhittank(Tank tank1,Tank tank2,int canshu) // canshu=0123����ת����Ԥ��ײ��canshu=5�����ƶ�Ԥ��
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
				&&tank1.getZxx()<=tank2.getYxx() &&tank1.getZxy()<=tank2.getYxy()))//�ӵ�����̹��
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
				if(Recorder.zt==true)                       //��ͣ�����ٶȵ�Ϊ0
				{
					Recorder.enemyshotspeed=0;
					Recorder.myshotspeed=0;
					Recorder.mytankspeed=0;
					Recorder.enemytankspeed=0;
				}else{                                      //�ָ�
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
					for(int j=ets.size()-1;j>=0 && hit==false ;j--)     //�ж��Ƿ�ײ����̹��
					{
						hit=this.tankwillhittank(this.mytank, ets.get(j), direct);
					}
					if(hit==false)this.mytank.setDirect(direct);
					for(int j=ets.size()-1;j>=0 && hit==false ;j--)     //�ж��Ƿ�ײ����̹��
					{
						hit=this.tankwillhittank(this.mytank, ets.get(j), 5);
					}
					if(hit==false)this.mytank.move();
				}
				if(e.getKeyCode()==KeyEvent.VK_J)                       //����J������
				{
					if(this.mytank.ss.size()<this.mytank.shotnum)                         //���ֻ������5���ӵ�
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
				System.out.println("��ͣ��");
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
			//int randNumber = rand.nextInt(MAX - MIN + 1) + MIN ����MIN �� MAX ��Χ�ڵ��������
			
			for(int i=0;i<ets.size();i++)                             //����̹���ƶ�
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

