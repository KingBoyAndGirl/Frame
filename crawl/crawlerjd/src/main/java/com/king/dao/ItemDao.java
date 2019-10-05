package com.king.dao;

import com.king.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.dao
 * @ClassName: ItemDao
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/4 13:41
 * @Version: 1.0
 */
public interface ItemDao extends JpaRepository<Item,Long> {
}
