package eventmanagementproject.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {

    private static HikariDataSource ds;

    static{
        HikariConfig config = new HikariConfig();

        Properties prop=new Properties();
        try(InputStream input=ConnectionPool.class.getClassLoader().getResourceAsStream("db.properties")){
            prop.load(input);
        } catch(IOException e){
           e.printStackTrace();
        }
        config.setJdbcUrl(prop.getProperty("db.url"));
        config.setUsername(prop.getProperty("db.username"));
        config.setPassword(prop.getProperty("db.password"));

        ds=new HikariDataSource(config);
    }

    private ConnectionPool(){
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}

