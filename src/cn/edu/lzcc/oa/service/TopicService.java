package cn.edu.lzcc.oa.service;

import java.util.List;

import cn.edu.lzcc.oa.base.DAOSupport;
import cn.edu.lzcc.oa.domain.Forum;
import cn.edu.lzcc.oa.domain.PageBean;
import cn.edu.lzcc.oa.domain.Topic;

public interface TopicService extends DAOSupport<Topic>{

	@Deprecated
	List<Forum> findByForum(Forum forum);

	/**
	 * 查询分页信息
	 * @param pageNum
	 * @param pageSize
	 * @param forum
	 * @return
	 */
	PageBean getPageBeanByForum(int pageNum, int pageSize, Forum forum);

}
