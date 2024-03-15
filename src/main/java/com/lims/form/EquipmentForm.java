package com.lims.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class EquipmentForm {
    private String idEquipment;
    private String name;
    private String model;
    private String manufacturer;
    private String address;
    @JsonProperty("cUser")
    private Integer cUser;
}
