package cn.edu.lzcc.oa.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.lzcc.oa.base.BaseAction;
import cn.edu.lzcc.oa.domain.Forum;
import cn.edu.lzcc.oa.domain.PageBean;
import cn.edu.lzcc.oa.domain.Reply;
import cn.edu.lzcc.oa.domain.Topic;
import cn.edu.lzcc.oa.utils.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3287682280885286246L;
	/**
	 * 
	 */

	private Long forumId;

	/** 显示单个主题 */
	public String show() throws Exception {
		// 准备数据
		Topic topic = topicService.getByID(model.getId());
		ActionContext.getContext().put("topic", topic);

		// 准备回复列表
		// PageBean pageBean = replyService.getPageBeanByTopic(pageNum,
		// pageSize, topic);
		// ActionContext.getContext().put("replyList", replyList);
		// 通过传递hql语句进行分页。
		// String hql = "FROM Reply r WHERE r.topic=? ORDER BY r.postTime";
		// List<Object> paramters = new ArrayList<>();
		// paramters.add(topic);

		// PageBean pageBean =
		// replyService.getPageBean(pageNum,pageSize,hql,paramters);
		// ActionContext.getContext().getValueStack().push(pageBean);
		new QueryHelper(Reply.class, "r").addCondition("r.topic=?", topic)
				.addOrderByProperty("r.postTime", true)
				.preparePageBean(replyService, pageNum, pageSize);
		return "show";
	}

	/** 发帖页面 */
	public String addUI() throws Exception {
		Forum forum = forumService.getByID(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}

	/** 发帖 */
	public String add() throws Exception {
		// >>表单中已经封装的数据
		// model.setContent(content);
		// model.setTitle(title);
		model.setForum(forumService.getByID(forumId));
		// >>当前能够直接获取到的数据
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());

		// 直接获取不到的数据，需要在业务层中获取
		// model.setLastReply(lastReply);
		// model.setLastUpdateTime(lastUpdateTime);
		// model.setReplies(replies);
		// model.setReplyCount(replyCount);
		// model.setType(type);
		topicService.save(model);
		return "toShow";
	}

	// ==============================
	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
}
