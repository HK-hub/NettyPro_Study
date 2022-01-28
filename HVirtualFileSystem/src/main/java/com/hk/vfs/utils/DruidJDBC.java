package com.hk.vfs.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.Properties;

/**
 * @author : HK意境
 * @ClassName : DruidJDBC
 * @date : 2021/10/16 8:39
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class DruidJDBC {

    //声明druid连接池的数据源
    public static DruidDataSource dataSource;

    //

    static{
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void init() throws IOException {
        //创建连接池对象
        dataSource = new DruidDataSource();

        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(systemResourceAsStream);
        Formatter formatter = new Formatter();


        dataSource.setDriverClassName(properties.getProperty("driverClassName"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));

        //设置最大连接数
        dataSource.setMaxActive(10);//CUP*2+1
        //设置最小的闲置连接数
        dataSource.setMinIdle(1);
        //设置初始的连接数
        dataSource.setInitialSize(2);
        //最长等待连接时间(MS)
        dataSource.setMaxWait(10000);
    }

    public static synchronized DruidPooledConnection getConn() throws IOException {
        //确保连接池是单例
        if(dataSource == null || dataSource.isClosed()){
            //重新初始化连接池
            init();
        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet resultSet , PreparedStatement preparedStatement , Connection connection){

        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (preparedStatement != null){

            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (connection != null) {

            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static DruidDataSource getDataSource() {
        return dataSource;
    }
}
