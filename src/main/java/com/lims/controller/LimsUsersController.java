package com.lims.controller;


import com.lims.entity.LimsUsers;
import com.lims.form.LoginForm;
import com.lims.form.RegistForm;
import com.lims.form.SearchForm;
import com.lims.service.LimsUsersService;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-01
 */
@RestController
@RequestMapping("/limsUsers")
public class LimsUsersController {
    @Autowired//自动注入
    private LimsUsersService limsUsersService;

    @PostMapping("/login")
    public DataFormat login(@RequestBody LoginForm loginForm) {
        //获取数据库的查询结果
        DataFormat dataFormat = this.limsUsersService.login(loginForm);
        return dataFormat;
    }

    @PostMapping("/regist")
    public DataFormat regist(@RequestBody RegistForm registForm) {
        DataFormat dataFormat = this.limsUsersService.regist(registForm);
        return dataFormat;
    }

    @GetMapping("/list/{page}/{size}")
    public DataFormat list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO list = this.limsUsersService.list(page, size);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        dataFormat.setData(list);
        return dataFormat;
    }

    @GetMapping("/search")
    public DataFormat search(SearchForm searchForm) {
        PageVO searchResult = this.limsUsersService.search(searchForm);
        DataFormat dataFormat = new DataFormat();
        dataFormat.setCode(0);
        dataFormat.setData(searchResult);
        return dataFormat;
    }

    @GetMapping("/newRegists/{page}/{size}")
    public DataFormat newRegists(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO searchResult = this.limsUsersService.newRegists(page, size);
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setData(searchResult);
        dataFormat.setCode(0);
        return dataFormat;
    }

    @PutMapping("/setRole")
    public DataFormat setRole(@RequestBody LimsUsers limsUsers) {
        boolean code = this.limsUsersService.updateById(limsUsers);
        DataFormat dataFormat = new DataFormat<>();
        if (!code) {
            dataFormat.setCode(-1);
        } else {
            dataFormat.setCode(0);
        }
        return dataFormat;
    }

    @PutMapping("/changeInfo")
    public DataFormat changeInfo(@RequestBody LimsUsers limsUsers) {
        DataFormat dataFormat = this.limsUsersService.changeInfo(limsUsers);
        return dataFormat;
    }
}