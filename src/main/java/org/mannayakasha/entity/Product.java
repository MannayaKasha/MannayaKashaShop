package org.mannayakasha.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Pavel
 * @version 1.0
 */

@javax.persistence.Entity
@Table(name = "Products")
public class Product implements Serializable {

    private static final long serialVersionUID = -7859506120106260458L;

    private String code;
    private String name;
    private double price;
    private byte[] image;
    private boolean novelty;
    private Category category;

    // For sort.
    private Date createDate;

    public Product() {
    }

    @Id
    @Column(name = "Code", length = 20, nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "Name", length = 255, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Create_Date", nullable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "Novelty", length = 1, nullable = false)
    public boolean isNovelty() {
        return novelty;
    }

    public void setNovelty(boolean novelty) {
        this.novelty = novelty;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false,
            foreignKey = @ForeignKey(name = "PRODUCT_CATEG_FK"))
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
