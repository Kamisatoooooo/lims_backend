package com.lims.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TestAddressForm {
    private String idExperiment;
    @JsonProperty("tTestAddress")
    private String tTestAddress;
}
