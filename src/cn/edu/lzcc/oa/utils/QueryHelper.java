package cn.edu.lzcc.oa.utils;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.lzcc.oa.base.DAOSupport;
import cn.edu.lzcc.oa.domain.PageBean;

/**
 * 用于拼接hql语句的辅助类。 返回QueryHelper本身有助于编程效率的提高，链式编程。
 * 
 * @author Administrator
 * 
 */
public class QueryHelper {

	private String fromClause;// from子句（必须）
	private String whereClause = "";// where子句（非必须）
	private String orderByClause = "";// order子句（非必须）

	private List<Object> paramters = new ArrayList<>();// 参数列表

	/**
	 * 配置from子句，因为是必须的，所以放在构造方法中。
	 * 
	 * @param clazz
	 * @param alias
	 *            别名
	 */
	public QueryHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * 拼接where子句,where子句可能有多个条件。 select user from user where user = ?;
	 * 
	 * @return
	 */
	public QueryHelper addCondition(String condition, Object... params) {
		if (whereClause.length() == 0) {// 第一次添加
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}

		if (params != null) {
			for (Object object : params) {

				paramters.add(object);
			}
		}

		return this;
	}

	/**
	 * 将控制权下放到这里,自带判断
	 * 
	 * @param append
	 * @param condition
	 * @param params
	 * @return
	 */
	public QueryHelper addCondition(boolean append, String condition,
			Object... params) {
		if (append) {
			addCondition(condition, params);
		}
		return this;
	}

	/**
	 * 自带判断条件。
	 * 
	 * @param append
	 * @param propertyName
	 * @param asc
	 * @return
	 */
	public QueryHelper addOrderByProperty(boolean append, String propertyName,
			boolean asc) {
		if (append) {
			addOrderByProperty(propertyName, asc);
		}
		return this;
	}

	/**
	 * 增加order by子句条件
	 * 
	 * @param propertyName
	 * @param asc
	 * @return
	 */
	public QueryHelper addOrderByProperty(String propertyName, boolean asc) {
		if (orderByClause.length() == 0) {// 第一次
			orderByClause = " ORDER BY " + propertyName
					+ (asc ? " ASC" : " DESC");
		} else {
			orderByClause += " , " + propertyName + (asc ? " ASC" : " DESC");
		}

		return this;
	}

	/**
	 * 获取HQL中的参数值列表
	 * 
	 * @return
	 */
	public List<Object> getParameters() {
		return paramters;
	}

	/**
	 * 获取生成的用于查询数据列表的HQL语句
	 * 
	 * @return
	 */
	public String getListQueryHql() {
		return fromClause + whereClause + orderByClause;
	}

	/**
	 * 获取生成的用于查询总记录数的HQL语句
	 * 
	 * @return
	 */
	public String getCountQueryHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 查询分页信息，并放到值栈栈顶
	 * 
	 * @param service
	 * @param pageNum
	 * @param pageSize
	 */
	public void preparePageBean(DAOSupport<?> service, int pageNum, int pageSize) {
		PageBean pageBean = service.getPageBean(pageNum, pageSize, this);
		ActionContext.getContext().getValueStack().push(pageBean);
	}

}
