package com.pipe.stock.dao;

import com.pipe.stock.entity.StockEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author pipe
 * @Date 2024/5/30 19:21
 */
public interface StockDao {

    List<StockEntity> selectAll();

    void insert(StockEntity stockEntity);

    void insertMap(Map<String, Object> stockEntity);
}
