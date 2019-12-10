package xmu.oomall.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import xmu.oomall.OoMallApplication;
import xmu.oomall.dao.CommentDao;
import xmu.oomall.domain.Comment;
import xmu.oomall.service.CommentService;


@ActiveProfiles({"test"})
@Sql({"/sql/test/schema.sql"})

@SpringBootTest(classes = OoMallApplication.class)
class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentDao commentDao;


    @Test
    void reviewTest() {
        Comment cmnt = commentDao.findCommentById(10011);
        System.out.println("Before: " + cmnt);

        cmnt.setStatusCode((short) 0);
        commentService.editComment(cmnt);

        System.out.println("After: ");
        System.out.println(commentDao.findCommentById(10011));
    }
}