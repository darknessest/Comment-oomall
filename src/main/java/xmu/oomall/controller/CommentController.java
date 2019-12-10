package xmu.oomall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmu.oomall.domain.Comment;
import xmu.oomall.service.CommentService;
import xmu.oomall.util.ResponseUtil;

import java.util.List;

/**
 * @author byl
 */
@RestController
@RequestMapping(produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private CommentService commentService;

    /**
     * 更新数据库中的货品库存量
     *
     * @param userId
     * @param productId
     * @param topicId
     * @param content
     * @param star
     * @return 更改结果
     * 0    - saved successfully
     * 1    - not saved
     * -1   - error
     */
    @PostMapping(path = "/commentCreate", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public Integer createComment(Integer userId, String content, Short star, Integer productId, Integer topicId) {

        int creatingStatus = 0;
        Comment newComment = new Comment();

        if (userId != null && productId != null) {
            newComment.setUserId(userId);
            logger.debug("User with ID: " + userId + " creates new comment");

            newComment.setContent(content);
            logger.debug("Setting content");

            newComment.setType((short) 0);
            logger.debug("Setting stars");

            newComment.setStar(star);
            logger.debug("Setting Stars");

            newComment.setProductId(productId);
            logger.debug("Setting product id");

            // TODO: check what is topicId, and is there any way to get it from product information or any other source
            newComment.setTopicId(topicId);
            logger.debug("Setting topic id");

            newComment.setId(commentService.generateId(newComment));
            logger.debug("Newly generated id: " + newComment.getId());

            logger.debug("Passing new comment to service");
            commentService.makeComment(newComment);

            creatingStatus = 1;
        }
        return creatingStatus;
    }

    /**
     * 审核评论
     *
     * @param commentId
     * @param statusCode
     */
    @PostMapping(path = "/{commentId}/review", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public void reviewComment(Integer commentId, Short statusCode) {
        logger.debug("starting to review comment");
        commentService.reviewComment(commentId, statusCode);

    }

    /**
     * 获得数据库中的货品库存量
     *
     * @param userId
     */
    @GetMapping("/{userId}/comments")
    void getUserComments(Integer userId) {
        logger.debug("starting to show comments by user");
        List<Comment> userComments = commentService.showCommentsByUser(userId);

    }

    /**
     * 获得数据库中的用户所有评论
     *
     * @param productId
     */
    @GetMapping("/product/{productId}/comments")
    public Object getProductComments(Integer productId) {
        Object retObj = null;

        logger.debug("starting to show comments by product");
        List<Comment> productComments = commentService.showCommentsByProduct(productId);

        if (productComments.isEmpty()) {
            retObj = ResponseUtil.badArgument();
            return retObj;
        }

        retObj = ResponseUtil.ok(retObj);

        return retObj;
    }


}
