package tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.WrapperRemover;

public class IOStream {
	
	private WrapperRemover wr = new WrapperRemover(36, 130);
	private byte[] imagesRaw;
	
	public byte[] filterBytes(File file) throws IOException{
		
		ByteArrayOutputStream bos = null;
		
	
		FileInputStream fis = new FileInputStream(file);
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
		buf = bos.toByteArray();
		buf = wr.removeWrapper(buf);
		imagesRaw = buf;
	
		return imagesRaw;
	}
}
