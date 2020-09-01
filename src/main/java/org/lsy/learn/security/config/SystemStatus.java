package org.lsy.learn.security.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemStatus {

    PHONE_FORMAT_ERROR(4001, "手机号格式错误，只支持印度手机号"),
    PHONE_SEND_ERROR(4002, "验证码发送失败，请检查手机号是否正确"),
    USER_EXISTED(4003, "已注册，请直接登录"),
    CODE_ERROR(4004, "验证码错误"),
    USER_UNREGISTERED(4005, "未注册，请先注册"),
    USER_PASSWORD_ERROR(4006, "密码错误"),
    USER_NOT_LOGIN(4007, "您还未登录，请先登录"),
    TOKEN_ERROR(4008, "token错误，请重新登录"),
    TOKEN_EXPIRED(4009, "token已过期，请重新登录"),
    TOKEN_PARS_ERROR(4010, "token解析异常，请重新登录"),
    INVITATION_CODE_ERROR(4011, "邀请码错误"),
    FILE_UPLOAD_ERROR(4012, "文件上传异常"),
    PASSWORD_NOT_NULL(4013, "密码不能为空"),
    PAY_FAILURE(4040, "支付失败"),
    APK_UPLOAD_ERROR(4041, "apk上传失败");

    private Integer code;

    private String msg;
}
