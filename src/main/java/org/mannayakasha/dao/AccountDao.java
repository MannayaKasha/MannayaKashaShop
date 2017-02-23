package org.mannayakasha.dao;

import org.mannayakasha.entity.Account;

/**
 *
 * @author Pavel
 * @version 1.0
 */

public interface AccountDao extends Dao<Account> {

    Account findAccount(String userName);
}
