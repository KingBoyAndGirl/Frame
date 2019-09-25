package com.king.dao;

import com.king.domain.Items;

/**
 * @ProjectName: maven
 * @Package: com.king.dao
 * @ClassName: ItemsDao
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/3 10:41
 * @Version: 1.0
 */
public interface ItemsDao {

    public Items findById(Integer id);
}
