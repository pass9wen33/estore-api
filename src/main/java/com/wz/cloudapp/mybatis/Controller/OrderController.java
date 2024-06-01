package com.wz.cloudapp.mybatis.Controller;

import com.wz.cloudapp.mybatis.dao.OrderDao;
import com.wz.cloudapp.mybatis.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/list")
    public List<Order> getAll() {
        return orderDao.selectAll();
    }

    @GetMapping("query")
    public ResponseEntity<Order> getListById(@RequestParam("id")String id) {
        try {
            Order order = orderDao.getOrderById(id);
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
