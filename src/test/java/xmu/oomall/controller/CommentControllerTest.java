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
import xmu.oomall.controller.vo.CommentVo;
import xmu.oomall.mapper.CommentMapper;
import xmu.oomall.util.JacksonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    void getCommentTest() throws Exception {

        CommentVo vo = new CommentVo();

        String jsonString = JacksonUtil.toJson(vo);

        String responseString = this.mockMvc.perform(get("/product/12412/comments").contentType("application/json;charset=UTF-8"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();


        String content = JacksonUtil.parseString(responseString, "content");
//        Integer fee = JacksonUtil.parseInteger(data, "fee");
//        System.out.println("");
        System.out.println(content);

    }
}
