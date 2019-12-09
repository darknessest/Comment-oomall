package xmu.oomall.controller;

import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import xmu.oomall.OoMallApplication;
import xmu.oomall.controller.vo.CommentCreateVo;
import xmu.oomall.dao.CommentDao;
import xmu.oomall.domain.Comment;
import xmu.oomall.mapper.CommentMapper;
import xmu.oomall.service.CommentService;
import xmu.oomall.util.JacksonUtil;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = OoMallApplication.class)
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private CommentMapper cmnt_map;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCommentTest() throws Exception {

        cmnt_map.findCommentById(1);


//        CommentController cntrlr = new CommentController();
//
//        cntrlr.createComment(7563, "Great Product !", (short) 4, 846, 110);
//
//        CommentCreateVo vo = new CommentCreateVo();
//
//        String jsonString = JacksonUtil.toJson(vo);
//
//        String responseString = this.mockMvc.perform(get("/commentCreate").contentType("application/json;charset=UTF-8").content(jsonString))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andReturn().getResponse().getContentAsString();
//
//
//        String p_cont = JacksonUtil.parseString(responseString, "content");
//        Integer p_id = JacksonUtil.parseInteger(responseString, "id");

//        System.out.println("id: " + p_id + "\n content: " + p_cont);

    }
}
