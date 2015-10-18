package cn.edu.lzcc.oa.service;

import cn.edu.lzcc.oa.base.DAOSupport;
import cn.edu.lzcc.oa.domain.User;

public interface UserService extends DAOSupport<User>{

	User getUserByLoginNameAndPassword(String loginName, String password);

}
