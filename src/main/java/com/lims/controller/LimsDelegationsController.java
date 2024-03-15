package com.lims.controller;


import com.lims.service.LimsDelegationsService;
import com.lims.vo.DataFormat;
import com.lims.vo.NewDelegation;
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
@RequestMapping("/limsDelegations")
public class LimsDelegationsController {
    @Autowired
    private LimsDelegationsService limsDelegationsService;

    @GetMapping("/list/{page}/{size}")
    public DataFormat list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO list = this.limsDelegationsService.list(page, size);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        dataFormat.setData(list);
        return dataFormat;
    }

    @PostMapping("/newDelegation")
    public DataFormat newDelegation(@RequestBody NewDelegation newDelegation) {
        DataFormat dataFormat = this.limsDelegationsService.newDelegation(newDelegation);
        return dataFormat;
    }
}

