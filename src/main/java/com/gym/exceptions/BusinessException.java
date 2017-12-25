package com.gym.exceptions;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 自定义异常
 * @author lemon
 *
 */
public class BusinessException extends Exception{
	private static final long serialVersionUID = 1L;

	public static enum ServerError{
		internal_error,
		unknow,
		verified_failed,
		//您的帐号在另外一个地方登录
		kickout, 
		// 必须要登录
		login_required, 
		// 需要是管理
		admin_required, 
		 // 权限不够
		operation_denied,
		// 已经登录了
		admin_logined,
		// 无效的参数
		invalid_parameters,
		// 尚未实现
		not_implemented, 
		// 不支持的设备
		unsupported_device,
		// 不支持的版本
		unsupported_version,
		//需要输入用户名
		username_required,
		//该用户已被禁用
        username_banned,
        //用户名无效
        username_invalid,
        //需要输入密码
        password_required,
        //密码无效
        password_invalid,
        //密码过于简单
        password_toosimple,
        //用户名已经存在
        username_existed,
        //用户不存在
        username_notexisted,
        //密码不匹配
        password_notmatch,
        //旧密码错误
        password_wrong,
        //需要输入邮件地址
        email_required, 
        //邮件地址不匹配
        email_notmatch,
        //邮件地址已存在
        email_existed,
        //邮件地址不存在
        email_notexist, 
        //邮箱未激活
        email_inactive, 
        //邮箱验证码不正确
        email_codeerror, 
        //----------------------
        //需要输入手机号码
        mobile_required,
        //该手机号码已经被占用
        mobile_existed,
        //手机未激活
        mobile_inactive,
        //手机不存在
        mobile_notexist,
        //手机验证码不正确
        mobile_codeerror,
        //无效的昵称
        nickname_invalid,
        //昵称已存在
        nickname_existed, 
        //需要输入用户名
        login_username_required, 
        //需要输入密码
        login_password_required,
        //用户名或密码错
        login_wrong_usernameorpassword,
        //无效的用户名
        login_wrong_username,
        //无效的密码
        login_wrong_password,
        //帐号已过期
        login_account_expired,
        //帐号已被锁
        login_account_locked,
        //帐号已被封
        login_account_denied,
        //帐号未激活
        login_account_inactive,

        //-----
        //需要输入用户名", // 需要登录名
        loginname_required,
        //登录名无效", // 登录名无效
        loginname_invalid,
        //登录名已存在", // 
        loginname_existed,
        //登录名不存在", // 
        oginname_notexist, 
        //登录名未激活", //
        loginname_inactive,
		register_game_exceed;
		
		public static ServerError get(String code, ServerError defaultServerError) {
			ServerError[] serverErrors = ServerError.values();
			for (ServerError serverError : serverErrors) {
				if (serverError.name().equals(code)) {
					return serverError;
				}
			}
			return defaultServerError;
		}
		
		/**
		* 根据code获取对应的枚举，如果没有找到对应的枚举，返回internal_error
		* @param code
		* @return
		*/
		public static ServerError get(String code) {
			return get(code, ServerError.internal_error);
		}
	}
	
	private ServerError serverError;
	
	public BusinessException(ServerError error) {
		this(error,error.toString());
	}

	public BusinessException(ServerError error, String message) {
		super(message);
		this.serverError = error;
	}

	public BusinessException(ServerError error, Throwable cause) {
		super(cause);
		this.serverError = error;
		// TODO Auto-generated constructor stub
	}
	
	public JSONObject toJSON() {
		JSONObject errorJSON = new JSONObject();
		try {
			if(getError() != null) {
				errorJSON.put("code", getError().toString());
			}else if(serverError != null) {
				errorJSON.put("code",serverError.toString());
			}else {
				errorJSON.put("code", getMessage());
			}
		} catch(JSONException e) {
			e.printStackTrace();
		}
		return errorJSON;
	}
	public ServerError getError() {
		return serverError;
	}
}
