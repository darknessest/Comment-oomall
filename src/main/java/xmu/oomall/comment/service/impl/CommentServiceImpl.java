package xmu.oomall.comment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.comment.controller.vo.CommentVO;
import xmu.oomall.comment.dao.CommentDao;
import xmu.oomall.comment.domain.Comment;
import xmu.oomall.comment.service.CommentService;

import java.time.LocalDateTime;
import java.util.Collection;
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
        String userId = Integer.toString(comment.getUserId());

        String numOfcomments = Integer.toString(commentDao.findCommentsByUser(comment.getUserId()).size() + 1);

        String id = productId + userId + numOfcomments;
        // String id = "" + productId + userId + numOfcomments;
        logger.debug("Newly generated Id: " + id);
        // return the formed integer
        return Integer.parseInt(id);
    }

    @Override
    public Integer makeComment(Comment comment) {
        comment.setId(generateId(comment));
        if (commentDao.findCommentById(comment.getId()) != null) {
            logger.debug("comment with id " + comment.getId() + " already exists");
            return 1;
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        comment.setGmtCreate(localDateTime);
        comment.setGmtModified(localDateTime);

        logger.debug("Saving new comment");
        return commentDao.saveComment(comment);
    }

    @Override
    public Integer editComment(Comment comment) {
        logger.debug("comment id for editing: " + comment.getId());

        if (commentDao.findCommentById(comment.getId()) == null) {
            logger.debug("comment with id " + comment.getId() + " doesn't exist");
            return 1;
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        comment.setGmtModified(localDateTime);

        return commentDao.updateComment(comment);
    }

    @Override
    public Integer deleteComment(Integer id) {
        logger.debug("comment id for editing: " + id);

        if (commentDao.findCommentById(id) == null) {
            logger.debug("comment with id " + id + " doesn't exist");
            return 1;
        }

        return commentDao.deleteComment(id);
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
