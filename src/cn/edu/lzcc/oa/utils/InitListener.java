package cn.edu.lzcc.oa.utils;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.edu.lzcc.oa.domain.Privilege;
import cn.edu.lzcc.oa.service.PrivilegeService;

public class InitListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 获取容器与相关的Service对象
		/**
		 * 这里不能直接new一个ctx，要得到privilegeService这个service
		 * 必须使用原先的spring容器。可以通过这个util类来得到这个容器。
		 * 并且这个类不能由spring注入，因为在web.xml文件配置这个监听器的时候，
		 * 要配置全名，可以看出这个类是new出来的。不能交给spring容器管理。
		 */
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ctx.getBean("privilegeServiceImpl");
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("------------> 已准备数据topPrivilegeList <------------");
		
		List<String> allPrivilegeUrls = privilegeService.findAllPrivilegeUrls();
		sce.getServletContext().setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		System.out.println("------------> 已准备数据allPrivilegeUrls <------------");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
