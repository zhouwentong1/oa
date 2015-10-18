package cn.edu.lzcc.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.lzcc.oa.base.BaseAction;
import cn.edu.lzcc.oa.domain.Forum;
import cn.edu.lzcc.oa.domain.Topic;
import cn.edu.lzcc.oa.utils.QueryHelper;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4025068222225037459L;
	private Long forumId;

	/**
	 * 0 表示查看全部主题<br>
	 * 1 表示只看精华帖
	 */
	private int viewType = 0;

	/**
	 * 0 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)<br>
	 * 1 表示只按最后更新时间排序<br>
	 * 2 表示只按主题发表时间排序<br>
	 * 3 表示只按回复数量排序
	 */
	private int orderBy = 0;

	/**
	 * true 表示升序<br>
	 * false 表示降序
	 */
	private boolean asc = false;

	/** 显示板块列表 */
	public String list() throws Exception {
		List<Forum> forumList = forumService.listAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/** 显示单个板块 */
	public String show() throws Exception {
		Forum forum = forumService.getByID(model.getId());
		ActionContext.getContext().put("forum", forum);

		// List<Forum> topicList = topicService.findByForum(forum);
		// ActionContext.getContext().put("topicList", topicList);
		// PageBean pageBean = topicService.getPageBeanByForum(pageNum,
		// pageSize,
		// forum);
//		String hql = "FROM Topic t WHERE t.forum=? ";
//		List<Object> paramters = new ArrayList<>();
//		paramters.add(forum);

		// 通过拼接hql语句的方式来实现
		// if (viewType == 1) {// 只看精华帖
		// hql += " AND t.type=?";
		// paramters.add(Topic.TYPE_BEST);
		// }
		//
		// if (orderBy == 1) {// 表示只按最后更新时间排序
		// hql += " ORDER BY t.lastUpdateTime " + (asc ? "ASC" : "DESC");
		// } else if (orderBy == 2) {// 表示只按主题发表时间排序
		// hql += " ORDER BY t.postTime " + (asc ? "ASC" : "DESC");
		// } else if (orderBy == 3) {// 表示只按回复数量排序
		// hql += " ORDER BY t.replyCount " + (asc ? "ASC" : "DESC");
		// } else {// 按照默认排序
		// hql +=
		// "ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC";
		// }
		//
		// PageBean pageBean = topicService.getPageBean(pageNum, pageSize, hql,
		// paramters);
		// ActionContext.getContext().getValueStack().push(pageBean);
		new QueryHelper(Topic.class, "t")//
				// 过滤条件
				.addCondition("t.forum=?", forum)//
				.addCondition((viewType == 1), "t.type=?", Topic.TYPE_BEST) // 1
																			// 表示只看精华帖
				// 排序条件
				.addOrderByProperty((orderBy == 1), "t.lastUpdateTime", asc) // 1
																				// 表示只按最后更新时间排序
				.addOrderByProperty((orderBy == 2), "t.postTime", asc) // 2
																		// 表示只按主题发表时间排序
				.addOrderByProperty((orderBy == 3), "t.replyCount", asc) // 3
																			// 表示只按回复数量排序
				.addOrderByProperty((orderBy == 0),
						"(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)//
				.addOrderByProperty((orderBy == 0), "t.lastUpdateTime", false) // 0
																				// 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)
				.preparePageBean(topicService, pageNum, pageSize);
		return "show";
	}

	// >>>>
	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

}
