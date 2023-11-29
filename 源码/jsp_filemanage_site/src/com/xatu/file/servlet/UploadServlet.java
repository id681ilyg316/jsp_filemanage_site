package com.xatu.file.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xatu.file.bean.KlFile;
import com.xatu.file.service.KlFileService;


import net.sf.json.JSONObject;

/**
 * Servlet implementation class uploadServlet
 */
@WebServlet("/uploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpServletRequest aa;
	String filename;  
	String fileExtName;
	String saveFilename;
	String realSavePath;
	String auth;
	long FileSize;//��λΪB
	StringBuffer fileclass=new StringBuffer(" ");
	StringBuffer description=new StringBuffer(" ");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
	     if(request.getSession().getAttribute("username")==null){
		      request.setAttribute("message", "�����½");
		      request.getRequestDispatcher("/jsp/page/Login.jsp").forward(request, response);    	 
	     }
	     auth=request.getSession().getAttribute("username").toString();
	     String savePath = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(0,Thread.currentThread().getContextClassLoader().getResource("").getPath().length()-16)
+"/upload";
	     //�ϴ�ʱ���ɵ���ʱ�ļ�����Ŀ¼
	     String tempPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(0,Thread.currentThread().getContextClassLoader().getResource("").getPath().length()-16)
	    		 +"/temp";
	     File tmpFile = new File(tempPath);
	     if (!tmpFile.exists()) {
	    	 //������ʱĿ¼
	    	 tmpFile.mkdir();
	     }

	     //��Ϣ��ʾ
	     String message = "�ļ��ϴ��ɹ�";
	     try{
	      //ʹ��Apache(�ϴ����)�ļ��ϴ���������ļ��ϴ����裺
	      //1������һ��DiskFileItemFactory����
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      //���ù����Ļ������Ĵ�С�����ϴ����ļ���С�����������Ĵ�Сʱ���ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼���С�
	      factory.setSizeThreshold(1024*100);//���û������Ĵ�СΪ100KB�������ָ������ô�������Ĵ�СĬ����10KB
	      //�����ϴ�ʱ���ɵ���ʱ�ļ��ı���Ŀ¼
	      factory.setRepository(tmpFile);
	      //2������һ���ļ��ϴ�������
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      //�����ļ��ϴ�����
	      upload.setProgressListener(new ProgressListener(){
	       public void update(long pBytesRead, long pContentLength, int arg2) {
	    	   FileSize=pContentLength;
	//        System.out.println("�ļ���СΪ��" + pContentLength + ",��ǰ�Ѵ���" + pBytesRead);
	       }
	      });
	      //����ϴ��ļ�������������
	      upload.setHeaderEncoding("UTF-8"); 
	      //3���ж��ύ�����������Ƿ����ϴ���������
	      if(!ServletFileUpload.isMultipartContent(request)){
	       //���մ�ͳ��ʽ��ȡ����
	       return;
	      }
	       
	      //�����ϴ������ļ��Ĵ�С�����ֵ��Ŀǰ������Ϊ1024*1024�ֽڣ�Ҳ����1MB
	      upload.setFileSizeMax(1024*1024*1024);
	      //�����ϴ��ļ����������ֵ�����ֵ=ͬʱ�ϴ��Ķ���ļ��Ĵ�С�����ֵ�ĺͣ�Ŀǰ����Ϊ10MB
	      upload.setSizeMax(1024*1024*10*1024);
	      //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
	      List<FileItem> list = upload.parseRequest(request);
	      for(FileItem item : list){
	       //���fileitem�з�װ������ͨ�����������
	       if(item.isFormField()){
	        String name = item.getFieldName();
	        //�����ͨ����������ݵ�������������
	        String value = item.getString("UTF-8");
	        //value = new String(value.getBytes("UTF-8"),"UTF-8");
	//        System.out.println(name + "=" + value);
	        if(name.equals("class")){
	        	fileclass.append(value);
	        	
	        }else if(name.equals("description")){
	        	description.append(value);
	        }
	       

	       }else{//���fileitem�з�װ�����ϴ��ļ�
	        //�õ��ϴ����ļ����ƣ�
	        filename = item.getName();
	        System.out.println(filename);
	        if(filename==null || filename.trim().equals("")){
	         continue;
	        }

	        //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺 c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
	        //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
	        filename = filename.substring(filename.lastIndexOf("/")+1);
	        //�õ��ϴ��ļ�����չ��
	        fileExtName = filename.substring(filename.lastIndexOf(".")+1);
	        //�����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
	    //    System.out.println("�ϴ����ļ�����չ���ǣ�"+fileExtName);
	        //��ȡitem�е��ϴ��ļ���������
	        InputStream in = item.getInputStream();
	        //�õ��ļ����������
	         saveFilename = makeFileName(filename);
	        //�õ��ļ��ı���Ŀ¼
	         realSavePath = makePath(saveFilename, savePath);
	    //     System.out.println(realSavePath);
	         File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(0,Thread.currentThread().getContextClassLoader().getResource("").getPath().length()-16)
	        		 +realSavePath);
	         if(!file.exists()) {
	        	 file.mkdirs();
	         }
	         file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(0,Thread.currentThread().getContextClassLoader().getResource("").getPath().length()-16)
	        		 +realSavePath + "/" + saveFilename);
	         if(!file.exists()) {
	        	 file.createNewFile();
	         }
	        //����һ���ļ������
	        FileOutputStream out = new FileOutputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(0,Thread.currentThread().getContextClassLoader().getResource("").getPath().length()-16)
+realSavePath + "/" + saveFilename);
	       //����һ��������
	        byte buffer[] = new byte[1024];
	        //�ж��������е������Ƿ��Ѿ�����ı�ʶ        
	        int len = 0;
	        //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
	        while((len=in.read(buffer))>0){
	         //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "/" + filename)����
	         out.write(buffer, 0, len);
	        }
	        //�ر�������
	        in.close();
	        //�ر������
	        out.close();        //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�        //       message = "�ļ��ϴ��ɹ���";
	       }
	      }
	  //    System.out.println(fileclass);
	 //     System.out.println(description); 
	     }catch (FileUploadBase.FileSizeLimitExceededException e) {
	      e.printStackTrace();
	      request.setAttribute("message", "�����ļ��������ֵ������");
	      request.getRequestDispatcher("/jsp/page/Message.jsp").forward(request, response);
	      return;
	     }catch (FileUploadBase.SizeLimitExceededException e) {
	      e.printStackTrace();
	      request.setAttribute("message", "�ϴ��ļ����ܵĴ�С�������Ƶ����ֵ������");
	      request.getRequestDispatcher("/jsp/page/Message.jsp").forward(request, response);
	      return;
	     }catch (Exception e) {
	      message= "�ļ��ϴ�ʧ�ܣ�";
	      e.printStackTrace();
	     }
	        KlFile flfile = new KlFile();
	        JSONObject json=addFile(flfile);
	        System.out.println(json.get("result"));
	     request.setAttribute("message",message);
	     request.getRequestDispatcher("/jsp/page/Message.jsp").forward(request, response);
	}

	/**
	  * @Method: makeFileName
	  * @Description: �����ϴ��ļ����ļ������ļ����ԣ�uuid+"_"+�ļ���ԭʼ����
	  * @Anthor:sangfei
	  * @param filename �ļ���ԭʼ����
	  * @return uuid+"_"+�ļ���ԭʼ����
	  */
	  private String makeFileName(String filename){ //2.jpg
	   //Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
		  // ȫ��Ψһ��ʶ�롣32λ���û�����ʶ+ʱ����ʾ���ɵ������ʶ��
	   return UUID.randomUUID().toString() + "_" + filename;
	  }
	/**
	  * Ϊ��ֹһ��Ŀ¼�������̫���ļ���Ҫʹ��hash�㷨��ɢ�洢
	  * @Method: makePath
	  * @Description: 
	  * @Anthor:sangfei
	  *
	  * @param filename �ļ�����Ҫ�����ļ������ɴ洢Ŀ¼
	  * @param savePath �ļ��洢·��
	  * @return �µĴ洢Ŀ¼
	  */
	 private String makePath(String filename,String savePath){
	   //�õ��ļ�����hashCode��ֵ���õ��ľ���filename����ַ����������ڴ��еĵ�ַ
	   int hashcode = filename.hashCode();
	   int dir1 = hashcode&0xf; //0--15
	   int dir2 = (hashcode&0xf0)>>4; //0-15
	   //�����µı���Ŀ¼
	   String dir = "/upload" + "/" + dir1 + "/" + dir2; //upload\2\3 upload\3\5
	   return dir;
	  }
	 private JSONObject addFile(KlFile flfile){
		 KlFileService KlFileService = new KlFileService();
		 HttpServletRequest request =null;
		// String author = (String)aa.getSession().getAttribute("userId");
		 flfile.setKlFile_fileName(filename);
		 flfile.setKlfile_describtion(description.toString());
		 flfile.setKlFile_documentClassification(fileclass.toString());
		 flfile.setKlfile_uuid(saveFilename);
		 flfile.setKlfile_savepath(realSavePath);
		 flfile.setKlFile_fileType(fileExtName);
		 flfile.setKlFile_fileSize(FileSize);
		 flfile.setKlFile_author(auth);
		 JSONObject json = KlFileService.addFile(flfile);
		 return json; 
	 }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	
		this.doGet(request, response);
	}
}
