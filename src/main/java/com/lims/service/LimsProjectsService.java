package com.lims.service;

import com.lims.entity.LimsDelegations;
import com.lims.entity.LimsProjects;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lims.form.AllocateForm;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-15
 */
public interface LimsProjectsService extends IService<LimsProjects> {
    public PageVO myProjects(Integer user, Integer page, Integer size);

    public boolean newProject(LimsDelegations limsDelegation);

    public PageVO listNull(Integer page, Integer size);

    public PageVO listNotNull(Integer page, Integer size);


    public DataFormat allocate(AllocateForm allocateForm);
}
