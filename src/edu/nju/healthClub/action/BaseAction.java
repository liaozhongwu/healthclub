package edu.nju.healthClub.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements  SessionAware,ServletRequestAware,ServletResponseAware {

	private static final long serialVersionUID = 1L;  
	 
    public HttpServletRequest   request;  
    public HttpServletResponse  response;   
    public Map<String,Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		 this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;  
	}
	
}
