package xmu.oomall.comment.service;

import org.springframework.stereotype.Service;
import xmu.oomall.comment.controller.vo.CommentVO;
import xmu.oomall.comment.domain.Comment;

import java.util.Collection;
import java.util.List;

/**
 * 评论服务
 *
 * @author: byl
 * @date: Created in 21:34 2019/12/05
 **/
@Service
public interface CommentService {

    /**
     * 生成评论id
     * comment is partly empty
     *
     * @param comment
     * @return id 评论id
     */
    Integer generateId(Comment comment);

    /**
     * 生成评论
     *
     * @param comment 评论的id
     * @return 更改结果
     * 0：saved successfully
     * 1：not saved
     * -1：error
     */
    Integer makeComment(Comment comment);

    /**
     * 更改评论
     *
     * @param id 评论的id
     * @return 更改结果
     * 0：edited successfully
     * 1：not edited
     * -1：error
     */
    Integer editComment(Comment id);

    /**
     * 根据id删除评论
     *
     * @param id 评论的id
     * @return 更改结果
     * 0：edited successfully
     * 1：not edited
     * -1：error
     */
    Integer deleteComment(Integer id);

    /**
     * 根据productId获得所有评论
     *
     * @param userId 货品id
     * @return 货品的评论列表
     */
    List<Comment> showCommentsByUser(Integer userId);

    /**
     * 根据productId获得所有评论
     *
     * @param productId 货品id
     * @return 货品的评论列表
     */
    List<Comment> showCommentsByProduct(Integer productId);


}
