package com.lims.service;

import com.lims.entity.LimsReports;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-01
 */
public interface LimsReportsService extends IService<LimsReports> {
    public String newReport(String idSample);

    public void delReport(String id);

}
