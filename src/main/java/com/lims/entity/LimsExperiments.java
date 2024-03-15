package com.lims.entity;

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
    public class LimsExperiments implements Serializable {

    private static final long serialVersionUID=1L;

      private String idExperiment;

    private Integer status;

    private String idSample;

    private String idData;

    private String testAddress;

    private Integer idTester;

    private Integer idHelper1;

    private Integer idHelper2;


}
