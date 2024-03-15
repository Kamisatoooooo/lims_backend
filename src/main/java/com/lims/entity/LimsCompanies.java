package com.lims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    public class LimsCompanies implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id_company", type = IdType.AUTO)
      private Integer idCompany;

    private String name;

    private String contact;

    private String phoneNumber;

    private String address;


}
