package com.study.dangdang.sharding.jdbc.service;

import com.study.dangdang.sharding.jdbc.entity.User;
import java.util.List;

/**
 * 描述: ${DESCRIPTION}
 *
 * @author pangpeijie
 * @create 2017-11-10 20:04
 */
public interface UserService {

  public boolean insert(User u);

  public List<User> findAll();

  public List<User> findByUserIds(List<Integer> ids);

  public void transactionTestSucess();

  public void transactionTestFailure() throws IllegalAccessException;

}
