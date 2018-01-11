/*
 * ÉùÒô²âÊÔ
 */

package Test10;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;


public class Test10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AePlayWave apw=new AePlayWave("F:\\tank.wav");
		apw.start();
	}

}
class AePlayWave extends Thread {

	private String filename;

	public AePlayWave(String wavfile) 
	{
		filename = wavfile;
	}
	public void run() {
		File soundFile = new File(filename);
		System.out.print(soundFile);
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		auline.start();
		int nBytesRead = 0;
		//ÕâÊÇ»º³å
		byte[] abData = new byte[512];
		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}
	}
}