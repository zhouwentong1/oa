package cn.edu.lzcc.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lzcc.oa.domain.User;

@Service
public class TestService {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void addTwoUser() {
		Session session = sessionFactory.getCurrentSession();
		session.save(new User());
//		int i = 1 / 0;
		session.save(new User());
	}
	@Transactional
	public void addUsers() {
		Session session = sessionFactory.getCurrentSession();
		for(int i = 0;i<51;i++){
			User user = new User();
			user.setName("Name_" + (char)('A' + i));
			session.save(user);
		}
	}
}
