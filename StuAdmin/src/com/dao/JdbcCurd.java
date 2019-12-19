package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcCurd {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true&useAffectedRows=true";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASS = "123456";
	private Connection conn = null;
	private Statement stmt = null;

	/* 连接数据库.. */
	public void init() {
		if (this.conn == null) {
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
				stmt = conn.createStatement();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// jdbc驱动加载失败
				System.out.println("jdbc驱动加载失败");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// 数据库连接失败
				System.out.println("数据库连接失败");
			}
		}
	}

	/**
	 * 查询完数据不关闭
	 * 
	 * @param sql
	 * @param isClose true不关闭
	 * @return
	 */
	public List<Map<String, Object>> select(String sql, boolean isClose) {
		if (isClose) {
			List<Map<String, Object>> list = selectStart(sql);
			return list;
		} else {
			List<Map<String, Object>> list = select(sql);
			return list;
		}
	}

	/**
	 * 数据库查询 默认完毕关闭数据库
	 * 
	 * @param sql sql查询语句
	 * @return
	 */
	public List<Map<String, Object>> select(String sql) {
		List<Map<String, Object>> list = selectStart(sql);
		close(); // 关闭连接
		return list;
	}

	/**
	 * 数据库查询
	 * 
	 * @param sql sql查询语句
	 * @return
	 */
	private List<Map<String, Object>> selectStart(String sql) {
		try {
			init();
			ResultSet rs = this.stmt.executeQuery(sql);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <=rsmd.getColumnCount(); i++) {
					String key = rsmd.getColumnLabel(i);
					map.put(key, rs.getObject(key));
				}
				list.add(map);
			}
			rs.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 数据库更新 /添加 /删除 操作
	 * 
	 * @param sql     sql查询语句
	 * @param isClose 操作完毕后是否关闭连接
	 * @return 更新条数
	 */
	public int insert_update_delete(String sql, boolean isClose) {
		try {
			init();
			System.out.println("连接："+conn+stmt);
			int i = this.stmt.executeUpdate(sql);
			System.out.println(i);
			if (isClose) {
				close();
			}
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 关闭连接
	 * 
	 * @throws SQLException
	 */
	public void close() {
		try {
			if (this.conn != null) {
				conn.close();
				conn=null;
			}
			if (this.stmt != null) {
				stmt.close();
				stmt=null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
