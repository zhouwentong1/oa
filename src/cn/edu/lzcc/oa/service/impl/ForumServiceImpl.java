package cn.edu.lzcc.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.lzcc.oa.base.DAOSupportImpl;
import cn.edu.lzcc.oa.domain.Forum;
import cn.edu.lzcc.oa.service.ForumService;

@Service
@Transactional
public class ForumServiceImpl extends DAOSupportImpl<Forum> implements ForumService{


}
