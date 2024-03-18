package cn.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbutil {
    private String dburl="jdbc:mysql://localhost:3306/student";
    private String dbusername="root";
    private String dbpassword="123456";
    private String jdbcname="com.mysql.jdbc.Driver";
    public Connection getcon() throws Exception{
        Class.forName(jdbcname);
        Connection connection = DriverManager.getConnection(dburl,dbusername,dbpassword);
        return connection;
    }
    public void closecon(Connection connection)throws Exception{
        if (connection!=null){
            connection.close();
        }
    }
}
