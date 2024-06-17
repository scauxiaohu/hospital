package com.hospital.mapper;

import com.hospital.entity.CheckItemDetailed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (CheckItemDetailed)表数据库访问层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@Mapper
public interface CheckItemDetailedMapper {

        /**
         * 通过ID查询单条数据
         *
         * @param cdId 主键
         * @return 实例对象
         */
    CheckItemDetailed queryById(Integer cdId);

        /**
         * 查询指定行数据
         *
         * @param checkItemDetailed 查询条件
         * @return 对象列表
         */
        List<CheckItemDetailed> queryAll(CheckItemDetailed checkItemDetailed);

        /**
         * 统计总行数
         *
         * @param checkItemDetailed 查询条件
         * @return 总行数
         */
        long count(CheckItemDetailed checkItemDetailed);

        /**
         * 新增数据
         *
         * @param checkItemDetailed 实例对象
         * @return 影响行数
         */
        int insert(CheckItemDetailed checkItemDetailed);

        /**
         * 批量新增数据（MyBatis原生foreach方法）
         *
         * @param entities List<CheckItemDetailed> 实例对象列表
         * @return 影响行数
         */
        int insertBatch(@Param("entities") List<CheckItemDetailed> entities);

        /**
         * 批量新增或按主键更新数据（MyBatis原生foreach方法）
         *
         * @param entities List<CheckItemDetailed> 实例对象列表
         * @return 影响行数
         * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
         */
        int insertOrUpdateBatch(@Param("entities") List<CheckItemDetailed> entities);

        /**
         * 修改数据
         *
         * @param checkItemDetailed 实例对象
         * @return 影响行数
         */
        int update(CheckItemDetailed checkItemDetailed);

        /**
         * 通过主键删除数据
         *
         * @param cdId 主键
         * @return 影响行数
         */
        int deleteById(Integer cdId);

    List<CheckItemDetailed> queryByCiId(@Param("ciId") Integer ciId);
}


