package com.lims.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lims.entity.LimsEquipments;
import com.lims.form.EquipmentForm;
import com.lims.form.SearchForm;
import com.lims.service.LimsEquipmentsService;
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
@RequestMapping("/limsEquipments")
public class LimsEquipmentsController {
    @Autowired
    private LimsEquipmentsService limsEquipmentsService;

    @GetMapping("/list/{page}/{size}")
    public DataFormat list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO list = this.limsEquipmentsService.list(page, size);
        DataFormat dataFormat = new DataFormat();
        dataFormat.setCode(0);
        dataFormat.setData(list);
        return dataFormat;
    }

    @GetMapping("/search")
    public DataFormat search(SearchForm searchForm) {
        PageVO searchResult = this.limsEquipmentsService.search(searchForm);
        DataFormat dataFormat = new DataFormat();
        dataFormat.setCode(0);
        dataFormat.setData(searchResult);
        return dataFormat;
    }

    @PostMapping("/newEquipment")
    public DataFormat newEquipment(@RequestBody EquipmentForm equipmentForm) {
        DataFormat dataFormat = this.limsEquipmentsService.newEquipment(equipmentForm);
        return dataFormat;
    }

    @GetMapping("/unuseds/{page}/{size}")
    public DataFormat unuseds(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO pageVO = this.limsEquipmentsService.unuseds(page, size);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        dataFormat.setData(pageVO);
        return dataFormat;
    }

    @GetMapping("/unusedsSearch")
    public DataFormat unusedsSearch(SearchForm searchForm) {
        PageVO pageVO = this.limsEquipmentsService.unusedsSearch(searchForm);
        DataFormat dataFormat = new DataFormat();
        dataFormat.setCode(0);
        dataFormat.setData(pageVO);
        return dataFormat;
    }

    @PutMapping("/lend")
    public DataFormat lend(@RequestBody EquipmentForm equipmentForm) {
        DataFormat dataFormat = this.limsEquipmentsService.lend(equipmentForm);
        return dataFormat;
    }

    @GetMapping("/myEquipments/{user}/{page}/{size}")
    public DataFormat myEquipments(@PathVariable("user") Integer user, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO pageVO = this.limsEquipmentsService.myEquipments(user, page, size);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        dataFormat.setData(pageVO);
        return dataFormat;
    }

    @PutMapping("/return")
    public DataFormat returnEquipment(@RequestBody LimsEquipments limsEquipments) {
        DataFormat dataFormat = this.limsEquipmentsService.returnEquipment(limsEquipments);
        return dataFormat;
    }
}

