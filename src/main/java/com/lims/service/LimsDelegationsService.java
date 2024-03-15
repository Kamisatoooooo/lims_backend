package com.lims.service;

import com.lims.entity.LimsDelegations;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lims.vo.DataFormat;
import com.lims.vo.NewDelegation;
import com.lims.vo.PageVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-15
 */
public interface LimsDelegationsService extends IService<LimsDelegations> {
    public PageVO list(Integer page, Integer size);

    public DataFormat newDelegation(NewDelegation newDelegation);
}
