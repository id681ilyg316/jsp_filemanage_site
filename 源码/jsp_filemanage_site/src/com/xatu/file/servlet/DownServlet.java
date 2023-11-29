package com.xatu.file.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xatu.file.service.KlFileService;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DownServlet
 */
@WebServlet("/DownServlet")
public class DownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�õ�Ҫ���ص��ļ���
		//response.setContentType("application/x-download");
	     if(request.getSession().getAttribute("username")==null){
		      request.setAttribute("message", "�������½");
		      request.getRequestDispatcher("/jsp/page/Message.jsp").forward(request, response);  
		      return;
	     }
		String fileName=request.getParameter("filename");
		//�õ���ȡ·
		KlFileService kService= new KlFileService();
		JSONObject json = kService.getFileByUuid(fileName);
		
		fileName = new String(fileName.getBytes("UTF-8"),"utf-8");
		String path=json.getString("klfile_savepath");
		//�õ�Ҫ���ص��ļ�
   		File file=new File(path+"\\"+fileName);
		//����ļ�����
		if(!file.exists()){
			request.setAttribute("message", "��Ҫ���ص������Ѿ���ɾ�������Ǽ�¼���ڣ�����ϵ����Ա");
			request.getRequestDispatcher("/jsp/page/Message.jsp").forward(request, response);
			return;
		}
		//�����ļ���
		String realName=fileName.substring(fileName.indexOf("_")+1);
		//������Ӧͷ������������������ظ��ļ�
		response.setHeader("Content-Disposition", "attachment;filename="+ java.net.URLEncoder.encode(realName, "UTF-8"));
		//��ȡҪ���ص��ļ�,���浽������
		FileInputStream in = new FileInputStream(path+"\\"+fileName);
		//���������
		OutputStream out = response.getOutputStream();
		//����������
		byte buffer[]= new byte[1024];
		int len=0;
		//ѭ�����������е����ݶ�ȡ������������
		while((len=in.read(buffer))>0){
			//��������������ݵ��������ʵ���ļ�����
			out.write(buffer,0,len);
		}
	//	out.flush();
		in.close();
		out.close();
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
