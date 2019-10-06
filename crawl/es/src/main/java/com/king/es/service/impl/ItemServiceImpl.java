package com.king.es.service.impl;

import com.king.es.dao.ItemRepository;
import com.king.es.domain.Item;
import com.king.es.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.es.service.impl
 * @ClassName: ItemServiceImpl
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/6 9:54
 * @Version: 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public void saveAll(List<Item> list) {
        itemRepository.saveAll(list);
    }

    @Override
    public Iterable<Item> findAll() {
       return itemRepository.findAll();
    }

    @Override
    public Page<Item> finByPage(int page, int rows) {

        return itemRepository.findAll(PageRequest.of(page,rows));
    }

    @Override
    public List<Item> findByTitleAndContent(String title, String content) {
        return itemRepository.findByTitleAndContent(title,content);
    }

    @Override
    public Page<Item> findByTitleOrContent(String title, String content, int page, int rows) {
        return itemRepository.findByTitleOrContent(title,content,PageRequest.of(page-1,rows));
    }

    @Override
    public Page<Item> findByTitleAndContentAndIdBetween(String title, String content, int min, int max, int page, int rows) {
        return itemRepository.findByTitleAndContentAndIdBetween(title,content,min,max,PageRequest.of(page-1,rows));
    }
}
