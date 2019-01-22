package com.yuhao.entity;


import java.util.Date;

public class Stream {
    private Integer id;
    private Integer userId;
    private String userSno;
    private Integer type;
    private String money;
    private Date createTime;
    private Date lastUptateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserSno() {
        return userSno;
    }

    public void setUserSno(String userSno) {
        this.userSno = userSno;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUptateTime() {
        return lastUptateTime;
    }

    public void setLastUptateTime(Date lastUptateTime) {
        this.lastUptateTime = lastUptateTime;
    }

    @Override
    public String toString() {
        return "Stream{" +
                "id=" + id +
                ", userSno='" + userSno + '\'' +
                ", type=" + type +
                ", money='" + money + '\'' +
                ", createTime=" + createTime +
                ", lastUptateTime=" + lastUptateTime +
                '}';
    }
}
