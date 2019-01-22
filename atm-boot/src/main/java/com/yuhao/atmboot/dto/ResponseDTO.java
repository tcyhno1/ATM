package com.yuhao.atmboot.dto;

/**
 * 手写 类似 AjaxResult
 */
public class ResponseDTO {

    private int code = 1000; //1000成功处理  ，999-未知异常
    private Object data;
    private String message = "";

    private ResponseDTO() {

    }


    private ResponseDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResponseDTO(int code,Object data) {
        this.code = code;
        this.data = data;
    }

    public static ResponseDTO success(){
        return new ResponseDTO();
    }

    public static ResponseDTO success (Object data){
        return new ResponseDTO(1000,data);
    }

    public static ResponseDTO failed (String  message){
        return new ResponseDTO(9999,message);
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
