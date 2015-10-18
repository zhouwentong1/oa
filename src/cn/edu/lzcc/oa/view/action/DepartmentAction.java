package cn.edu.lzcc.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.lzcc.oa.base.BaseAction;
import cn.edu.lzcc.oa.domain.Department;
import cn.edu.lzcc.oa.utils.DepartmentUtil;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long parentId;

	/** 列表 */
	public String list() throws Exception {
		List<Department> departmentList = null;
		if (parentId == null) {// 最顶级部门。
			departmentList = departmentService.findTopList();
		} else {
			departmentList = departmentService.findChildren(parentId);
			Department parent = departmentService.getByID(parentId);
			ActionContext.getContext().put("parent", parent);
		}

		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/** 添加 */
	public String add() throws Exception {
//		这个model如果是完整封装了全部的信息的话，就可以直接save，但是在这里没有包含parent属性，只是包含了
		//parent的id,所以要使用的话,还是要加上parent属性.
		Department parent = departmentService.getByID(parentId);
		model.setParent(parent);
		departmentService.save(model);
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据
		List<Department> topList = departmentService.findTopList();
		// 找到所有带标志的名称。
		List<Department> departmentList = DepartmentUtil
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	/** 删除 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/** 修改 */
	public String edit() throws Exception {
		Department department = departmentService.getByID(model.getId());
		department.setDescription(model.getDescription());
		department.setName(model.getName());

		departmentService.update(department);
		return "toList";
	}

	/** 修改页面 需要设置回显数据 */
	public String editUI() throws Exception {
		// 准备数据
		List<Department> departmentList = departmentService.listAll();
		ActionContext.getContext().put("departmentList", departmentList);

		Department department = departmentService.getByID(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}
		return "saveUI";
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
