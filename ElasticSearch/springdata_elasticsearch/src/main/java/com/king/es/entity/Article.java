package com.king.es.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ProjectName: springdata_elasticsearch
 * @Package: com.king.es.entity
 * @ClassName: Article
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/3 14:32
 * @Version: 1.0
 */
@Document(indexName = "sdes_blog",type = "article")
public class Article {

    @Id
    private long id;

    @Field(type = FieldType.text,store = true,analyzer = "ik_smart")
    private String title;
    @Field(type = FieldType.text,store = true,analyzer = "ik_smart")
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
