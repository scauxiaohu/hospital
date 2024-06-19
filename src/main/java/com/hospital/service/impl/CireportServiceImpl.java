package com.hospital.service.impl;

import com.hospital.entity.*;
import com.hospital.mapper.*;

import com.hospital.response.CireportInfo;
import com.hospital.response.ReportInfoResponse;
import com.hospital.service.CireportService;
import com.hospital.util.Result;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hospital.util.Status.CIREPORT_FIND_NOT_EXIST;

/**
 * (Cireport)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@Service("cireportService")
public class CireportServiceImpl implements CireportService {
        @Resource
        private CireportMapper cireportMapper;
        @Resource
        private OrdersMapper ordersMapper;
        @Resource
        private CheckItemMapper checkItemMapper;
        @Resource
        private OverallResultMapper overallResultMapper;
        @Resource
        private CidetailedReportMapper cidetailedReportMapper;
        @Resource
        private HospitalMapper hospitalMapper;
        /**
         * 通过ID查询单条数据
         *
         * @param cirId 主键
         * @return 实例对象
         */
        @Override
        public Result queryById(Integer cirId) {
                return Result.success(this.cireportMapper.queryById(cirId));
        }

        /**
         * 全查询
         *
         * @param cireport 筛选条件
         * @return 查询结果
         */
        @Override
        public Result queryAll(Cireport cireport) {
                return Result.success(this.cireportMapper.queryAll(cireport));
        }

        /**
         * 新增数据
         *
         * @param cireport 实例对象
         * @return 实例对象
         */
        @Override
        public Result insert(Cireport cireport) {
                this.cireportMapper.insert(cireport);
                return Result.success(cireport);
        }

        /**
         * 修改数据
         *
         * @param cireport 实例对象
         * @return 实例对象
         */
        @Override
        public Result update(Cireport cireport) {
                this.cireportMapper.update(cireport);
                return Result.success(this.queryById(cireport.getCirId()));
        }

        /**
         * 通过主键删除数据
         *
         * @param cirId 主键
         * @return 是否成功
         */
        @Override
        public Result deleteById(Integer cirId) {
                boolean del = this.cireportMapper.deleteById(cirId) > 0;
                return Result.success(del);
        }

        @Override
    public  Result checkCireport(String userId) {

            List<Orders> ordersList = ordersMapper.queryOrdersByUserId(userId);
            List<ReportInfoResponse> reportInfoResponses = new ArrayList<>();
            if (ordersList.isEmpty()) {
                return Result.error(CIREPORT_FIND_NOT_EXIST);
            }
            for (Orders order : ordersList) {
                ReportInfoResponse reportInfoResponse = new ReportInfoResponse();
                reportInfoResponse.setOrderId(order.getOrderId());
                reportInfoResponse.setUserId(userId);
                reportInfoResponse.setHpId(order.getHpId());
                reportInfoResponse.setReportDate(order.getOrderDate());

                Hospital hospital = hospitalMapper.queryById(order.getHpId());
                reportInfoResponse.setHpName(hospital.getName());

                List<OverallResult> overallResults = overallResultMapper.queryOverallResultByOrderId(order.getOrderId());
                reportInfoResponse.setOverallResults(overallResults);

               List<CireportInfo> cireportInfos = new ArrayList<>();
                List<Cireport> cireports = cireportMapper.queryByOrderId(order.getOrderId());
                for (Cireport cireport : cireports) {
                    CireportInfo cireportInfo = new CireportInfo();
                    cireportInfo.setCiId(cireport.getCiId());
                    cireportInfo.setCiName(cireport.getCiName());
                    List<CidetailedReport> cidetailedReports = cidetailedReportMapper.queryCidetailedReportByCirIdAndOrderId(cireport.getCiId(),order.getOrderId());
                    cireportInfo.setCidetailedReportList(cidetailedReports);
                    cireportInfos.add(cireportInfo);
                }
                reportInfoResponse.setReports(cireportInfos);

                reportInfoResponses.add(reportInfoResponse);
            }
            return Result.success(reportInfoResponses);

        /*    //优化代码
            // 查询用户的所有订单及关联的医院信息
            List<Orders> ordersList = ordersMapper.queryOrdersByUserId(userId);

            // 提取所有订单ID以进行后续批量查询
            List<Integer> orderIds = ordersList.stream().map(Orders::getOrderId).collect(Collectors.toList());
            List<Integer> hospitalIds = ordersList.stream().map(Orders::getHpId).distinct().collect(Collectors.toList());

            //批量查询所有医院的名称
            Map<Integer, Hospital> hospitalNamesMap = hospitalMapper.batchQueryHospitalNames(hospitalIds);
*//*System.out.println("hospitalNamesMap: " + hospitalNamesMap);*//*
            // 批量查询所有订单的OverallResult
            Map<Integer, List<OverallResult>> overallResultsMap = overallResultMapper.batchQueryOverallResults(orderIds);
System.out.println("overallResultsMap: " + overallResultsMap);

            // 批量查询所有订单的Cireport信息
            Map<Integer, List<Cireport>> cireportsMap = cireportMapper.batchQueryCireportsByOrderIds(orderIds);
        System.out.println("cireportsMap: " + cireportsMap);

            // 批量查询所有CidetailedReport信息
            Map<Integer, List<CidetailedReport>> cidetailedReportsMap = cidetailedReportMapper.batchQueryCidetailedReports(orderIds);
System.out.println("cidetailedReportsMap: " + cidetailedReportsMap);
         System.out.println("cireportsMap: " + cidetailedReportsMap);
                List<ReportInfoResponse> reportInfoResponses = new ArrayList<>();


            return Result.success(reportInfoResponses);*/
        }

}

