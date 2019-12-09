package xmu.oomall.controller.vo;

import java.math.BigDecimal;

/**
 * @author byl
 * get /commentCreate 方法返回的VO
 */
public class CommentCreateVo {
    private Integer id;
    /**
     * 发表评论的用户的id
     */
    private Integer userId;

    @Override
    public String toString() {
        return "CommentCreateVo{" +
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

    /**
     * 发表评论的内容
     */
    private String content;
    /**
     * 评论的状态 0：未审核 1：审核通过 2：审核失败
     */
    private Short statusCode;
    /**
     * 发表评论的类型
     */
    private Short type;
    /**
     * 发表评论的星级
     */
    private Short star;
    /**
     * 评论的产品的id
     */
    private Integer productId;
    /**
     * 评论的专题的id
     */
    private Integer topicId;

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
}


