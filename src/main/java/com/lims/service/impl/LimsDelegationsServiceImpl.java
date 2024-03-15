package com.lims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lims.entity.LimsDelegations;
import com.lims.mapper.LimsDelegationsMapper;
import com.lims.service.LimsDelegationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lims.service.LimsProjectsService;
import com.lims.vo.DataFormat;
import com.lims.vo.NewDelegation;
import com.lims.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-15
 */
@Service
public class LimsDelegationsServiceImpl extends ServiceImpl<LimsDelegationsMapper, LimsDelegations> implements LimsDelegationsService {
    @Autowired
    private LimsDelegationsMapper limsDelegationsMapper;

    @Autowired
    private LimsProjectsService limsProjectsService;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<LimsDelegations> limsDelegationsPage = new Page<>(page, size);
        //时间倒序排序查询
        QueryWrapper<LimsDelegations> limsDelegationsQueryWrapper = new QueryWrapper<>();
        limsDelegationsQueryWrapper.orderByDesc("t");
        Page<LimsDelegations> resultPage = this.limsDelegationsMapper.selectPage(limsDelegationsPage, limsDelegationsQueryWrapper);
        //形成数据
        PageVO pageVO = new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public DataFormat newDelegation(NewDelegation newDelegation) {
        QueryWrapper<LimsDelegations> limsDelegationsQueryWrapper = new QueryWrapper<>();
        limsDelegationsQueryWrapper.eq("id_sample", newDelegation.getIdSample());
        LimsDelegations have = this.limsDelegationsMapper.selectOne(limsDelegationsQueryWrapper);

        DataFormat dataFormat = new DataFormat<>();
        if (have == null) {//该样本尚未登记委托
            LimsDelegations limsDelegation = new LimsDelegations();

            limsDelegation.setIdDelegation(newDelegation.getIdDelegation());
            limsDelegation.setIdSample(newDelegation.getIdSample());
            limsDelegation.setStatus(0);
            limsDelegation.setIdCompany(newDelegation.getIdCompany());
            limsDelegation.setContact(newDelegation.getContact());
            limsDelegation.setPhoneNumber(newDelegation.getPhoneNumber());
            limsDelegation.setRegistrant(newDelegation.getRegistrant());
            limsDelegation.setAddress(newDelegation.getAddress());
            limsDelegation.setT(LocalDateTime.parse(newDelegation.getT()));

            boolean statu = this.save(limsDelegation);
            if (statu) {
                //生成项目条目信息
                boolean statu1 = this.limsProjectsService.newProject(limsDelegation);
                if (statu1) {//生成中未出现错误
                    dataFormat.setCode(0);
                } else {//出现错误
                    dataFormat.setCode(-2);
                }
            } else {
                dataFormat.setCode(-2);
            }
        } else {
            dataFormat.setCode(-1);
        }
        return dataFormat;
    }
}
