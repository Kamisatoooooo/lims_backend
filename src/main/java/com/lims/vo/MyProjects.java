package com.lims.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 此类用于承接MyProjects页面的数据，比limsProjects多了一个idTester字段
 */
@Data
public class MyProjects {
    private String idProject;

    private Integer status;

    private String idExperiment;

    private String idReport;

    private Integer idAssigner;

    private LocalDateTime deadline;

    private LocalDateTime assignTime;

    private Integer idCompany;

    private Integer idTester;
}
