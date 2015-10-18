package cn.edu.lzcc.oa.service;

import java.util.List;

import cn.edu.lzcc.oa.base.DAOSupport;
import cn.edu.lzcc.oa.domain.Department;

public interface DepartmentService extends DAOSupport<Department>{

	List<Department> findTopList();

	List<Department> findChildren(Long parentId);

}
