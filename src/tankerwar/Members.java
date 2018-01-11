package tankerwar;

import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

//��¼��,����ȫ�ֱ���
class Recorder
{
	public static final int mbx=600;
	public static final int mby=400;
	public static final int px=800;
	public static final int py=550;
	public static final int fimyshotspeed=8;
	public static final int fienemyshotspeed=8;
	public static final int fimytankspeed = 4;
	public static final int fienemytankspeed = 2;
	public static final int myshotnum=10;
	public static final int enemyshotnum=5;
	public static final int fimyLife=3;
	public static int myshotspeed=fimyshotspeed;
	public static int enemyshotspeed=fienemyshotspeed;
	public static int mytankspeed = fimytankspeed;
	public static int enemytankspeed = fienemytankspeed;
	public static boolean zt=false;
	public static int enNum=20;              //��¼ÿ�صĵ�����
	private static int enNumrc=0;             //��¼����ĵ�����
	private static int myLife=3;              //��¼�ҵ�Ѫ��
	public static Vector<EnemyTank> ets;
	
	private static FileWriter fw=null;
	private static BufferedWriter bw=null;
	private static FileReader fr=null;
	private static BufferedReader br=null;
	
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}
	public static void deen() {
		enNum--;
		enNumrc++;
	}
	public static void demylife() {
		myLife--;
	}
	public static int getEnNumrc() {
		return enNumrc;
	}
	
	public static void keep()
	{
		try {
			ets=MyTankerGame1.mp.ets;
			fw=new FileWriter("f:/recorder.txt");
			bw=new BufferedWriter(fw);
			bw.write("Gold:"+enNumrc+"\r\n");
			bw.write("Mylife:"+myLife+"\r\n");
			ets.addAll(MyTankerGame1.mp.edts);
			String s=null;
			String s1=null;
			MyTank mytank=MyTankerGame1.mp.mytank;
			s="Mytank:"+mytank.x+","+mytank.y+","+mytank.direct;       //��¼�ҵ�̹��
			bw.write(s+"\r\n");
			for(int i=0;i<mytank.ss.size();i++)                        //��¼�ҵ��ӵ�        
			{
				shot myshot=mytank.ss.get(i);
				if (myshot.islive==true)
				{
					s1="myshot:"+myshot.x+","+myshot.y+","+myshot.direct;
					bw.write(s1+"\r\n");
				}
			}
			for(int i=0;i<ets.size();i++)                              //��¼����̹��
			{
				bw.write("Next"+"\r\n");
				EnemyTank et=ets.get(i);
				if(et.islive==true)
				{
					s="Enemytanklive:"+et.x+","+et.y+","+et.direct;
					bw.write(s+"\r\n");
				}else{
					s="Enemytankdie:";
					bw.write(s+"\r\n");
				}
				for(int j=0;j<et.ss.size();j++)                        //��¼�����ӵ�
				{
					shot myshot=et.ss.get(j);
					if (myshot.islive==true)
					{
						s1="enshot:"+myshot.x+","+myshot.y+","+myshot.direct;
						bw.write(s1+"\r\n");
					}
				}
			}
			bw.write("That is all"+"\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bw.close();                   //����ȹر�
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void get()
	{
		try {
			File f=new File("f:/recorder.txt");
			if(f.exists()==false)
			{
				f.createNewFile();
				fw=new FileWriter(f);
				fw.write(0+"\r\n");
				fw.close();
			}
			fr=new FileReader("f:/recorder.txt");
			br=new BufferedReader(fr);
			String n=null;
			String[] nf=null;
			String[] nt=null;
			EnemyTank tank1=null;
			MyTank mytank=null;
			shot shot1=null;
			int k=0;
			while((n=br.readLine())!=null)
			{
				if(!n.equals("Next")&&!n.equals("That is all"))
				{
					nf=n.split(":");
					if(nf[0].equals("Gold"))
					{
						enNumrc=Integer.parseInt(nf[1]);
					}else if(nf[0].equals("Enemytanklive"))            //�ָ����ŵ�̹��
					{
						nt=nf[1].split(",");
						tank1=new EnemyTank(Integer.parseInt(nt[0]),Integer.parseInt(nt[1]));
						tank1.setDirect(Integer.parseInt(nt[2]));
//						System.out.println(tank1.x+","+tank1.y+","+tank1.direct);
						MyTankerGame1.mp.ets.add(tank1);
						Thread t=new Thread(tank1);                    //��������̹���߳�
						t.start();
					}else if(nf[0].equals("enshot"))                   //�ָ��ӵ�
					{
						nt=nf[1].split(",");
						shot1=new shot(Integer.parseInt(nt[0]),Integer.parseInt(nt[1]),Integer.parseInt(nt[2]),1);
						tank1.ss.add(shot1);
						Thread t=new Thread(shot1);                    //��������̹���߳�
						t.start();
					}else if(nf[0].equals("Enemytankdie"))
					{
						tank1=new EnemyTank(0,0);
						tank1.setDirect(0);
//						MyTankerGame1.mp.edts.add(tank1);
					}else if(nf[0].equals("Mytank"))                   //�ָ��Լ���̹��
					{
						nt=nf[1].split(",");
						mytank=new MyTank(Integer.parseInt(nt[0]),Integer.parseInt(nt[1]));
						mytank.setDirect(Integer.parseInt(nt[2]));
//						System.out.println(tank1.x+","+tank1.y+","+tank1.direct);
						MyTankerGame1.mp.mytank=mytank;
					}else if(nf[0].equals("myshot"))                   //�ָ��Լ����ӵ�
					{
						nt=nf[1].split(",");
						shot1=new shot(Integer.parseInt(nt[0]),Integer.parseInt(nt[1]),Integer.parseInt(nt[2]),1);
						mytank.ss.add(shot1);
						Thread t=new Thread(shot1);                    //�����߳�
						t.start();
					}else if(nf[0].equals("Mylife"))                   //�ָ��Լ�������
					{
						nt=nf[1].split(",");
						myLife=Integer.parseInt(nt[0]);
					}
				}
			}
			//enNumrc=Integer.parseInt(n1[1]);      //ת��Ϊint
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();                   //����ȹر�
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}




//̹����
class Tank                                    //̹��
{
	int x=0;                                  //������
	int y=0;                                  //������
	int direct=0;                             //����0��1��2��3��
	int speed;
	int zsx=0;                                //���Ͻ�x����
	int zsy=0;                                //���Ͻ�y����
	int yxx=0;                                //���½�x����
	int yxy=0;                                //���½�y����
	int ysx=0;
	int ysy=0;
	int zxx=0;
	int zxy=0;
	int qx=0;                                 //ǹ��x����
	int qy=0;                                 //ǹ��y����
	int type=0;                               //̹�����ͣ�0Ϊ�ҵģ�1Ϊ����
	int zj=0;                                 //ײ����1Ϊ�ǣ�0Ϊ��
	int shotnum=5;                            //�����ӵ���
	boolean islive=true;                      //�Ƿ񻹴�1Ϊ��0Ϊ��
	
	public int getQx() {
		switch(this.getDirect())
		{
		case 0:
		case 2:
			this.qx=this.getX();
			break;
		case 1:
			this.qx=this.getX()+13;
			break;
		case 3:
			this.qx=this.getX()-13;
			break;
		}
		return this.qx;
	}
	public int getQy() {
		switch(this.getDirect())
		{
		case 0:
			this.qy=this.getY()-13;
			break;
		case 1:
		case 3:
			this.qy=this.getY();
			break;
		case 2:
			this.qy=this.getY()+13;
			break;
		}
		return this.qy;
	}

	public int getYsx() {
		this.ysx= this.getYxx();
		return this.ysx;
	}
	public int getYsy() {
		this.ysy= this.getZsy();
		return this.ysy;
	}
	public int getZxx() {
		this.zxx=this.getZsx();
		return zxx;
	}
	public int getZxy() {
		this.zxy=this.getYxy();
		return zxy;
	}
	
	public int getZsx() {
		if(this.direct==0 || this.direct==2)
		{
			this.zsx= this.x-10;
		}else if(this.direct==1 || this.direct==3){
			this.zsx=this.x-15;
		}
		return this.zsx;
	}
	public int getZsy() {
		if(this.direct==0 || this.direct==2)
		{
			this.zsy= this.y-15;
		}else if(this.direct==1 || this.direct==3){
			this.zsy=this.y-10;
		}
		return this.zsy;
	}
	public int getYxx() {
		if(this.direct==0 || this.direct==2)
		{
			this.yxx= this.x+10;
		}else if(this.direct==1 || this.direct==3){
			this.yxx= this.x+15;
		}
		return this.yxx;
	}
	public int getYxy() {
		if(this.direct==0 || this.direct==2)
		{
			this.yxy= this.y+15;
		}else if(this.direct==1 || this.direct==3){
			this.yxy=this.y+10;
		}
		return this.yxy;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
		
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void move()                        //̹���ƶ�
	{
		int speed=0;
		switch(this.type)
		{
		case 0:
			speed=Recorder.mytankspeed;
			break;
		case 1:
			speed=Recorder.enemytankspeed;
			break;
		}
		switch(this.direct)
		{
		case 0:
			if(this.getZsy()-speed>=0)
			{
				this.y-=speed;
				this.zj=0;
			}else{
				this.y=15;
				this.zj=1;
			}
			break;
		case 2:
			if(this.getYxy()+speed<=Recorder.mby)
			{
				this.y+=speed;
				this.zj=0;
			}else{
				this.y=Recorder.mby-15;
				this.zj=1;
			}
			break;
		case 1:
			if(this.getYxx()+speed<=Recorder.mbx)
			{
				this.x+=speed;
				this.zj=0;
			}else{
				this.x=Recorder.mbx-15;
				this.zj=1;
			}
			break;
		case 3:
			if(this.getZsx()-speed>=0)
			{
				this.x-=speed;
				this.zj=0;
			}else{
				this.x=15;
				this.zj=1;
			}
			break;
		}
	}

	public Tank(int x,int y,int type)                  //���캯��
	{
		this.type=type;
		this.x=x;
		this.y=y;
//		switch(type)
//		{
//		case 0:
//			this.speed=Recorder.mytankspeed;
//			this.shotnum=Recorder.myshotnum;
//			break;
//		case 1:
//			this.speed=Recorder.enemytankspeed;
//			this.shotnum=Recorder.enemyshotnum;
//			break;
//		}
	}
}

class MyTank extends Tank                     //�ҵ�̹����
{	
	Vector<shot> ss=new Vector<shot>();
	shot s=null;
	public MyTank(int x,int y)
	{
		super(x,y,0);                           //���ø��๹�캯��
		if(Recorder.getMyLife()==0)this.islive=false;
	}
	public void shotEnemy()
	{
		s=new shot(this.getQx(),this.getQy(),this.getDirect(),0);
		ss.add(s);
		Thread t=new Thread(s);
		t.start();                            //�����ӵ��߳�
	}
	public void delife()
	{
		Recorder.demylife();
		if(Recorder.getMyLife()<=0)
		{
			this.islive=false;
		}
	}
}

class EnemyTank extends Tank implements Runnable                 //����̹��
{
	Vector<shot> ss=new Vector<shot>();
	shot s=null;
	Random rand = new Random();
	int randnum=0;
	
	public EnemyTank(int x,int y)
	{
		super(x,y,1);	
	}

	public void shotme()
	{
		s=new shot(this.getQx(),this.getQy(),this.getDirect(),1);
		ss.add(s);
		Thread t=new Thread(s);
		t.start();                            //�����ӵ��߳�
	}
	@Override
	public void run() {
		while(this.islive)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			randnum = rand.nextInt(4);
			if(randnum==2&&this.ss.size()<this.shotnum)                         //���ֻ������5���ӵ�
			{
				this.shotme();
			}
		}

	}
}

//�ӵ���
class shot implements Runnable{
	int x;
	int y;
	int direct=0;
	int type=0;                               //�ӵ����ͣ�0Ϊ�ң�1Ϊ����
	boolean islive=true;                      //�ӵ��Ƿ񻹴���
	int speed=Recorder.myshotspeed;
	
	public shot(int x,int y,int direct,int type)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
		this.type = type;
	}
	@Override
	public void run() {
		while(this.islive)
		{
			switch(this.type)
			{
			case 0:
				this.speed=Recorder.myshotspeed;
				break;
			case 1:
				this.speed=Recorder.enemyshotspeed;
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(this.direct)
			{
			case 0:
				this.y-=this.speed;
				break;
			case 1:
				this.x+=this.speed;
				break;
			case 2:
				this.y+=this.speed;
				break;
			case 3:
				this.x-=this.speed;
				break;
			}
			if(this.x<0||this.x>Recorder.mbx||this.y<0||this.y>Recorder.mby)
			{
				this.islive=false;
			}
		}
		
	}
}

//zը����,һ���������̵߳�ԭ�򣺴���������ı�
class Bomb
{
	int x,y;
	int life=9;
	boolean islive=true;
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void lifedown()
	{
		if(this.life>0)
		{
			this.life--;
		}else{
			this.islive=false;
		}
	}
}

//������
class AePlayWave extends Thread {

	private String filename;

	public AePlayWave(String wavfile) 
	{
		filename = wavfile;
	}
	public void run() {
		File soundFile = new File(filename);
		System.out.print(soundFile);
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		auline.start();
		int nBytesRead = 0;
		//���ǻ���
		byte[] abData = new byte[512];
		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}
	}
}