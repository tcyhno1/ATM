package com.yuhao.atmboot.Service;


import com.yuhao.atmboot.Exception.BizException;
import com.yuhao.atmboot.dto.CardDTO;
import com.yuhao.atmboot.entity.Card;
import com.yuhao.atmboot.entity.Flow;
import com.yuhao.atmboot.enums.CardStatusEnum;
import com.yuhao.atmboot.enums.FlowTypeEnum;
import com.yuhao.atmboot.mapper.CardMapper;
import com.yuhao.atmboot.mapper.FlowMapper;
import com.yuhao.atmboot.util.CardUtils;
import com.yuhao.atmboot.util.MoneyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BankCardService {

    @Resource
    CardMapper cardMapper;

    @Resource
    FlowMapper flowMapper;

    public void openAccount(String pwd,String confirmPwd,int userId){
        if (StringUtils.isBlank(pwd) || StringUtils.isBlank(confirmPwd)) {
            throw new BizException("有必填参数没有填写");
        }

        if (!pwd.equals(confirmPwd)) {
            throw new BizException("两次密码不一致");
        }

        Card card = new Card();
        card.setBalance("0");
        card.setCardNum(CardUtils.createCardNum());
        card.setCreateTime(new Date());
        card.setModifyTime(new Date());
        card.setPwd(pwd);
        card.setStatus(CardStatusEnum.enable.getK());
        card.setUserId(userId);

        int rows = cardMapper.insert(card);
        if (1!=rows) {
            throw new BizException("开户失败");
        }

    }

    public List<CardDTO> listEnableCards(int userId){
         List<Card> cards = cardMapper.listCard(userId, CardStatusEnum.enable.getK());

        if (cards.size() ==0){
            throw new BizException("没有可用的银行卡");
        }
        ArrayList<CardDTO> cardsList = new ArrayList<>(cards.size());//推荐此种写法 防止数组扩容问题 提高性能

        for (Card card : cards){
            CardDTO cardDTO = new CardDTO();

            char first = card.getCardNum().charAt(0);
            char last = card.getCardNum().charAt(card.getCardNum().length()-1);
            cardDTO.setId(card.getId());
            cardDTO.setCardNum(String.valueOf(first) + "***" + String.valueOf(last));

            cardsList.add(cardDTO);
        }

        return cardsList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deposit(int userId, int cardId, String pwd, String amount){
        if (StringUtils.isBlank(pwd)) {
            throw new BizException("银行卡密码不能为空");
        }

        Card card = cardMapper.selectByPrimaryKey(cardId);
        if (null == card) {
            throw new BizException("银行卡不存在");
        }

        if (!pwd.equals(card.getPwd())) {
            throw new BizException("银行卡不存在或者密码错误");
        }

        if (userId != card.getUserId()) {
            throw new BizException("银行卡不属于你");
        }

        if (card.getStatus() != CardStatusEnum.enable.getK()) {
            throw new BizException("银行卡不可用");
        }

        if (StringUtils.isBlank(amount)) {
            throw new BizException("请输入存款金额");
        }

        int amountNum = Integer.valueOf(amount);
        if (amountNum <= 0) {
            throw new BizException("请输入合法存款金额");
        }

        //TODO 修改银行卡余额

        card.setBalance(MoneyUtils.plus(card.getBalance(),amount));
        card.setModifyTime(new Date());
        int rows = cardMapper.modifyBalance(card.getCardNum(),card.getBalance(),card.getModifyTime());
        if(1!=rows){
            throw new BizException("存款失败");
        }

        //TODO 添加流水
        Flow flow = new Flow();
        flow.setAmount(amount);
        flow.setCardNum(card.getCardNum());
        flow.setCreateTime(new Date());
        flow.setFlowType(FlowTypeEnum.deposit.getK());
        flow.setFlowDesc(FlowTypeEnum.deposit.getV());
        flow.setUserId(userId);

        rows = flowMapper.insert(flow);
        if (1!=rows){
            throw  new BizException("存款失败");
        }
    }


}
