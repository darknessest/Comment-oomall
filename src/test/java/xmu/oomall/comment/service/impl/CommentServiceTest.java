package xmu.oomall.comment.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import xmu.oomall.comment.CommentApplication;
import xmu.oomall.comment.dao.CommentDao;
import xmu.oomall.comment.domain.Comment;
import xmu.oomall.comment.service.CommentService;

import java.util.List;

import static xmu.oomall.comment.util.Common.getRandomNum;


@SpringBootTest(classes = CommentApplication.class)
class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentDao commentDao;


    @Test
    @Rollback
    void addComments() {

        for (int i = 0; i < 10; i++) {
            Comment newCmnt = new Comment();
            newCmnt.setUserId(233);
            newCmnt.setContent("默认好评");
            newCmnt.setProductId(5421+i);
            newCmnt.setStar((short) 5);
            newCmnt.setStatusCode((short) 0);

            commentService.makeComment(newCmnt);
        }

    }

    @Test
    @Rollback
    void reviewTest() {
        Comment cmnt = commentDao.findCommentById(54211231);
        System.out.println("Before: " + cmnt);

        cmnt.setStatusCode((short) 0);
        commentService.editComment(cmnt);

        System.out.println("After: ");
        System.out.println(commentDao.findCommentById(54211231));
    }
}