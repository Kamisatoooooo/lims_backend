package com.lims.controller;


import com.lims.form.CompanyForm;
import com.lims.service.LimsCompaniesService;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-01
 */
@RestController
@RequestMapping("/limsCompanies")
public class LimsCompaniesController {
    @Autowired
    private LimsCompaniesService limsCompaniesService;

    @GetMapping("/list/{page}/{size}")
    public DataFormat list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO list = this.limsCompaniesService.list(page, size);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setData(list);
        dataFormat.setCode(0);
        return dataFormat;
    }

    @PostMapping("/newCompany")
    public DataFormat newCompany(@RequestBody CompanyForm companyForm) {
        DataFormat dataFormat = this.limsCompaniesService.newCompany(companyForm);
        return dataFormat;
    }

}

