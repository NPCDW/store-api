package com.github.npcdw.storeapi.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Goods {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private String qrcode;

    private String name;

    private String cover;

    private BigDecimal price;

}