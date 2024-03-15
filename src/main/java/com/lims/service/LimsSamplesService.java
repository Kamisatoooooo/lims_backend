package com.lims.service;

import com.lims.entity.LimsSamples;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lims.form.SampleForm;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-15
 */
@Repository
public interface LimsSamplesService extends IService<LimsSamples> {
    public PageVO list(Integer page, Integer size);
    public DataFormat newSample(SampleForm sampleForm);
}
