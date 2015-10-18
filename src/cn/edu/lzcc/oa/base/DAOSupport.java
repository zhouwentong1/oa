package cn.edu.lzcc.oa.base;

import java.util.List;

import cn.edu.lzcc.oa.domain.PageBean;
import cn.edu.lzcc.oa.utils.QueryHelper;

public interface DAOSupport<T> {
	/**
	 * 添加一个用户
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除一个用户
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 更新一个用户
	 * @param entity
	 */
	void update(T entity);
	/**
	 * ͨ通过id得到
	 * @param id
	 * @return
	 */
	T getByID(Long id);
	/**
	 *通过id得到
	 * @param ids
	 * @return
	 */
	List<T> getByIDS(Long[] ids);
	
	/**
	 * 得到所有
	 * @return
	 */
	List<T> listAll();
	
	/**
	 * 分页
	 * @param pageNum
	 * @param pageSize
	 * @param hql
	 * @param parameters
	 * @return
	 */
	@Deprecated
	public PageBean getPageBean(int pageNum, int pageSize, String hql, List<Object> parameters);

	/**
	 * 分页的最终版本。
	 * @param pageNum
	 * @param pageSize
	 * @param queryHelper
	 * @return
	 */
	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);
}
