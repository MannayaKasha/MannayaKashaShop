package org.mannayakasha.service;

import org.mannayakasha.dao.ProductDao;
import org.mannayakasha.entity.Product;
import org.mannayakasha.model.PaginationResult;
import org.mannayakasha.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pavel
 * @version 1.0
 */

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public Product findProduct(String code) {
        return this.productDao.findProduct(code);
    }

    @Override
    @Transactional
    public ProductInfo findProductInfo(String code) {
        return this.productDao.findProductInfo(code);
    }

    @Override
    @Transactional
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        return this.productDao.queryProducts(page, maxResult, maxNavigationPage);
    }

    @Override
    @Transactional
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName) {
        return this.productDao.queryProducts(page, maxResult, maxNavigationPage, likeName);
    }

    @Override
    @Transactional
    public PaginationResult<ProductInfo> queryProductsByCategory(int page, int maxResult, int maxNavigationPage, String likeCategory) {
        return this.productDao.queryProductsByCategory(page, maxResult, maxNavigationPage, likeCategory);
    }

    @Override
    @Transactional
    public void save(ProductInfo productInfo) {
        this.productDao.save(productInfo);
    }
}
