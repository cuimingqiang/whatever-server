package com.cmq.whatever.uc.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 16/7/3.
 */
@Entity(name = "sms")
@Table
public class SMSEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sId;

    @Column
    public String code;

    @Column(unique = true)
    public String phone;

    @Column
    public Date createTime;

}
