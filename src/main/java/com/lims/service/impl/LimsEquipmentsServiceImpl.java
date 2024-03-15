package com.lims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lims.entity.LimsEquipments;
import com.lims.form.EquipmentForm;
import com.lims.form.SearchForm;
import com.lims.mapper.LimsEquipmentsMapper;
import com.lims.service.LimsEquipmentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-15
 */
@Service
public class LimsEquipmentsServiceImpl extends ServiceImpl<LimsEquipmentsMapper, LimsEquipments> implements LimsEquipmentsService {
    @Autowired
    private LimsEquipmentsMapper limsEquipmentsMapper;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<LimsEquipments> limsEquipmentsPage = new Page<>(page, size);
        Page<LimsEquipments> resultPage = this.limsEquipmentsMapper.selectPage(limsEquipmentsPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        //实现模糊查询和分页
        Page<LimsEquipments> limsEquipmentsPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<LimsEquipments> resultPage;
        if (searchForm.getValue().equals("")) {
            resultPage = this.limsEquipmentsMapper.selectPage(limsEquipmentsPage, null);
        } else {
            QueryWrapper<LimsEquipments> limsEquipmentsQueryWrapper = new QueryWrapper<>();
            limsEquipmentsQueryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.limsEquipmentsMapper.selectPage(limsEquipmentsPage, limsEquipmentsQueryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public DataFormat newEquipment(EquipmentForm equipmentForm) {
        LimsEquipments limsEquipments = new LimsEquipments();
        //设定字段值
        limsEquipments.setIdEquipment(equipmentForm.getIdEquipment());
        limsEquipments.setName(equipmentForm.getName());
        limsEquipments.setModel(equipmentForm.getModel());
        limsEquipments.setAddress(equipmentForm.getAddress());
        limsEquipments.setManufacturer(equipmentForm.getManufacturer());
        limsEquipments.setStatus(0);

        boolean statu = this.save(limsEquipments);
        DataFormat dataFormat = new DataFormat<>();
        if (!statu) {
            dataFormat.setCode(-1);
        } else {
            dataFormat.setCode(0);
        }
        return dataFormat;
    }

    @Override
    public PageVO unuseds(Integer page, Integer size) {
        Page<LimsEquipments> limsEquipmentsPage = new Page<>(page, size);
        QueryWrapper<LimsEquipments> limsEquipmentsQueryWrapper = new QueryWrapper<>();
        limsEquipmentsQueryWrapper.eq("status", 0);
        Page<LimsEquipments> resultPage = this.limsEquipmentsMapper.selectPage(limsEquipmentsPage, limsEquipmentsQueryWrapper);
        PageVO pageVO = new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO unusedsSearch(SearchForm searchForm) {
        Page<LimsEquipments> limsEquipmentsPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        QueryWrapper<LimsEquipments> limsEquipmentsQueryWrapper = new QueryWrapper<>();
        limsEquipmentsQueryWrapper.eq("status", 0).like(searchForm.getKey(), searchForm.getValue());
        Page<LimsEquipments> resultPage = this.limsEquipmentsMapper.selectPage(limsEquipmentsPage, limsEquipmentsQueryWrapper);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public DataFormat lend(EquipmentForm equipmentForm) {
        QueryWrapper<LimsEquipments> limsEquipmentsQueryWrapper = new QueryWrapper<>();
        limsEquipmentsQueryWrapper.eq("id_equipment", equipmentForm.getIdEquipment());
        LimsEquipments limsEquipments = limsEquipmentsMapper.selectOne(limsEquipmentsQueryWrapper);
        DataFormat dataFormat = new DataFormat<>();
        if (limsEquipments.getStatus() == 0) {
            UpdateWrapper<LimsEquipments> limsEquipmentsUpdateWrapper = new UpdateWrapper<>();
            limsEquipmentsUpdateWrapper.eq("id_equipment", equipmentForm.getIdEquipment()).set("status", 1).set("c_user", equipmentForm.getCUser());
            int update = this.limsEquipmentsMapper.update(null, limsEquipmentsUpdateWrapper);
            if (update == 0) {
                dataFormat.setCode(-1);
            } else {
                dataFormat.setCode(0);
            }
        } else {//仪器已经被借走了
            dataFormat.setCode(-2);
        }
        return dataFormat;
    }

    @Override
    public PageVO myEquipments(Integer user, Integer page, Integer size) {
        Page<LimsEquipments> limsEquipmentsPage = new Page<>(page, size);
        QueryWrapper<LimsEquipments> limsEquipmentsQueryWrapper = new QueryWrapper<>();
        limsEquipmentsQueryWrapper.eq("c_user", user);
        Page<LimsEquipments> resultPage = this.limsEquipmentsMapper.selectPage(limsEquipmentsPage, limsEquipmentsQueryWrapper);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public DataFormat returnEquipment(LimsEquipments limsEquipments) {
        UpdateWrapper<LimsEquipments> limsEquipmentsUpdateWrapper = new UpdateWrapper<>();
        limsEquipmentsUpdateWrapper.eq("id_equipment", limsEquipments.getIdEquipment()).set("status", 0).set("c_user", null);
        int update = limsEquipmentsMapper.update(null, limsEquipmentsUpdateWrapper);
        DataFormat dataFormat = new DataFormat<>();
        if (update == 0) {
            dataFormat.setCode(-1);
        } else {
            dataFormat.setCode(0);
        }
        return dataFormat;
    }

}
