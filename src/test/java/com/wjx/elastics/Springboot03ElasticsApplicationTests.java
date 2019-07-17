//package com.wjx.elastics;
//
//import com.wjx.elastics.bean.Article;
//import io.searchbox.client.JestClient;
//import io.searchbox.core.Index;
//import io.searchbox.core.Search;
//import io.searchbox.core.SearchResult;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class Springboot03ElasticsApplicationTests {
//
//    @Autowired
//    JestClient jestClient;
//
//    @Test
//    public void contextLoads() {
//        //1、给elasticsearch中索引（保存）一个文档；
//        Article article = new Article();
//        article.setId(1);
//        article.setTitle("好消息");
//        article.setAuthor("zhangsan");
//        article.setContent("Hello World");
//
//        //构建一个索引功能
//        Index index = new Index.Builder(article).index("atguigu").type("news").build();
//
//        try {
//            //执行
//            jestClient.execute(index);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    //测试搜索
//    @Test
//    public void search() {
//
//        //查询表达式
//        String json = "{\n" +
//                "    \"query\" : {\n" +
//                "        \"match\" : {\n" +
//                "            \"content\" : \"hello\"\n" +
//                "        }\n" +
//                "    }\n" +
//                "}";
//        //构建搜索功能
//        Search search = new Search.Builder(json).addIndex("atguigu").addType("news").build();
//        //执行
//        try {
//            SearchResult result = jestClient.execute(search);
//            System.out.println( result.getJsonString() );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
