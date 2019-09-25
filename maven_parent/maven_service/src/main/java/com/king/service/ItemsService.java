package com.king.service;

import com.king.domain.Items;

/**
 * @ProjectName: maven
 * @Package: com.king.service
 * @ClassName: ItemsService
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/3 12:06
 * @Version: 1.0
 */
public interface ItemsService {

    public Items findById(Integer id);
}
