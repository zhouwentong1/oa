package cn.edu.lzcc.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lzcc.oa.base.DAOSupportImpl;
import cn.edu.lzcc.oa.domain.Department;
import cn.edu.lzcc.oa.service.DepartmentService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DAOSupportImpl<Department> implements DepartmentService {

	@Resource
	private SessionFactory sessionFactory;
//
//	@Override
//	public List<Department> listAll() {
//		return departmentDAO.listAll();
//	}
//
//	@Override
//	public void save(Department department) {
//		departmentDAO.save(department);
//	}
//
//	@Override
//	public void delete(Long id) {
//		departmentDAO.delete(id);
//	}
//
//	@Override
//	public Department getByID(Long id) {
//		return departmentDAO.getByID(id);
//	}
//
//	@Override
//	public void update(Department department) {
//		departmentDAO.update(department);
//	}


	@Override
	public List<Department> findTopList() {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL")//
				.list();
	}

	@Override
	public List<Department> findChildren(Long parentId) {
		return sessionFactory.getCurrentSession().createQuery(//
				"FROM Department d WHERE d.parent.id = ?")//
				.setParameter(0, parentId)
				.list();
	}

}
