package com.github.npcdw.storeapi.service.impl;

import com.github.npcdw.storeapi.entity.Goods;
import com.github.npcdw.storeapi.entity.common.TableInfo;
import com.github.npcdw.storeapi.mapper.GoodsMapper;
import com.github.npcdw.storeapi.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Goods getById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        return goodsMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean updateById(Goods data) {
        return goodsMapper.insert(data) > 0;
    }

    @Override
    public boolean create(Goods data) {
        return goodsMapper.updateByPrimaryKeySelective(data) > 0;
    }

    @Override
    public TableInfo<Goods> list(int pageNumber, int pageSize, String name, String qrcode) {
        int count = goodsMapper.count(pageNumber, pageSize, name, qrcode);
        if (count == 0) {
            return new TableInfo<>(count, null);
        }
        List<Goods> list = goodsMapper.list(pageNumber, pageSize, name, qrcode);
        return new TableInfo<>(count, list);
    }
}
