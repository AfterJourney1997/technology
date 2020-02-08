package com.technologygarden.entity.ResultBean;

import lombok.Getter;

@Getter
public enum ResultStatus {

    SUCCESS(200, "success"),
    PARAMETER_MISSING_ERROR(-1, "缺少所需参数！"),
    DELETE_ERROR(-2, "删除项仍被使用！"),
    PARAMETER_ERROR(-3, "参数错误！"),
    FILE_UPLOAD_ERROR(-4, "文件上传错误！"),
    FILE_DELETE_ERROR(-5, "文件删除错误！"),

    NOT_LOGIN_ERROR(-20, "未登录！"),
    UNKNOWN_ACCOUNT_ERROR(-21, "该账号不存在！"),
    PASSWORD_ERROR(-22, "密码错误！"),

    REGISTER_ACCOUNT_REPEAT_ERROR(-40, "该账号已存在！"),

    AUTH_EMPTY_ERROR(-100, "权限错误！");


    private int code;
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
