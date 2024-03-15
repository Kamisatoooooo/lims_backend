package com.lims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lims.entity.LimsDelegations;
import com.lims.entity.LimsEquipments;
import com.lims.entity.LimsExperiments;
import com.lims.entity.LimsProjects;
import com.lims.form.AllocateForm;
import com.lims.mapper.LimsExperimentsMapper;
import com.lims.mapper.LimsProjectsMapper;
import com.lims.mapper.LimsReportsMapper;
import com.lims.service.LimsExperimentsService;
import com.lims.service.LimsProjectsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lims.service.LimsReportsService;
import com.lims.vo.DataFormat;
import com.lims.vo.MyProjects;
import com.lims.vo.PageVO;
import com.lims.vo.ProjectDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class LimsProjectsServiceImpl extends ServiceImpl<LimsProjectsMapper, LimsProjects> implements LimsProjectsService {
    @Autowired
    private LimsProjectsMapper limsProjectsMapper;

    @Autowired
    private LimsReportsService limsReportsService;

    @Autowired
    private LimsExperimentsService limsExperimentsService;

    @Override
    public PageVO myProjects(Integer user, Integer page, Integer size) {
        //通过预设好的SQL语句进行多表查询
        Page<MyProjects> limsProjectsPage = new Page<>(page, size);
        Page<MyProjects> resultPage = this.limsProjectsMapper.getJoin(limsProjectsPage, user);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public boolean newProject(LimsDelegations limsDelegation) {
        //此方法用于根据新生成的委托信息生成项目信息
        LimsProjects limsProject = new LimsProjects();

        limsProject.setIdProject("PRJ" + limsDelegation.getIdSample().replace("SMP", ""));
        limsProject.setStatus(0);
        limsProject.setIdCompany(limsDelegation.getIdCompany());
        limsProject.setIdDelegation(limsDelegation.getIdDelegation());
        //生成报告条目
        String id = this.limsReportsService.newReport(limsDelegation.getIdSample());
        if (id != null) {
            limsProject.setIdReport(id.split(" ")[0]);
        } else {
            return false;
        }
        //生成实验条目
        String idExperiment = this.limsExperimentsService.newExperiment(limsDelegation.getIdSample(), id.split(" ")[1]);
        if (idExperiment != null) {
            limsProject.setIdExperiment(idExperiment);
        } else {
            //删除已经生成的报告条目及数据条目
            this.limsReportsService.delReport(id);
            return false;
        }

        if (this.save(limsProject)) {
            return true;
        } else {
            //删除已经生成的实验条目
            this.limsExperimentsService.delExperiment(idExperiment + " " + id);
            return false;
        }
    }

    @Override
    public PageVO listNull(Integer page, Integer size) {
        Page<LimsProjects> limsProjectsPage = new Page<>(page, size);
        QueryWrapper<LimsProjects> limsProjectsQueryWrapper = new QueryWrapper<>();
        limsProjectsQueryWrapper.isNull("id_assigner");
        Page<LimsProjects> resultPage = this.limsProjectsMapper.selectPage(limsProjectsPage, limsProjectsQueryWrapper);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public PageVO listNotNull(Integer page, Integer size) {
        Page<ProjectDetails> limsProjectsPage = new Page<>(page, size);

        Page<ProjectDetails> resultPage = this.limsProjectsMapper.getProjectDetails(limsProjectsPage);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public DataFormat allocate(AllocateForm allocateForm) {
        QueryWrapper<LimsProjects> limsProjectsQueryWrapper = new QueryWrapper<>();
        limsProjectsQueryWrapper.eq("id_project", allocateForm.getIdProject());
        LimsProjects limsProject = this.limsProjectsMapper.selectOne(limsProjectsQueryWrapper);

        limsProject.setIdAssigner(allocateForm.getIdAssigner());
        limsProject.setDeadline(LocalDateTime.parse(allocateForm.getDeadline()));
        limsProject.setAssignTime(LocalDateTime.parse(allocateForm.getAssignTime()));
        UpdateWrapper<LimsProjects> limsProjectsUpdateWrapper = new UpdateWrapper<>();
        limsProjectsUpdateWrapper.eq("id_project", allocateForm.getIdProject());

        int update = this.limsProjectsMapper.update(limsProject, limsProjectsUpdateWrapper);
        int update1 = this.limsExperimentsService.allocate(limsProject.getIdExperiment(), allocateForm.getIdTester());
        DataFormat dataFormat = new DataFormat<>();
        dataFormat.setCode(0);
        if (update == 0 || update1 == 0) {
            dataFormat.setCode(-1);
        }
        return dataFormat;
    }
}
