//��Ʊϵͳ,ԭ����

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
		//����һ������ͬʱ���ö���̣߳���ʱ�ö������Դ���̹߳�������������ֱ����this
//		Thread t2=new Thread(tw1);
//		Thread t3=new Thread(tw1);
		t1.start();
		t2.start();
		t3.start();
	}

}

//��Ʊ������
class TicketWindow implements Runnable{
	private static int nums=200;                              //static��֤numsΨһ�����������࣬���и���Ķ�����
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
			synchronized(dog)                                //ͬ������飬��֤ԭ���ԣ������ڲ���Ϊ�䵱�������Ķ�����Ҫͬ����ȫ�ĸ��̶߳���������һ��
			{
				if(nums>0)
				{
					System.out.println(Thread.currentThread().getName()+"���۳���"+nums+"Ʊ");//�õ���ǰ�߳�����
					nums--;
				}else{
					System.out.println("������");
					break;
				}
			}
		}
	}
}
class Dog
{

}
