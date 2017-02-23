package org.mannayakasha.dao;

import org.mannayakasha.model.CartInfo;
import org.mannayakasha.model.OrderDetailInfo;
import org.mannayakasha.model.OrderInfo;
import org.mannayakasha.model.PaginationResult;

import java.util.List;

/**
 *
 * @author Pavel
 * @version 1.0
 */

public interface OrderDao extends Dao {

    void saveOrder(CartInfo cartInfo);

    public PaginationResult<OrderInfo> listOrderInfo(int page,
                                                     int maxResult, int maxNavigationPage);

    public OrderInfo getOrderInfo(String orderId);

    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);
}
