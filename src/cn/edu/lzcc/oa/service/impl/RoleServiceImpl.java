package cn.edu.lzcc.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lzcc.oa.base.DAOSupportImpl;
import cn.edu.lzcc.oa.domain.Role;
import cn.edu.lzcc.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DAOSupportImpl<Role> implements RoleService{

//	@Override
//	public List<Role> listAll() {
//		return roleDAO.listAll();
//	}
//
//	@Override
//	public void add(Role role) {
//		roleDAO.save(role);
//	}
//	
//	@Override
//	public void delete(Long id){
//		roleDAO.delete(id);
//	}
//
//	@Override
//	public Role getByID(Long id) {
//		return roleDAO.getByID(id);
//	}
//
//	@Override
//	public void update(Role role) {
//		roleDAO.update(role);
//	}
}
