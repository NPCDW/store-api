package com.github.npcdw.storeapi.mapper;

import com.github.npcdw.storeapi.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    List<Goods> list(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("name") String name, @Param("qrcode") String qrcode);

    int count(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("name") String name, @Param("qrcode") String qrcode);

}