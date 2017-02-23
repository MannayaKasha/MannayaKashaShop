package org.mannayakasha.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Pavel
 * @version 1.0
 */

@Entity
@Table(name = "Accounts")
public class Account implements Serializable {

    private static final long serialVersionUID = 3524723776229161979L;


    public static final String ROLE_MANAGER = AccountType.MANAGER.getConstantName();
    public static final String ROLE_CLIENT = AccountType.CLIENT.getConstantName();

    private String userName;
    private String password;
    private boolean active;
    private String userRole;

    @Id
    @Column(name = "User_Name", length = 20, nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "Password", length = 20, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "Active", length = 1, nullable = false)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name = "User_Role", length = 20, nullable = false)
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "[" + this.userName + "," + this.password + "," + this.userRole + "]";
    }
}
