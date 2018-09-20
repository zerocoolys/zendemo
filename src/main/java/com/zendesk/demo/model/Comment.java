package com.zendesk.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long itemId;
    private long postUserId;

    public long getpId() {
        return pId;
    }

    public Comment setpId(long pId) {
        this.pId = pId;
        return this;
    }

    private long pId;

    private String content;
    private Date createTime;

    public long getId() {
        return id;
    }

    public Comment setId(long id) {
        this.id = id;
        return this;
    }

    public long getItemId() {
        return itemId;
    }

    public Comment setItemId(long itemId) {
        this.itemId = itemId;
        return this;
    }

    public long getPostUserId() {
        return postUserId;
    }

    public Comment setPostUserId(long postUserId) {
        this.postUserId = postUserId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Comment setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
