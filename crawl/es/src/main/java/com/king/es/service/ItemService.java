package com.king.es.service;

import com.king.es.domain.Item;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.es.service
 * @ClassName: ItemService
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/6 9:54
 * @Version: 1.0
 */
public interface ItemService {
    /**
     * 新增
     * @param item
     */
    void save(Item item);

    /**
     * 删除
     * @param item
     */
    void delete(Item item);

    /**
     * 批量保存
     * @param list
     */
    void saveAll(List<Item> list);

    /**
     * 查询所有数据
     * @return
     */
    Iterable<Item> findAll();

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    Page<Item> finByPage(int page, int rows);

    /**
     * 根据标题和内容查询，交集
     * @param title
     * @param content
     * @return
     */
    List<Item> findByTitleAndContent(String title, String content);

    /**
     * 根据title或者content进行分页查询，并集
     * @param title
     * @param content
     * @param page
     * @param rows
     * @return
     */
    Page<Item> findByTitleOrContent(String title, String content, int page, int rows);

    Page<Item> findByTitleAndContentAndIdBetween(String title, String content, int min, int max, int page, int rows);
}
