package com.king.es;

/**
 * @ProjectName: ElasticSearchClient
 * @Package: com.king.es
 * @ClassName: Article
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/3 9:53
 * @Version: 1.0
 */
public class Article {

    private Long id;
    private String title;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
