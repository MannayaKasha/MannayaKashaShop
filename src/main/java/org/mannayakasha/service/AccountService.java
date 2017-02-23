package org.mannayakasha.service;

import org.mannayakasha.entity.Account;

/**
 *
 * @author Pavel
 * @version 1.0
 */

public interface AccountService extends Service {

    Account findAccount(String userName);
}
