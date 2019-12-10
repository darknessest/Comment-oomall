package xmu.oomall.controller.vo;

import xmu.oomall.domain.Comment;

import java.util.List;

/**
 * @author byl
 * get /commentCreate 方法的VO
 */
public class CommentVo {

    private Integer id;
    private Integer userId;
    private String content;
    private Short statusCode;
    private Short type;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public Short getStatusCode() {
        return statusCode;
    }

    public Short getType() {
        return type;
    }

    public Short getStar() {
        return star;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    @Override
    public String toString() {
        return "CommentVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", statusCode=" + statusCode +
                ", type=" + type +
                ", star=" + star +
                ", productId=" + productId +
                ", topicId=" + topicId +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatusCode(Short statusCode) {
        this.statusCode = statusCode;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public void setStar(Short star) {
        this.star = star;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    private Short star;

    private Integer productId;

    private Integer topicId;
}


