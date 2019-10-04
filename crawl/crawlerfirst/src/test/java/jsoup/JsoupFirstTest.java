package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Set;

/**
 * @ProjectName: crawlerfirst
 * @Package: jsoup
 * @ClassName: JsoupFirstTest
 * @Author: 王团结
 * @Description:
 * @Date: 2019/10/4 11:12
 * @Version: 1.0
 */
public class JsoupFirstTest {

    @Test
    public void testUrl() throws Exception {
        //解析url地址，第一个参数是访问的url，第二个参数是访问时候的超时时间
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);

        //使用标签选择器，获取title标签中的内容
        String title = document.getElementsByTag("title").first().text();

        System.out.println(title);
    }

    @Test
    public void testString() throws Exception {
        //使用工具类读取文件，获取字符串
        String content = FileUtils.readFileToString(new File("C:\\Users\\ASUS\\Desktop\\test.html"), "utf-8");

        //解析字符串
        Document document = Jsoup.parse(content);

        String title = document.getElementsByTag("title").first().text();

        System.out.println(title);

    }

    @Test
    public void testFile() throws Exception {
        //解析文件
        Document document = Jsoup.parse(new File("C:\\Users\\ASUS\\Desktop\\test.html"), "utf-8");

        String title = document.getElementsByTag("title").first().text();

        System.out.println(title);
    }

    @Test
    public void testDOM() throws Exception {
        //解析文件,获取document对象
        Document document = Jsoup.parse(new File("C:\\Users\\ASUS\\Desktop\\test.html"), "utf-8");

        //1.根据id查询元素getElementById
        Element element = document.getElementById("city_bj");

        //2.根据标签获取元素getElementsByTag
        Element element1 = document.getElementsByTag("span").first();

        //3.根据class获取元素getElementsByClass
        Element element2 = document.getElementsByClass("class_a class_b").first();
        Element element2_1 = document.getElementsByClass("class_a").first();
        Element element2_2 = document.getElementsByClass("class_b").first();

        //4.根据属性获取元素getElementsByAttribute
        Element element3 = document.getElementsByAttribute("abc").first();


        Element element4 = document.getElementsByAttributeValue("href", "http://sh.itcast.cn").first();

        System.out.println("获取到的元素内容：" + element.text());
        System.out.println("获取到的元素内容：" + element1.text());
        System.out.println("获取到的元素内容：" + element2.text());
        System.out.println("获取到的元素内容：" + element2_1.text());
        System.out.println("获取到的元素内容：" + element2_2.text());
        System.out.println("获取到的元素内容：" + element3.text());
        System.out.println("获取到的元素内容：" + element4.text());
    }

    @Test
    public void testData() throws Exception {
        //解析文件,获取document对象
        Document document = Jsoup.parse(new File("C:\\Users\\ASUS\\Desktop\\test.html"), "utf-8");

        //根据id获取元素
        Element element = document.getElementById("test");


        //1.从元素中获取id
        String id = element.id();

        //2.从元素中获取className
        String className = element.className();
        Set<String> classNames = element.classNames();

        //3.从元素中获取属性的值attr
        String attr = element.attr("id");

        //4.从元素中获取所有属性attributes
        Attributes attributes = element.attributes();

        //5.从元素中获取文本内容text
        String text = element.text();

        System.out.println("获取到的数据是："+id);
        System.out.println("获取到的数据是："+className);
        classNames.forEach(s -> System.out.println("获取到的数据是："+s));
        System.out.println("获取到的数据是："+attr);
        System.out.println("获取到的数据是："+attributes.toString());
        System.out.println("获取到的数据是："+text);

    }

    @Test
    public void testSelector() throws Exception{
        //解析文件,获取document对象
        Document document = Jsoup.parse(new File("C:\\Users\\ASUS\\Desktop\\test.html"), "utf-8");


        //tagname: 通过标签查找元素，比如：span
        Elements elements = document.select("span");

        //#id: 通过ID查找元素，比如：# city_bj
        Element element1 = document.select("#city_bj").first();

        //.class: 通过class名称查找元素，比如：.class_a
        Element element2 = document.select(".class_a").first();

        //[attribute]: 利用属性查找元素，比如：[abc]
        Element element3 = document.select("[abc]").first();

        //[attr=value]: 利用属性值来查找元素，比如：[class=s_name]
        Elements elements1 = document.select("[class=s_name]");

        elements.forEach(element -> System.out.println(element.text()));
        System.out.println("获取到的结果："+element1.text());
        System.out.println("获取到的结果："+element2.text());
        System.out.println("获取到的结果："+element3.text());
        elements1.forEach(element -> System.out.println(element.text()));

    }
    @Test
    public void testSelector2() throws Exception{
        //解析文件,获取document对象
        Document document = Jsoup.parse(new File("C:\\Users\\ASUS\\Desktop\\test.html"), "utf-8");

        //el#id: 元素+ID，比如： h3#city_bj
        Element element = document.select("h3#city_bj").first();

        //el.class: 元素+class，比如： li.class_a
        Element element1 = document.select("li.class_a").first();

        //el[attr]: 元素+属性名，比如： span[abc]
        Element element2 = document.select("span[abc]").first();

        //任意组合: 比如：span[abc].s_name
        Element element3 = document.select("span[abc].s_name").first();

        //ancestor child: 查找某个元素下子元素，比如：.city_con li 查找"city_con"下的所有li
        Elements elements = document.select(".city_con li");

        //parent > child: 查找某个父元素下的直接子元素，比如：
        //.city_con > ul > li 查找city_con第一级（直接子元素）的ul，再找所有ul下的第一级li
        Elements elements1 = document.select(".city_con > ul > li");

        //parent > *: 查找某个父元素下所有直接子元素
        Elements elements2 = document.select(".city_con > ul > *");


        System.out.println("element获取到的结果："+element.text());
        System.out.println("element1获取到的结果："+element1.text());
        System.out.println("element2获取到的结果："+element2.text());
        System.out.println("element3获取到的结果："+element3.text());
        elements.forEach(element4 -> System.out.println("elements获取到的结果是"+element4.text()));
        elements1.forEach(element4 -> System.out.println("elements1获取到的结果是"+element4.text()));
        elements2.forEach(element4 -> System.out.println("elements2获取到的结果是"+element4.text()));
    }
}
