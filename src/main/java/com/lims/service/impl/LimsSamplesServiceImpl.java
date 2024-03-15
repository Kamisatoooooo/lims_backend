package com.lims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lims.entity.LimsSamples;
import com.lims.form.SampleForm;
import com.lims.mapper.LimsSamplesMapper;
import com.lims.service.LimsSamplesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-15
 */
@Service
public class LimsSamplesServiceImpl extends ServiceImpl<LimsSamplesMapper, LimsSamples> implements LimsSamplesService {

    @Autowired
    private LimsSamplesMapper limsSamplesMapper;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<LimsSamples> limsSamplesPage = new Page<>(page, size);
        QueryWrapper<LimsSamples> limsSamplesQueryWrapper = new QueryWrapper<>();
        limsSamplesQueryWrapper.orderByDesc("t");
        Page<LimsSamples> resultPage = this.limsSamplesMapper.selectPage(limsSamplesPage, limsSamplesQueryWrapper);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public DataFormat newSample(SampleForm sampleForm) {
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        LimsSamples limsSamples = new LimsSamples();
        limsSamples.setIdSample(sampleForm.getIdSample());
        limsSamples.setRegistrant(sampleForm.getUser());
        limsSamples.setAddress(sampleForm.getAddress());
        limsSamples.setStandard(sampleForm.getStandard());
        limsSamples.setT(LocalDateTime.parse(sampleForm.getT()));
        boolean statu = this.save(limsSamples);
        if (!statu) {
            dataFormat.setCode(-1);
        }
        return dataFormat;
    }
}
