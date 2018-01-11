/*
 * 复制，读入内存→写入文件
 */
package Test7;
import java.io.*;

public abstract class Test7 {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//缓冲字符流
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			//先创建FileReader对象再转换成BufferedReader
			FileReader fr=new FileReader("f:/aa.txt");
			br=new BufferedReader(fr);
			FileWriter fw=new FileWriter("f:/bb.txt");
			bw=new BufferedWriter(fw);
			String s="";
			while((s=br.readLine())!=null)
			{
				//System.out.print(s);                  //该读取方法不读取终止符、换行符
				bw.write(s+"\r\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			br.close();
			bw.close();
		}
		
		
		//字符流
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
//		//字节流
//		FileInputStream fis=null;                     //输入流
//		FileOutputStream fos=null;	
//		try {
//			fis=new FileInputStream("D:/bomb1.gif");
//			fos=new FileOutputStream("D:/bomb.gif");
//			 byte buf[]=new byte[1024];
//			 int n=0;                                 //记录实际读取到的字节数
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
