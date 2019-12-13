package xmu.oomall.comment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.comment.controller.vo.CommentVO;
import xmu.oomall.comment.domain.Comment;
import xmu.oomall.comment.service.CommentService;
import xmu.oomall.comment.util.ResponseUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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
     * @param commentVo
     * @return CommentVO 对象
     */
    @PostMapping(path = "/goods/{id}/comments", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public Object createComment(@RequestBody CommentVO commentVo, @PathVariable String id) {
        Object retObj = new CommentVO();

        if (commentVo.getComment().getUserId() != null && commentVo.getComment().getProductId() != null) {
            logger.debug("Comment: " + commentVo.toString() + " has been received in controller");
            commentService.makeComment(commentVo.getComment());

            retObj = ResponseUtil.ok();
        } else {
            logger.debug("Comment: " + commentVo.toString() + " is empty or corrupted, so it hasn't been added");
            retObj = ResponseUtil.badArgument();
        }

        return retObj;
    }

    /**
     * 更新评论，审核
     *
     * @param commentVo
     * @return CommentVO
     */
    @PutMapping(path = "/admin/comments/{id}", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public Object updateComment(@RequestBody CommentVO commentVo, @PathVariable String id) {
        Object retObj;

        if (commentVo.getComment().getUserId() != null && commentVo.getComment().getProductId() != null) {
            logger.debug("Comment: " + commentVo.toString() + " has been received in controller");
            commentService.editComment(commentVo.getComment());

            retObj = ResponseUtil.ok(commentVo.getComment());
        } else {
            logger.debug("Comment: " + commentVo.toString() + " is empty or corrupted, so it hasn't been edited");
            retObj = ResponseUtil.badArgument();
        }

        return retObj;
    }

    /**
     * 获得数据库中的货品库存量
     *
     * @return CommentVO 对象
     */
    @GetMapping("/admin/comments")
    public Object getCommentsAdmin(@RequestParam("userId") Integer userId, @RequestParam("productId") Integer productId, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        Object retObj = new CommentVO();

        logger.debug("starting to show comments by user");
        List<Comment> commentList = commentService.showCommentsByUser(userId);

        if (commentList.isEmpty()) {
            logger.debug("No comments with user id: " + userId);
            retObj = ResponseUtil.badArgument();
        } else {
            logger.debug("received comments by user id isn't empty!");
            retObj = ResponseUtil.ok(commentList);
        }

        return retObj;
    }

    /**
     * 获得数据库中的用户所有评论
     *
     * @param productId
     * @return CommentVO 对象
     */
    @GetMapping("/goods/{productId}/comments")
    public Object getProductComments(@PathVariable Integer productId) {
        Object retObj = new CommentVO();

        logger.debug("starting to show comments by productId");
        List<Comment> productComments = commentService.showCommentsByProduct(productId);

        if (productComments.isEmpty()) {
            logger.debug("No comments with product id: " + productId);
            retObj = ResponseUtil.badArgument();
        } else {
            logger.debug("received comments by productId isn't empty!");
            retObj = ResponseUtil.ok(productComments);
        }

        return retObj;
    }

    /**
     * 删除评论
     *
     * @param id
     */
    @PostMapping("/comments/{id}")
    public void deleteComments(@PathVariable Integer id) {
        logger.debug("starting to show comments by productId");
        commentService.deleteComment(id);
    }
}
