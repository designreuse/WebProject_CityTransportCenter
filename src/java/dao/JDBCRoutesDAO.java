/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: DAO for operating with Marshroutes.
 * 
 */
package dao;

import dao.connections.GeneralConnectionsFactory;
import entities.Marshroutes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmytro Deinichenko
 */
public class JDBCRoutesDAO extends JDBCGeneralDAO implements IRoutesDAO {


    private static final Logger log = Logger.getLogger(JDBCRoutesDAO.class.getName());
    
    public JDBCRoutesDAO(GeneralConnectionsFactory connFactory) {
        super(connFactory);
        
    }

    @Override
    protected Connection getConnection() {
       
        try {
            if (conn.isClosed()){
                conn = connFactoryInstance.getConnection();
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    @Override
    protected void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Marshroutes findByRouteName(String login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Marshroutes parseRoute(ResultSet rs) throws SQLException {
        Marshroutes route = new Marshroutes();
        route.setIdmarshroutes(rs.getInt("idmarshroutes"));
        log.log(Level.INFO, new Integer(rs.getInt("idmarshroutes")).toString());
        route.setRoutename(rs.getString("Routename"));
        log.log(Level.INFO, rs.getString("Routename"));
        route.setDescription(rs.getString("Description"));
        log.log(Level.INFO, rs.getString("Description"));
        return route;
    }

    @Override
    public boolean create(Marshroutes entity) {
        
        boolean result = false;
        
        if (conn == null){
            conn = connFactoryInstance.getConnection();
        }
        String stmt = "INSERT INTO `citytransport`.`marshroutes` (`Routename`, `Description`) VALUES (?, ?);";
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setString(1, entity.getRoutename());
            pStmt.setString(2, entity.getDescription());
            log.log(Level.OFF, null, pStmt);
            pStmt.executeUpdate();

            pStmt.close();
            conn.close();
            result = true;
            log.log(Level.OFF, "creating marshroute: successfull");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean delete(Marshroutes entity) {
        boolean result = false;
        
        getConnection();
        String stmt = "DELETE FROM `citytransport`.`marshroutes` WHERE `idmarshroutes`= ?;";
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setInt(1, entity.getIdmarshroutes());
            log.log(Level.OFF, pStmt.toString());
            pStmt.executeUpdate();

            pStmt.close();
            conn.close();
            result = true;
            log.log(Level.OFF, "deleting vehicle: successfull");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(Marshroutes entity) {
       
        boolean result = false;
        
        if (conn == null){
            conn = connFactoryInstance.getConnection();
        }
        String stmt = "UPDATE `citytransport`.`marshroutes` SET `Routename`=?, `Description`=? WHERE `idmarshroutes`=?;";
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setString(1, entity.getRoutename());
            log.log(Level.INFO, "getRoutename() = " + entity.getRoutename());
            pStmt.setString(2, entity.getDescription());
            log.log(Level.INFO, "getDescription() = " + entity.getDescription());
            pStmt.setInt(3, entity.getIdmarshroutes());
            log.log(Level.INFO, "getIdmarshroutes() = " + entity.getIdmarshroutes());
            log.log(Level.OFF, pStmt.toString());
            pStmt.executeUpdate();

            pStmt.close();
            conn.close();
            result = true;
            log.log(Level.OFF, "updating marshroute: successfull");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return result; 
    }

    @Override
    public Marshroutes findById(int uId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Marshroutes> findAll() {
        
        List<Marshroutes> listRoutes = new ArrayList<>();
        
        if (conn == null){
            conn = connFactoryInstance.getConnection();
        }
        
        try {
            String stmt = "select * from citytransport.marshroutes";
            log.log(Level.OFF, stmt);
            Statement pStmt = conn.createStatement();
            log.log(Level.OFF, pStmt.toString());
            ResultSet rs = pStmt.executeQuery(stmt);
            log.log(Level.OFF, rs.toString());
            while (rs.next()) {
                listRoutes.add(parseRoute(rs));
            }
            
            for (Marshroutes m : listRoutes){
                log.log(Level.INFO, (m.getIdmarshroutes().toString() + m.getRoutename()+ m.getDescription()));
            }
            rs.close();
            pStmt.close();
            conn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return listRoutes;
    }
}
