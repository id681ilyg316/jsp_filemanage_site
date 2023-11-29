package com.xatu.file.service;


import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.xatu.file.bean.Student;
import com.xatu.file.bean.Student;
import com.xatu.file.dao.StudentDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentService {
	StudentDao dao = new StudentDao();
	/**
	 * �û�ע��
	 * @param Student
	 * @return json
	 *
	 * */

	public JSONObject register(Student Student){
		JSONObject json= new JSONObject();
		Integer result=dao.insertStudent(Student);
		json.put("result", result);
		return json;		
	}
	/**
	 * �����û�
	 * @param Student
	 * */

	public JSONObject updateStudent(Student Student){
		JSONObject json= new JSONObject();
		Integer result=dao.updateStudent(Student);
		json.put("result", result);
		return json;		
	}
	/**
	 * ɾ���û�
	 * @param Student_number
	 * */

	public JSONObject deleteStudent(String Student_number){
		JSONObject json= new JSONObject();
		Integer result=dao.deleteStudent(Student_number);
		json.put("result", result);
		return json;		
	}
	/**
	 * ͨ��id��ȡStudent����
	 * @param  Student_number
	 * @return json
	 * */
	public JSONObject getStudentById(String Student_number){
		JSONObject json= new JSONObject();
		Student result=dao.getStudentById(Student_number);
		if(result!=null)
		{ 
			json.put("student_password", result.getStudent_password());
			json.put("result", result.getStudent_number());
			json.put("student_phone", result.getStudent_phone());	
			json.put("student_email", result.getStudent_email());
			json.put("student_name", result.getStudent_name());
		}
		return json;
	}
	/**
	 * �û���½��֤
	 * @param Student
	 * @return json
	 * 
	 * */
	public JSONObject login(Student Student){
		JSONObject json= new JSONObject();
		Integer result=dao.login(Student);
		json.put("result", result);
		return json;
	}
	/**
	 * �û���½��֤
	 * @param request
	 * @return json
	 * 
	 * */
	public JSONObject login(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String Student_id= request.getParameter("userId");
		String password=request.getParameter("password");
		
		Student Student = new Student();
		Student.setStudent_number(Student_id);;
		Student.setStudent_password(password);
		
		Integer result = dao.login(Student);
		json.put("result", result);
		
		return json;
	}
	/**
	 * �û�ע��
	 * @param request
	 * @return json
	 *
	 * */
	public JSONObject register(HttpServletRequest request){
		String student_number=(String)request.getParameter("snumber");
		String student_name=(String)request.getParameter("sname");
		String student_password=(String)request.getParameter("spassword1");
		String student_email=(String)request.getParameter("semail");
		String student_phone=(String)request.getParameter("sphone");
		String student_birthday=(String)request.getParameter("sbrithday");
		String student_QQ=(String)request.getParameter("sqq");
		String student_weChat= request.getParameter("swechat");
		String student_academy =request.getParameter("sacademy");
		String[] student_strongPoint=request.getParameterValues("studentlike");
	
		Student student = new Student();
		student.setStudent_number(student_number);
		student.setStudent_name(student_name);
		student.setStudent_password(student_password);
		student.setStudent_email(student_email);
		student.setStudent_phone(student_phone);
		student.setStudent_birthday(strToDate(student_birthday));
		student.setStudent_QQ(student_QQ);
		student.setStudent_weChat(student_weChat);
		student.setStudent_academy(student_academy);
		student.setStudent_strongPoint(student_strongPoint.toString());
		
		int result = dao.insertStudent(student);
		JSONObject json= new JSONObject();
		json.put("result", result);

		return json;
	}
	//�ַ���תDate
	 private static java.sql.Date strToDate(String strDate) {  
	        String str = strDate;  
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");  
	        java.util.Date d = null;  
	        try {  
	            d = format.parse(str);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        java.sql.Date date = new java.sql.Date(d.getTime());  
	        return date;  
	    }
	 /**
	  * ��ҳ��ѯ
	  * @param StudentObj
	  * @param pageSize
	  * @param pageIndex
	  * @param orderByName
	  * @param orderByRule
	  * @return
	  */
	 public JSONArray getStudentListByPage(JSONObject StudentObj,Integer pageSize,Integer pageIndex,String orderByName,
				String orderByRule){
		 return dao.getStudentListByPage(StudentObj, pageSize, pageIndex, orderByName, orderByRule);
	 }
	 /**
	  * dwr���ѧ��
	  * @param Student
	  * @return
	  */
		public JSONObject addStudent(Student Student){
			JSONObject json= new JSONObject();
			Integer result=dao.insertStudent(Student);
			json.put("result", result);
			return json;		
		}
		
}
