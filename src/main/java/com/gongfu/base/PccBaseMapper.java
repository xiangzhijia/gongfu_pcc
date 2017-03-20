package com.gongfu.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by a on 2017/3/17.
 */
public interface PccBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
