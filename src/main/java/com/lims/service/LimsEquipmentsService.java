package com.lims.service;

import com.lims.entity.LimsEquipments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lims.form.EquipmentForm;
import com.lims.form.SearchForm;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;

import javax.xml.crypto.Data;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-15
 */
public interface LimsEquipmentsService extends IService<LimsEquipments> {
    public PageVO list(Integer page, Integer size);

    public PageVO search(SearchForm searchForm);

    public DataFormat newEquipment(EquipmentForm equipmentForm);

    public PageVO unuseds(Integer page, Integer size);

    public PageVO unusedsSearch(SearchForm searchForm);

    public DataFormat lend(EquipmentForm equipmentForm);

    public PageVO myEquipments(Integer user, Integer page, Integer size);

    public DataFormat returnEquipment(LimsEquipments limsEquipments);
}
