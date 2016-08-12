/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Factory for creating of Entities.
 * 
 */

package dao;


import dao.connections.MySQLConnectionsFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmytro Deinichenko
 */
public class JDBCEntityDAOFactory {
    
    private static volatile JDBCEntityDAOFactory instance;
    private static Logger log = Logger.getLogger(JDBCEntityDAOFactory.class.getName());

    private JDBCEntityDAOFactory() {
        log.log(Level.INFO,"JDBCEntityDAOFactory instance created.");
    }
    
    public static JDBCEntityDAOFactory getInstance() {
        JDBCEntityDAOFactory localInst = instance;
        if (localInst == null) {
            synchronized (JDBCEntityDAOFactory.class) {
                localInst = instance;
                if (localInst == null) {
                    instance = localInst = new JDBCEntityDAOFactory();
                }
            }
        }
        return localInst;
    }

    public synchronized IUsersDAO getUserDAO() {
        return new JDBCUsersDAO(MySQLConnectionsFactory.getInstance());
    }

    public synchronized IRoutesDAO getRoutesDAO() {
        return new JDBCRoutesDAO(MySQLConnectionsFactory.getInstance());
    }

    public synchronized IVehiclesDAO getVehiclesDAO() {
        return new JDBCVehiclesDAO(MySQLConnectionsFactory.getInstance());
    }
    
    public synchronized ITimetableDAO getTimetableDAO() {
        return new JDBCTimetableDAO(MySQLConnectionsFactory.getInstance());
    }
}
