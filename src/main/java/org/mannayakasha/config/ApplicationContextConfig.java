package org.mannayakasha.config;

//import oracle.jdbc.proxy.annotation.ProxyLocale;
import org.hibernate.SessionFactory;
import org.mannayakasha.dao.AccountDao;
import org.mannayakasha.dao.OrderDao;
import org.mannayakasha.dao.ProductDao;
import org.mannayakasha.dao.impl.AccountDaoImpl;
import org.mannayakasha.dao.impl.OrderDaoImpl;
import org.mannayakasha.dao.impl.ProductDaoImpl;
import org.mannayakasha.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

/**
 *
 * @author Pavel
 * @version 1.0
 */

@Configuration
@ComponentScan("org.mannayakasha.*")
@EnableTransactionManagement
// Load to Environment.
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class ApplicationContextConfig {

    // The Environment class serves as the property holder
    // and stores all the properties loaded by the @PropertySource
    @Autowired
    private Environment env;

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
        // Load property in message/validator.properties
        rb.setBasenames(new String[]{"/validator"});
        return rb;
    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    // Config for Upload.
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

        // Set Max Size...
        // commonsMultipartResolver.setMaxUploadSize(...);

        return commonsMultipartResolver;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // See: ds-hibernate-cfg.properties
        dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
        dataSource.setUrl(env.getProperty("ds.url"));
        dataSource.setUsername(env.getProperty("ds.username"));
        dataSource.setPassword(env.getProperty("ds.password"));

        System.out.println("## getDataSource: " + dataSource);

        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        Properties properties = new Properties();

        // See: ds-hibernate-cfg.properties
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("current_session_context_class", env.getProperty("current_session_context_class"));


        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        // Package contain entity classes
        factoryBean.setPackagesToScan(new String[]{"org.mannayakasha.entity"});
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();
        //
        SessionFactory sf = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sf);
        return sf;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

        return transactionManager;
    }

    @Bean(name = "accountDao")
    public AccountDao getApplicantDao() {
        return new AccountDaoImpl();
    }

    @Bean(name = "productDao")
    public ProductDao getProductDao() {
        return new ProductDaoImpl();
    }

    @Bean(name = "orderDao")
    public OrderDao getOrderDAO() {
        return new OrderDaoImpl();
    }

    @Bean(name = "accountDao")
    public AccountDao getAccountDao() {
        return new AccountDaoImpl();
    }

    @Bean(name = "productService")
    public ProductService getProductService() {
        return new ProductServiceImpl();
    }

    @Bean(name = "orderService")
    public OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    @Bean(name = "accountService")
    public AccountService getAccountService() {
        return new AccountServiceImpl();
    }

    /*@Bean(name = "serviceFactory")
    public ServiceFactory getServiceFactory() {
        return new ServiceLocatorFactoryBean().setServiceLocatorInterface(Class<? extends Service>);
        //return new ServiceLocatorFactoryBean().setServiceLocatorInterface(Class<?> ServiceFactory);
    }*/
}
