package com.study.dangdang.sharding.jdbc;

import com.study.dangdang.sharding.jdbc.entity.User;
import com.study.dangdang.sharding.jdbc.service.UserService;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 描述: ${DESCRIPTION}
 *
 * @author pangpeijie
 * @create 2017-11-10 20:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-database.xml",
    "classpath*:spring/spring-sharding.xml" })
public class ShardingJdbcMybatisTest {

  @Resource
  public UserService userService;

  @Test
  public void testUserInsert() {
    User u = new User();
    u.setUserId(11);
    u.setAge(25);
    u.setName("github");
    Assert.assertEquals(userService.insert(u), true);
  }

  @Test
  public void testFindAll(){
    List<User> users = userService.findAll();
    if(null != users && !users.isEmpty()){
      for(User u :users){
        System.out.println(u);
      }
    }
  }

  @Test
  public void testSQLIN(){
    List<User> users = userService.findByUserIds(Arrays.asList(10,1));
    if(null != users && !users.isEmpty()){
      for(User u :users){
        System.out.println(u);
      }
    }
  }

  @Test
  public void testTransactionTestSucess(){
    userService.transactionTestSucess();
  }

  @Test(expected = IllegalAccessException.class)
  public void testTransactionTestFailure() throws IllegalAccessException{
    userService.transactionTestFailure();
  }

}
