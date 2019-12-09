package xmu.oomall.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xmu.oomall.OoMallApplication;
import xmu.oomall.domain.Comment;
import xmu.oomall.service.CommentService;

@SpringBootTest(classes = OoMallApplication.class)
class CommentServiceTest {
    @Autowired
    private CommentService commentService;

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

        commentService.makeComment(cmnt);

    }
}