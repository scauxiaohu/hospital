package com.hospital.mapper;

import com.hospital.entity.CheckItemDetailed;
import com.hospital.entity.CidetailedReport;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (CidetailedReport)表数据库访问层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@Mapper
public interface CidetailedReportMapper {

        /**
         * 通过ID查询单条数据
         *
         * @param cidrId 主键
         * @return 实例对象
         */
    CidetailedReport queryById(Integer cidrId);

        /**
         * 查询指定行数据
         *
         * @param cidetailedReport 查询条件
         * @return 对象列表
         */
        List<CidetailedReport> queryAll(CidetailedReport cidetailedReport);

        /**
         * 统计总行数
         *
         * @param cidetailedReport 查询条件
         * @return 总行数
         */
        long count(CidetailedReport cidetailedReport);

        /**
         * 新增数据
         *
         * @param cidetailedReport 实例对象
         * @return 影响行数
         */
        int insert(CidetailedReport cidetailedReport);

        /**
         * 批量新增数据（MyBatis原生foreach方法）
         *
         * @param entities List<CidetailedReport> 实例对象列表
         * @return 影响行数
         */
        int insertBatch(@Param("entities") List<CidetailedReport> entities);

        /**
         * 批量新增或按主键更新数据（MyBatis原生foreach方法）
         *
         * @param entities List<CidetailedReport> 实例对象列表
         * @return 影响行数
         * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
         */
        int insertOrUpdateBatch(@Param("entities") List<CidetailedReport> entities);

        /**
         * 修改数据
         *
         * @param cidetailedReport 实例对象
         * @return 影响行数
         */
        int update(CidetailedReport cidetailedReport);

        /**
         * 通过主键删除数据
         *
         * @param cidrId 主键
         * @return 影响行数
         */
        int deleteById(Integer cidrId);

    List<CidetailedReport> queryCidetailedReportByCirIdAndOrderId(@Param("ciId") Integer cirId, @Param("orderId") Integer orderId);

    @MapKey("orderId")
    Map<Integer, List<CidetailedReport>> batchQueryCidetailedReports(@Param("orderIds") List<Integer> orderIds);
}


