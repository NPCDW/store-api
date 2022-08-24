package com.github.npcdw.storeapi.service;

import com.github.npcdw.storeapi.entity.Goods;
import com.github.npcdw.storeapi.entity.common.TableInfo;

public interface GoodsService {

    Goods getById(Integer id);

    Goods getByQRCode(String qrcode);

    boolean deleteById(Integer id);

    boolean updateById(Goods data);

    boolean create(Goods data);

    TableInfo<Goods> list(int pageNumber, int pageSize, String name);

}
