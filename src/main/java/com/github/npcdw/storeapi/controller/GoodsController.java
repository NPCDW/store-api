package com.github.npcdw.storeapi.controller;

import com.github.npcdw.storeapi.entity.Goods;
import com.github.npcdw.storeapi.entity.common.ResponseResult;
import com.github.npcdw.storeapi.entity.common.TableInfo;
import com.github.npcdw.storeapi.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("list")
    public ResponseResult<TableInfo<Goods>> list(int pageNumber, int pageSize, String name) {
        TableInfo<Goods> tableInfo = goodsService.list(pageNumber, pageSize, name);
        return ResponseResult.ok(tableInfo);
    }

    @GetMapping("getInfo/{id}")
    public ResponseResult<Goods> getInfo(@PathVariable Integer id) {
        if (id == null) {
            return ResponseResult.error("ID不能为空");
        }
        Goods data = goodsService.getById(id);
        return ResponseResult.ok(data);
    }

    @GetMapping("getInfoByQRCode")
    public ResponseResult<Goods> getInfoByQRCode(String qrcode) {
        if (StringUtils.isBlank(qrcode)) {
            return ResponseResult.error("qrcode不能为空");
        }
        Goods data = goodsService.getByQRCode(qrcode);
        return ResponseResult.ok(data);
    }

    @PostMapping("create")
    public ResponseResult<Integer> create(@RequestBody Goods data) {
        goodsService.create(data);
        return ResponseResult.ok(data.getId());
    }

    @PutMapping("update")
    public ResponseResult<Void> updateById(@RequestBody Goods data) {
        if (data.getId() == null) {
            return ResponseResult.error("ID不能为空");
        }
        if (goodsService.updateById(data)) {
            return ResponseResult.ok();
        } else {
            return ResponseResult.error("修改失败");
        }
    }

    @DeleteMapping("remove/{id}")
    public ResponseResult<Void> remove(@PathVariable Integer id) {
        if (id == null) {
            return ResponseResult.error("ID不能为空");
        }
        goodsService.deleteById(id);
        return ResponseResult.ok();
    }

}
