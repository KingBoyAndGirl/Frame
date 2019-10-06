package com.king.es.test;

import com.king.es.domain.Item;
import com.king.es.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: crawlerfirst
 * @Package: com.king.es.test
 * @ClassName: ESTest
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/6 10:04
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ESTest {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //创建索引和映射
    @Test
    public void createIndex(){
        elasticsearchTemplate.createIndex(Item.class);
        elasticsearchTemplate.putMapping(Item.class);
    }

    //新增
    @Test
    public void testSave(){
        Item item = new Item();
        item.setId(100);
        item.setTitle("SpringData ES");
        item.setContent("今天我们使用springdata es完成搜索功能");

        itemService.save(item);
    }

    //修改，和新增的代码是一样的，如果id存在就是修改，如果id不存在就是新增
    @Test
    public void testUpdate(){
        Item item = new Item();
        item.setId(100);
        item.setTitle("SpringData ES");
        item.setContent("今天我们使用springdata es完成job搜索功能。");

        itemService.save(item);
    }

    //删除
    @Test
    public void testDelete(){
        Item item = new Item();
        item.setId(100);

        itemService.delete(item);
    }

    //批量保存
    @Test
    public void testSaveAll(){
        //创建集合
        List<Item> list=new ArrayList<Item>();

        //封装数据
        for (int i = 1; i < 100; i++) {
            Item item = new Item();
            item.setId(i);
            item.setTitle("SpringData ES "+i);
            item.setContent("今天我们使用springdata es完成job搜索功能。"+i);
            list.add(item);
        }
        //批量保存
        itemService.saveAll(list);
    }

    //查询所有数据
    @Test
    public void testFindAll(){
        Iterable<Item> items = itemService.findAll();
        items.forEach(item -> System.out.println(item));
    }

    //分页查询
    @Test
    public void testFindByPage(){
        Page<Item> page=itemService.finByPage(1,30);
        page.forEach(item -> System.out.println(item));
    }

    //复杂查询
    //根据title和context进行查询，交集
    @Test
    public void testFindByTitleAndContext(){
        List<Item> items=itemService.findByTitleAndContent("ES","job");

        items.forEach(item -> System.out.println(item));
    }

    //根据title或者content进行分页查询，并集
    @Test
    public void testFindByTitleOrContent(){
        Page<Item> page=itemService.findByTitleOrContent("22","23",1,5);

        page.forEach(item -> System.out.println(item));
    }


    //根据title和Content和id范围分页查询
    @Test
    public void findByTitleAndContentAndIdBetween() {
        Page<Item> items = itemService.findByTitleAndContentAndIdBetween("ES", "job", 10, 16, 1, 5);
        for (Item item : items.getContent()) {
            System.out.println(item);
        }
    }
}
