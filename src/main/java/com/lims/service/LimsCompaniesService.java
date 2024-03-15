package com.lims.service;

import com.lims.entity.LimsCompanies;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lims.form.CompanyForm;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-01
 */
public interface LimsCompaniesService extends IService<LimsCompanies> {

    public PageVO list(Integer page, Integer size);

    public DataFormat newCompany(CompanyForm companyForm);
}
