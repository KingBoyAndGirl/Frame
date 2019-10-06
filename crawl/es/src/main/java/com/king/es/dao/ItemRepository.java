package com.king.es.dao;

import com.king.es.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.es.dao
 * @ClassName: ItemRepository
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/6 9:52
 * @Version: 1.0
 */
public interface ItemRepository  extends ElasticsearchRepository<Item,Integer> {


    List<Item> findByTitleAndContent(String title, String content);

    Page<Item> findByTitleOrContent(String title, String content, Pageable pageable);

    Page<Item> findByTitleAndContentAndIdBetween(String title, String content, int min, int max, Pageable pageable);
}
