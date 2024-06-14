package com.hospital.service.impl;

import com.hospital.entity.Hospital;
import com.hospital.mapper.HospitalMapper;
import com.hospital.service.HospitalService;
import com.hospital.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Hospital)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService {
@Resource
private HospitalMapper hospitalMapper;

/**
 * 通过ID查询单条数据
 *
 * @param hpId 主键
 * @return 实例对象
 */
@Override
public Result queryById(Integer hpId) {
        return Result.success(this.hospitalMapper.queryById(hpId));
        }

/**
 * 全查询
 *
 * @param hospital 筛选条件
 * @return 查询结果
 */
@Override
public Result queryAll(Hospital hospital) {
        return Result.success(this.hospitalMapper.queryAll(hospital));
        }

/**
 * 新增数据
 *
 * @param hospital 实例对象
 * @return 实例对象
 */
@Override
public Result insert(Hospital hospital) {
        this.hospitalMapper.insert(hospital);
        return Result.success(hospital);
        }

/**
 * 修改数据
 *
 * @param hospital 实例对象
 * @return 实例对象
 */
@Override
public Result update(Hospital hospital) {
        this.hospitalMapper.update(hospital);
        return Result.success(this.queryById(hospital.getHpId()));
        }

/**
 * 通过主键删除数据
 * @param hpId 主键
 * @return 是否成功
 */
@Override
public Result deleteById(Integer hpId) {
        boolean del = this.hospitalMapper.deleteById(hpId) > 0;
        return Result.success(del);
        }
/**
 * 查询所有医院列表
 * @return 医院列表
 */
        @Override
        public Result hospitalList()
        {
                List<Hospital> hospitalList = hospitalMapper.queryByState();
                if(hospitalList.size() == 0||hospitalList == null)
                {
                        return Result.error("暂无数据");
                }
                return Result.success(hospitalList);
        }

        }

