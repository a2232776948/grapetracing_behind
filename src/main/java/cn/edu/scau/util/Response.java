package cn.edu.scau.util;

import lombok.Data;

@Data
public class Response<T> {
    private Integer status;
    private String msg;
    private T result;

    public static Response build() {
        return new Response();
    }

    public static Response ok(String msg) {
        return new Response(200, msg, null);
    }

    public static Response ok(String msg, Object obj) {
        return new Response(200, msg, obj);
    }

    public static Response error(String msg) {
        return new Response(500, msg, null);
    }

    public static Response error(String msg, Object obj) {
        return new Response(500, msg, obj);
    }

    private Response() {
    }

    private Response(Integer status, String msg, T result) {
        this.status = status;
        this.msg = msg;
        this.result = result;
    }
}

