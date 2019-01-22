package com.yuhao.atmboot.entity;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String pwd;

    private Date createtiime;

    private Date modifytime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Date getCreatetiime() {
        return createtiime;
    }

    public void setCreatetiime(Date createtiime) {
        this.createtiime = createtiime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}