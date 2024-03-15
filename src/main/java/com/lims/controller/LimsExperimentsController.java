package com.lims.controller;


import com.lims.entity.LimsExperiments;
import com.lims.form.TestAddressForm;
import com.lims.service.LimsExperimentsService;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.xml.crypto.Data;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-01
 */
@RestController
@RequestMapping("/limsExperiments")
public class LimsExperimentsController {
    @Autowired
    private LimsExperimentsService limsExperimentsService;

    @GetMapping("/list/{user}/{page}/{size}")
    public DataFormat list(@PathVariable("user") Integer user, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO pageVO = this.limsExperimentsService.list(user, page, size);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        dataFormat.setData(pageVO);
        return dataFormat;
    }

    @PutMapping("/changeStatus")
    public DataFormat changeStatus(@RequestBody LimsExperiments limsExperiments) {
        DataFormat dataFormat = this.limsExperimentsService.changeStatus(limsExperiments);
        return dataFormat;
    }

    @PutMapping("/changeHelper/{idExperiment}/{helperNo}/{idHelper}")
    public DataFormat changeHelper(@PathVariable("idExperiment") String idExperiment, @PathVariable("helperNo") Integer helperNo, @PathVariable("idHelper") Integer idHelper) {
        DataFormat dataFormat = this.limsExperimentsService.changeHelper(idExperiment, helperNo, idHelper);
        return dataFormat;
    }

    @PutMapping("/changeTestAddress")
    public DataFormat changeTestAddress(@RequestBody TestAddressForm testAddressForm) {
        DataFormat dataFormat = this.limsExperimentsService.changeTestAddress(testAddressForm);
        return dataFormat;
    }
}

