package com.king.es.repositories;

import com.king.es.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @ProjectName: springdata_elasticsearch
 * @Package: com.king.es.repositories
 * @ClassName: ArticleRepository
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/3 14:39
 * @Version: 1.0
 */
public interface ArticleRepository extends ElasticsearchRepository<Article,Long> {

    List<Article> findByTitle(String title);
    List<Article> findByTitleOrContent(String title,String content);
    List<Article> findByTitleOrContent(String title, String content, Pageable pageable);
}
