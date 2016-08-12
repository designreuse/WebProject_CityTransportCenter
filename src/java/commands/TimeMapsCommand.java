/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for entering to time interval page.
 * 
 */
package commands;

import controllermanagers.ConfigManager;
import dao.ITimetableDAO;
import dao.JDBCEntityDAOFactory;
import entities.Timetable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dmytro Deinichenko
 */
public class TimeMapsCommand extends Command {
    private static Logger log = Logger.getLogger(TimeMapsCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        log.log(Level.INFO, "TimeMapsCommand instance: created");
        
        ITimetableDAO timeTableDAO = JDBCEntityDAOFactory.getInstance().getTimetableDAO();
        List<Timetable> listTimetable = timeTableDAO.findAll();
        request.setAttribute("timetablelist", listTimetable);
        
        log.log(Level.INFO, "routesDAO.findAll(): ListSize: " + listTimetable.size());
        String page = ConfigManager.getInstance().getProperty(ConfigManager.TIMEMAPS_PAGE_PATH);
        return page;
    }
}
