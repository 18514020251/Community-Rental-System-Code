package com.alibaba.pojo;

//统一响应结果
import lombok.Data;

@Data
public class Result<T> {
    private Integer code;//业务状态码  200 -成功  .-失败
    private String message;//提示信息
    private T data;//响应数据



    public Result() {

    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public static <E> Result<E> success(E data) {
        return new Result<>(200, "操作成功", data);
    }

    //快速返回操作成功响应结果
    public static Result success() {
        return new Result(200, "操作成功");
    }

    public static Result error(Integer code, String message) {
        return new Result(code, message);
    }

    public static Result error(String message) {
        return new Result(500, message);
    }
}
