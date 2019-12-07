package xmu.oomall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.dao.CommentDao;

import xmu.oomall.domain.Comment;
import xmu.oomall.service.CommentService;
import xmu.oomall.util.Config;

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

    @Autowired
    Config config;


    @Override
    public Integer generateId(Comment comment) {
        // TODO: make more intelligent ID generation
        // it also mb generated in DTO

        String productId = Integer.toString(comment.getProductId());
        String topicId = Integer.toString(comment.getTopicId());
        String userId = Integer.toString(comment.getId());
        String numOfcomments = Integer.toString(commentDao.numOfCommentsByUser(comment.getUserId()) + 1);

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
    public Integer editComment(Integer id) {
        Comment comment = commentDao.findCommentById(id);
        /**
         * editing
         */
        return null;
    }


    @Override
    public Integer reviewComment(Integer id, Short statusCode) {
        Comment comment = commentDao.findCommentById(id);
        comment.setStatusCode(statusCode);


        commentDao.updateReview(comment);

        return null;
    }


}
