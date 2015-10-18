package cn.edu.lzcc.oa.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

public class User implements Serializable{

	private Long id;
	private String name;
	private String loginName;
	private String password;
	private String sex;
	private String phoneNumber;
	private String email;
	private String description;
	private Department department;
	private Set<Role> roles;

	public boolean hasPrivilegeByName(String name) {

		// 如果用户是超级管理员，拥有全部权限，直接返回true，超级管理员没有角色。
		if (isAdmin()) {
			return true;
		}

		for (Role role : roles) {
			for (Privilege privilege : role.getPrivileges()) {
				if (privilege.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasPrivilegeByUrl(String privilegeUrl) {

		// 如果用户是超级管理员，拥有全部权限，直接返回true，超级管理员没有角色。
		if (isAdmin()) {
			return true;
		}

		// 将url去掉？之后的
		int pos = privilegeUrl.indexOf("?");
		if (pos != -1) {
			privilegeUrl = privilegeUrl.substring(0, pos);
		}
		// 将url去掉UI后缀
		if (privilegeUrl.endsWith("UI")) {
			privilegeUrl = privilegeUrl.substring(0, privilegeUrl.length() - 2);
		}

		//如果是共有的权限，直接返回true
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext
				.getContext().getApplication().get("allPrivilegeUrls");
		//如果不是数据库里面列出的权限，那么就赋给用户这个权限。
		if(!allPrivilegeUrls.contains(privilegeUrl)){
			return true;
		}else {
			for (Role role : roles) {
				for (Privilege privilege : role.getPrivileges()) {
					if (privilegeUrl.equals(privilege.getUrl())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean isAdmin() {
		// 登录名不能相同。
		return "admin".equals(loginName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
