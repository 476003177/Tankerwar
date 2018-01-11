/*
 * 记事本
 */
package Test8;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test8 extends JFrame implements ActionListener{
	
	JTextArea jta=null;
	JMenuBar jmb=null;
	JMenu jm1=null;
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test8 test8=new Test8();
	}
	public Test8()
	{
		jta=new JTextArea();
		jmb=new JMenuBar();
		jm1=new JMenu("文件(o)");
		jm1.setMnemonic('F');
		jmi1=new JMenuItem("打开",new ImageIcon("new.jpg"));
		jmi2=new JMenuItem("保存");
		
		this.setJMenuBar(jmb);
		jmb.add(jm1);
		jm1.add(jmi1);
		jm1.add(jmi2);
		
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		
		this.add(jta);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,300);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("open"))
		{
//			System.out.println("open");
			JFileChooser jfc1=new JFileChooser();            //文件选择组件
			jfc1.setDialogTitle("请选择文件");                   //设置标题名字
			jfc1.showOpenDialog(null);                       //null表示默认属性
			jfc1.setVisible(true);                           //显示
			
			String filename=jfc1.getSelectedFile().getAbsolutePath();//得到用户选择的文件全路径
//			System.out.println(filename);
			
			FileReader fr=null;
			BufferedReader br=null;                          //用Buffer流读取
			
			try { 
				fr=new FileReader(filename);
				br=new BufferedReader(fr);
				 
				String s="";
				String all="";
				while((s=br.readLine())!=null)
				{
					all+=s+"\r\n";
					
				}
				jta.setText(all);                            //输出到多行文本框
				
				
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}finally{
				try {
					br.close();
					fr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}else if(e.getActionCommand().equals("save"))
		{
//			System.out.println("save");
			JFileChooser jfc2=new JFileChooser();
			jfc2.setDialogTitle("另存为");
			jfc2.showSaveDialog(null);
			jfc2.setVisible(true);
			String filename=jfc2.getSelectedFile().getAbsolutePath();
			String s="";
			
			FileWriter fw = null;
			BufferedWriter bw=null;
			try {
				fw=new FileWriter(filename);
				bw=new BufferedWriter(fw);
				
				bw.write(this.jta.getText());
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					bw.close();
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
	}
}
