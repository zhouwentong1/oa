package cn.edu.lzcc.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lzcc.oa.base.DAOSupportImpl;
import cn.edu.lzcc.oa.domain.Forum;
import cn.edu.lzcc.oa.domain.PageBean;
import cn.edu.lzcc.oa.domain.Reply;
import cn.edu.lzcc.oa.domain.Topic;
import cn.edu.lzcc.oa.service.ReplyService;

@Service
@Transactional
public class ReplyServiceImpl extends DAOSupportImpl<Reply> implements ReplyService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)
				.list();
	}

	@Override
	/**
	 * 维护自己的属性和特殊属性
	 */
	public void save(Reply reply) {
		getSession().save(reply);
		Topic topic = reply.getTopic();
		//topic的特殊属性
		topic.setLastReply(reply);
		topic.setLastUpdateTime(reply.getPostTime());
		topic.setReplyCount(topic.getReplyCount() + 1);
		
		//forum的特殊属性
		Forum forum = topic.getForum();
		forum.setArticleCount(forum.getArticleCount() + 1);
		
		getSession().update(topic);
		getSession().update(forum);
	}

	
/*	===================================================
	 假设有36个数据，每页10个数据，需要分成4页
	第一页：	0		10
	第二页:	10		10
	第三页：	20		10
	===================================================
*/
	@Override
	@Deprecated
	public PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic) {
		Long recordCount = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Reply r WHERE r.topic=?")//
				.setParameter(0, topic)//
				.uniqueResult();
		List list = getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime")//
				.setParameter(0, topic)//
				.setFirstResult((pageNum - 1 ) * pageSize)//
				.setMaxResults(pageSize)//
				.list();
		PageBean pageBean = new PageBean(pageSize, pageNum, list, recordCount.intValue());
		return pageBean;
	}

	

}
