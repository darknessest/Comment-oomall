package xmu.oomall.service;

import org.springframework.stereotype.Service;
import xmu.oomall.domain.Comment;

import java.util.List;

/**
 * 评论服务
 * @author: byl
 * @date: Created in 21:34 2019/12/05
 **/
@Service
public interface CommentService {

    /**
     * 生成评论id
     * comment is partly empty
     * @param comment
     * @return id 评论id
     */
    Integer generateID(Comment comment);

    /**
     * 生成评论
     *
     * @param comment 评论的id
     * @return 更改结果
     * 0：edited successfully
     * 1：not edited
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
    Integer editComment(Integer id);

    /**
     * 审核评论
     * 仅针对admin实施
     *
     * @param id 评论id
     * @return 审核结果
     * 评论的状态
     * 0：未审核
     * 1：审核通过
     * 2：审核失败
     */
    Integer reviewComment(Integer id, Short statusCode);

}
