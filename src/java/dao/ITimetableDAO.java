/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Interface for Time Intervals DAO-s.
 * 
 */
package dao;

import entities.Marshroutes;
import entities.Timetable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dmytro Deinichenko
 */
public interface ITimetableDAO extends IGeneralEntityDAO<Timetable> {

    Timetable parseTimetableRecord(ResultSet rs) throws SQLException;
    
    List<Timetable> getLinkedToRouteList(Marshroutes entity);
    
    boolean deleteIntervalsList(List<Timetable> timeIntervalList);
    
}
