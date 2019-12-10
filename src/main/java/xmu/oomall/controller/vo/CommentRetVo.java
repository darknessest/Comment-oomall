package xmu.oomall.controller.vo;

import xmu.oomall.domain.Comment;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author byl
 * 获得评论的VO
 */
public class CommentRetVo {
    List<Comment> commentList;

    @Override
    public String toString() {
        return "CommentVo{" +
                "commentList=" + commentList +
                '}';
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }
}


