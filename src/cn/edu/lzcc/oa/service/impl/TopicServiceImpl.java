package cn.edu.lzcc.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lzcc.oa.base.DAOSupportImpl;
import cn.edu.lzcc.oa.domain.Forum;
import cn.edu.lzcc.oa.domain.PageBean;
import cn.edu.lzcc.oa.domain.Topic;
import cn.edu.lzcc.oa.service.TopicService;

@Service
@Transactional
public class TopicServiceImpl extends DAOSupportImpl<Topic> implements
		TopicService {

	@SuppressWarnings("unchecked")
	@Override
	@Deprecated
	public List<Forum> findByForum(Forum forum) {
		return getSession()
				.createQuery(//
						// 排序：所有置顶帖在最上面，并按最后更新时间排序，让新状态的在上面。
						"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
	}

	@Override
	public void save(Topic topic) {
		// 默认设置成普通贴
		topic.setType(Topic.TYPE_NORMAL);
		// 发表新主题默认没有回复
		topic.setLastReply(null);
		// 最后发表时间为该主题的发表时间
		topic.setLastUpdateTime(topic.getPostTime());
		// 刚发表的回复数量为0
		topic.setReplyCount(0);
		getSession().save(topic);

		// 维护特殊属性
		Forum forum = topic.getForum();
		// 设置文章数量，发表一个新主题就增加一个
		forum.setArticleCount(forum.getArticleCount() + 1);
		// 设置主题数量，发表一个就增加一个
		forum.setTopicCount(forum.getTopicCount() + 1);
		// 最后的主题就是刚发的主题
		forum.setLastTopic(topic);
		getSession().update(forum);
	}

	@Override
	public PageBean getPageBeanByForum(int pageNum, int pageSize, Forum forum) {
		Long recordCount = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Topic t WHERE t.forum=?")//
				.setParameter(0, forum)//
				.uniqueResult();
		List list = getSession()
				.createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();
		PageBean pageBean = new PageBean(pageSize, pageNum, list,
				recordCount.intValue());
		return pageBean;
	}

}
