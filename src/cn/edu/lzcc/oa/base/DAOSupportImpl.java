package cn.edu.lzcc.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lzcc.oa.domain.PageBean;
import cn.edu.lzcc.oa.utils.QueryHelper;

@Transactional
@SuppressWarnings("unchecked")
public class DAOSupportImpl<T> implements DAOSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	public DAOSupportImpl() {
		// 通过反射获取类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void delete(Long id) {
		Object entity = getByID(id);
		// 做非空判断
		if (entity != null) {
			getSession().delete(entity);
		}
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public T getByID(Long id) {
		// 作非空判断，如果上级部门为空的话，得到它的上级部门就是空值。
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}

	@Override
	// 通过id获取
	public List<T> getByIDS(Long[] ids) {
		if (ids == null || ids.length == 0) {
			// 这个东西直接将自己的size设置成0
			return Collections.EMPTY_LIST;
		}
		return (List<T>) getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}

	@Override
	@Deprecated
	public PageBean getPageBean(int pageNum, int pageSize, String hql,
			List<Object> parameters) {
		// 获取list的query
		Query listQuery = getSession().createQuery(hql);
		if (parameters != null) {
			for (int i = 0;i<parameters.size();i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}

		// 获取count的query

		Query countQuery = getSession().createQuery(
				"SELECT COUNT(*) " + hql);
		if (parameters != null) {
			for (int i = 0;i<parameters.size();i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long recordCount = (Long) countQuery.uniqueResult();
		List list = listQuery//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();
		PageBean pageBean = new PageBean(pageSize, pageNum, list,
				recordCount.intValue());
		return pageBean;
	}

	@Override
	public List<T> listAll() {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}

	@Override
	public PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper) {
		System.out.println("-------> DaoSupportImpl.getPageBean( int pageNum, int pageSize, QueryHelper queryHelper )");

		// 参数列表
		List<Object> parameters = queryHelper.getParameters();

		// 查询本页的数据列表
		Query listQuery = getSession().createQuery(queryHelper.getListQueryHql()); // 创建查询对象
		if (parameters != null) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list(); // 执行查询

		// 查询总记录数量
		Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
		if (parameters != null) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) countQuery.uniqueResult(); // 执行查询

		return new PageBean(pageSize,pageNum,list,count.intValue());
	}

}
