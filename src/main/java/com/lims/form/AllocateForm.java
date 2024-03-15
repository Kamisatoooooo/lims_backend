package com.lims.form;

import lombok.Data;

@Data
public class AllocateForm {
    private String idProject;
    private Integer idAssigner;
    private Integer idTester;
    private String deadline;
    private String assignTime;
}
