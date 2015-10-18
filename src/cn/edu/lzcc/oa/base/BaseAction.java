package cn.edu.lzcc.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.edu.lzcc.oa.domain.User;
import cn.edu.lzcc.oa.service.DepartmentService;
import cn.edu.lzcc.oa.service.ForumManageService;
import cn.edu.lzcc.oa.service.ForumService;
import cn.edu.lzcc.oa.service.PrivilegeService;
import cn.edu.lzcc.oa.service.ReplyService;
import cn.edu.lzcc.oa.service.RoleService;
import cn.edu.lzcc.oa.service.TopicService;
import cn.edu.lzcc.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 对Action层的抽取。 2015年3月28日
 * 这个类有两个作用：
 * 	1.维护Service
 * 	2.维护ModelDriven
 * @author Zhou Wentong
 * 
 */
@SuppressWarnings("unchecked")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 设置成protected的子包可以访问。
	@Resource
	protected DepartmentService departmentService;

	@Resource
	protected RoleService roleService;
	
	@Resource
	protected UserService userService;
	
	@Resource
	protected PrivilegeService privilegeService;

	@Resource
	protected ForumManageService forumManageService;
	
	@Resource
	protected ForumService forumService;
	
	@Resource
	protected TopicService topicService;
	
	@Resource
	protected ReplyService replyService;
	
	protected T model = null;
	public BaseAction() {
		try {
			ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) ptype.getActualTypeArguments()[0];
			model = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T getModel() {
		return model;
	}
	
	protected User getCurrentUser() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		return user;
	}
	
	protected int pageNum = 1;
	protected int pageSize = 10;
	

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
