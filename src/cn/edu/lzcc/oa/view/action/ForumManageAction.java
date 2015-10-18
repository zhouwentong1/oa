package cn.edu.lzcc.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.lzcc.oa.base.BaseAction;
import cn.edu.lzcc.oa.domain.Forum;

@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -446656227999246497L;

	/** 列表功能 */
	public String list() throws Exception {
		List<Forum> forumList = forumManageService.listAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}
	
	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}
	
	/** 添加 */
	public String add() throws Exception {
		forumManageService.save(model);
		return "toList";
	}
	
	/** 修改页面 */
	public String editUI() throws Exception {
		//准备回显数据。
		Forum forum = forumManageService.getByID(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		return "saveUI";
	}
	
	/** 修改  */
	public String edit() throws Exception {
		Forum forum = forumManageService.getByID(model.getId());
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		forumManageService.update(forum);
		return "toList";
	}
	
	/** 删除 */
	public String delete() throws Exception {
		forumManageService.delete(model.getId());
		return "toList";
	}
	
	/** 上移 */
	public String moveUp() throws Exception {
		forumManageService.moveUp(model.getId());
		return "toList";
	}
	
	/** 下移 */
	public String moveDown() throws Exception {
		forumManageService.moveDown(model.getId());
		return "toList";
	}
	
	
}
