package xmu.oomall.comment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import xmu.oomall.comment.CommentApplication;
import xmu.oomall.comment.controller.vo.CommentVO;
import xmu.oomall.comment.dao.CommentDao;
import xmu.oomall.comment.domain.Comment;
import xmu.oomall.comment.service.CommentService;
import xmu.oomall.comment.util.JacksonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CommentApplication.class)
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CommentService commentService;

    @Test
    @Rollback
    void getCommentsByUserIdTest() throws Exception {
//        Integer userId, Integer productId, Integer page, Integer limit
        String responseString = this.mockMvc.perform(get("/admin/comments").contentType("application/json;charset=UTF-8").param("userId", String.valueOf(233)).param("productId", String.valueOf(0)).param("page", String.valueOf(1)).param("limit", String.valueOf(12)))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();


        System.out.println(responseString);
    }


    @Test
    @Rollback
    void getCommentsByProductIdTest() throws Exception {

        String responseString = this.mockMvc.perform(get("/goods/5422/comments").contentType("application/json;charset=UTF-8"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseString);
    }


    @Test
    @Rollback
    void commentCreationTest() throws Exception {
        // in case it exists
        if (commentDao.findCommentById(562341) != null)
            commentDao.deleteComment(562341);

        Comment cmnt = new Comment();

        cmnt.setContent("Trying out this comment");
        cmnt.setId(0);
        cmnt.setProductId(56);
        cmnt.setStar((short) 5);
        cmnt.setStatusCode((short) 2);
        cmnt.setUserId(234);
        cmnt.setId(commentService.generateId(cmnt));
        CommentVO cmntVo = new CommentVO(cmnt);

        String jsonString = JacksonUtil.toJson(cmntVo);

        String responseString = this.mockMvc.perform(post("/goods/" + cmnt.getId() + "/comments").accept(MediaType.APPLICATION_JSON).contentType("application/json;charset=UTF-8").content(jsonString))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(commentDao.findCommentById(562341));
        System.out.println(responseString);
    }


    @Test
    @Rollback
    void commentEditingTest() throws Exception {

        Comment cmnt = commentDao.findCommentById(562341);
        System.out.println("Before: " + cmnt);
        cmnt.setContent("Bad");
        cmnt.setStar((short) 1);
        cmnt.setStatusCode((short) 1);

        CommentVO cmntVo = new CommentVO(cmnt);
        String jsonString = JacksonUtil.toJson(cmntVo);

        String responseString = this.mockMvc.perform(put("/admin/comments/562341").accept(MediaType.APPLICATION_JSON).contentType("application/json;charset=UTF-8").content(jsonString))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        System.out.println("After: " + commentDao.findCommentById(562341));
        System.out.println(responseString);
    }

    @Test
    @Rollback
    void commentDeletionTest() throws Exception {
        // in case it exists
        if (commentDao.findCommentById(562341) == null) {
            Comment cmnt = new Comment();

            cmnt.setContent("Trying out this comment");
            cmnt.setId(0);
            cmnt.setProductId(56);
            cmnt.setStar((short) 5);
            cmnt.setStatusCode((short) 2);
            cmnt.setUserId(234);
            commentService.makeComment(cmnt);
        }


        String responseString = this.mockMvc.perform(post("/comments/562341").accept(MediaType.APPLICATION_JSON).contentType("application/json;charset=UTF-8").param("id", String.valueOf(562341)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();
        if (commentDao.findCommentById(562341) == null)
            System.out.println("Deletion of 562341 was successful");
    }
}
