package action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * Servlet implementation class OrcAction
 */
@MultipartConfig
@WebServlet("/OrcAction")
public class OrcAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Tesseract instance = null;
	static{
		 ImageIO.scanForPlugins();
		  instance = Tesseract.getInstance();  // JNA Interface Mapping
		    instance.setLanguage("chi_sim");
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrcAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		System.out.println("----------------------"+request.getPart("file"));
		System.out.println("----------------------"+request.getParameterMap());
		System.out.println("----------------------"+request.getInputStream());
		InputStream is = request.getPart("file").getInputStream();
		
  
		// File imageFile = new File("C:\\Users\\Administrator\\Desktop\\IMG_0445.JPG.tif");  
		   
		    BufferedImage bi = ImageIO.read(is);
		   
		   instance.setDatapath("C:/Program Files (x86)/Tesseract-OCR/tessdata");
		    //instance.setDatapath("D:/Tesseract-Build/tesseract-ocr/tessdata");
		    String result;
			try {
				result = instance.doOCR(bi);
			
			
				 System.err.println(result);
				 response.setContentType("text/html;charset=utf-8");
				 response.getWriter().write(result);
			} catch (TesseractException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		   
		
//		FileOutputStream fos = new FileOutputStream("d:\\a.png");
//
//		byte[] b = new byte[1024];
//
//		while((is.read(b)) != -1){
//
//		fos.write(b);
//
//		}

		is.close();

		//fos.close();
		

		
	}

}
