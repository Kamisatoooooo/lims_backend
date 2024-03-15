package com.lims.controller;


import com.lims.entity.LimsDatas;
import com.lims.service.LimsDatasService;
import com.lims.vo.DataFormat;
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
@RequestMapping("/limsDatas")
public class LimsDatasController {
    @Autowired
    private LimsDatasService limsDatasService;

    @GetMapping("/getData/{idData}")
    public DataFormat getData(@PathVariable("idData") String idData) {
        LimsDatas limsData = this.limsDatasService.getData(idData);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        dataFormat.setData(limsData);
        return dataFormat;
    }

    @PutMapping("/changeData")
    public DataFormat changeData(@RequestBody LimsDatas limsData) {
        DataFormat dataFormat = this.limsDatasService.changeData(limsData);
        return dataFormat;
    }

}

