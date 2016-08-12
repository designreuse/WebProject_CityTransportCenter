/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for entering to time interval form modifying.
 * 
 */
package commands;

import controllermanagers.ConfigManager;
import dao.IRoutesDAO;
import dao.JDBCEntityDAOFactory;
import entities.Marshroutes;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author city.com
 */
public class TimeIntervalFormCommand extends Command {

    private static Logger log = Logger.getLogger(TimeIntervalFormCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        log.log(Level.INFO, "TimeIntervalFormCommand instance: created");
        
        IRoutesDAO routesDAO = JDBCEntityDAOFactory.getInstance().getRoutesDAO();
        List<Marshroutes> listRoutes = routesDAO.findAll();
        
        request.setAttribute("routeslist", listRoutes);
        log.log(Level.INFO, "routesDAO.findAll(): ListSize: " + listRoutes.size());
        String page = ConfigManager.getInstance().getProperty(ConfigManager.CREATE_TIME_INTERVAL_FORM);
        return page;
    }
    
}
