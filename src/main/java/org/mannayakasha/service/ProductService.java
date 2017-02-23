package org.mannayakasha.service;

import org.mannayakasha.entity.Product;
import org.mannayakasha.model.PaginationResult;
import org.mannayakasha.model.ProductInfo;

/**
 *
 * @author Pavel
 * @version 1.0
 */

public interface ProductService extends Service {

    Product findProduct(String code);

    ProductInfo findProductInfo(String code);


    PaginationResult<ProductInfo> queryProducts(int page,
                                                int maxResult, int maxNavigationPage);

    PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
                                                int maxNavigationPage, String likeName);

    PaginationResult<ProductInfo> queryProductsByCategory(int page, int maxResult,
                                                          int maxNavigationPage, String likeCategory);

    void save(ProductInfo productInfo);
}
