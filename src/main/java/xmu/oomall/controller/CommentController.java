package xmu.oomall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.controller.vo.CommentRetVo;
import xmu.oomall.controller.vo.CommentVo;
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
     * @param comment
     * @return 更改结果
     * 0    - saved successfully
     * 1    - not saved
     * -1   - error
     */
    @PostMapping(path = "/commentCreate", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public Object createComment(@RequestBody Comment comment) {
        Object retObj;

        if (comment.getUserId() != null && comment.getProductId() != null) {
            logger.debug("Comment: " + comment.toString() + " has been received in controller");
            commentService.makeComment(comment);
            retObj = ResponseUtil.ok();
        } else {
            logger.debug("Comment: " + comment.toString() + " is empty or corrupted, so it hasn't been added");
            retObj = ResponseUtil.badArgument();
        }

        return retObj;
    }

    /**
     * 审核评论
     *
     * @param commentId
     * @param statusCode
     */
    @PutMapping(path = "/{commentId}/review", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public void reviewComment(@PathVariable Integer commentId, @RequestParam("statusCode") Short statusCode) {
        logger.debug("starting to review comment with id: " + commentId.toString() + " and new status code: " + statusCode.toString());

        commentService.reviewComment(commentId, statusCode);
    }

    /**
     * 更新评论，审核
     *
     * @param comment
     */
    @PutMapping(path = "/comments/{commentId}", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public Object updateComment(@RequestBody Comment comment) {
        Object retObj;

        if (comment.getUserId() != null && comment.getProductId() != null) {
            logger.debug("Comment: " + comment.toString() + " has been received in controller");
            commentService.editComment(comment);
            retObj = ResponseUtil.ok();
        } else {
            logger.debug("Comment: " + comment.toString() + " is empty or corrupted, so it hasn't been edited");
            retObj = ResponseUtil.badArgument();
        }

        return retObj;
    }

    /**
     * 获得数据库中的货品库存量
     *
     * @param userId
     */
    @GetMapping("/{userId}/comments")
    public Object getUserComments(@PathVariable Integer userId) {
        Object retObj;
        CommentRetVo retVO = new CommentRetVo();

        logger.debug("starting to show comments by user");
        List<Comment> userComments = commentService.showCommentsByUser(userId);

        if (userComments.isEmpty()) {
            retObj = ResponseUtil.badArgument();
            return retObj;
        } else {
            logger.debug("received comments by productId isn't empty!");
        }
        retVO.setCommentList(userComments);

        retObj = ResponseUtil.ok(retVO);

        return retObj;
    }

    /**
     * 获得数据库中的用户所有评论
     *
     * @param productId
     */
    @GetMapping("/product/{productId}/comments")
    public Object getProductComments(@PathVariable Integer productId) {
        Object retObj;
        CommentRetVo retVO = new CommentRetVo();

        logger.debug("starting to show comments by productId");
        List<Comment> productComments = commentService.showCommentsByProduct(productId);

        if (productComments.isEmpty()) {
            retObj = ResponseUtil.badArgument();
            return retObj;
        } else {
            logger.debug("received comments by productId isn't empty!");
        }
        retVO.setCommentList(productComments);

        retObj = ResponseUtil.ok(retVO);

        return retObj;
    }


}
