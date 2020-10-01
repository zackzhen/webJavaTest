package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_system_user")
//@Quer
public class UserDo {

    @Id                 //表示该字段是一个id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    //该注解表示该字段自增
    private Long id;
    @Column(length = 32)
    private String name;

    @Override
    public String toString() {
        return "UserDo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    @Column(length = 32)
    private String account;
    @Column(length = 64)
    private String pwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
