/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: DAO for operating with vehicles.
 * 
 */
package dao;

import dao.connections.GeneralConnectionsFactory;
import entities.Marshroutes;
import entities.Vehicles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmytro Deinichenko
 */
public class JDBCVehiclesDAO extends JDBCGeneralDAO implements IVehiclesDAO{
    
    private static final Logger log = Logger.getLogger(JDBCVehiclesDAO.class.getName());

    public JDBCVehiclesDAO(GeneralConnectionsFactory connFactory) {
        
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
    public Vehicles findByVehicleName(String login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vehicles parseVehicle(ResultSet rs) throws SQLException {
        Vehicles vehicles = new Vehicles();
        vehicles.setVid(rs.getInt("vid"));
        log.log(Level.INFO, new Integer(rs.getInt("vid")).toString());
        vehicles.setVehiclestypesname(rs.getString("vtypename"));
        log.log(Level.INFO, rs.getString("vtypename"));
        vehicles.setVehiclestypesdescription(rs.getString("vtypedescription"));
        log.log(Level.INFO, rs.getString("vtypedescription"));
        vehicles.setVmark(rs.getString("vmark"));
        log.log(Level.INFO, rs.getString("vmark"));
        vehicles.setVmodel(rs.getString("vmodel"));
        log.log(Level.INFO, rs.getString("vmodel"));
        vehicles.setAssignedto(rs.getInt("assignedto"));
        log.log(Level.INFO, new Integer(rs.getInt("assignedto")).toString());
        vehicles.setAssignedtoname(rs.getString("assignedtoname"));
        log.log(Level.INFO, rs.getString("assignedtoname"));
        vehicles.setStatus(rs.getString("Status"));
        log.log(Level.INFO, rs.getString("Status"));
        return vehicles;
    }

    @Override
    public boolean create(Vehicles entity) {
        boolean result = false;
        
        getConnection();
        String stmt = "INSERT INTO `citytransport`.`vehicles` (`vtype`, `vmark`, `vmodel`, `Status`) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setInt(1, entity.getVehiclestypes());
            pStmt.setString(2, entity.getVmark());
            pStmt.setString(3, entity.getVmodel());
            pStmt.setString(4, entity.getStatus());
            log.log(Level.OFF, pStmt.toString());
            pStmt.executeUpdate();

            pStmt.close();
            conn.close();
            result = true;
            log.log(Level.OFF, "creating vehicle: successfull");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean delete(Vehicles entity) {
        
        boolean result = false;
        
        getConnection();
        String stmt = "DELETE FROM `citytransport`.`vehicles` WHERE `vid`=?;";
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setInt(1, entity.getVid());
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
    public boolean update(Vehicles entity) {
        
        boolean result = false;
        
        getConnection();
        String stmt = "UPDATE `citytransport`.`vehicles` SET `vtype`=?, `vmark`=?, `vmodel`=?, `Status`=? WHERE `vid`=?;";
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setInt(1, entity.getVehiclestypes());
            pStmt.setString(2, entity.getVmark());
            log.log(Level.INFO, "getVmark() = " + entity.getVmark());
            pStmt.setString(3, entity.getVmodel());
            log.log(Level.INFO, "getVmodel() = " + entity.getVmodel());
            pStmt.setString(4, entity.getStatus());
            log.log(Level.INFO, "getStatus() = " + entity.getStatus());
            pStmt.setInt(5, entity.getVid());
            
            log.log(Level.OFF, pStmt.toString());
            pStmt.executeUpdate();

            pStmt.close();
            conn.close();
            result = true;
            log.log(Level.OFF, "updating vehicle: successfull");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public Vehicles findById(int uId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vehicles> findAll() {
        
       List<Vehicles> listVehicles = new ArrayList<>();
        
        getConnection();
        try {
            String stmt = "SELECT * FROM citytransport.vehicles_view;";
            log.log(Level.OFF, stmt);
            Statement pStmt = conn.createStatement();
            log.log(Level.OFF, pStmt.toString());
            ResultSet rs = pStmt.executeQuery(stmt);
            log.log(Level.OFF, rs.toString());
            while (rs.next()) {
                listVehicles.add(parseVehicle(rs));
            }
            for (Vehicles m : listVehicles) {
                log.log(Level.INFO, (m.getVid().toString() + m.getVmark() 
                        + m.getVmodel() + m.getVehiclestypesname()
                        + m.getVehiclestypesdescription() + m.getAssignedto() 
                        + m.getStatus()));
            }
            rs.close();
            pStmt.close();
            conn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return listVehicles;
    }

    @Override
    public boolean assignToRoute(Vehicles vehEntity, Marshroutes routeEntity) {
        
        boolean result = false;
        int assignTo = 0;
        
        getConnection();
        String stmt = "UPDATE `citytransport`.`vehicles` SET `assignedto`=? WHERE `vid`=?;";
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            assignTo = routeEntity.getIdmarshroutes();
            if (assignTo == 0){
                pStmt.setNull(1, Types.NULL);
            } else {
                pStmt.setInt(1, assignTo);
            }
            log.log(Level.INFO, "routeEntity.getIdmarshroutes() = " + routeEntity.getIdmarshroutes());
            pStmt.setInt(2, vehEntity.getVid());
            log.log(Level.INFO, "vehEntity.getVid() = " + vehEntity.getVid());
            
            log.log(Level.OFF, pStmt.toString());
            pStmt.executeUpdate();

            pStmt.close();
            conn.close();
            result = true;
            log.log(Level.OFF, "assigning vehicle: successfull");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public List<Vehicles> getLinkedToRouteList(Marshroutes entity) {
        
        List<Vehicles> listVehicles = new ArrayList<>();
        
        getConnection();
        String stmt = "SELECT * FROM `citytransport`.`vehicles_view` WHERE `assignedto`= ?";
        
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setInt(1, entity.getIdmarshroutes());
            log.log(Level.OFF, pStmt.toString());
            ResultSet rs = pStmt.executeQuery();
            log.log(Level.OFF, rs.toString());
            while (rs.next()) {
                listVehicles.add(parseVehicle(rs));
            }
            for (Vehicles m : listVehicles) {
                log.log(Level.INFO, (m.getVid().toString() + m.getVmark() 
                        + m.getVmodel() + m.getVehiclestypesname()
                        + m.getVehiclestypesdescription() + m.getAssignedto() 
                        + m.getStatus()));
            }
            rs.close();
            pStmt.close();
            conn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return listVehicles;
    }

    @Override
    public boolean clearVehiclesList(List<Vehicles> listVehicles) {
        
        boolean result = false;
        
        getConnection();
        String stmt = "UPDATE `citytransport`.`vehicles` SET `assignedto`= NULL WHERE `vid`= ?;";
        try {
            for (Vehicles vehEntity : listVehicles){
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setInt(1, vehEntity.getVid());  
            log.log(Level.OFF, pStmt.toString());
            pStmt.executeUpdate();

            pStmt.close();
            }
            conn.close();
            result = true;
            log.log(Level.OFF, "clearing vehicles references: successfull");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
