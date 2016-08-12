/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Interface for Users DAO-s.
 * 
 */
package dao;


import entities.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dmytro Deinichenko
 */

public interface IUsersDAO extends IGeneralEntityDAO<Users> {

    Users findByLogin(String login);
    
    void parseUsers(ResultSet rs, Users user) throws SQLException;

}
