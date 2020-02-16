package com.technologygarden.entity.ResultBean;

import lombok.Data;

@Data
public class FileProductResultBean {
    //企业产品文件上传返回的状态码，0为成功，非0失败。
    private Integer errno;
    //企业产品文件上传返回的数据
    private Object data;
    public FileProductResultBean(Integer errno,Object data){
        this.errno=errno;
        this.data=data;
    }
}
