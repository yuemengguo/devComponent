package com.gym.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionTest {
	@RequestMapping("/index.html")
	public String getIndex(HttpServletRequest request) throws Exception {
//		return "exceptions/index";
		throw new Exception("有问题");
	}
	
	@RequestMapping(value="/getJson.html",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String getJson(String callback) throws BusinessException {
//		JSONObject json = new JSONObject();
//		System.out.println(2/0);
//		json.put("success", true);
//		return callback+"("+json.toString()+")";
		throw new BusinessException(BusinessException.ServerError.internal_error,"服务器内部错误");
	}
}
