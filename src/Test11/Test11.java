/*
 * 猴子排队,排序方法
 */
package Test11;
import java.io.*;

public class Test11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size=5;                                                 //猴子数量
		InputStreamReader isr=null; 
		BufferedReader br=null;
		Monkey[] monkey=new Monkey[size];
		isr=new InputStreamReader(System.in);                       //输入
		br=new BufferedReader(isr);
		for(int i=0;i<monkey.length;i++)
		{
			System.out.print("请输入第"+(i+1)+"只猴子的身高:");
			try {
				String h=br.readLine();
				monkey[i]=new Monkey((i+1)+"",Float.parseFloat(h));//数和字符串相加自动转换为字符串
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		Monkey.mpsort(monkey);                                     //冒泡排序
//		Monkey.xzsort(monkey);                                     //选择排序
		Monkey.crsort(monkey);                                     //插入排序
		
		for(int k=0;k<monkey.length;k++)
		{
			System.out.println("第"+(k+1)+"只猴子的身高是:"+""+monkey[k].getH());
		}
	}
}

class Monkey{
	private String id;
	private float h;
	public float getH() {
		return h;
	}

	public Monkey(String id,float h)
	{
		this.id=id;
		this.h=h;
	}
	
	public static void mpsort(Monkey[] monkey)               //冒泡排序
	{
		for(int i=0;i<monkey.length-1;i++)                   //外层循环
		{
			for(int j=0;j<monkey.length-1-i;j++)             //内层循环
			{ 
				if(monkey[j].h>monkey[j+1].h)
				{
					Monkey monkey1=null;
					monkey1=monkey[j];
					monkey[j]=monkey[j+1];
					monkey[j+1]=monkey1;
				}
				
			}
		}
	}
	
	public static void xzsort(Monkey[] monkey)               //选择排序
	{
		for(int i=0;i<monkey.length-1;i++)                   //外层循环
		{
			int min=i;
			for(int j=i+1;j<monkey.length;j++)
			{
				if(monkey[j].h<monkey[min].h)
				{
					min=j;
				}
			}
			if(min!=i)
			{
				Monkey monkey1=null;
				monkey1=monkey[i];
				monkey[i]=monkey[min];
				monkey[min]=monkey1;
			}
		}
	}
	
	public static void crsort(Monkey[] monkey)              //插入排序
	{
		for(int i=1;i<monkey.length;i++)
		{
			Monkey monkey1=null;
			monkey1=monkey[i];
			float ih=monkey[i].h;
			int iid=i-1;
			while(iid>=0&&monkey[iid].h>ih)
			{
				monkey[iid+1]=monkey[iid];
				iid--;
			}
			monkey[iid+1]=monkey1;
		}
	}
}