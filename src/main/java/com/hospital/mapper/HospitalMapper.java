package com.hospital.mapper;

import com.hospital.entity.Hospital;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Hospital)表数据库访问层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Mapper
public interface HospitalMapper {

        /**
         * 通过ID查询单条数据
         *
         * @param hpId 主键
         * @return 实例对象
         */
    Hospital queryById(Integer hpId);

        /**
         * 查询指定行数据
         *
         * @param hospital 查询条件
         * @return 对象列表
         */
        List<Hospital> queryAll(Hospital hospital);

        /**
         * 统计总行数
         *
         * @param hospital 查询条件
         * @return 总行数
         */
        long count(Hospital hospital);

        /**
         * 新增数据
         *
         * @param hospital 实例对象
         * @return 影响行数
         */
        int insert(Hospital hospital);

        /**
         * 批量新增数据（MyBatis原生foreach方法）
         *
         * @param entities List<Hospital> 实例对象列表
         * @return 影响行数
         */
        int insertBatch(@Param("entities") List<Hospital> entities);

        /**
         * 批量新增或按主键更新数据（MyBatis原生foreach方法）
         *
         * @param entities List<Hospital> 实例对象列表
         * @return 影响行数
         * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
         */
        int insertOrUpdateBatch(@Param("entities") List<Hospital> entities);

        /**
         * 修改数据
         *
         * @param hospital 实例对象
         * @return 影响行数
         */
        int update(Hospital hospital);

        /**
         * 通过主键删除数据
         *
         * @param hpId 主键
         * @return 影响行数
         */
        int deleteById(Integer hpId);

}


