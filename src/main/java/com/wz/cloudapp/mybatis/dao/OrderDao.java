package com.wz.cloudapp.mybatis.dao;

import com.wz.cloudapp.mybatis.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDao {
    @Select("select * from tb_order where order_id = #{orderId}")
    public Order getOrderById(String orderId);

    @Select("select * from tb_order")
    public List<Order> selectAll();
}
