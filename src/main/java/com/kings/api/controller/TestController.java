package com.kings.api.controller;

import model.UserBean;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Reader;
import java.util.logging.Logger;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("test01")
    @ResponseBody
    private UserBean test(){
        String resource = "mybatis-config.xml";
        Reader reader = null;
        SqlSession sqlSession = null;
        Logger logger = null;
        try {
            logger = Logger.getLogger(TestController.class.getName());
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = factory.openSession();
            UserBean user = sqlSession.selectOne("findById", 15);
            logger.info(user.toString());
            sqlSession.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            sqlSession.close();
        }
    }
}
