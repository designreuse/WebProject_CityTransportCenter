/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Interface for Marshroutes DAO-s.
 * 
 */
package dao;

import entities.Marshroutes;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dmytro Deinichenko
 */
public interface IRoutesDAO extends IGeneralEntityDAO<Marshroutes> {

    Marshroutes findByRouteName(String login);

    Marshroutes parseRoute(ResultSet rs) throws SQLException;
}
