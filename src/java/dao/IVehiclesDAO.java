/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Interface for Vehicles DAO-s.
 * 
 */
package dao;

import entities.Marshroutes;
import entities.Vehicles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dmytro Deinichenko
 */
public interface IVehiclesDAO extends IGeneralEntityDAO<Vehicles>{
    
    Vehicles findByVehicleName(String login);
    
    Vehicles parseVehicle(ResultSet rs) throws SQLException;
    
    boolean assignToRoute(Vehicles idVehicle, Marshroutes idRoute);
    
    List<Vehicles> getLinkedToRouteList(Marshroutes entity);
    
    boolean clearVehiclesList(List<Vehicles> listVehicles);
}
