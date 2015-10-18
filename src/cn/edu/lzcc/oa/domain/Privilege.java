package cn.edu.lzcc.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Privilege implements Serializable{
	private Long id;
	private String name;//权限名称
	private String url;//要拦截的url地址
	
	private Set<Role> roles = new HashSet<>();//和角色的映射
	private Set<Privilege> children = new HashSet<>();//自己下属的子权限列表
	private Privilege parent;//自己的上级权限.
	
	public Privilege(){}
	
	
	public Privilege(String name, String url, Privilege parent) {
		this.name = name;
		this.url = url;
		this.parent = parent;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Privilege> getChildren() {
		return children;
	}
	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}

}
