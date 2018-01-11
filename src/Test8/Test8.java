/*
 * ���±�
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
		jm1=new JMenu("�ļ�(o)");
		jm1.setMnemonic('F');
		jmi1=new JMenuItem("��",new ImageIcon("new.jpg"));
		jmi2=new JMenuItem("����");
		
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
			JFileChooser jfc1=new JFileChooser();            //�ļ�ѡ�����
			jfc1.setDialogTitle("��ѡ���ļ�");                   //���ñ�������
			jfc1.showOpenDialog(null);                       //null��ʾĬ������
			jfc1.setVisible(true);                           //��ʾ
			
			String filename=jfc1.getSelectedFile().getAbsolutePath();//�õ��û�ѡ����ļ�ȫ·��
//			System.out.println(filename);
			
			FileReader fr=null;
			BufferedReader br=null;                          //��Buffer����ȡ
			
			try { 
				fr=new FileReader(filename);
				br=new BufferedReader(fr);
				 
				String s="";
				String all="";
				while((s=br.readLine())!=null)
				{
					all+=s+"\r\n";
					
				}
				jta.setText(all);                            //����������ı���
				
				
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
			jfc2.setDialogTitle("���Ϊ");
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
