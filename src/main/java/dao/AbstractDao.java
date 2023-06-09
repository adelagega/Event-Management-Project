package dao;
import util.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDao<T> implements GenericDao<T> {
    protected Connection getConnection() throws SQLException{
        return ConnectionPool.getConnection();
    }

    protected PreparedStatement prepareStatement(String sql) throws SQLException{
        Connection con= this.getConnection();
        return con.prepareStatement(sql);
    }
}
