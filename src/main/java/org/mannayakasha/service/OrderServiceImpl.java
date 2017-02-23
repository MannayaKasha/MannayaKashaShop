package org.mannayakasha.service;

import org.mannayakasha.dao.OrderDao;
import org.mannayakasha.model.CartInfo;
import org.mannayakasha.model.OrderDetailInfo;
import org.mannayakasha.model.OrderInfo;
import org.mannayakasha.model.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Pavel
 * @version 1.0
 */

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public void saveOrder(CartInfo cartInfo) {
        this.orderDao.saveOrder(cartInfo);
    }

    @Override
    @Transactional
    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        return this.orderDao.listOrderInfo(page, maxResult, maxNavigationPage);
    }

    @Override
    @Transactional
    public OrderInfo getOrderInfo(String orderId) {
        return this.orderDao.getOrderInfo(orderId);
    }

    @Override
    @Transactional
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
        return this.orderDao.listOrderDetailInfos(orderId);
    }
}
