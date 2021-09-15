package com.codecool.shop.manager;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.CartDaoJdbc;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.Supplier;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class CodecoolShopDbManager {
    CartDao cartDao;
    ProductCategoryDao productCategoryDao;
    ProductDao productDao;
    SupplierDao supplierDao;
    OrderDao orderDao;
    ProductForOrderDao productForOrderDao;

    public CodecoolShopDbManager() {
        this.run();
    }

    private void run() {
        try {
            setup();
        } catch (SQLException throwable) {
            System.err.println("Could not connect to the database.");
            System.exit(-1);
        }
    }

    public CartDao getCartDao() {
        return cartDao;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public ProductForOrderDao getProductForOrderDao() {
        return productForOrderDao;
    }

    private void setup() throws SQLException {
        DataSource dataSource = connect();
        cartDao = new CartDaoJdbc(dataSource);
        productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        productDao = new ProductDaoJdbc(dataSource);
        supplierDao = new SupplierDaoJdbc(dataSource);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("codecoolshop");
        dataSource.setUser("postgres");
        dataSource.setPassword("admin");

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");

        return dataSource;
    }
}
