package cn.edu.lzcc.oa.service;

import java.util.List;

import cn.edu.lzcc.oa.base.DAOSupport;
import cn.edu.lzcc.oa.domain.Privilege;

public interface PrivilegeService extends DAOSupport<Privilege>{

	List<Privilege> findTopList();

	List<String> findAllPrivilegeUrls();

}
