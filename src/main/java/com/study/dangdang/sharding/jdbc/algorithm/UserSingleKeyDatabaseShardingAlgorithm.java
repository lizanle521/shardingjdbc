package com.study.dangdang.sharding.jdbc.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 描述: user表分库的逻辑函数
 *
 * @author pangpeijie
 * @create 2017-11-10 20:09
 */
public class UserSingleKeyDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

  /**
   * sql 中关键字 匹配符为 = 的时候，表的路由函数
   * @param collection
   * @param shardingValue
   * @return
   */
  public String doEqualSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
    for (String each : collection) {
      if (each.endsWith(shardingValue.getValue() % 2 + "")) {
        return each;
      }
    }
    throw new IllegalArgumentException();
  }

  /**
   * sql 中关键字 匹配符为 in 的时候，表的路由函数
   * @param collection
   * @param shardingValue
   * @return
   */
  public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
    Collection<String> result = new LinkedHashSet<String>(collection.size());
    for (Integer value : shardingValue.getValues()) {
      for (String tableName : collection) {
        if (tableName.endsWith(value % 2 + "")) {
          result.add(tableName);
        }
      }
    }
    return result;
  }

  /**
   * sql 中关键字 匹配符为 between的时候，表的路由函数
   * @param collection
   * @param shardingValue
   * @return
   */
  public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
    Collection<String> result = new LinkedHashSet<String>(collection.size());
    Range<Integer> range = (Range<Integer>) shardingValue.getValueRange();
    for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
      for (String each : collection) {
        if (each.endsWith(i % 2 + "")) {
          result.add(each);
        }
      }
    }
    return result;
  }
}
