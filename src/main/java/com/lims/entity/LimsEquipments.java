package com.lims.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    public class LimsEquipments implements Serializable {

    private static final long serialVersionUID=1L;

      private String idEquipment;

    private String name;

    private String model;

    private Integer status;

    @JsonProperty("cUser")
    private Integer cUser;

    private String manufacturer;

    private String address;


}
