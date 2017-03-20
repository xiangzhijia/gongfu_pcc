package com.gongfu.base;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private Class<T> clazz;

    public BaseServiceImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Autowired
    private Mapper<T> mapper;


    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(this.mapper.selectByPrimaryKey(id));
    }

    /**
     * 查询所有的数据
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        return this.mapper.select(null);
    }

    /**
     * 根据条件查询
     *
     * @param t 输入
     * @return
     */
    @Override
    public List<T> findAll(T t) {
        return this.mapper.select(t);
    }

    /**
     * 查询数据总条数
     *
     * @return
     */
    @Override
    public Integer findCount() {
        return this.mapper.selectCount(null);
    }
    
    /**
     * 根据条件查询一条
     *
     * @param t
     * @return
     */
    @Override
    public Optional<T> findOne(T t) {
        return Optional.ofNullable(this.mapper.selectOne(t));
    }

    /**
     * 保存非空数据
     *
     * @param t
     * @return
     */
    @Transactional
    @Override
    public Integer save(T t) {
        return this.mapper.insertSelective(t);
    }


    /**
     * 更新非空数据
     *
     * @param t
     * @return
     */
    @Transactional
    @Override
    public Integer updateById(T t) {
        return this.mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 根据ids删除数据
     *
     * @param ids "id1,id2,id3"
     * @return
     */
    @Transactional
    @Override
    public Integer deleteByIdStr(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)) {
            return 0;
        }
        String[] idss = ids.split(",");

        if (idss.length == 1) {
            return this.mapper.deleteByPrimaryKey(Long.parseLong(idss[0]));
        }
        List<Long> list = new ArrayList<>();
        for (String s : idss) {
            list.add(Long.parseLong(s));
        }
        Example example = new Example(this.clazz);
        example.createCriteria().andIn("id", list);
        return this.mapper.deleteByExample(example);
    }

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Integer deleteById(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据ids删除数据
     *
     * @param ids
     * @return
     */
    @Transactional
    @Override
    public Integer deleteByIds(List<Long> ids) {
        Example example = new Example(this.clazz);
        example.createCriteria().andIn("id", ids);
        return this.mapper.deleteByExample(example);
    }

}
