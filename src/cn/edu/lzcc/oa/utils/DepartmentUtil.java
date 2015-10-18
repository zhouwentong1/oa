package cn.edu.lzcc.oa.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.edu.lzcc.oa.domain.Department;

public class DepartmentUtil {

	private DepartmentUtil() {
		throw new RuntimeException("工具类不允许实例化!");
	}

	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		walkDepartmentTreeList(topList, "┣", list);
		return list;
	}

	private static void walkDepartmentTreeList(Collection<Department> topList,
			String prefix, List<Department> list) {
		for(Department top : topList){
			/**
			 * 在这里建副本对象因为
			 * 这里是在session的范围内，一旦改变了对象的状态，如果后面又新开了一个事务
			 * 那么这里更改过的top对象的名字就会被更改到数据库中，这不是我们希望看到的。
			 * 但是如果用的是副本对象的话，就相当于间接的保护了原来的对象，列表的时候只需要
			 * id和name，我们这个副本对象就只设置这两个值。
			 */
			Department copy = new Department();
			copy.setName(prefix + top.getName());
			copy.setId(top.getId());
			list.add(copy);
			
			walkDepartmentTreeList(top.getChildren(), "　　" + prefix, list);
		}
	}

}
