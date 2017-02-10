import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Mian {

	public static void main(String[] args) throws TesseractException {
	    
	   // File imageFile = new File("Z:\\桌面\\sandao\\自拍\\IMG_0368-heade4.png");  
	    File imageFile = new File("Z:\\桌面\\sandao\\自拍\\1472797160.png");  
	    Tesseract instance = Tesseract.getInstance();  // JNA Interface Mapping 
	   
	    instance.setLanguage("chi_sim");
	   
	   
	   instance.setDatapath("C:/Program Files (x86)/Tesseract-OCR/tessdata");
	    //instance.setDatapath("D:/Tesseract-Build/tesseract-ocr/tessdata");
	    String result = instance.doOCR(imageFile);  
	    System.err.println(result);
	}
}
