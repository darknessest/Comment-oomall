package xmu.oomall.comment.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import xmu.oomall.comment.CommentApplication;
import xmu.oomall.comment.domain.Comment;

import java.time.LocalDateTime;

@SpringBootTest(classes = CommentApplication.class)
class CommentDaoTest {
    @Autowired
    private CommentDao commentDao;

    @Test
    @Rollback
    void getCommentsTest() {
        Comment cmnt = commentDao.findCommentById(1232321);

        System.out.println("Fount comment: " + cmnt);
    }

    @Test
    @Rollback
    void deleteTest() {
        System.out.println("Before: ");
        System.out.println(commentDao.findCommentById(1232321));

        commentDao.deleteComment(1232321);

        System.out.println("After: ");
        System.out.println(commentDao.findCommentById(1232321));
    }


    @Test
    @Rollback
    void saveCommentTest() {
        if (commentDao.findCommentById(1232321) != null)
            commentDao.deleteComment(1232321);

        Comment cmnt = new Comment();
        cmnt.setId(1232321);
        cmnt.setUserId(123);
        cmnt.setStar((short) 3);
        cmnt.setContent("Cool stuff!");
        cmnt.setProductId(533);
        LocalDateTime localDateTime = LocalDateTime.now();
        cmnt.setGmtCreate(localDateTime);
        cmnt.setGmtModified(localDateTime);
        cmnt.setStatusCode((short) 0);

        commentDao.saveComment(cmnt);

        System.out.println("new comment: " + commentDao.findCommentById(1232321));
    }

    @Test
    @Rollback
    void showCommentByProductIdTest() {
        System.out.println(commentDao.showCommentsByProduct(5422));


    }

}