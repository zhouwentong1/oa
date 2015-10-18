package cn.edu.lzcc.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lzcc.oa.base.DAOSupportImpl;
import cn.edu.lzcc.oa.domain.Forum;
import cn.edu.lzcc.oa.service.ForumManageService;

@Service
@Transactional
public class ForumManageServiceImpl extends DAOSupportImpl<Forum> implements
		ForumManageService {

	/**
	 * 由于列表显示的时候需要按照位置进行排序 所以这里需要重写这个listAll方法
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> listAll() {
		return getSession().createQuery(//
				"FROM Forum f ORDER BY f.position")//
				.list();
	}

	@Override
	public void save(Forum entity) {
		// 在保存这个用户forum之前是没有值的
		// 但是在保存之后，hibernate会将这个值返回回来，以便于我们的使用。
		// 所以如果在这个之前尝试获取它的id，会抛出空指针异常。
		super.save(entity);
		entity.setPosition(entity.getId().intValue());
	}

	@Override
	public void moveUp(Long id) {
		// 找到当前的forum
		Forum forum = getByID(id);
		// 找到相关的forum
		Forum other = (Forum) getSession().createQuery(//
				"FROM Forum f WHERE f.position < ? ORDER BY f.position DESC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();

		if (other == null) {
			return;
		}
		// 交换双方的position
		int temp = 0;
		temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		// 更新到数据库中，下面这两句可以不写，因为此时在session中，对象状态的更新可以直接更新到数据库中。
		getSession().update(forum);
		getSession().update(other);
	}

	@Override
	public void moveDown(Long id) {
		// 找到当前的forum
		Forum forum = getByID(id);
		// 找到相关的forum
		Forum other = (Forum) getSession().createQuery(//
				"FROM Forum f WHERE f.position > ? ORDER BY f.position")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();

		if (other == null)
			return;
		// 交换双方的position
		int temp = 0;
		temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		// 更新到数据库中，下面这两句可以不写，因为此时在session中，对象状态的更新可以直接更新到数据库中。
		getSession().update(forum);
		getSession().update(other);
	}

}
