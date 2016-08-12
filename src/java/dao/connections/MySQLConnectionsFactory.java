/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: MySQL Connection Factory implements operations with TomCat
 * Connection Pool.
 */
package dao.connections;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Dmytro Deinichenko
 */
public class MySQLConnectionsFactory extends GeneralConnectionsFactory {
    
    private static volatile MySQLConnectionsFactory instance;
    private DataSource dSrc;
    private static Logger log = Logger.getLogger(MySQLConnectionsFactory.class.getName()); 

    private MySQLConnectionsFactory() {
        log.log(Level.INFO,"MySQLConnectionsFactory: instance created.");
        Context initialCtx = null;
        Context envCtx = null;
        
        try {
            initialCtx = new InitialContext();
            envCtx = (Context) initialCtx.lookup("java:comp/env");
            dSrc = (DataSource) envCtx.lookup("jdbc/citytransport");
            log.log(Level.INFO,"MySQLConnectionsFactory: DataSource acquired.");
        } catch (NamingException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static MySQLConnectionsFactory getInstance(){
        MySQLConnectionsFactory localInst = instance;
        if (localInst == null){
            synchronized (MySQLConnectionsFactory.class){
                localInst = instance;
                if (localInst == null){
                    instance = localInst = new MySQLConnectionsFactory();
                }
            }
        }
        return localInst;
    }
    
    public Connection getConnection(){
        Connection con = null;
        try {
            con = dSrc.getConnection();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
