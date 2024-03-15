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
    public class LimsDatas implements Serializable {

    private static final long serialVersionUID=1L;

      private String idData;

    private String field1;

    private String value1;

    private String field2;

    private String value2;

    private String field3;

    private String value3;


}
