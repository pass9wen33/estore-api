package com.wz.cloudapp.mybatis.Controller;

import com.wz.cloudapp.mybatis.dao.OrderDao;
import com.wz.cloudapp.mybatis.entity.Order;
import com.wz.cloudapp.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estore-api/order")
public class OrderController {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/list")
    public List<Order> getAll() {
        return orderDao.selectAll();
    }

    @GetMapping("query")
    public ResponseEntity<Order> getListById(@RequestParam("id")String id) {
        try {
            Order order = (Order)redisUtils.get(id);
            if (null == order) {
                order = orderDao.getOrderById(id);
                redisUtils.set(id, order);
            }
            if (null == order) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
