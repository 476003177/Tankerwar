/**
 * File�Ļ����÷�
 */
package Test6;
import java.io.*;
public class Test6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		File f=new File("f:\\aa.txt");          //�����ļ�����,Fileû�ж�д��������Ҫ�õ�InputStream
//		FileInputStream fis=null;
//		FileOutputStream fis=null;
//		try {                                   //��ȡ�ļ�������
//			fis=new FileInputStream(f);
//			int n=0;                            //ʵ�ʶ�ȡ�����ֽ���
//			byte []bytes=new byte[1024];        //�����ȡ
//			while((n=fis.read(bytes))!=-1)
//			{
//				String s=new String(bytes,0,n); //���ֽ�ת��String
//				System.out.println(s);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			try {
//				fis.close();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		try {                                   //д���ļ�������
//			fis=new FileOutputStream(f);
//			String s="hello\r\n";               //\r\nΪ�����ַ�
//			fis.write(s.getBytes());            //���ַ���ת��Ϊ�ֽ������
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			try {
//				fis.close();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
		
//		System.out.println("�ļ�·��Ϊ"+f.getAbsolutePath());
//		System.out.println("�ļ���СΪ"+f.length());
//		System.out.println("�ɶ���"+f.canRead());
		
//		if(!f.exists())                        //���ļ����ڷ���1��ȡ��Ϊ0
//		{
//			f.createNewFile();                 //�����ļ�
//			f.mkdir();                         //�����ļ���
//		}else{
//			System.out.println("�ļ��Ѿ�����");
//		}
//		if(f.isDirectory())                    //�г��ļ��������ļ����ļ���
//		{
//			File lists[]=f.listFiles();
//			for(int i=0;i<lists.length;i++)
//			{
//				System.out.println("�ļ�����"+lists[i].getName());
//			}
//		}
		
	}

	
	
}
