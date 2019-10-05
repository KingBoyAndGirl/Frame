package com.king.service;

import com.king.domain.Item;

import java.util.List;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.service
 * @ClassName: ItemService
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/4 13:43
 * @Version: 1.0
 */
public interface ItemService {

    /**
     * 保存商品
     * @param item
     */
    public void save(Item item);

    /**
     * 根据条件查询商品
     * @param item
     * @return
     */
    public List<Item> findAll(Item item);
}
