//测试线程

package Test4;

public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cat cat =new Cat();
		cat.start();                                  //start会导致run函数运行
		
		Dog dog=new Dog();
		Thread t=new Thread(dog);                     //必须创建Thread对象并把实现Runnable接口的对象放入
		t.start();
	}

}

//实现线程有两种方法
class Cat extends Thread{                             //继承Thread类再重写run函数实现线程
	int times=0;
	public void run()                                 
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                       //休眠1000毫秒，让线程进入Blocker阻塞状态，释放资源
			System.out.println("cat"+"  ,  "+times);
			times++;
			if(times==10)
			{
				break;                                //退出线程,进入Dead死亡状态
			}
		}
		
	}
}

class Dog implements Runnable{                             //实现Runnable接口再重写run函数实现线程
	int times=0;
	public void run()                                 
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                       //休眠1000毫秒，让线程进入Blocker阻塞状态，释放资源
			System.out.println("dog"+"  ,  "+times);
			times++;
			if(times==10)
			{
				break;                                //退出线程,进入Dead死亡状态
			}
		}
		
	}
}