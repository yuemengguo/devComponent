package com.gym.action;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.entity.TestModel;

@Controller
public class Test extends BaseAction{
	public static Logger log = Logger.getLogger("infofile");
	
	@RequestMapping(value="/test", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String test(HttpSession session){
		JSONObject json = new JSONObject();
		JSONArray jsonArray =  dataService.toJSONArray("select * from t_user");
		json.put("itms", jsonArray);
		return json.toString();
	}
	
	@RequestMapping(value="/test2", method= {RequestMethod.GET})
	public String test2() {
		return "test/index";
	}
	
	@RequestMapping(value="/test3", method = {RequestMethod.GET,RequestMethod.POST})
	public String test(HttpSession session, TestModel tt,Model mp){
		JSONObject json = new JSONObject();
		json.put("tt", tt);
		mp.addAttribute("mess",json.toString());
		return "test/index";
	}
	
	@RequestMapping(value="/test4", method= {RequestMethod.GET})
	public String test4() {
		return "test/form";
	}
}
