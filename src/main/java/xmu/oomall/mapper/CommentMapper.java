package xmu.oomall.mapper;

import org.apache.ibatis.annotations.Mapper;
import xmu.oomall.domain.Comment;

import java.util.List;

/**
 * @author: byl
 * @date: Created in 22:02 2019/12/5
 **/
@Mapper
public interface CommentMapper {


    /**
     * 根据id返回订单对象
     * @param id 评论id
     * @return 订单对象，带订单明细
     */
    Comment findCommentById(Integer id);

    /**
     * 新增一个订单
     * @param order 订单对象
     * @return 结果
     */
    int addComment(Comment order);

    /**
     * 删除一个订单
     * @param comment 订单对象
     * @return 结果
     */
    int deleteComment(Comment comment);

    /**
     * 更新一个订单
     * @param comment 订单对象
     * @return 结果
     */
    int updateComment(Comment comment);

    /**
     * 用productId获得所有评论
     * @param productId 货品id
     * @return 货品的评论列表
     */
    List<Comment> showCommentsByProduct(Integer productId);

    /**
     * 用userId获得所有评论
     * @param userId 货品id
     * @return 用户的评论列表
     * TODO: if not used outside of CommentDao, replace with comments counting
     */
    List<Comment> showCommentsByUser(Integer userId);


}
