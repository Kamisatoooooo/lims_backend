package com.lims.service;

import com.lims.entity.LimsDatas;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lims.vo.DataFormat;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-01
 */
public interface LimsDatasService extends IService<LimsDatas> {
    public String newData(String idSample);

    public void delData(String idData);

    public LimsDatas getData(String idData);

    public DataFormat changeData(LimsDatas limsData);

}
