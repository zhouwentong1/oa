package cn.edu.lzcc.oa.service;

import java.util.List;

import cn.edu.lzcc.oa.base.DAOSupport;
import cn.edu.lzcc.oa.domain.PageBean;
import cn.edu.lzcc.oa.domain.Reply;
import cn.edu.lzcc.oa.domain.Topic;

public interface ReplyService extends DAOSupport<Reply>{

	List<Reply> findByTopic(Topic topic);

	/**
	 * 查询分页信息。
	 * @param pageNum
	 * @param pageSize
	 * @param topic
	 * @return
	 */
	@Deprecated
	PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic);


}
