package com.lims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lims.entity.LimsExperiments;
import com.lims.form.TestAddressForm;
import com.lims.mapper.LimsExperimentsMapper;
import com.lims.service.LimsExperimentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lims.service.LimsReportsService;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-15
 */
@Service
public class LimsExperimentsServiceImpl extends ServiceImpl<LimsExperimentsMapper, LimsExperiments> implements LimsExperimentsService {
    @Autowired
    private LimsExperimentsMapper limsExperimentsMapper;

    @Autowired
    private LimsReportsService limsReportsService;

    @Override
    public String newExperiment(String idSample, String idData) {
        String idExperiment = "EX" + idSample.replace("SMP", "");
        LimsExperiments limsExperiment = new LimsExperiments();
        limsExperiment.setIdExperiment(idExperiment);
        limsExperiment.setStatus(0);
        limsExperiment.setIdSample(idSample);
        limsExperiment.setIdData(idData);
        if (this.save(limsExperiment)) {//插入成功
            return idExperiment;
        } else {//插入失败
            return null;
        }
    }

    @Override
    public void delExperiment(String id) {
        this.limsExperimentsMapper.deleteById(id.split(" ", 2)[0]);
        this.limsReportsService.delReport(id.split(" ", 2)[1]);
    }

    @Override
    public int allocate(String idExperiment, Integer idTester) {
        UpdateWrapper<LimsExperiments> limsExperimentsUpdateWrapper = new UpdateWrapper<>();
        limsExperimentsUpdateWrapper.eq("id_experiment", idExperiment).set("id_tester", idTester);
        int update = this.limsExperimentsMapper.update(null, limsExperimentsUpdateWrapper);
        return update;
    }

    @Override
    public PageVO list(Integer user, Integer page, Integer size) {
        Page<LimsExperiments> limsExperimentsPage = new Page<>(page, size);
        QueryWrapper<LimsExperiments> limsExperimentsQueryWrapper = new QueryWrapper<>();
        limsExperimentsQueryWrapper.eq("id_tester", user).or()
                .eq("id_helper1", user).or()
                .eq("id_helper2", user);
        Page<LimsExperiments> resultPage = this.limsExperimentsMapper.selectPage(limsExperimentsPage, limsExperimentsQueryWrapper);
        PageVO pageVO = new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public DataFormat changeStatus(LimsExperiments limsExperiments) {
        UpdateWrapper<LimsExperiments> limsExperimentsUpdateWrapper = new UpdateWrapper<>();
        limsExperimentsUpdateWrapper.eq("id_experiment", limsExperiments.getIdExperiment()).set("status", limsExperiments.getStatus());
        int update = this.limsExperimentsMapper.update(null, limsExperimentsUpdateWrapper);
        DataFormat dataFormat = new DataFormat<>();
        if (update == 0) {
            dataFormat.setCode(-1);
        } else {
            dataFormat.setCode(0);
        }
        return dataFormat;
    }

    @Override
    public DataFormat changeHelper(String idExperiment, Integer helperNo, Integer idHelper) {
        UpdateWrapper<LimsExperiments> limsExperimentsUpdateWrapper = new UpdateWrapper<>();
        limsExperimentsUpdateWrapper.eq("id_experiment", idExperiment)
                .set("id_helper" + helperNo, idHelper);
        int update = this.limsExperimentsMapper.update(null, limsExperimentsUpdateWrapper);
        DataFormat dataFormat = new DataFormat<>();
        if (update == 0) {
            dataFormat.setCode(-1);
        } else {
            dataFormat.setCode(0);
        }
        return dataFormat;
    }

    @Override
    public DataFormat changeTestAddress(TestAddressForm testAddressForm) {
        UpdateWrapper<LimsExperiments> limsExperimentsUpdateWrapper = new UpdateWrapper<>();
        limsExperimentsUpdateWrapper.eq("id_experiment", testAddressForm.getIdExperiment())
                .set("test_address", testAddressForm.getTTestAddress());
        int update = limsExperimentsMapper.update(null, limsExperimentsUpdateWrapper);
        DataFormat dataFormat = new DataFormat<>();
        if (update == 0) {
            dataFormat.setCode(-1);
        } else {
            dataFormat.setCode(0);
        }
        return dataFormat;
    }
}
