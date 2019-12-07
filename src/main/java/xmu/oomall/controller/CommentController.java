package xmu.oomall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmu.oomall.dao.CommentDao;
import xmu.oomall.domain.Comment;
import xmu.oomall.service.CommentService;

/**
 * @author byl
 */
@RestController
@RequestMapping(value = "/orders", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

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
     * TODO: 考虑把参数换到Comment类
     * TODO: finish mapping
     */
    @PostMapping(path = "/commentCreate", consumes = "", produces = "")
    Integer createComment(Integer userId, String content, Short star, Integer productId, Integer topicId) {

        int creatingStatus = -1, savingStatus = -1;
        Comment newComment = new Comment();


        if (userId != null && productId != null) {
            newComment.setUserId(userId);
            logger.debug("User with ID: " + userId + " creates new comment");

            newComment.setContent(content);
            logger.debug("Comment content: " + content);

            newComment.setType((short) 0);

            newComment.setStar(star);
            logger.debug("Number of stars: " + star);

            newComment.setProductId(productId);

            // TODO: check what is topicId, and is there any way to get it from product information or any other source
            newComment.setTopicId(topicId);

            newComment.setId(commentService.generateID(newComment));

            commentService.makeComment(newComment);

            creatingStatus = 1;
        }


        return creatingStatus;
    }

}