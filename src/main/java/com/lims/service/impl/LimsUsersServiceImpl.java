package com.lims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lims.entity.LimsUsers;
import com.lims.form.LoginForm;
import com.lims.form.RegistForm;
import com.lims.form.SearchForm;
import com.lims.mapper.LimsUsersMapper;
import com.lims.service.LimsUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-01
 */
@Service
public class LimsUsersServiceImpl extends ServiceImpl<LimsUsersMapper, LimsUsers> implements LimsUsersService {

    @Autowired
    private LimsUsersMapper limsUsersMapper;

    @Override
    public DataFormat login(LoginForm loginForm) {
        //判断用户名是否存在
        QueryWrapper<LimsUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginForm.getUsername());
        LimsUsers limsUsers = this.limsUsersMapper.selectOne(queryWrapper);
        DataFormat dataFormat = new DataFormat();
        if (limsUsers == null) {//用户名不存在
            dataFormat.setCode(-1);
        } else {
            //判断密码是否正确
            if (!limsUsers.getPwd().equals(loginForm.getPassword())) {
                dataFormat.setCode(-2);
            } else {
                dataFormat.setCode(0);
                dataFormat.setData(limsUsers);
            }
        }
        return dataFormat;
    }

    @Override
    public DataFormat regist(RegistForm registForm) {
        //判断用户名是否存在
        QueryWrapper<LimsUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", registForm.getUsername());
        LimsUsers limsUsers = this.limsUsersMapper.selectOne(queryWrapper);
        DataFormat dataFormat = new DataFormat();
        if (limsUsers == null) {//用户名不存在即选取的用户名唯一
            dataFormat.setCode(0);
            //将信息插入数据库
            LimsUsers limsUsers1 = new LimsUsers();
            limsUsers1.setUsername(registForm.getUsername());
            limsUsers1.setPwd(registForm.getPassword());
            limsUsers1.setName(registForm.getName());
            limsUsers1.setGender(registForm.getGender());
            limsUsers1.setDuties(registForm.getDuties());
            limsUsers1.setPhoneNumber(registForm.getPhoneNumber());
            limsUsers1.setRole(0);
            boolean stat = this.save(limsUsers1);
            if (!stat) {
                dataFormat.setCode(-2);
            }
        } else {
            dataFormat.setCode(-1);
        }
        return dataFormat;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<LimsUsers> limsUsersPage = new Page<>(page, size);
        Page<LimsUsers> resultPage = this.limsUsersMapper.selectPage(limsUsersPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        //实现模糊查询和分页
        Page<LimsUsers> limsUsersPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<LimsUsers> resultPage;
        if (searchForm.getValue().equals("")) {//未给条件，查询全部
            resultPage = this.limsUsersMapper.selectPage(limsUsersPage, null);
        } else {
            QueryWrapper<LimsUsers> limsUsersQueryWrapper = new QueryWrapper<>();
            limsUsersQueryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.limsUsersMapper.selectPage(limsUsersPage, limsUsersQueryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO newRegists(Integer page, Integer size) {
        //创建分页信息
        Page<LimsUsers> limsUsersPage = new Page<>(page, size);
        //创建查询信息
        QueryWrapper<LimsUsers> limsUsersQueryWrapper = new QueryWrapper<>();
        limsUsersQueryWrapper.eq("role", 0);
        Page<LimsUsers> resultPage = this.limsUsersMapper.selectPage(limsUsersPage, limsUsersQueryWrapper);
        PageVO pageVO = new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public DataFormat changeInfo(LimsUsers limsUsers) {
        UpdateWrapper<LimsUsers> limsUsersUpdateWrapper = new UpdateWrapper<>();
        limsUsersUpdateWrapper.eq("id_user", limsUsers.getIdUser());
        int update = this.limsUsersMapper.update(limsUsers, limsUsersUpdateWrapper);
        DataFormat dataFormat = new DataFormat<>();
        if (update == 0) {
            dataFormat.setCode(-1);
        } else {
            dataFormat.setCode(0);
            dataFormat.setData(limsUsers);
        }
        return dataFormat;
    }
}
