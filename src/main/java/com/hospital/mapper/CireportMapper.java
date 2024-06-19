package com.hospital.mapper;

import com.hospital.entity.Cireport;
import com.hospital.response.CireportInfo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (Cireport)表数据库访问层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@Mapper
public interface CireportMapper {

        /**
         * 通过ID查询单条数据
         *
         * @param cirId 主键
         * @return 实例对象
         */
    Cireport queryById(Integer cirId);

        /**
         * 查询指定行数据
         *
         * @param cireport 查询条件
         * @return 对象列表
         */
        List<Cireport> queryAll(Cireport cireport);

        /**
         * 统计总行数
         *
         * @param cireport 查询条件
         * @return 总行数
         */
        long count(Cireport cireport);

        /**
         * 新增数据
         *
         * @param cireport 实例对象
         * @return 影响行数
         */
        int insert(Cireport cireport);

        /**
         * 批量新增数据（MyBatis原生foreach方法）
         *
         * @param entities List<Cireport> 实例对象列表
         * @return 影响行数
         */
        int insertBatch(@Param("entities") List<Cireport> entities);

        /**
         * 批量新增或按主键更新数据（MyBatis原生foreach方法）
         *
         * @param entities List<Cireport> 实例对象列表
         * @return 影响行数
         * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
         */
        int insertOrUpdateBatch(@Param("entities") List<Cireport> entities);

        /**
         * 修改数据
         *
         * @param cireport 实例对象
         * @return 影响行数
         */
        int update(Cireport cireport);

        /**
         * 通过主键删除数据
         *
         * @param cirId 主键
         * @return 影响行数
         */
        int deleteById(Integer cirId);

    List<Cireport> queryByOrderId(Integer orderId);

    @MapKey("orderId")
    Map<Integer, List<Cireport>> batchQueryCireportsByOrderIds(@Param("orderIds") List<Integer> orderIds);

}


