package com.lims.controller;


import com.lims.form.AllocateForm;
import com.lims.service.LimsProjectsService;
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
@RequestMapping("/limsProjects")
public class LimsProjectsController {
    @Autowired
    private LimsProjectsService limsProjectsService;

    @GetMapping("/myProjects/{user}/{page}/{size}")
    public DataFormat myProjects(@PathVariable("user") Integer user, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO pageVO = this.limsProjectsService.myProjects(user, page, size);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        dataFormat.setData(pageVO);
        return dataFormat;
    }

    @GetMapping("/listNull/{page}/{size}")
    public DataFormat listNull(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO list = this.limsProjectsService.listNull(page, size);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        dataFormat.setData(list);
        return dataFormat;
    }

    @GetMapping("/listNotNull/{page}/{size}")
    public DataFormat listNotNull(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageVO list = this.limsProjectsService.listNotNull(page, size);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        dataFormat.setData(list);
        return dataFormat;
    }

    @PutMapping("/allocate")
    public DataFormat allocate(@RequestBody AllocateForm allocateForm) {
        DataFormat dataFormat = this.limsProjectsService.allocate(allocateForm);
        return dataFormat;
    }
}

