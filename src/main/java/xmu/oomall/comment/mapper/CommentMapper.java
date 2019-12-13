package xmu.oomall.comment.mapper;

import org.apache.ibatis.annotations.Mapper;
import xmu.oomall.comment.domain.Comment;

import java.util.List;

/**
 * @author: byl
 * @date: Created in 22:02 2019/12/5
 **/
@Mapper
public interface CommentMapper {


    /**
     * 根据id返回评论对象
     * @param id 评论id
     * @return 评论对象，带评论明细
     */
    Comment findCommentById(Integer id);

    /**
     * 新增一个评论
     * @param comment 评论对象
     * @return 结果
     */
    int insertComment(Comment comment);

    /**
     * 删除一个评论
     * @param id 评论对象
     * @return 结果
     */
    int deleteComment(Integer id);

    /**
     * 更新一个评论
     * @param comment 评论对象
     * @return 结果
     */
    int updateComment(Comment comment);

    /**
     * 根据productId获得所有评论
     * @param productId 货品id
     * @return 货品的评论列表
     */
    List<Comment> showCommentsByProduct(Integer productId);

    /**
     * 用userId获得所有评论
     * @param userId 货品id
     * @return 用户的评论列表
     */
    List<Comment> showCommentsByUser(Integer userId);


}
