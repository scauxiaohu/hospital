package com.xiaohu.hospital.mapper;

import com.xiaohu.hospital.entity.SetMeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SetMeal)表数据库访问层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Mapper
public interface SetMealMapper {

        /**
         * 通过ID查询单条数据
         *
         * @param smId 主键
         * @return 实例对象
         */
    SetMeal queryById(Integer smId);

        /**
         * 查询指定行数据
         *
         * @param setMeal 查询条件
         * @return 对象列表
         */
        List<SetMeal> queryAll(SetMeal setMeal);

        /**
         * 统计总行数
         *
         * @param setMeal 查询条件
         * @return 总行数
         */
        long count(SetMeal setMeal);

        /**
         * 新增数据
         *
         * @param setMeal 实例对象
         * @return 影响行数
         */
        int insert(SetMeal setMeal);

        /**
         * 批量新增数据（MyBatis原生foreach方法）
         *
         * @param entities List<SetMeal> 实例对象列表
         * @return 影响行数
         */
        int insertBatch(@Param("entities") List<SetMeal> entities);

        /**
         * 批量新增或按主键更新数据（MyBatis原生foreach方法）
         *
         * @param entities List<SetMeal> 实例对象列表
         * @return 影响行数
         * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
         */
        int insertOrUpdateBatch(@Param("entities") List<SetMeal> entities);

        /**
         * 修改数据
         *
         * @param setMeal 实例对象
         * @return 影响行数
         */
        int update(SetMeal setMeal);

        /**
         * 通过主键删除数据
         *
         * @param smId 主键
         * @return 影响行数
         */
        int deleteById(Integer smId);

}


