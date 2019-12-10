package xmu.oomall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmu.oomall.dao.CommentDao;
import xmu.oomall.domain.Comment;
import xmu.oomall.service.CommentService;

import java.time.LocalDateTime;
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
        /*
         * TODO: make more intelligent ID generation
         * it also mb generated in DTO
         */

        String productId = Integer.toString(comment.getProductId());
        String topicId = Integer.toString(comment.getTopicId());
        String userId = Integer.toString(comment.getUserId());

        String numOfcomments = Integer.toString(commentDao.showCommentsByUser(comment.getUserId()).size() + 1);

        String id = productId + topicId + userId + numOfcomments;
        // String id = "" + productId + topicId + userId + numOfcomments;
        logger.debug("Newly generated Id: " + id);
        // return the formed integer
        return Integer.parseInt(id);
    }

    @Override
    public Integer makeComment(Comment comment) {
        LocalDateTime localDateTime = LocalDateTime.now();
        comment.setGmtCreate(localDateTime);
        comment.setId(generateId(comment));

        return commentDao.saveComment(comment);
    }

    @Override
    public Integer editComment(Comment comment) {
        logger.debug("comment id for editing: " + comment);
        LocalDateTime localDateTime = LocalDateTime.now();
        comment.setGmtModified(localDateTime);

        commentDao.updateComment(comment);
        return null;
    }

    @Override
    public List<Comment> showCommentsByUser(Integer userId) {
        logger.debug("Trying to get all products with userId: " + userId);
        return commentDao.showCommentsByUser(userId);
    }

    @Override
    public List<Comment> showCommentsByProduct(Integer productId) {
        logger.debug("Trying to get all products with productId: " + productId);
        return commentDao.showCommentsByProduct(productId);
    }
}
