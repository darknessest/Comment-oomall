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
    void saveCommentTest() {
        Comment cmnt = new Comment();
        cmnt.setId(1);
        cmnt.setUserId(123);
        cmnt.setStar((short) 3);
        cmnt.setContent("Great product!");
        cmnt.setType((short) 3);
        cmnt.setProductId(533);
        cmnt.setStatusCode((short) 0);

        commentDao.saveComment(cmnt);

    }
}