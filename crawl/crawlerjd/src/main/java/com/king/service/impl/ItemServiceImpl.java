package com.king.service.impl;

import com.king.dao.ItemDao;
import com.king.domain.Item;
import com.king.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.service.impl
 * @ClassName: ItemServiceImpl
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/4 13:46
 * @Version: 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    @Transactional
    public void save(Item item) {
        itemDao.save(item);
    }

    @Override
    public List<Item> findAll(Item item) {
        //声明查询条件
        Example<Item> example=Example.of(item);

        //根据查询条件进行查询
        return itemDao.findAll(example);
    }
}
