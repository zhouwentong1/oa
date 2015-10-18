package cn.edu.lzcc.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 由于这个类只是实现转发功能，不需要做哪些增删改查工作，所以只需要继承ActionSupport这个就行了
 * 	
 * */
@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String index() throws Exception {
		return "index";
	}
	public String top() throws Exception {
		return "top";
	}
	public String bottom() throws Exception {
		return "bottom";
	}
	public String left() throws Exception {
		return "left";
	}
	public String right() throws Exception {
		return "right";
	}

	
}
