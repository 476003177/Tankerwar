package Test9;

public class Test9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		father c1=new father();
		printl(c1);
	}
	public static void printl(father c1)
	{
		System.out.println(c1.a);
	}
}

class father
{
	int a=1;
//	public void print(int b)
//	{
//		System.out.println(b);
//	}
}
class child extends father
{
	int a=2;
}
