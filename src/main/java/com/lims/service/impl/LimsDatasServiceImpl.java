package com.lims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lims.entity.LimsDatas;
import com.lims.mapper.LimsDatasMapper;
import com.lims.service.LimsDatasService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lims.vo.DataFormat;
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
public class LimsDatasServiceImpl extends ServiceImpl<LimsDatasMapper, LimsDatas> implements LimsDatasService {

    @Autowired
    private LimsDatasMapper limsDatasMapper;

    @Override
    public String newData(String idSample) {
        String idData = "DT" + idSample.replace("SMP", "");
        LimsDatas limsData = new LimsDatas();
        limsData.setIdData(idData);
        if (this.save(limsData)) {
            return idData;
        } else {
            return null;
        }
    }

    @Override
    public void delData(String idData) {
        this.limsDatasMapper.deleteById(idData);
    }

    @Override
    public LimsDatas getData(String idData) {
        QueryWrapper<LimsDatas> limsDatasQueryWrapper = new QueryWrapper<>();
        limsDatasQueryWrapper.eq("id_data", idData);
        LimsDatas limsData = this.limsDatasMapper.selectOne(limsDatasQueryWrapper);
        return limsData;
    }

    @Override
    public DataFormat changeData(LimsDatas limsData) {
        UpdateWrapper<LimsDatas> limsDatasUpdateWrapper = new UpdateWrapper<>();
        limsDatasUpdateWrapper.eq("id_data", limsData.getIdData());

        int update = this.limsDatasMapper.update(limsData, limsDatasUpdateWrapper);
        DataFormat dataFormat = new DataFormat();
        if (update == 0) {
            dataFormat.setCode(-1);
        } else {
            dataFormat.setCode(0);
        }
        return dataFormat;
    }
}
