package xmu.oomall.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import xmu.oomall.OoMallApplication;
import xmu.oomall.domain.Comment;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles({"test"})
//@Sql({"/sql/test/schema.sql"})
@SpringBootTest(classes = OoMallApplication.class)
class CommentDaoTest {
    @Autowired
    private CommentDao commentDao;

    @Test
    void getCommentsTest() {
        Comment cmnt = commentDao.findCommentById(10011);

        System.out.println(cmnt);
    }

    @Test
    void deleteTest() {
        System.out.println("Before: ");
        System.out.println(commentDao.findCommentById(10011));

        commentDao.deleteComment(10011);

        System.out.println("After: ");
        System.out.println(commentDao.findCommentById(10011));
    }


    @Test
    void saveCommentTest() {
        Comment cmnt = new Comment();
        cmnt.setId(1232321);
        cmnt.setUserId(123);
        cmnt.setStar((short) 3);
        cmnt.setContent("Cool stuff!");
        cmnt.setType((short) 2);
        cmnt.setProductId(533);
        cmnt.setStatusCode((short) 0);

        commentDao.saveComment(cmnt);

        System.out.println("new comment: " + commentDao.findCommentById(1232321));

    }
}