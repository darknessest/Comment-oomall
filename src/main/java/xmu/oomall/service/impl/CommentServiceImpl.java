package xmu.oomall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmu.oomall.dao.CommentDao;
import xmu.oomall.domain.Comment;
import xmu.oomall.service.CommentService;

import java.util.List;

/**
 * @author: byl
 * @description:
 * @date: Created in 21:56 2019/12/05
 * @modified By:
 **/
@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    CommentDao commentDao;


    @Override
    public Integer generateId(Comment comment) {
        /**
         * TODO: make more intelligent ID generation
         * it also mb generated in DTO
         */

        String productId = Integer.toString(comment.getProductId());
        String topicId = Integer.toString(comment.getTopicId());
        String userId = Integer.toString(comment.getId());

        String numOfcomments = Integer.toString(commentDao.showCommentsByUser(comment.getUserId()).size() + 1);

        String id = productId + topicId + userId + numOfcomments;
        // String id = "" + productId + topicId + userId + numOfcomments;

        // return the formed integer
        return Integer.parseInt(id);
    }

    @Override
    public Integer makeComment(Comment comment) {
        return commentDao.saveComment(comment);
    }

    @Override
    public Integer editComment(Comment comment) {

        commentDao.updateComment(comment);
        return null;
    }


    @Override
    public Integer reviewComment(Integer id, Short statusCode) {
        Comment comment = commentDao.findCommentById(id);


        /**
         * reviewing
         */
        logger.debug("comment id for update: " + id);
        logger.debug("received statusCode: " + statusCode);
        comment.setStatusCode(statusCode);
        commentDao.updateComment(comment);

        return null;
    }

    /**
     * 根据productId获得所有评论
     *
     * @param userId 货品id
     * @return 货品的评论列表
     */
    @Override
    public List<Comment> showCommentsByUser(Integer userId) {
        logger.debug("Trying to get all products with userId: " + userId);
        return commentDao.showCommentsByUser(userId);
    }

    /**
     * 根据productId获得所有评论
     *
     * @param productId 货品id
     * @return 货品的评论列表
     */
    @Override
    public List<Comment> showCommentsByProduct(Integer productId) {
        logger.debug("Trying to get all products with productId: " + productId);
        return commentDao.showCommentsByProduct(productId);
    }
}
