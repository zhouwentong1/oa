package cn.edu.lzcc.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.lzcc.oa.base.BaseAction;
import cn.edu.lzcc.oa.domain.Reply;
import cn.edu.lzcc.oa.domain.Topic;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7589287012128883053L;
	private Long topicId;

	/** 回复页面 */
	public String addUI() throws Exception {
		// 提供数据
		Topic topic = topicService.getByID(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}

	/** 回复 */
	public String add() throws Exception {
		// >>表单已经封装好了的.
		// model.setTitle(title);
		// model.setContent(content);
		// 设置为当前登录用户
		System.out.println("================");
		System.out.println(topicId);
		model.setAuthor(getCurrentUser());
		// 当前用户的ip地址
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		// 当前时间
		model.setPostTime(new Date());
		model.setTopic(topicService.getByID(topicId));

		replyService.save(model);
		return "toTopicShow";
	}

	// >>>get/set方法
	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
