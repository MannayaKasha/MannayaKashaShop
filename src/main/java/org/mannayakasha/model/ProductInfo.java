package org.mannayakasha.model;

import org.mannayakasha.entity.Category;
import org.mannayakasha.entity.Product;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Pavel
 * @version 1.0
 */

public class ProductInfo {

    private String code;
    private String name;
    private double price;
    private boolean novelty;
    private Category category;

    private boolean newProduct = false;

    // Upload file.
    private CommonsMultipartFile fileData;

    public ProductInfo() {
    }

    public ProductInfo(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
        this.novelty = product.isNovelty();
        this.category = product.getCategory();
    }

    public ProductInfo(String code, String name, double price, boolean novelty, Category category) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.novelty = novelty;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public boolean isNovelty() {
        return novelty;
    }

    public void setNovelty(boolean novelty) {
        this.novelty = novelty;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
