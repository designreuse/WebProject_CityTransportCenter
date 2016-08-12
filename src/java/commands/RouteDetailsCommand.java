/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command to entering to marshroute details page.
 * 
 */
package commands;

import controllermanagers.ConfigManager;
import dao.ITimetableDAO;
import dao.IVehiclesDAO;
import dao.JDBCEntityDAOFactory;
import entities.Marshroutes;
import entities.Timetable;
import entities.Vehicles;
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
public class RouteDetailsCommand extends Command {

    private static Logger log = Logger.getLogger(RouteDetailsCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        log.log(Level.INFO, "RouteDetailsCommand instance: created");
        Marshroutes detailsRoute = new Marshroutes(Integer.parseInt(request.getParameter("idroute")));
        detailsRoute.setRoutename(request.getParameter("routename"));
        detailsRoute.setDescription(request.getParameter("routedescription"));
        
        IVehiclesDAO vehiclesDAO = JDBCEntityDAOFactory.getInstance().getVehiclesDAO();
        ITimetableDAO timeIntervalDAO = JDBCEntityDAOFactory.getInstance().getTimetableDAO();
        List<Vehicles> linkedVehicles = vehiclesDAO.getLinkedToRouteList(detailsRoute);
        List<Timetable> linkedTimeIntervals = timeIntervalDAO.getLinkedToRouteList(detailsRoute);
        
        request.setAttribute("route", detailsRoute);
        log.log(Level.INFO, "Route details: " + detailsRoute.toString());
        request.setAttribute("vehicleslist", linkedVehicles);
        log.log(Level.INFO, "Linked to Route vehicle list size: " + linkedVehicles.size());
        request.setAttribute("intervalslist", linkedTimeIntervals);
        log.log(Level.INFO, "Linked to Route time intervals list size: " + linkedTimeIntervals.size());
        
        String page = ConfigManager.getInstance().getProperty(ConfigManager.MARSHROUTE_DETAILS);
        return page;
    }
    
    
    
}
