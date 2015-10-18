package cn.edu.lzcc.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.lzcc.oa.base.BaseAction;
import cn.edu.lzcc.oa.domain.Privilege;
import cn.edu.lzcc.oa.domain.Role;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3864736924994840121L;

	private Long[] privilegeIds;

	/** 列表 */
	public String list() throws Exception {
		List<Role> roleList = roleService.listAll();
		// 将roleList放到值栈中的map中去
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/** 添加 */
	public String add() throws Exception {
		roleService.save(model);
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 删除 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/** 修改 */
	public String edit() throws Exception {
		Role role = roleService.getByID(model.getId());
		role.setDescription(model.getDescription());
		role.setName(model.getName());
		roleService.update(role);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 从数据库中得到role
		Role role = roleService.getByID(model.getId());
		// 将role放到值栈的对象栈顶部。
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}

	/** 设置权限页面 */
	public String setPrivilegeUI() {
		// 准备回显数据。
		Role role = roleService.getByID(model.getId());
		ActionContext.getContext().getValueStack().push(role);

		// 设置要回显的ids
		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for (Privilege privilege : role.getPrivileges()) {
				privilegeIds[index++] = privilege.getId();
			}
		}
		
		//准备权限的数据。
		List<Privilege> privilegeList = privilegeService.listAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		
		return "setPrivilegeUI";
	}

	/** 设置权限 */
	public String setPrivilege() {
		// 从数据库得到原来的对象
		Role role = roleService.getByID(model.getId());
		// 更新数据
		List<Privilege> privileges = privilegeService.getByIDS(privilegeIds);
		role.setPrivileges(new HashSet<>(privileges));
		// 将数据放回到数据库
		roleService.update(role);
		return "toList";
	}

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
