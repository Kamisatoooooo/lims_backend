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
    public class LimsDelegations implements Serializable {

    private static final long serialVersionUID=1L;

      private String idDelegation;

    private String idSample;

    private Integer status;

    private Integer idCompany;

    private String contact;

    private String phoneNumber;

    private Integer registrant;

    private String address;

    private LocalDateTime t;


}
