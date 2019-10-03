import com.king.es.entity.Article;
import com.king.es.repositories.ArticleRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

/**
 * @ProjectName: springdata_elasticsearch
 * @Package: PACKAGE_NAME
 * @ClassName: SpringDataElasticSearchTest
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/3 14:41
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringDataElasticSearchTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void createIndex() throws Exception{
        //创建索引，，并配置映射关系
        template.createIndex(Article.class);
        //配置映射关系
        //template.putMapping(Article.class);
    }

    @Test
    public void addDocument() throws Exception{
        for (int i = 10; i <=20; i++) {
            Article article = new Article();
            article.setId(i);
            article.setTitle("主创团队和演员揭秘《奋斗吧 中华儿女》排演始末"+i);
            article.setContent("张裕卡斯特酒庄获两金牌 双汇童品智趣多亮相成都");
            articleRepository.save(article);

        }
    }

    @Test
    public void deleteDocumentById() throws Exception{
        articleRepository.deleteById(1L);

    }

    @Test
    public void findAll() throws Exception{
        Iterable<Article> articles = articleRepository.findAll();
        articles.forEach(a-> System.out.println(a));
    }

    @Test
    public void testFindById() throws Exception{
        Optional<Article> optional = articleRepository.findById(1L);
        Article article = optional.get();
        System.out.println(article);
    }

    @Test
    public void testFindByTitle() throws Exception{
        List<Article> maven = articleRepository.findByTitle("maven是一个工程构建工程");
        maven.stream().forEach(article -> System.out.println(article));
    }

    @Test
    public void testFindByTitleOrContent() throws Exception{
        Pageable pageable= PageRequest.of(1,15);
        articleRepository.findByTitleOrContent("maven","双汇童品",pageable)
                .forEach(article -> System.out.println(article));
    }


    @Test
    public void TestNativeSearchQuery() throws Exception{
        NativeSearchQuery query=new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("maven是一个工程构建工具").defaultField("title"))
                .withPageable(PageRequest.of(0,15))
                .build();

        List<Article> articles = template.queryForList(query, Article.class);
        articles.forEach(article -> System.out.println(article));
    }
}
