package com.yuhao.atmboot.controller;

import com.yuhao.atmboot.Exception.BizException;
import com.yuhao.atmboot.dto.ResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {

    @ExceptionHandler(BizException.class)
    public ResponseDTO processBizException(BizException be){
        be.printStackTrace();
        return ResponseDTO.failed(be.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseDTO processBizException(Exception e){
        e.printStackTrace();
        return ResponseDTO.failed("系统异常，请联系客服或稍后再试");
    }

}
