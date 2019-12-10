package xmu.oomall.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import xmu.oomall.OoMallApplication;
import xmu.oomall.dao.CommentDao;
import xmu.oomall.domain.Comment;
import xmu.oomall.util.JacksonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = OoMallApplication.class)
@ActiveProfiles({"test"})
@Sql({"/sql/test/schema.sql"})
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentDao commentDao;


    @Test
    void getCommentsByProductIdTest() throws Exception {

        String responseString = this.mockMvc.perform(get("/123/comments").contentType("application/json;charset=UTF-8"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();


        System.out.println(responseString);
    }

    @Test
    void getCommentsByUserIdTest() throws Exception {

        String responseString = this.mockMvc.perform(get("/product/12412/comments").contentType("application/json;charset=UTF-8"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();


        System.out.println(responseString);
    }


    @Test
    void commentCreationTest() throws Exception {
        // in case it exists
        commentDao.deleteComment(56832341);

        Comment cmnt = new Comment();
        cmnt.setContent("Trying out this comment");
        cmnt.setId(0);
        cmnt.setProductId(56);
        cmnt.setStar((short) 5);
        cmnt.setStatusCode((short) 2);
        cmnt.setTopicId(83);
        cmnt.setType((short) 1);
        cmnt.setUserId(234);


        String jsonString = JacksonUtil.toJson(cmnt);

        String responseString = this.mockMvc.perform(post("/commentCreate").accept(MediaType.APPLICATION_JSON).contentType("application/json;charset=UTF-8").content(jsonString))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(commentDao.findCommentById(56832341));
        System.out.println(responseString);
    }


    @Test
    void commentEditingTest() throws Exception {
        Comment cmnt = commentDao.findCommentById(10011);
        System.out.println("Before: " + cmnt);
        cmnt.setContent("Bad");
        cmnt.setStar((short) 1);
        cmnt.setStatusCode((short) 1);
        String jsonString = JacksonUtil.toJson(cmnt);


        String responseString = this.mockMvc.perform(put("/comments/10011").accept(MediaType.APPLICATION_JSON).contentType("application/json;charset=UTF-8").content(jsonString))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        System.out.println("After: " + commentDao.findCommentById(10011));
        System.out.println(responseString);
    }
}
