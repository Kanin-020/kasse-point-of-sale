package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.data.Product;
import com.utils.DatabaseConnection;

public class ProductDAO {

    private static Connection connection = DatabaseConnection.getInstance().getConnection();

    public static int add(Product product) throws SQLException {

        String query = "INSERT INTO product_table(name, category, quantity, supplierCost, costOfSale) VALUES (?,?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, product.getName());
        statement.setString(2, product.getCategory());
        statement.setInt(3, product.getQuantity());
        statement.setDouble(4, product.getSupplierCost());
        statement.setDouble(5, product.getCostOfSale());

        return statement.executeUpdate();
    }

    public static ArrayList<Product> select(Product product) throws SQLException {

        ArrayList<Product> productList = new ArrayList<Product>();

        String query = "SELECT * FROM product_table WHERE code = ?";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, product.getCode());

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            product = new Product(
                    resultSet.getInt("code"),
                    resultSet.getString("name"),
                    resultSet.getString("category"),
                    resultSet.getInt("quantity"),
                    resultSet.getDouble("supplierCost"),
                    resultSet.getDouble("costOfSale"));

            productList.add(product);

        }

        return productList;
    }

    public static ArrayList<Product> selectAll() throws SQLException {

        ArrayList<Product> productList = new ArrayList<>();

        String query = "SELECT * FROM product_table";

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            Product product = new Product(
                    resultSet.getInt("code"),
                    resultSet.getString("name"),
                    resultSet.getString("category"),
                    resultSet.getInt("quantity"),
                    resultSet.getDouble("supplierCost"),
                    resultSet.getDouble("costOfSale")

            );

            productList.add(product);
        }

        return productList;
    }

    public static int modify(Product product, Product newProduct) throws SQLException {

        String query = "UPDATE product_table SET name = ?, category = ?, quantity = ?, supplierCost = ?, costOfSale = ? WHERE code = ?";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, newProduct.getName());
        statement.setString(2, newProduct.getCategory());
        statement.setInt(3, newProduct.getQuantity());
        statement.setDouble(4, newProduct.getSupplierCost());
        statement.setDouble(5, newProduct.getCostOfSale());

        statement.setInt(6, product.getCode());

        return statement.executeUpdate();
    }

    public static int delete(Product product) throws SQLException {

        String query = "DELETE FROM product_table WHERE code = ?";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, product.getCode());

        return statement.executeUpdate();
    }

}
