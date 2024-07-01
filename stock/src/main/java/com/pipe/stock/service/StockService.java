package com.pipe.stock.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author：pipe
 * @Project：cloud-mould
 * @since：2024/3/10 10:57
 */
@Service
public class StockService {

    //====================Tcc 分布式事务====================
    // 在User服务中，您需要按照TCC模式的流程来调用这些接口
    @PostMapping("/tryReduceStock")
    public ResponseEntity<?> tryReduceStock(@RequestBody Object stockOperation) {
        // 尝试扣减库存，预留资源

        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirmReduceStock")
    public ResponseEntity<?> confirmReduceStock(@RequestBody Object stockOperation) {
        // 确认扣减库存

        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancelReduceStock")
    public ResponseEntity<?> cancelReduceStock(@RequestBody Object stockOperation) {
        // 取消扣减库存，释放资源

        return ResponseEntity.ok().build();
    }

    //====================seata 分布式事务====================
//    @GlobalTransactional
    public void createOrder(){

        // 创建订单

        // 调用库存服务

        // 调用指服务


    }

}
