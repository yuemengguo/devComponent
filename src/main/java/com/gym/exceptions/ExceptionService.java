package com.gym.exceptions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * spring mvc 异常拦截
 * @author lemon
 * 
 */
public class ExceptionService implements HandlerExceptionResolver{
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		JSONObject json = new JSONObject();
		// 判断是否为ajax请求
		boolean isAjax = request.getHeader("accept").indexOf("application/json")> -1 || 
				(request.getParameter("callback") != null && request.getHeader(
		                "accept").indexOf("application/json") == -1);
		if(!isAjax ) {
			//如果这里不是ajax请求的话，则以页面的形式返回，安全起见，只对业务异常可见
			json.put("success", false);
			if(exception instanceof BusinessException) {
				json.put("errorType", "BusinessExceptions");
				//请求出错,来自业务操作
				json.put("code", HttpStatus.BAD_REQUEST.value());
			}else {
				 //服务器内部错误
				json.put("errorType", "SystemExceptions"); 
				json.put("code", 500);
			}
			json.put("errorMsg", exception.getMessage());
			//这是需要手动打印出异常，实际生产环境下，应该将其打印到log中去
			exception.printStackTrace();
			return new ModelAndView("exceptions/error","message",json);
		}else {
			//如果请求时ajax，将以json数据格式返回
			response.setContentType("application/json;charset=UTF-8");
			try {
				
				PrintWriter writer = response.getWriter();
				json.put("success",false);
				if(exception instanceof BusinessException) {
					//请求服务出错
					json.put("code", HttpStatus.BAD_REQUEST.value()); 
					json.put("errorType", "BusinessExceptions");
				}else {
					//服务器内部错误
					json.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value()); 
					json.put("errorType", "SystemExceptions");
				}
				json.put("errorMsg", exception.getMessage());
				String callback = request.getParameter("callback");
				System.out.println(callback);
				if(callback == null || callback.equals("")) {
					writer.write(json.toString());
				}else {
					writer.write(callback + "(" + json.toString() +")");
				}
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}

}
