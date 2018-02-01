package com.gym.db;

import java.util.List;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class DataService {
	@Autowired
	private DataSource dataSouce;
	
	/**
	 * 加载jdbc
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSouce);
	}
	
	/**
	 * 单条记录查询
	 * @param sql
	 * @param args
	 * @return
	 */
	public JSONObject toJSONObject(String sql, Object ...args) {
		JSONObject json = new JSONObject();
		List<?> list = getJdbcTemplate().queryForList(sql, args);
		if(list.size() == 0) {
			return json;
		}else {
			return new JSONObject(list.get(0));
		}
	}
	
	/**
	 * 多条数据查询
	 * @param sql
	 * @param args
	 * @return
	 */
	public JSONArray toJSONArray(String sql, Object ...args) {
		JSONArray jsonArray = new JSONArray();
		List<?> list = getJdbcTemplate().queryForList(sql,args);
		if(list.size() == 0) {
			return jsonArray;
		}else{
			return new JSONArray(list);
		}
	}
	
	/**
	 * 加载配置文件
	 * @param tableName
	 * @param name
	 * @return
	 */
	public String getConfig(String tableName, String name) {
		JSONObject json = new JSONObject();
		json = toJSONObject("select value from "+tableName+" where name= "+name);
		String returnName = json.optString(name,null);
		return returnName;
	}
}
