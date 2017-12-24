package com.gym.exceptions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 404、403、500异常处理
 * @author lemon
 *
 */
@Controller
public class ExceptionController {
	@RequestMapping("/error_500")
	public String error_500() {
		return "exceptions/error_500";
	}
	
	@RequestMapping("/error_404")
	public String error_404() {
		return "exceptions/error_404";
	}
	
	@RequestMapping("/error_403")
	public String error_403() {
		return "exceptions/error_403";
	}
}
