/*
 * ���ƣ������ڴ��д���ļ�
 */
package Test7;
import java.io.*;

public abstract class Test7 {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//�����ַ���
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			//�ȴ���FileReader������ת����BufferedReader
			FileReader fr=new FileReader("f:/aa.txt");
			br=new BufferedReader(fr);
			FileWriter fw=new FileWriter("f:/bb.txt");
			bw=new BufferedWriter(fw);
			String s="";
			while((s=br.readLine())!=null)
			{
				//System.out.print(s);                  //�ö�ȡ��������ȡ��ֹ�������з�
				bw.write(s+"\r\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			br.close();
			bw.close();
		}
		
		
		//�ַ���
//		FileReader fr=null;
//		FileWriter fw=null;
//		try {
//			fr=new FileReader("f:/aa.txt");
//			fw=new FileWriter("f:/bb.txt");
//			char c[]=new char[1024];
//			int n=0;
//			while((n=fr.read(c))!=-1)
//			{
//				//String s=new String(c,0,n);
//				//System.out.println(s);
//				fw.write(c,0,n);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally{
//			fr.close();
//			fw.close();
//		}
//		//�ֽ���
//		FileInputStream fis=null;                     //������
//		FileOutputStream fos=null;	
//		try {
//			fis=new FileInputStream("D:/bomb1.gif");
//			fos=new FileOutputStream("D:/bomb.gif");
//			 byte buf[]=new byte[1024];
//			 int n=0;                                 //��¼ʵ�ʶ�ȡ�����ֽ���
//			 while((n=fis.read(buf))!=-1)
//			 {
//				 fos.write(buf);
//			 }
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			fis.close();
//			fos.close();
//		}
	}

}
