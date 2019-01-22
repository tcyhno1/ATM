package com.yuhao.atmboot.mapper;

import com.yuhao.atmboot.entity.Card;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);

    List<Card> listCard(@Param("userId") Integer userId, @Param("status") Integer status);

    int modifyBalance(@Param("cardNum") String cardNum, @Param("balance")String balance, @Param("modifyTime") Date modifyTime);

}