package cn.edu.lzcc.test;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller("testAction")
@Scope(value="prototype")
public class TestAction extends ActionSupport{
	
	@Resource
	private TestService service;
	@Override
	public String execute() throws Exception {
		service.addUsers();
		return "success";
	}

}
