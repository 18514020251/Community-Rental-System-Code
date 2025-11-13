package com.alibaba.service.impl;

import com.alibaba.pojo.Orders;
import com.alibaba.mapper.OrdersMapper;
import com.alibaba.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sister
 * @since 2025-11-13
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
