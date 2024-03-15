package com.lims.form;

import lombok.Data;

@Data
public class RegistForm {
    private String username;
    private String password;

    private String name;
    private String gender;
    private String duties;
    private String phoneNumber;
}
