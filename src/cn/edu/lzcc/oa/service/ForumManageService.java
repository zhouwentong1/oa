package cn.edu.lzcc.oa.service;

import cn.edu.lzcc.oa.base.DAOSupport;
import cn.edu.lzcc.oa.domain.Forum;

public interface ForumManageService extends DAOSupport<Forum>{

	void moveUp(Long id);

	void moveDown(Long id);

}
