package com.king.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @ProjectName: lucene
 * @Package: com.king.lucene
 * @ClassName: SearchIndex
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/15 21:45
 * @Version: 1.0
 */
public class SearchIndex {
    private IndexReader indexReader;
    private IndexSearcher indexSearcher;

    @Before
    public void init() throws Exception{
        indexReader= DirectoryReader.open(FSDirectory.open(new File("D:\\Project\\frame\\lucene\\src\\main" +
                "\\resources\\index").toPath()));
        indexSearcher=new IndexSearcher(indexReader);
    }
    @Test
    public void testRangeQuery() throws Exception {
        Query query = LongPoint.newRangeQuery("size", 0l, 10000l);
        printResult(query);
    }

    private void  printResult(Query query) throws Exception{
        TopDocs topDocs=indexSearcher.search(query, 100);
        System.out.println(topDocs.totalHits);
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
        indexReader.close();
    }
}
