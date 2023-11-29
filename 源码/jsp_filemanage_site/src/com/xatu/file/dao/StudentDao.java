package com.xatu.file.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xatu.file.bean.Student;
import com.xatu.file.bean.Student;
import com.xatu.file.common.db.DB;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class StudentDao {
	/**
	 * �����û�ID��ȡ�û���Ϣ
	 * @param Student_number
	 * */
	public Student getStudentById(String Student_number){
		String sql="select*from student where student_number=?";
		Connection conn = DB.getconn();
		PreparedStatement stmt  = DB.getStatement(conn, sql);
		ResultSet rs=null;
		Student  Student=null;
		try{
			stmt.setString(1, Student_number);
			rs=stmt.executeQuery();
			while(rs.next()){
				Student= new Student();
				getStudentFromRs(Student,rs);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Student;
	}
	/**
	 * ��rs��ȡstudent����
	 * @param  student rs
	 * 
	 * */
	private void getStudentFromRs(Student Student,ResultSet rs) throws SQLException{
		Student.setStudent_number(rs.getString("student_number"));
		Student.setStudent_password(rs.getString("student_password"));
		Student.setStudent_name(rs.getString("student_name"));
		Student.setStudent_phone(rs.getString("student_phone"));
		Student.setStudent_email(rs.getString("student_email"));
		Student.setStudent_QQ(rs.getString("student_QQ"));
		Student.setStudent_weChat(rs.getString("student_weChat"));
	//	Student.setStudent_birthday(rs.getDate("student_brithday"));
		Student.setStudent_class(rs.getString("student_class"));
		Student.setStudent_academy(rs.getString("student_academy"));
	}
	/**
	 * �û���¼У��
	 * @param Student_number
	 * */
	public Integer login(Student Student){
		Integer result=0;//��½����(0:�쳣;1:�ɹ�;-1���û�������;-2:�������)
		String sql="select*from student where student_number=? and student_password=?";
		Connection conn = DB.getconn();
		PreparedStatement stmt  = DB.getStatement(conn, sql);
		ResultSet rs=null;

		List<Student> list= new ArrayList<Student>();
		try{
			stmt.setString(1, Student.getStudent_number());
			stmt.setString(2, Student.getStudent_password());
			rs=stmt.executeQuery();
			while(rs.next()){
				Student= new Student();
				getStudentFromRs(Student,rs);
				list.add(Student);
			}
			if(list.size()==1){
				result=1;//�û���������У��ɹ�
			}else{
				Student  isStudent= getStudentById(Student.getStudent_number());
				if(isStudent==null){ 
					result=-1;//�û�������
				}else{
					result=-2;//�û������������
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * �����û�
	 * 
	 * */
	public Integer insertStudent(Student Student){

		String insertSQL="insert into student(student_number,student_password,student_name,student_phone,student_email,student_QQ,student_weChat,student_brithday,student_class,student_academy,student_strongPoint,student_loginTime,student_lastTime)values"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 Connection conn = DB.getconn();
	PreparedStatement stmt= DB.getStatement(conn, insertSQL);
	Integer result=null;
	try{
		
		stmt.setString(1, Student.getStudent_number());
		stmt.setString(2, Student.getStudent_password());
		stmt.setString(3,Student.getStudent_name());
		stmt.setString(4, Student.getStudent_phone());
		stmt.setString(5, Student.getStudent_email());
		stmt.setString(6, Student.getStudent_QQ()==null?" ":Student.getStudent_QQ());
		stmt.setString(7, Student.getStudent_weChat()==null?"":Student.getStudent_weChat());
		stmt.setDate(8, Student.getStudent_birthday()==null?new Date(0):Student.getStudent_birthday());
		stmt.setString(9, Student.getStudent_class()==null?"000":Student.getStudent_class());
		stmt.setString(10, Student.getStudent_academy()==null?"�����ѧԺ":Student.getStudent_academy());
		stmt.setString(11, Student.getStudent_strongPoint()==null?"000":Student.getStudent_strongPoint());	
		stmt.setInt(12, Student.getStudent_loginTime()==0?0:Student.getStudent_loginTime());
		stmt.setDate(13, new Date(0));
//		stmt.setString(14, Student.getRole()==null?"1":Student.getRole());
		
		result=stmt.executeUpdate();
		
	}catch(SQLException  e){
		e.printStackTrace();
	}
	return result;
	}
	/**
	 * ����ѧ��
	 * 
	 * 
	 * */
	public Integer updateStudent(Student Student){
		Integer result=0;
		String updateSQL="update student set student_name=?student_phone=?,student_email=?,student_QQ=?,student_weChat=?,student_brithday=?,student_class=?,student_academy=?,student_strongPoint=? where student_number=?";
	 Connection conn = DB.getconn();
	PreparedStatement stmt= DB.getStatement(conn, updateSQL);

	try{
		stmt.setString(1, Student.getStudent_name()==null?"":Student.getStudent_name());
		stmt.setString(2, Student.getStudent_phone()==null?"":Student.getStudent_phone());
		stmt.setString(3, Student.getStudent_email()==null?"":Student.getStudent_email());
		stmt.setString(4, Student.getStudent_QQ()==null?"":Student.getStudent_QQ());
		stmt.setString(5, Student.getStudent_weChat()==null?"":Student.getStudent_weChat());
		stmt.setDate(6, Student.getStudent_birthday()==null?new Date(0):Student.getStudent_birthday());
		stmt.setString(7, Student.getStudent_class()==null?"":Student.getStudent_class());
		stmt.setString(8, Student.getStudent_academy()==null?"":Student.getStudent_academy());
		stmt.setString(9, Student.getStudent_strongPoint()==null?"":Student.getStudent_strongPoint());
		
		stmt.setString(10, Student.getStudent_number());
		
		result=stmt.executeUpdate();
		
	}catch(SQLException  e){
		e.printStackTrace();
	}
	return result;
	}
	/**
	 * ɾ���û�
	 * @param student_number �û�id
	 * @return ��������
	 * */
	public Integer deleteStudent(String student_number){
		Integer result=0;
		String deleteSQL="delete student where student_number=?";
		//�����ݿ�
	 Connection conn = DB.getconn();
	 PreparedStatement stmt= DB.getStatement(conn, deleteSQL);

	 try{
		//��stmt����ֵ
		stmt.setString(1, student_number);
		//���ز�������
		result=stmt.executeUpdate();
		
	}catch(SQLException  e){
		e.printStackTrace();
	}
	return result;
	}
	/**
	 * ��ҳ��ѯѧ��
	 * @param StudentObj
	 * @param pageSize
	 * @param pageIndex
	 * @param orderByName
	 * @param orderByRule
	 * @return
	 */
	public static JSONArray getStudentListByPage(JSONObject StudentObj,Integer pageSize,Integer pageIndex,String orderByName,
			String orderByRule){
	//	List<Role> list= new ArrayList();
		
		JSONArray jsonArray = new JSONArray();
		StringBuffer sql=new StringBuffer("SELECT *FROM student where 1=1 ");
	//	sql.append( "AND klfile_documentClassification LIKE'%"+file_class+"%'");
		sql.append("order by "+orderByName+" "+orderByRule);

		//���ݴ���ķ�ҳ�����ͷ�ҳ��ʾ�����з�ҳ��ѯ
		if(pageSize>0){
		Integer countFrom = pageSize*(pageIndex-1);
		Integer countTo=pageSize*pageIndex;
		sql.append(" limit "+countFrom+","+countTo);//ָ�����ݶε�����
		}
		//����ָ�����������������ѯ
		
		//System.out.println(sql);
		Connection conn= DB.getconn();
		PreparedStatement stmt=  DB.getStatement(conn, sql.toString());
		ResultSet rs=null;		
		//Role Role;
		System.out.println(sql);
		try{
		rs=stmt.executeQuery();
		while(rs.next()){
		JSONObject json= new JSONObject();
		/**
		* ����json��ʽ
		*/
		json.put("student_number", rs.getString("student_number"));
		json.put("student_name", rs.getString("student_name"));
		json.put("student_email", rs.getString("student_email"));
		json.put("student_phone", rs.getString("student_phone"));
		jsonArray.add(json);
		}
		}catch(SQLException e){
		e.printStackTrace();
		}
		return jsonArray;
		
		}

}
