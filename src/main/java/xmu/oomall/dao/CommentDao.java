package xmu.oomall.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Comment;
import xmu.oomall.mapper.CommentMapper;
import xmu.oomall.service.CommentService;
import xmu.oomall.service.impl.CommentServiceImpl;
import xmu.oomall.util.Config;

/**
 * Dao
 *
 * @author: byl
 * @date: Created in 22:00 2019/12/5
 * TODO: finish logger
 **/

@Repository
public class CommentDao {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Config config;

    @Autowired
    private CommentService commentService;

    /**
     * 保存新的评论，包括评论明细
     * @param comment 评论
     * @return 结果
     */
    public Integer saveComment(Comment comment) { return commentMapper.addComment(comment); }

    /**
     * 根据id返回评论对象
     * @param id 评论id
     * @return 评论对象，带评论明细
     */
    public Comment findCommentById(Integer id){ return commentMapper.findCommentById(id); }

    /**
     * 根据userId返回评论数量
     * 本来被生成id方法调用的
     * @param userId 用户id
     * @return 某个用户评论数量
     */
    public Integer numOfCommentsByUser(Integer userId) { return commentMapper.showCommentsByUser(userId).size(); }

    /**
     * 更新评论审核
     * @param comment
     * @return 更新结果
     */
    public Integer updateReview(Comment comment){ return commentMapper.updateComment(comment); };
}
