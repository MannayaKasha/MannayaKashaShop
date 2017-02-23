package org.mannayakasha.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.mannayakasha.dao.AccountDao;
import org.mannayakasha.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Pavel
 * @version 1.0
 */

@Transactional
public class AccountDaoImpl implements AccountDao {

    private static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Account findAccount(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Account.class);
        crit.add(Restrictions.eq("userName", userName));
        logger.info("Account successfully found.)");
        return (Account) crit.uniqueResult();
    }
}
