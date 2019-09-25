package com.king.service.impl;

import com.king.dao.ItemsDao;
import com.king.domain.Items;
import com.king.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: maven
 * @Package: com.king.service.impl
 * @ClassName: ItemsServiceImpl
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/3 12:07
 * @Version: 1.0
 */

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsDao itemsDao;

    @Override
    public Items findById(Integer id) {
        return itemsDao.findById(id);
    }
}
