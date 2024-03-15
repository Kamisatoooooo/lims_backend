package com.lims.controller;


import com.lims.form.SampleForm;
import com.lims.service.LimsSamplesService;
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
@RequestMapping("/limsSamples")
public class LimsSamplesController {
    @Autowired
    private LimsSamplesService limsSamplesService;

    @GetMapping("/list/{page}/{size}")
    public DataFormat list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO list = this.limsSamplesService.list(page, size);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        dataFormat.setData(list);
        return dataFormat;
    }

    @PostMapping("/newSample")
    public DataFormat newSample(@RequestBody SampleForm sampleForm) {
        DataFormat dataFormat = this.limsSamplesService.newSample(sampleForm);
        return dataFormat;
    }
}

