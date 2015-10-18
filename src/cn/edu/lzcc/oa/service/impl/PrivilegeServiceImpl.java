package cn.edu.lzcc.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lzcc.oa.base.DAOSupportImpl;
import cn.edu.lzcc.oa.domain.Privilege;
import cn.edu.lzcc.oa.service.PrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl extends DAOSupportImpl<Privilege> implements
		PrivilegeService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Privilege> findTopList() {
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL")//
				.list();
		
	}

	@Override
	public List<String> findAllPrivilegeUrls() {
		return getSession().createQuery(//
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
	}

}
