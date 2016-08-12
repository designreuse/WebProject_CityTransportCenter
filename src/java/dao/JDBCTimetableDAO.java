/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: DAO for operating with Time Intervals.
 * 
 */
package dao;

import dao.connections.GeneralConnectionsFactory;
import entities.Marshroutes;
import entities.Timetable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author city.com
 */
public class JDBCTimetableDAO extends JDBCGeneralDAO implements ITimetableDAO {
    
    private static final Logger log = Logger.getLogger(JDBCTimetableDAO.class.getName());
 
    public JDBCTimetableDAO(GeneralConnectionsFactory connFactory) {
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
    public Timetable parseTimetableRecord(ResultSet rs) throws SQLException {
        
        Timetable timeTable = new Timetable();
        
        timeTable.setTtid(rs.getInt("ttid"));
        log.log(Level.INFO, new Integer(rs.getInt("ttid")).toString());
        
        timeTable.setDirection(rs.getString("Direction"));
        log.log(Level.INFO, rs.getString("Direction"));
        
        timeTable.setStartPoint(rs.getString("StartPoint"));
        log.log(Level.INFO, rs.getString("StartPoint"));
        
        timeTable.setEndPoint(rs.getString("EndPoint"));
        log.log(Level.INFO, rs.getString("EndPoint"));
        
        timeTable.setInterval(rs.getTime("Interval"));
        log.log(Level.INFO, rs.getTime("Interval").toString());
        
        timeTable.setRouteRef(rs.getInt("RouteRef"));
        log.log(Level.INFO, new Integer(rs.getInt("RouteRef")).toString());
        
        timeTable.setRoutename(rs.getString("Routename"));
        log.log(Level.INFO, rs.getString("Routename"));
        
        return timeTable;
    }

    @Override
    public boolean create(Timetable entity) {
        
        boolean result = false;
        getConnection();
        String stmt = "INSERT INTO `citytransport`.`timetable` (`Direction`, `StartPoint`, `EndPoint`, `Interval`, `RouteRef`)"
                + " VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setString(1, entity.getDirection());
            pStmt.setString(2, entity.getStartPoint());
            pStmt.setString(3, entity.getEndPoint());
            pStmt.setTime(4, new Time(entity.getInterval().getTime()));
            pStmt.setInt(5, entity.getRouteRef());
            log.log(Level.OFF, pStmt.toString());
            pStmt.executeUpdate();

            pStmt.close();
            conn.close();
            result = true;
            log.log(Level.OFF, "creating of time interval: successfull");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean delete(Timetable entity) {
        
        boolean result = false;
        getConnection();
        String stmt = "DELETE FROM `citytransport`.`timetable` WHERE `ttid`=?;";
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setInt(1, entity.getTtid());
            log.log(Level.OFF, pStmt.toString());
            pStmt.executeUpdate();

            pStmt.close();
            conn.close();
            result = true;
            log.log(Level.OFF, "deleting time interval: successfull");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(Timetable entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Timetable findById(int uId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Timetable> findAll() {
        
        List<Timetable> listTimetable = new ArrayList<>();
        getConnection();
        try {
            String stmt = "SELECT * FROM citytransport.timetable_view;";
            log.log(Level.OFF, stmt);
            Statement pStmt = conn.createStatement();
            log.log(Level.OFF, pStmt.toString());
            ResultSet rs = pStmt.executeQuery(stmt);
            log.log(Level.OFF, rs.toString());
            while (rs.next()) {
                listTimetable.add(parseTimetableRecord(rs));
            }

            for (Timetable t : listTimetable) {
                log.log(Level.INFO, (t.getTtid() + " " + t.getDirection() + " " 
                        + t.getStartPoint() + " " + t.getEndPoint() + " " + t.getInterval()
                        + " " + t.getRoutename()));
            }

            rs.close();
            pStmt.close();
            conn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return listTimetable;
    }

    @Override
    public List<Timetable> getLinkedToRouteList(Marshroutes entity) {
        
        List<Timetable> listTimeIntervals = new ArrayList<>();
        String stmt = "SELECT * FROM `timetable_view` t WHERE t.`RouteRef`= ? ORDER BY Direction DESC;";
        getConnection();
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setInt(1, entity.getIdmarshroutes());
            log.log(Level.OFF, pStmt.toString());
            ResultSet rs = pStmt.executeQuery();
            log.log(Level.OFF, rs.toString());
            while (rs.next()) {
                listTimeIntervals.add(parseTimetableRecord(rs));
            }
            for (Timetable t : listTimeIntervals) {
                log.log(Level.INFO, t.getTtid() + " " + t.getDirection() + " "
                        + t.getStartPoint() + " " + t.getEndPoint() + " "
                        + t.getIntervalStr() + " " + t.getRouteRef()
                        + " " + t.getRoutename());
            }
            rs.close();
            pStmt.close();
            conn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return listTimeIntervals;
        
        
    }

    @Override
    public boolean deleteIntervalsList(List<Timetable> timeIntervalList) {
              
        boolean result = false;

        getConnection();
        String stmt = "DELETE FROM `citytransport`.`timetable` WHERE `ttid`=?;";
        try {
            for (Timetable timeTableEntity : timeIntervalList) {
                PreparedStatement pStmt = conn.prepareStatement(stmt);
                pStmt.setInt(1, timeTableEntity.getTtid());
                log.log(Level.OFF, pStmt.toString());
                pStmt.executeUpdate();
                pStmt.close();
            }
            conn.close();
            result = true;
            log.log(Level.OFF, "deleting time interval: successfull");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
