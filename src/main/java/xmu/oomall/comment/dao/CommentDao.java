package xmu.oomall.comment.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import xmu.oomall.comment.domain.Comment;
import xmu.oomall.comment.mapper.CommentMapper;
import xmu.oomall.comment.service.CommentService;
import xmu.oomall.comment.service.impl.CommentServiceImpl;
import xmu.oomall.comment.util.Config;

import java.util.List;

/**
 * Dao
 *
 * @author: byl
 * @date: Created in 22:00 2019/12/5
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
     *
     * @param comment 评论
     * @return 结果
     */
    public Integer saveComment(Comment comment) {
        logger.debug("CommentDao: trying to insert a comment with commentMapper");
        return commentMapper.insertComment(comment);
    }

    /**
     * 更新评论审核
     *
     * @param comment
     * @return 更新结果
     */
    public Integer updateComment(Comment comment) {
        logger.debug("Trying to update a comment: " + comment.getId() + " with commentMapper");
        return commentMapper.updateComment(comment);
    }

    /**
     * 根据id删除评论
     *
     * @param id 评论id
     * @return 更新结果
     */
    public Integer deleteComment(Integer id) {
        logger.debug("Trying to delete a comment : " + id + " with commentMapper");
        return commentMapper.deleteComment(id);
    }

    /**
     * 根据id返回评论对象
     *
     * @param id 评论id
     * @return 评论对象，带评论明细
     */
    public Comment findCommentById(Integer id) {
        logger.debug("Trying to find a comment : " + id + " with commentMapper");
        return commentMapper.findCommentById(id);
    }

    /**
     * 根据userId返回评论
     * 本来被生成id方法调用的
     *
     * @param userId 用户id
     * @return 某个用户评论
     */
    public List<Comment> findCommentsByUser(Integer userId) {
        logger.debug("Trying to find all comments by user id " + userId + " with commentMapper");
        return commentMapper.showCommentsByUser(userId);
    }

    /**
     * 根据userId返回评论
     * 本来被生成id方法调用的
     *
     * @param userId 用户id
     * @return 某个用户评论
     */
    public List<Comment> showCommentsByUser(Integer userId) {
        return commentMapper.showCommentsByUser(userId);
    }

    /**
     * 根据productId返回评论
     * 本来被生成id方法调用的
     *
     * @param productId 用户id
     * @return 某个货品所有评论
     */
    public List<Comment> showCommentsByProduct(Integer productId) {
        return commentMapper.showCommentsByProduct(productId);
    }

}
