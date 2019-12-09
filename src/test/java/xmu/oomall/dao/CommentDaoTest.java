package xmu.oomall.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xmu.oomall.OoMallApplication;
import xmu.oomall.domain.Comment;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OoMallApplication.class)
class CommentDaoTest {
    @Autowired
    private CommentDao commentDao;

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