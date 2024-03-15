package com.lims.vo;

import lombok.Data;

@Data
public class DataFormat<T> {
    /*
    返回前端的数据格式
    {
        code:0,//0表示正常，其他值为异常根据值的类型区分异常
        data:{}
    }
    * */
    private Integer code;
    private T data;
}
