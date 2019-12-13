package xmu.oomall.comment.controller.vo;

import xmu.oomall.comment.domain.Comment;

import java.util.List;

/**
 * @author byl
 * 获得评论的VO
 */
public class CommentVO {
    Comment comment;

    public CommentVO(Comment comment) {
        this.comment = comment;
    }

    public CommentVO() {
        this.comment = new Comment();
    }

    @Override
    public String toString() {
        return "CommentVo{" +
                "comment=" + comment +
                '}';
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}


