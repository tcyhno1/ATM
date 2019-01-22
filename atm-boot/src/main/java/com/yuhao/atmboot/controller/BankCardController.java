package com.yuhao.atmboot.controller;


import com.yuhao.atmboot.Service.BankCardService;
import com.yuhao.atmboot.dto.ResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("card")
public class BankCardController extends BaseController {

    @Resource
    BankCardService bankCardService;

    //开户
    @RequestMapping("openAccount")
    public ResponseDTO openAccount(String pwd , String confirmPwd, HttpSession session){
        bankCardService.openAccount(pwd ,confirmPwd,2);
        return ResponseDTO.success();
    }

    //卡号List
    @RequestMapping("listCard")
    public ResponseDTO listCard(){

        return ResponseDTO.success(bankCardService.listEnableCards(2));
    }

    //存款
    @RequestMapping("deposit")
    public ResponseDTO deposit(int cardId, String pwd, String amount, HttpSession session){
        bankCardService.deposit(2,cardId,pwd,amount);
        return ResponseDTO.success();
    }




}
