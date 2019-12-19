package com.dao;

import java.util.List;
import java.util.Map;

public class StuDao extends JdbcCurd{
	
	/**
	 * 获取所有学生
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getStuList() {
		List<Map<String,Object>> list=select("SELECT * FROM stu");
		return list;
	}
	
	/**
	 * 获取单个学生
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getStu(String id){
		List<Map<String,Object>> list=select("SELECT * FROM stu WHERE id='"+id+"'");
		if(list.size()==0) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 *  	删除学生
	 * 
	 * @param id
	 * @return
	 */
	public boolean delStu(String id) {
		int i=insert_update_delete("DELETE FROM stu WHERE id='"+id+"'", true);
		if(i==1) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 	添加学生
	 * @param map
	 * @return
	 */
	public boolean addStu(Map<String,String> map) {
		int i=insert_update_delete("INSERT INTO stu (id,name,sex,age,phone,qq,cls_id) VALUES ('"+map.get("id")+"','"+map.get("name")+"','"+map.get("sex")+"','"+map.get("age")+"','"+map.get("phone")+"','"+map.get("qq")+"','"+map.get("cls_id")+"')", true);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 	修改学生信息
	 * @param map
	 * @return
	 */
	public boolean upStu(Map<String,String> map) {
		int i=insert_update_delete("UPDATE stu SET id='"+map.get("id")+"',name='"+map.get("name")+"',sex='"+map.get("sex")+"',age='"+map.get("age")+"',phone='"+map.get("phone")+"',qq='"+map.get("qq")+"',cls_id='"+map.get("cls_id")+"' WHERE id='"+map.get("id_old")+"'", true);
		if(i>0) {
			return true;
		}
		return false;
	}
	
}
