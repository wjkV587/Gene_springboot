package com.szu.demo;


import com.gene.dao.GeneDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class})
@SpringBootTest
public class DemoApplicationTests{

    @Autowired
    private GeneDao gd;

    @Test
    public void contextLoads() {
        System.out.println(gd.findAll());
    }



}
