//�����߳�

package Test4;

public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cat cat =new Cat();
		cat.start();                                  //start�ᵼ��run��������
		
		Dog dog=new Dog();
		Thread t=new Thread(dog);                     //���봴��Thread���󲢰�ʵ��Runnable�ӿڵĶ������
		t.start();
	}

}

//ʵ���߳������ַ���
class Cat extends Thread{                             //�̳�Thread������дrun����ʵ���߳�
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
			}                       //����1000���룬���߳̽���Blocker����״̬���ͷ���Դ
			System.out.println("cat"+"  ,  "+times);
			times++;
			if(times==10)
			{
				break;                                //�˳��߳�,����Dead����״̬
			}
		}
		
	}
}

class Dog implements Runnable{                             //ʵ��Runnable�ӿ�����дrun����ʵ���߳�
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
			}                       //����1000���룬���߳̽���Blocker����״̬���ͷ���Դ
			System.out.println("dog"+"  ,  "+times);
			times++;
			if(times==10)
			{
				break;                                //�˳��߳�,����Dead����״̬
			}
		}
		
	}
}