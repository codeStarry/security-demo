package org.lsy.learn.security.config;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class R<T> {

    private Integer code;

    private LocalDateTime timestamp;

    private String message;

    private T data;


    public static <T> R<T> ok(T data) {
        return resultApi(Constants.SUCCESS, LocalDateTime.now(), null, data);
    }

    public static <T> R<T> failure(String message) {
        return resultApi(Constants.FAILURE, LocalDateTime.now(), message, null);
    }

    public static <T> R<T> failure(Integer code, String message) {
        return resultApi(code, LocalDateTime.now(), message, null);
    }

    public static <T> R<T> resultApi(Integer code, LocalDateTime timestamp, String message, T data) {
        R<T> api = new R<>();
        api.setCode(code);
        api.setTimestamp(timestamp);
        api.setMessage(message);
        api.setData(data);

        return api;
    }
}
