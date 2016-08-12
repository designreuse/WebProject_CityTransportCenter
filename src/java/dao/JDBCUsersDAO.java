/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: DAO for operating with Users.
 * 
 */
package dao;

import dao.connections.GeneralConnectionsFactory;
import entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmytro Deinichenko
 */
public class JDBCUsersDAO extends JDBCGeneralDAO implements IUsersDAO {

    private static final Logger log = Logger.getLogger(JDBCUsersDAO.class.getName());
    
    public JDBCUsersDAO(GeneralConnectionsFactory connFactory) {
        super(connFactory);
        
    }

    @Override
    public Users findByLogin(String login) {

        Users localUsers = new Users();

        getConnection();
        try {
            String stmt = "select * from users t where t.`LoginName` = ?";
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setString(1, login);
            log.log(Level.SEVERE, pStmt.toString());
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                parseUsers(rs, localUsers);
                log.log(Level.INFO, localUsers.toString());
            } else {
                localUsers = null;
            }
            rs.close();
            pStmt.close();
            conn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return localUsers;
    }

    

    @Override
    public boolean create(Users entity) {
        boolean result = false;
        
        getConnection();
        String stmt = "INSERT INTO `citytransport`.`users` (`LoginName`, `Mail`, `Password`, `Role`, `Status`)"
                + " VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement pStmt = conn.prepareStatement(stmt);
            pStmt.setString(1, entity.getLoginName());
            pStmt.setString(2, entity.getMail());
            pStmt.setString(3, entity.getPassword());
            pStmt.setString(4, entity.getRole());
            pStmt.setString(5, entity.getStatus());
            log.log(Level.OFF, null, pStmt);
            pStmt.executeUpdate();

            pStmt.close();
            conn.close();
            result = true;
            log.log(Level.OFF, "creating new User: successfull");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean delete(Users entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Users entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Users findById(int uId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Users> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void parseUsers(ResultSet rs, Users user) throws SQLException {
        
        user.setUid(rs.getInt("Uid"));
        user.setLoginName(rs.getString("LoginName"));
        user.setMail(rs.getString("Mail"));
        user.setPassword(rs.getString("Password"));
        user.setRole(rs.getString("Role"));
        user.setStatus(rs.getString("Status"));
    }


    
    
}
