package com.lims.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-15
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class LimsProjects implements Serializable {

    private static final long serialVersionUID=1L;

      private String idProject;

    private Integer status;

    private String idExperiment;

    private String idDelegation;

    private String idReport;

    private Integer idAssigner;

    private LocalDateTime deadline;

    private LocalDateTime assignTime;

    private Integer idCompany;


}
