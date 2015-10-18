package cn.edu.lzcc.oa.utils;

import cn.edu.lzcc.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8551102226847913088L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 获取信息
		User user = (User) ActionContext.getContext().getSession().get("user");
		String namespace = invocation.getProxy().getNamespace();
		String url = invocation.getProxy().getActionName();
		String privilegeUrl = namespace + url;
		// 用户没有登录
		if (user == null) {
			if (privilegeUrl.startsWith("/user_login")) {
				//如果是去登录，就放行。
				return invocation.invoke();
			} else {
				return "loginUI";
			}
			// 用户已经登录
		} else {
			// 用户有权限
			if (user.hasPrivilegeByUrl(privilegeUrl)) {
				return invocation.invoke();
				// 用户没有权限
			} else {
				return "noPrivilegeError";
			}
		}
	}

}
