package com.xatu.file.service;

import java.sql.SQLException;

import com.xatu.file.bean.KlFile;
import com.xatu.file.dao.KlfileDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KlFileService {
	 KlfileDao dao= new KlfileDao();
	/**
	 * �ļ��ϴ�
	 * @throws SQLException 
	 * 
	 * */
	 
	public JSONObject addFile(KlFile flfile){
		JSONObject json= new JSONObject();
		Integer result = dao.addFile(flfile);
		json.put("result", result);
		return json;
	}
	/**
	 * ͨ��uuidɾ���ļ�
	 * Ҫ��ɾ���ݿ⣬��ɾ�ļ�(�˹���ûд�꣡)
	 * **/
	public JSONObject deleteFile(String klfile_uuid){
		JSONObject json= new JSONObject();
		Integer result = dao.deleteFile(klfile_uuid);
		json.put("result", result);
		return json;
	}
	/**
	 * ͨ��uuid�����ļ�������
	 * **/
	public JSONObject updateFile(KlFile klfile){
		JSONObject json= new JSONObject();
		Integer result = dao.updateFile(klfile);
		json.put("result", result);
		return json;	
	}
	/***
	 * ͨ���ļ������ļ�����ģ�����ң�
	 * **/
	public JSONObject getfileByName(String Name,String description){
		JSONObject json = new JSONObject();
		KlFile  result = dao.getfileByName(Name, description);
		if(result!=null) {
			json.put("name", result.getKlFile_fileName());
			json.put("author", result.getKlFile_author());
			json.put("descript", result.getKlfile_describtion());
		}
		return json;
	}
	public void updateDowns(Integer i,String klfile_uuid){
		dao.updateDowns(i, klfile_uuid);
	}	
	/**
	 * �����ѯ
	 * @param FileObj
	 * @param file_class
	 * @param pageSize
	 * @param pageIndex
	 * @param orderByName
	 * @param orderByRule
	 * @return
	 */
	public JSONArray getFileListByPageC(JSONObject FileObj,String file_class,Integer pageSize,Integer pageIndex,String orderByName,
			String orderByRule){
		return dao.getFileListByPageC(FileObj, file_class, pageSize, pageIndex, orderByName, orderByRule);
	}
//��������Ͳ�ѯ
	public JSONArray getFileListByPageCT(JSONObject FileObj,String file_class,String file_type,Integer pageSize,Integer pageIndex,String orderByName,
			String orderByRule){
		return dao.getFileListByPageCT(FileObj, file_class,file_type, pageSize, pageIndex, orderByName, orderByRule);
	}
	//ȫ����ѯ
	public  JSONArray getFileListByPage(JSONObject FileObj,Integer pageSize,Integer pageIndex,String orderByName,
			String orderByRule){
		return dao.getFileListByPage(FileObj, pageSize, pageIndex, orderByName, orderByRule);
	}
	public  JSONArray getFileListByPageND(JSONObject FileObj,String file_name,Integer pageSize,Integer pageIndex,String orderByName,
			String orderByRule){
		return dao.getFileListByPageND(FileObj, file_name, pageSize, pageIndex, orderByName, orderByRule);
	}
	public JSONObject getFileByUuid(String klfile_uuid){
		JSONObject json= new JSONObject();
		KlFile result=dao.getFileByUuid(klfile_uuid);
		if(result!=null) json.put("klfile_savepath", result.getKlfile_savepath());
		return json;
	}
	 
}
