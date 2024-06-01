package com.wz.cloudapp.mybatis.Controller;

import com.wz.cloudapp.mybatis.dao.OrderDao;
import com.wz.cloudapp.mybatis.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    private OrderDao orderDao;

    @GetMapping("/list")
    public List<Order> getAll() {
        return orderDao.selectAll();
    }
}