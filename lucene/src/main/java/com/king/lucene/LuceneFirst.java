package com.king.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.awt.*;
import java.io.File;

/**
 * @ProjectName: lucene
 * @Package: com.king.lucene
 * @ClassName: LuceneFirst
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/15 18:17
 * @Version: 1.0
 */
public class LuceneFirst {

    @Test
    public void createIndex() throws Exception{
//        第一步：创建一个java工程，并导入jar包。

//        第二步：创建一个indexwriter对象。
//        1）指定索引库的存放位置Directory对象
        Directory directory = FSDirectory.open(new File("D:\\Project\\frame\\lucene\\src\\main\\resources\\index").toPath());
//        2）指定一个IndexWriterConfig对象。
        IndexWriterConfig config=new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter=new IndexWriter(directory,config);

        File dir=new File("D:\\Project\\frame\\lucene\\src\\main\\resources\\searchsource");
        File[] files = dir.listFiles();
        for (File file : files) {

            String fileName = file.getName();
            String filePath = file.getPath();
            String fileContent = FileUtils.readFileToString(file, "utf-8");

            long fileSize = FileUtils.sizeOf(file);

//        第二步：创建field对象，
            Field fieldName=new TextField("name",fileName,Field.Store.YES);
            //Field fieldPath= new TextField("path", filePath, Field.Store.YES);
            Field fieldPath= new StoredField("path", filePath);
            Field fieldContent= new TextField("content", fileContent, Field.Store.YES);
            //Field fieldSize= new TextField("size", fileSize+"", Field.Store.YES);
            Field fieldSizeValue= new LongPoint("size", fileSize);
            Field fieldSizeStore=new StoredField("size",fileSize);
//        第三步：创建document对象。将field添加到document对象中。
            Document document = new Document();
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContent);
            //document.add(fieldSize);
            document.add(fieldSizeValue);
            document.add(fieldSizeStore);
//        第四步：使用indexwriter对象将document对象写入索引库，此过程进行索引创建。并将索引和document对象写入索引库。
            indexWriter.addDocument(document);
        }

//        第五步：关闭IndexWriter对象。
        indexWriter.close();
    }

    @Test
    public void searchIndex() throws Exception{
//        第一步：创建一个Directory对象，也就是索引库存放的位置。
        Directory directory = FSDirectory.open(new File("D:\\Project\\frame\\lucene\\src\\main\\resources\\index").toPath());
//        第二步：创建一个indexReader对象，需要指定Directory对象。
        IndexReader indexReader = DirectoryReader.open(directory);
//        第三步：创建一个indexsearcher对象，需要指定IndexReader对象
        IndexSearcher indexSearcher=new IndexSearcher(indexReader);
//        第四步：创建一个TermQuery对象，指定查询的域和查询的关键词。
        Query query=new TermQuery(new Term("content","spring"));
//        第五步：执行查询。
        //参数1：查询对象 参数2：查询结果返回的最大记录数
        TopDocs topDocs = indexSearcher.search(query, 10);
//        第六步：返回查询结果。遍历查询结果并输出。
        System.out.println("查询总记录数："+topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docId = scoreDoc.doc;
            Document document = indexSearcher.doc(docId);
            System.out.println(document.get("name"));
            System.out.println(document.get("path"));
            System.out.println(document.get("size"));
            //System.out.println(document.get("content"));
            System.out.println("-----------------------------------------------");
        }
//        第七步：关闭IndexReader对象
        indexReader.close();

    }

    @Test
    public void testTokenStream() throws Exception {
        //创建一个标准分析器对象
        //Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();
        //获得tokenStream对象
        //第一个参数：域名，可以随便给一个
        //第二个参数：要分析的文本内容
        TokenStream tokenStream = analyzer.tokenStream("test", "注意：hotword.dic和ext_stopword.dic文件的格式为UTF-8，注意是无BOM 的UTF-8 编码。\n" +
                "也就是说禁止使用windows记事本编辑扩展词典文件\n");
        //添加一个引用，可以获得每个关键词
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //添加一个偏移量的引用，记录了关键词的开始位置以及结束位置
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        //将指针调整到列表的头部
        tokenStream.reset();
        //遍历关键词列表，通过incrementToken方法判断列表是否结束
        while(tokenStream.incrementToken()) {
            //关键词的起始位置
            //System.out.println("start->" + offsetAttribute.startOffset());
            //取关键词
            System.out.println(charTermAttribute);
            //结束位置
            //System.out.println("end->" + offsetAttribute.endOffset());
        }
        tokenStream.close();
    }


}
