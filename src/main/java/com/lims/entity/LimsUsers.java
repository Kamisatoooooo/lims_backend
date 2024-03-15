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
    public class LimsUsers implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id_user", type = IdType.AUTO)
      private Integer idUser;

    private String username;

    private String pwd;

    private String name;

    private String gender;

    private String duties;

    private String phoneNumber;

    private Integer role;


}
