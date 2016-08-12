/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Abstract class for All JDBC DAO-s.
 * 
 */
package dao;

import dao.connections.GeneralConnectionsFactory;
import dao.connections.MySQLConnectionsFactory;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmytro Deinichenko
 */
public abstract class JDBCGeneralDAO {
    Connection conn;
    GeneralConnectionsFactory connFactoryInstance;  // Can be Object type without casting in line 19;
    
    public JDBCGeneralDAO(GeneralConnectionsFactory connFactory) {
        
            connFactoryInstance = connFactory;
            conn = connFactoryInstance.getConnection();
    }

    protected abstract Connection getConnection();

    protected abstract void closeConnection();
}
