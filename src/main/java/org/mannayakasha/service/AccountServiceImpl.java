package org.mannayakasha.service;

import org.mannayakasha.dao.AccountDao;
import org.mannayakasha.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pavel
 * @version 1.0
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @Override
    @Transactional
    public Account findAccount(String userName) {
        return this.accountDao.findAccount(userName);
    }
}
