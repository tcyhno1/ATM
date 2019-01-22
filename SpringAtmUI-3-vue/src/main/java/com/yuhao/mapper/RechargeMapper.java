package com.yuhao.mapper;

import com.yuhao.entity.RechargeInfo;
import org.apache.ibatis.annotations.Param;

public interface RechargeMapper {
    //用于用户下单，将下单信息通过对象存入数据库
    void insertRecharge(RechargeInfo rechargeInfo);
    //通过产品单号，拿到未完成的订单对象，然后锁住行
    RechargeInfo selectRechargeForLock(String orderId);
    //通过orderId查到行数据，添加tradeId,然后在.xml文件对应sql语句中还改状态为1
    void updateRechargeStatus(@Param("orderId") String orderId,
                              @Param("tradeId") String tradeId);

}
