package com.hospital.util;


import com.hospital.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 统一返回对象R
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
  
    private Integer code; //状态码, 1成功, 0失败
    private String message;
    private T data;


    // 成功的静态方法
    public static <T> Result<T> success(T data) {
        return new Result<>(1, null, data);
    }
    public static <T> Result<T> success() {
        return new Result<>(1, null, null);
    }
    public static <T> Result<T> success(Integer code, String message, T data) {
        return new Result<>(code,message, data);
    }
    public static <T>Result<T> success(Status status,T data) {
        return new Result(status.getCode(), status.getMessage(), data);
    }
    // 失败的静态方法
    public static <T> Result<T> error(String message) {
        return new Result<>(0, message,null);
    }
    public static <T> Result<T> error(Integer code, String message, T data) {
        return new Result<>(code, message,data);
    }
    public static Result error(Status status)
    {
        return new Result(status.getCode(), status.getMessage(), null);
    }

}

