package com.study.dangdang.sharding.jdbc.mapper;

import com.study.dangdang.sharding.jdbc.entity.User;
import java.util.List;

/**
 * 描述: ${DESCRIPTION}
 *
 * @author pangpeijie
 * @create 2017-11-10 20:02
 */
public interface UserMapper {

  Integer insert(User u);

  List<User> findAll();

  List<User> findByUserIds(List<Integer> userIds);

}
