package com.king.lucene;

import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * @ProjectName: lucene
 * @Package: com.king.lucene
 * @ClassName: IndexManager
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/15 21:15
 * @Version: 1.0
 */
public class IndexManager {

    private IndexWriter indexWriter;

    @Before
    public void init() throws Exception{
        Directory directory = FSDirectory.open(new File("D:\\Project\\frame\\lucene\\src\\main\\resources\\index").toPath());
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        //创建一个indexwriter对象
        indexWriter = new IndexWriter(directory, config);
    }

    @Test
    public void addDocument() throws Exception {
        //索引库存放路径
        //创建一个Document对象
        Document document = new Document();
        //向document对象中添加域。
        //不同的document可以有不同的域，同一个document可以有相同的域。
        document.add(new TextField("filename", "新添加的文档", Field.Store.YES));
        document.add(new TextField("content", "新添加的文档的内容", Field.Store.NO));
        //LongPoint创建索引
        document.add(new LongPoint("size", 1000l));
        //StoreField存储数据
        document.add(new StoredField("size", 1000l));
        //不需要创建索引的就使用StoreField存储
        document.add(new StoredField("path", "D:\\Project\\frame\\lucene\\src\\main\\resources\\1.txt"));
        //添加文档到索引库
        indexWriter.addDocument(document);
        //关闭indexwriter
        indexWriter.close();

    }

    //删除全部索引
    @Test
    public void deleteAllIndex() throws Exception {
        //删除全部索引
        indexWriter.deleteAll();
        //关闭indexwriter
        indexWriter.close();
    }

    //根据查询条件删除索引
    @Test
    public void deleteIndexByQuery() throws Exception {
        //创建一个查询条件
        Query query = new TermQuery(new Term("filename", "apache"));
        //根据查询条件删除
        indexWriter.deleteDocuments(query);
        //关闭indexwriter
        indexWriter.close();
    }

    //修改索引库
    @Test
    public void updateIndex() throws Exception {
        //创建一个Document对象
        Document document = new Document();
        //向document对象中添加域。
        //不同的document可以有不同的域，同一个document可以有相同的域。
        document.add(new TextField("filename", "要更新的文档", Field.Store.YES));
        document.add(new TextField("content", " Lucene 简介 Lucene 是一个基于 Java 的全文信息检索工具包," +
                "它不是一个完整的搜索应用程序,而是为你的应用程序提供索引和搜索功能。",
                Field.Store.YES));
        indexWriter.updateDocument(new Term("content", "java"), document);
        //关闭indexWriter
        indexWriter.close();
    }


}
