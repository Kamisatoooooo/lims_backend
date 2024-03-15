package com.lims.service;

import com.lims.entity.LimsExperiments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lims.form.TestAddressForm;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;

import javax.xml.crypto.Data;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-15
 */
public interface LimsExperimentsService extends IService<LimsExperiments> {
    public String newExperiment(String idSample, String idData);

    public void delExperiment(String idExperiment);

    public int allocate(String idExperiment, Integer idTester);

    public PageVO list(Integer user, Integer page, Integer size);

    public DataFormat changeStatus(LimsExperiments limsExperiments);

    public DataFormat changeHelper(String idExperiment, Integer helperNo, Integer idHelper);

    public DataFormat changeTestAddress(TestAddressForm testAddressForm);
}
