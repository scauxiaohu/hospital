package com.xiaohu.hospital.mapper;

import com.xiaohu.hospital.entity.SetMealDetailed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SetMealDetailed)表数据库访问层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Mapper
public interface SetMealDetailedMapper {

        /**
         * 通过ID查询单条数据
         *
         * @param sdId 主键
         * @return 实例对象
         */
    SetMealDetailed queryById(Integer sdId);

        /**
         * 查询指定行数据
         *
         * @param setMealDetailed 查询条件
         * @return 对象列表
         */
        List<SetMealDetailed> queryAll(SetMealDetailed setMealDetailed);

        /**
         * 统计总行数
         *
         * @param setMealDetailed 查询条件
         * @return 总行数
         */
        long count(SetMealDetailed setMealDetailed);

        /**
         * 新增数据
         *
         * @param setMealDetailed 实例对象
         * @return 影响行数
         */
        int insert(SetMealDetailed setMealDetailed);

        /**
         * 批量新增数据（MyBatis原生foreach方法）
         *
         * @param entities List<SetMealDetailed> 实例对象列表
         * @return 影响行数
         */
        int insertBatch(@Param("entities") List<SetMealDetailed> entities);

        /**
         * 批量新增或按主键更新数据（MyBatis原生foreach方法）
         *
         * @param entities List<SetMealDetailed> 实例对象列表
         * @return 影响行数
         * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
         */
        int insertOrUpdateBatch(@Param("entities") List<SetMealDetailed> entities);

        /**
         * 修改数据
         *
         * @param setMealDetailed 实例对象
         * @return 影响行数
         */
        int update(SetMealDetailed setMealDetailed);

        /**
         * 通过主键删除数据
         *
         * @param sdId 主键
         * @return 影响行数
         */
        int deleteById(Integer sdId);

}


