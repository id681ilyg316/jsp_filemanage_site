package com.xatu.file.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xatu.file.bean.Teacher;
import com.xatu.file.bean.Teacher;
import com.xatu.file.bean.Teacher;
import com.xatu.file.bean.Teacher;
import com.xatu.file.common.db.DB;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TeacherDao {
	/**
	 * �����û�ID��ȡ�û���Ϣ
	 * @param Teacher_number
	 * */
	public Teacher getTeacherById(String Teacher_number){
		String sql="select*from teacher where teacher_number=?";
		Connection conn = DB.getconn();
		PreparedStatement stmt  = DB.getStatement(conn, sql);
		ResultSet rs=null;
		Teacher Teacher=null;
		try{
			stmt.setString(1, Teacher_number);
			rs=stmt.executeQuery();
			while(rs.next()){
				Teacher= new Teacher();
				getTeacherFromRs(Teacher,rs);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Teacher;
	}
	/**
	 * ��rs��ȡTeacher����
	 * @param  Teacher rs
	 * 
	 * */
	private void getTeacherFromRs(Teacher Teacher,ResultSet rs) throws SQLException{
	
		Teacher.setTeacher_number(rs.getString("teacher_number"));
		Teacher.setTeacher_password(rs.getString("teacher_password"));
		Teacher.setTeacher_name(rs.getString("teacher_name"));
		Teacher.setTeacher_title(rs.getString("teacher_title"));
		Teacher.setTeacher_academy(rs.getString("teacher_academy"));
		Teacher.setTeacher_duty(rs.getString("teacher_duty"));
		Teacher.setTeacher_department(rs.getString("teacher_department"));
		Teacher.setTeacher_telephone(rs.getString("teacher_telephone"));
		Teacher.setTeacher_phone(rs.getString("teacher_phone"));
		Teacher.setTeacher_office(rs.getString("teacher_office"));
		
	}
	/**
	 * �û���¼У��
	 * @param Teacher_number
	 * */
	public Integer login(Teacher Teacher){
		Integer result=0;//��½����(0:�쳣;1:�ɹ�;-1���û�������;-2:�������)
		String sql="select*from teacher where teacher_number= ? and teacher_password= ?";
		Connection conn = DB.getconn();
		PreparedStatement stmt  = DB.getStatement(conn, sql);
		ResultSet rs=null;

		List<Teacher> list= new ArrayList<Teacher>();
		try{
			stmt.setString(1, Teacher.getTeacher_number());
			stmt.setString(2, Teacher.getTeacher_password());
			rs=stmt.executeQuery();
			while(rs.next()){
				Teacher= new Teacher();
				getTeacherFromRs(Teacher,rs);
				list.add(Teacher);
			}
			if(list.size()==1){
				result=1;//�û���������У��ɹ�
			}else{
				Teacher  isTeacher= getTeacherById(Teacher.getTeacher_number());
				if(isTeacher==null){ 
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
	 * ɾ����ʦ
	 * @param teacher_number �û�id
	 * @return ��������
	 * */
	public Integer deleteTeacher(String teacher_number){
		Integer result=0;
		String deleteSQL="delete teacher where teacher_number=?";
		//�����ݿ�
	 Connection conn = DB.getconn();
	 PreparedStatement stmt= DB.getStatement(conn, deleteSQL);

	 try{
		//��stmt����ֵ
		stmt.setString(1, teacher_number);
		//���ز�������
		result=stmt.executeUpdate();
		
	}catch(SQLException  e){
		e.printStackTrace();
	}
	return result;
	}
	/**
	 * ������ʦ
	 * 
	 * 
	 * */
	public Integer updateTeacher(Teacher Teacher){
		Integer result=0;
		String updateSQL="update teacher set teacher_name=?teacher_phone=?,teacher_telephone=?,teacher_email=?,teacher_academy=?,teacher_honor=?where Teacher_number=?";
	 Connection conn = DB.getconn();
	PreparedStatement stmt= DB.getStatement(conn, updateSQL);

	try{
		stmt.setString(1, Teacher.getTeacher_name()==null?"":Teacher.getTeacher_name());
		stmt.setString(2, Teacher.getTeacher_phone()==null?"":Teacher.getTeacher_phone());
		stmt.setString(3, Teacher.getTeacher_phone()==null?"":Teacher.getTeacher_phone());
		stmt.setString(4, Teacher.getTeacher_telephone()==null?"":Teacher.getTeacher_telephone());
		stmt.setString(5, Teacher.getTeacher_email()==null?"":Teacher.getTeacher_email());
		stmt.setString(6, Teacher.getTeacher_academy()==null?"":Teacher.getTeacher_academy());
		stmt.setString(7, Teacher.getTeacher_honor()==null?"":Teacher.getTeacher_honor());
		
		stmt.setString(8, Teacher.getTeacher_number());
		
		result=stmt.executeUpdate();
		
	}catch(SQLException  e){
		e.printStackTrace();
	}
	return result;
	}
	/**
	 * ������ʦ
	 * 
	 * */
	public Integer insertTeacher(Teacher Teacher){

		String insertSQL="insert into teacher(teacher_number,teacher_password,teacher_name,teacher_title,teacher_duty,teacher_department,teacher_office,teacher_phone,teacher_telephone,teacher_email,teacher_academy,teacher_honor,teacher_loginTime,teacher_lastTime)values"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 Connection conn = DB.getconn();
	PreparedStatement stmt= DB.getStatement(conn, insertSQL);
	Integer result=null;
	try{
		
		stmt.setString(1, Teacher.getTeacher_number());
		stmt.setString(2, Teacher.getTeacher_password());
		stmt.setString(3,Teacher.getTeacher_name());
		stmt.setString(4, Teacher.getTeacher_title()==null?"":Teacher.getTeacher_title());
		stmt.setString(5, Teacher.getTeacher_duty()==null?"":Teacher.getTeacher_duty());
		stmt.setString(6, Teacher.getTeacher_department()==null?"":Teacher.getTeacher_department());
		stmt.setString(7, Teacher.getTeacher_office()==null?"":Teacher.getTeacher_office());
		stmt.setString(8, Teacher.getTeacher_phone());
		stmt.setString(9, Teacher.getTeacher_telephone()==null?"":Teacher.getTeacher_telephone());
		stmt.setString(10, Teacher.getTeacher_email());
		stmt.setString(11, Teacher.getTeacher_academy()==null?"":Teacher.getTeacher_academy());
		stmt.setString(12, Teacher.getTeacher_honor()==null?"":Teacher.getTeacher_honor());
		stmt.setInt(13, Teacher.getTeacher_loginTime()==0?0:Teacher.getTeacher_loginTime());
		stmt.setDate(14, new Date(0));
//		stmt.setString(15, Teacher.getRole()==null?"1":Teacher.getRole());
		
		result=stmt.executeUpdate();
		
	}catch(SQLException  e){
		e.printStackTrace();
	}
	return result;
	}
	/**
	 * ��ҳ��ѯ��ʦ
	 * @param TeacherObj
	 * @param pageSize
	 * @param pageIndex
	 * @param orderByName
	 * @param orderByRule
	 * @return
	 */
	public JSONArray getTeacherListByPage(JSONObject TeacherObj,Integer pageSize,Integer pageIndex,String orderByName,
			String orderByRule){
	//	List<Role> list= new ArrayList();
		
		JSONArray jsonArray = new JSONArray();
		StringBuffer sql=new StringBuffer("SELECT *FROM Teacher where 1=1 ");
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
		json.put("teacher_number", rs.getString("teacher_number"));
		json.put("teacher_name", rs.getString("teacher_name"));
		json.put("teacher_email", rs.getString("teacher_email"));
		json.put("teacher_phone", rs.getString("teacher_phone"));
		jsonArray.add(json);
		}
		}catch(SQLException e){
		e.printStackTrace();
		}
		return jsonArray;
		
		}
}
