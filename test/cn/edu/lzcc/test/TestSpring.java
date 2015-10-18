package cn.edu.lzcc.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	@Test
	public void testSpring() throws Exception {
		TestAction action = (TestAction) ctx.getBean("testAction");
		System.out.println(action);
	}

	@Test
	public void testHibernate() throws Exception {
		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
	
	@Test
	public void testService() throws Exception {
		TestService service = (TestService) ctx.getBean("testService");
		service.addUsers();
	}
}
