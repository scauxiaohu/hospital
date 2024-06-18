package com.hospital.mapper;

import com.hospital.entity.OverallResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (OverallResult)表数据库访问层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Mapper
public interface OverallResultMapper {

        /**
         * 通过ID查询单条数据
         *
         * @param orId 主键
         * @return 实例对象
         */
    OverallResult queryById(Integer orId);

        /**
         * 查询指定行数据
         *
         * @param overallResult 查询条件
         * @return 对象列表
         */
        List<OverallResult> queryAll(OverallResult overallResult);

        /**
         * 统计总行数
         *
         * @param overallResult 查询条件
         * @return 总行数
         */
        long count(OverallResult overallResult);

        /**
         * 新增数据
         *
         * @param overallResult 实例对象
         * @return 影响行数
         */
        int insert(OverallResult overallResult);

        /**
         * 批量新增数据（MyBatis原生foreach方法）
         *
         * @param entities List<OverallResult> 实例对象列表
         * @return 影响行数
         */
        int insertBatch(@Param("entities") List<OverallResult> entities);

        /**
         * 批量新增或按主键更新数据（MyBatis原生foreach方法）
         *
         * @param entities List<OverallResult> 实例对象列表
         * @return 影响行数
         * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
         */
        int insertOrUpdateBatch(@Param("entities") List<OverallResult> entities);

        /**
         * 修改数据
         *
         * @param overallResult 实例对象
         * @return 影响行数
         */
        int update(OverallResult overallResult);

        /**
         * 通过主键删除数据
         *
         * @param orId 主键
         * @return 影响行数
         */
        int deleteById(Integer orId);

    List<OverallResult> queryOverallResultByOrderId(Integer orderId);
}


