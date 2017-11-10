package com.study.dangdang.sharding.jdbc.service.impl;

import com.study.dangdang.sharding.jdbc.entity.User;
import com.study.dangdang.sharding.jdbc.mapper.UserMapper;
import com.study.dangdang.sharding.jdbc.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Resource
  public UserMapper userMapper;

  public boolean insert(User u) {
    return userMapper.insert(u) > 0 ? true :false;
  }

  public List<User> findAll() {
    return userMapper.findAll();
  }

  public List<User> findByUserIds(List<Integer> ids) {
    return userMapper.findByUserIds(ids);
  }

  @Transactional(propagation=Propagation.REQUIRED)
  public void transactionTestSucess() {
    User u = new User();
    u.setUserId(13);
    u.setAge(25);
    u.setName("war3 1.27");
    userMapper.insert(u);
  }

  @Transactional(propagation= Propagation.REQUIRED)
  public void transactionTestFailure() throws IllegalAccessException {
    User u = new User();
    u.setUserId(13);
    u.setAge(25);
    u.setName("war3 1.27 good");
    userMapper.insert(u);
  }


}