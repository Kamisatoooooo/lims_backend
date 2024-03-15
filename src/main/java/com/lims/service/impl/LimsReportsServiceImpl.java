package com.lims.service.impl;

import com.lims.entity.LimsReports;
import com.lims.mapper.LimsReportsMapper;
import com.lims.service.LimsDatasService;
import com.lims.service.LimsReportsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-01
 */
@Service
public class LimsReportsServiceImpl extends ServiceImpl<LimsReportsMapper, LimsReports> implements LimsReportsService {
    @Autowired
    private LimsReportsMapper limsReportsMapper;

    @Autowired
    private LimsDatasService limsDatasService;

    @Override
    public String newReport(String idSample) {
        String idReport = "REP" + idSample.replace("SMP", "");
        LimsReports limsReports = new LimsReports();
        limsReports.setIdReport(idReport);
        /**生成数据条目*/
        String idData = this.limsDatasService.newData(idSample);
        if (idData == null) {//生成失败
            return null;
        } else {
            limsReports.setIdData(idData);
        }
        if (this.save(limsReports)) {
            return idReport + " " + idData;
        } else {
            //删除条目
            this.limsDatasService.delData(idData);
            return null;
        }
    }

    @Override
    public void delReport(String id) {
        this.limsReportsMapper.deleteById(id.split(" ")[0]);
        this.limsDatasService.delData(id.split(" ")[1]);
    }
}
