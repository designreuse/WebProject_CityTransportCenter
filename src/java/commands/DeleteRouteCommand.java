/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for deleting of marshroutes.
 * 
 */
package commands;

import controller.CommandHolder;
import controllermanagers.MessageManager;
import dao.IRoutesDAO;
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
public class DeleteRouteCommand extends Command {

    private static Logger log = Logger.getLogger(DeleteRouteCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean operationsResult = false;
        
        log.log(Level.INFO, "DeleteRouteCommand instance: created");
        MessageManager messMan = MessageManager.getInstance();
        
        IRoutesDAO routesDAO = JDBCEntityDAOFactory.getInstance().getRoutesDAO();
        Marshroutes delRoute = new Marshroutes(Integer.parseInt(request.getParameter("idroute")));
        delRoute.setRoutename(request.getParameter("routename"));
        delRoute.setDescription(request.getParameter("routedescription"));
        log.log(Level.INFO, "Deleting Marshroute details: " + delRoute.toString());
        
        IVehiclesDAO vehiclesDAO = JDBCEntityDAOFactory.getInstance().getVehiclesDAO();
        ITimetableDAO timeIntervalDAO = JDBCEntityDAOFactory.getInstance().getTimetableDAO();
        List<Vehicles> linkedVehicles = vehiclesDAO.getLinkedToRouteList(delRoute);
        List<Timetable> linkedTimeIntervals = timeIntervalDAO.getLinkedToRouteList(delRoute);
        log.log(Level.INFO, "Linked to Marshroute vehicles list size: " + linkedVehicles.size());
        log.log(Level.INFO, "Linked to Marshroute time intervals list size: " + linkedTimeIntervals.size());


        if (operationsResult = timeIntervalDAO.deleteIntervalsList(linkedTimeIntervals)){
            request.setAttribute("messageResult", messMan.getProperty(MessageManager.INTERVAL_LIST_DELETE_SUCCESS));
            log.log(Level.INFO, "Deleting the Interval list: successful");
        } else {
            request.setAttribute("messageError", messMan.getProperty(MessageManager.INTERVAL_LIST_DELETE_ERROR));
            log.log(Level.INFO, "Deleting the Interval list: failed");
        }
        
        if (operationsResult = vehiclesDAO.clearVehiclesList(linkedVehicles)){
            request.setAttribute("messageResult", messMan.getProperty(MessageManager.VEHICLE_LIST_DELETE_SUCCESS));
            log.log(Level.INFO, "Deleting the vehicles list: successful");
        } else {
            request.setAttribute("messageError", messMan.getProperty(MessageManager.VEHICLE_LIST_DELETE_ERROR));
            log.log(Level.INFO, "Deleting the vehicles list: failed");
        }
        
        if (operationsResult = routesDAO.delete(delRoute)){
            request.setAttribute("messageResult", messMan.getProperty(MessageManager.ROUTE_DELETE_SUCCESS));
            log.log(Level.INFO, "Deleting the route: successful");
        } else {
            request.setAttribute("messageError", messMan.getProperty(MessageManager.ROUTE_DELETE_ERROR));
            log.log(Level.INFO, "Deleting the route: failed");
        }
        
        String page = CommandHolder.getInstance().getCommandByName("cRoutes").execute(request, response);
        return page;
    }
}
