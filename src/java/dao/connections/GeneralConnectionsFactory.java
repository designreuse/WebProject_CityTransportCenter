/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Abstract class for all types of Connection Factories.
 * 
 */
package dao.connections;

import java.sql.Connection;

/**
 *
 * @author Dmytro Deinichenko
 */
public abstract class GeneralConnectionsFactory {

    public abstract Connection getConnection();
    
}
