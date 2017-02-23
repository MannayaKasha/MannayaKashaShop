package org.mannayakasha.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 *
 * @author Pavel
 * @version 1.0
 */

@MappedSuperclass
public abstract class Entity implements Serializable {

    @Id
    @Column(name = "Code", length = 50, nullable = false) //updatable = false
    private String code;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
