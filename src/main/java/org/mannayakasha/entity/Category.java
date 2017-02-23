package org.mannayakasha.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Pavel on 19.02.2017.
 */

@Entity
@Table(name = "Categories")
public class Category implements Serializable {

    private static final long serialVersionUID = 6064849857476413706L;

    private String name;

    public Category() {
    }

    @Id
    @Column(name = "Name", length = 20, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}