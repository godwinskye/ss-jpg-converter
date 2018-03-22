package tools;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.WrapperRemover;


public class IOStream {
	
	private WrapperRemover wr;
	private ArrayList<byte[]> imagesRaw = new ArrayList<byte[]>();
	
	public ArrayList<byte[]> filterBytes(File[] files) throws IOException{
		
		ByteArrayOutputStream bos = null;
		
		for(File f: files){
			FileInputStream fis = new FileInputStream(f);
			//input raw bytes from file system
			
			bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			
			try{
				for(int readNum; (readNum = fis.read(buf)) != -1;){
					bos.write(buf, 0, readNum);
					System.out.println("read "+readNum +" bytes,");
				}
			} catch (IOException e){
				Logger.getLogger(IOStream.class.getName()).log(Level.SEVERE, null, e);
			}
			//add into an arraylist which will then be passed into main for image processing to work.
			buf = wr.removeWrapper();
			imagesRaw.add(buf);
		}
		
		return imagesRaw;
		
	}
}
