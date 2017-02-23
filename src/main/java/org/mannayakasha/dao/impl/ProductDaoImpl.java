package org.mannayakasha.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.mannayakasha.dao.ProductDao;
import org.mannayakasha.entity.Product;
import org.mannayakasha.model.PaginationResult;
import org.mannayakasha.model.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *
 * @author Pavel
 * @version 1.0
 */

@Transactional
public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product findProduct(String code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("code", code));
        logger.info("Product successfully found.)");
        return (Product) crit.uniqueResult();
    }

    @Override
    public ProductInfo findProductInfo(String code) {
        Product product = this.findProduct(code);
        if (product == null) {
            return null;
        }
        logger.info("ProductInfo successfully found.)");
        return new ProductInfo(product.getCode(), product.getName(), product.getPrice(), product.isNovelty(), product.getCategory());
    }

    @Override
    public void save(ProductInfo productInfo) {
        String code = productInfo.getCode();

        Product product = null;

        boolean isNew = false;
        if (code != null) {
            product = this.findProduct(code);
        }
        if (product == null) {
            isNew = true;
            product = new Product();
            product.setCreateDate(new Date());
        }
        product.setCode(code);
        product.setName(productInfo.getName());
        product.setPrice(productInfo.getPrice());
        product.setNovelty(productInfo.isNovelty());
        product.setCategory(productInfo.getCategory());

        if (productInfo.getFileData() != null) {
            byte[] image = productInfo.getFileData().getBytes();
            if (image != null && image.length > 0) {
                product.setImage(image);
            }
        }
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(product);
        }
        // If error in DB, Exceptions will be thrown out immediately
        this.sessionFactory.getCurrentSession().flush();
    }

    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
                                                       String likeName) {
        String sql = "Select new " + ProductInfo.class.getName() //
                + "(p.code, p.name, p.price, p.novelty, p.category) " + " from "//
                + Product.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        sql += " order by p.createDate desc ";
        //
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
    }

    @Override
    public PaginationResult<ProductInfo> queryProductsByCategory(int page, int maxResult, int maxNavigationPage,
                                                                 String likeCategory) {
        String sql = "Select new " + ProductInfo.class.getName() //
                + "(p.code, p.name, p.price, p.novelty, p.category) " + " from "//
                + Product.class.getName() + " p ";
        if (likeCategory != null && likeCategory.length() > 0) {
            sql += " Where lower(p.category.name) like :likeCategory ";
        }
        sql += " order by p.createDate desc ";
        //
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(sql);
        if (likeCategory != null && likeCategory.length() > 0) {
            query.setParameter("likeCategory", "%" + likeCategory.toLowerCase() + "%");
        }
        return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
    }

    @Override
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        return queryProducts(page, maxResult, maxNavigationPage, null);
    }
}
