package com.yang.blog;

import com.yang.blog.service.BlogService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class GetPageTest extends BlogApplicationTests {
    @Autowired
    private BlogService blogService;

    @Test
    public void getAllBlogTest(){
        long start = System.currentTimeMillis();
        //ExecutorService exe = Executors.newFixedThreadPool(10000);
        for (int i = 0; i < 10000; i++) {
            //exe.execute(()->blogService.listRedisBlog());
            blogService.listRedisBlog();
        }
        //exe.shutdown();
        System.out.println("------------"+(System.currentTimeMillis()-start)+"-----------");
    }

    @Test
    public void getRedisBlogListTest(){

    }
}
