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
 * @since 2023-05-01
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class LimsReports implements Serializable {

    private static final long serialVersionUID=1L;

      private String idReport;

    private String idData;

    private String data;


}
