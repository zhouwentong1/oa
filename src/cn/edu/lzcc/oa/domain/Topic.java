package cn.edu.lzcc.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Topic extends Article{

	/** 普通帖 */
	public static final int TYPE_NORMAL = 0;

	/** 精华帖 */
	public static final int TYPE_BEST = 1;

	/** 置顶帖 */
	public static final int TYPE_TOP = 2;

	private Forum forum;// 所属板块
	private Set<Reply> replies = new HashSet<>();// 回复列表
	private int type;// 帖子类型
	private Reply lastReply;// 最后回复
	private Date lastUpdateTime;// 最后更新时间
	private int replyCount;// 回复数量

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	public Reply getLastReply() {
		return lastReply;
	}

	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

}
