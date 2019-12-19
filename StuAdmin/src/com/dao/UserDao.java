package com.dao;

import java.util.List;
import java.util.Map;

public class UserDao extends JdbcCurd{
	
	
	/**
	 * 判断是否存在该用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean isUser(String user) {
		List<Map<String, Object>> list=select("SELECT COUNT(*) AS size FROM user WHERE user='"+user+"'");
		if(list!=null&&(Long)(list.get(0).get("size"))>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @param user
	 * @return
	 */
	public Map<String,Object> getUserInfo(String user){
		List<Map<String, Object>> list=select("SELECT * FROM user WHERE user='"+user+"'");
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public boolean addUser(String user,String pass) {
		int i=insert_update_delete("INSERT INTO user (user,pass) VALUES ('"+user+"','"+pass+"')", true);
		if(i>0) {
			return true;
		}
		return false;
	}
	
}
