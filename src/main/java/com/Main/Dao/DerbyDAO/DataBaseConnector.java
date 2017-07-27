package com.Main.Dao.DerbyDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Connecting to database and getting connection
 */
public class DataBaseConnector {

    private static Connection connection;
    private static Statement statement;

    public static Statement getStatement()
    {
        return statement;
    }

    public static Connection getConnection()
    {
        return connection;
    }

    /**
     * connects to database if exist or creates new database
     * @throws java.sql.SQLException
     */
    public static void connectToDataBase(String name) throws java.sql.SQLException
    {

        try
        {
            String dbUrl = "jdbc:derby:.\\"+name+";create=false";
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(true);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
        catch(java.sql.SQLException excception)
        {
            String dbUrl = "jdbc:derby:.\\"+name+";create=true";
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(true);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.execute("Create table Notes (id int primary key, note varchar(500), created bigint, updated bigint)");
            connection.commit();
        }
    }
}
