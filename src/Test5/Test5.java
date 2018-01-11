//售票系统,原子性

package Test5;

public class Test5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketWindow tw1=new TicketWindow();
		TicketWindow tw2=new TicketWindow();
		TicketWindow tw3=new TicketWindow();
		Thread t1=new Thread(tw1);
		Thread t2=new Thread(tw2);
		Thread t3=new Thread(tw3);
		//可以一个对象同时启用多个线程，此时该对象的资源各线程共享，对象锁可以直接是this
//		Thread t2=new Thread(tw1);
//		Thread t3=new Thread(tw1);
		t1.start();
		t2.start();
		t3.start();
	}

}

//售票窗口类
class TicketWindow implements Runnable{
	private static int nums=200;                              //static保证nums唯一且属于整个类，所有该类的对象共享
	private static Dog dog=new Dog();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized(dog)                                //同步代码块，保证原子性，括号内参数为充当对象锁的对象，需要同步安全的各线程对象锁必须一致
			{
				if(nums>0)
				{
					System.out.println(Thread.currentThread().getName()+"在售出第"+nums+"票");//得到当前线程名字
					nums--;
				}else{
					System.out.println("卖完了");
					break;
				}
			}
		}
	}
}
class Dog
{

}
