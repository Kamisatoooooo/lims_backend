package com.lims.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDetails {
    private String idProject;

    private Integer status;

    private String idExperiment;

    private String idDelegation;

    private String idReport;

    private Integer idAssigner;

    private LocalDateTime deadline;

    private LocalDateTime assignTime;

    private Integer idCompany;

    private Integer idTester;
}
