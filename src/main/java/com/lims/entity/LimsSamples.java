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
    public class LimsSamples implements Serializable {

    private static final long serialVersionUID=1L;

      private String idSample;

    private String standard;

    private Integer registrant;

    private String address;

    private LocalDateTime t;


}
