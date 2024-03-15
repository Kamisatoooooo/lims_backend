package com.lims.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lims.entity.LimsCompanies;
import com.lims.form.CompanyForm;
import com.lims.mapper.LimsCompaniesMapper;
import com.lims.service.LimsCompaniesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;
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
public class LimsCompaniesServiceImpl extends ServiceImpl<LimsCompaniesMapper, LimsCompanies> implements LimsCompaniesService {

    @Autowired
    private LimsCompaniesMapper limsCompaniesMapper;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<LimsCompanies> limsCompaniesPage = new Page<>(page, size);
        Page<LimsCompanies> resultPage = this.limsCompaniesMapper.selectPage(limsCompaniesPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public DataFormat newCompany(CompanyForm companyForm) {
        LimsCompanies limsCompany = new LimsCompanies();

        limsCompany.setAddress(companyForm.getAddress());
        limsCompany.setName(companyForm.getName());
        limsCompany.setContact(companyForm.getContact());
        limsCompany.setPhoneNumber(companyForm.getPhoneNumber());

        boolean statu = this.save(limsCompany);
        DataFormat dataFormat = new DataFormat<>();
        if (!statu) {
            dataFormat.setCode(-1);
        } else {
            dataFormat.setCode(0);
        }
        return dataFormat;
    }
}
