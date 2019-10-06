package com.king.es.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.domain.es
 * @ClassName: Item
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/6 9:51
 * @Version: 1.0
 */
@Document(indexName = "item",type = "item")
public class Item {

    @Id
    private Integer id;
    @Field(store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.text)
    private String title;
    @Field(store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.text)
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
