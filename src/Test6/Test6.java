/**
 * File的基本用法
 */
package Test6;
import java.io.*;
public class Test6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		File f=new File("f:\\aa.txt");          //创建文件对象,File没有读写能力，需要用到InputStream
//		FileInputStream fis=null;
//		FileOutputStream fis=null;
//		try {                                   //读取文件流数据
//			fis=new FileInputStream(f);
//			int n=0;                            //实际读取到的字节数
//			byte []bytes=new byte[1024];        //缓存读取
//			while((n=fis.read(bytes))!=-1)
//			{
//				String s=new String(bytes,0,n); //把字节转成String
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
//		try {                                   //写入文件流数据
//			fis=new FileOutputStream(f);
//			String s="hello\r\n";               //\r\n为换行字符
//			fis.write(s.getBytes());            //将字符串转换为字节再输出
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
		
//		System.out.println("文件路径为"+f.getAbsolutePath());
//		System.out.println("文件大小为"+f.length());
//		System.out.println("可读吗："+f.canRead());
		
//		if(!f.exists())                        //若文件存在返回1，取反为0
//		{
//			f.createNewFile();                 //创建文件
//			f.mkdir();                         //创建文件夹
//		}else{
//			System.out.println("文件已经存在");
//		}
//		if(f.isDirectory())                    //列出文件下所有文件的文件名
//		{
//			File lists[]=f.listFiles();
//			for(int i=0;i<lists.length;i++)
//			{
//				System.out.println("文件名："+lists[i].getName());
//			}
//		}
		
	}

	
	
}
