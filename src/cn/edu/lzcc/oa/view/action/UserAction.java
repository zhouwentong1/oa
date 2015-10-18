package cn.edu.lzcc.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.lzcc.oa.base.BaseAction;
import cn.edu.lzcc.oa.domain.Department;
import cn.edu.lzcc.oa.domain.Role;
import cn.edu.lzcc.oa.domain.User;
import cn.edu.lzcc.oa.utils.DepartmentUtil;
import cn.edu.lzcc.oa.utils.QueryHelper;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long departmentId;
	private Long[] roleIds;

	/** 列表 */
	public String list() throws Exception {
//		List<User> userList = userService.listAll();
//		ActionContext.getContext().put("userList", userList);
		new QueryHelper(User.class, "u").preparePageBean(userService, pageNum, pageSize);
		return "list";
	}

	/** 增加页面 */
	public String addUI() throws Exception {
		// 需要数据支持
		// 准备数据
		List<Department> topList = departmentService.findTopList();
		// 提供department的支持
		List<Department> departmentList = DepartmentUtil
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 提供role的支持
		List<Role> roleList = roleService.listAll();
		// 将roleList放到值栈中的map中去
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	/** 增加 */
	public String add() throws Exception {
		model.setDepartment(departmentService.getByID(departmentId));
		model.setRoles(new HashSet<>(roleService.getByIDS(roleIds)));
		model.setPassword(DigestUtils.md5Hex("1234"));
		userService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 需要数据支持
		// 准备数据
		List<Department> topList = departmentService.findTopList();
		// 提供department的支持
		List<Department> departmentList = DepartmentUtil
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 提供role的支持
		List<Role> roleList = roleService.listAll();
		// 将roleList放到值栈中的map中去
		ActionContext.getContext().put("roleList", roleList);

		// 准备回显的数据。
		User user = userService.getByID(model.getId());
		ActionContext.getContext().getValueStack().push(user);

		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles() != null) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 从数据库中取出对象
		User user = userService.getByID(model.getId());

		// 为属性更新值
		user.setDepartment(departmentService.getByID(departmentId));
		user.setDescription(model.getDescription());
		user.setEmail(model.getEmail());
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setRoles(new HashSet<Role>(roleService.getByIDS(roleIds)));
		user.setSex(model.getSex());
		// 将数据放回到数据库中.

		userService.update(user);
		return "toList";
	}

	/** 删除 */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** 初始化密码为1234 */
	public String initPassword() throws Exception {
		// 从数据库中取出对象
		User user = userService.getByID(model.getId());
		// 为属性更新值
		user.setPassword(DigestUtils.md5Hex("1234"));
		// 将数据放回到数据库中.
		userService.update(user);

		return "toList";
	}

	/** 登录页面 */
	public String loginUI() throws Exception {
		return "loginUI";
	}

	/** 登录 */
	public String login() throws Exception {
		User user = userService.getUserByLoginNameAndPassword(
				model.getLoginName(), model.getPassword());
		if(user == null){
			addFieldError("login", "用户名或者密码错误");
			//重新跳回到登录页面
			return "loginUI";
		}else {
			//为用户设置session
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
	}

	/** 注销 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
