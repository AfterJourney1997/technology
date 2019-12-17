package com.technologygarden.entity.ResultBean;

import lombok.Data;

@Data
public class ResultBean<T> {

    // error_code 状态值：200 为成功，其他数值代表失败
    private Integer code;

    // message 错误信息，若status为 200 时，为success
    private String message;

    // data 返回体报文的出参，使用泛型兼容不同的类型
    private T data;

    public ResultBean() {
        this.code = 200;
        this.message = "success";
    }

    public ResultBean(Integer code) {
        this.code = code;
    }

    public ResultBean(T data){
        this.code = 200;
        this.message = "success";
        this.data = data;
    }

    public ResultBean(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultBean(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultBean(ResultStatus resultStatus){
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
    }

}
