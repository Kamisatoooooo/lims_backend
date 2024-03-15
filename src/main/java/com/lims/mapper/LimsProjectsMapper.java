package com.lims.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lims.entity.LimsProjects;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lims.vo.MyProjects;
import com.lims.vo.ProjectDetails;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-15
 */
@Repository
public interface LimsProjectsMapper extends BaseMapper<LimsProjects> {
    @Select("select lims_projects.*,id_tester from lims_projects,lims_experiments where lims_projects.id_experiment = lims_experiments.id_experiment AND (id_tester=#{id} or id_helper1=#{id} or id_helper2=#{id}) order by deadline desc")
    Page<MyProjects> getJoin(Page<MyProjects> iPage, Integer id);

    @Select("select lims_projects.*,id_tester from lims_projects,lims_experiments where lims_projects.id_experiment = lims_experiments.id_experiment AND id_assigner is not null order by assign_time desc")
    Page<ProjectDetails> getProjectDetails(Page<ProjectDetails> iPage);
}
