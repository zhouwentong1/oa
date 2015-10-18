package cn.edu.lzcc.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lzcc.oa.base.DAOSupportImpl;
import cn.edu.lzcc.oa.domain.User;
import cn.edu.lzcc.oa.service.UserService;

@Transactional
@Service
public class UserServiceImpl extends DAOSupportImpl<User> implements UserService {

	@Override
	public User getUserByLoginNameAndPassword(String loginName, String password) {
		String md5Digest = DigestUtils.md5Hex(password);
		return (User)getSession().createQuery(//
				"FROM User u WHERE u.loginName=? AND u.password=?")//
				.setParameter(0, loginName)//
				.setParameter(1, md5Digest)//
				.uniqueResult();
	}

}
