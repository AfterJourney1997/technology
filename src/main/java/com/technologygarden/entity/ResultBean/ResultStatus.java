package com.technologygarden.entity.ResultBean;

import lombok.Getter;

@Getter
public enum ResultStatus {

    SUCCESS(200, "success"),
    DELETE_ERROR(-1, "删除项仍被使用！"),
    FILE_NAME_ERROR(-2, "文件名称错误，正确格式：jiaotong_20190120_1900_2000.mp3");

    private int code;
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
