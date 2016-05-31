package com.cmq.whatever.uc.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 16/5/29.
 */
@Entity
@Table(name="user")
public class UserEntity {
    /**
     * 用户ID,自动生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uId;

    /**
     * 用户昵称
     */
    @Column(nullable = false)
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户密码
     */
    @Column(nullable = false)
    private String password;

    /**
     * 注册时间
     */
    @Column(nullable = false)
    private Date registerTime;

    /**
     * 注册设备
     */
    @Column(nullable = false)
    private String registerDevice;

    /**
     * 设备标示符,运营推广使用
     */
    @Column(nullable = false)
    private String registerDeviceId;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterDevice() {
        return registerDevice;
    }

    public void setRegisterDevice(String registerDevice) {
        this.registerDevice = registerDevice;
    }

    public String getRegisterDeviceId() {
        return registerDeviceId;
    }

    public void setRegisterDeviceId(String registerDeviceId) {
        this.registerDeviceId = registerDeviceId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
