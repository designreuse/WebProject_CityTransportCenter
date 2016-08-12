/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for creation of new Marshroutes.
 * 
 */
package commands;

import controller.CommandHolder;
import controllermanagers.ConfigManager;
import controllermanagers.MessageManager;
import dao.IRoutesDAO;
import dao.JDBCEntityDAOFactory;
import entities.Marshroutes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dmytro Deinichenko
 */

public class CreateRouteCommand extends Command {

    private static Logger log = Logger.getLogger(CreateRouteCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.log(Level.INFO, "CreateRouteCommand instance: created");
        MessageManager messMan = MessageManager.getInstance();
        
        IRoutesDAO routesDAO = JDBCEntityDAOFactory.getInstance().getRoutesDAO();
        String routeName = request.getParameter("routename");
        String routeDesc = request.getParameter("rdescription");
        Marshroutes creatingRoute = new Marshroutes(routeName, routeDesc);
                
        if (routesDAO.create(creatingRoute)){
            request.setAttribute("messageResult", messMan.getProperty(MessageManager.ROUTE_CREATING_SUCCESS));
            log.log(Level.INFO, "Creating new route: successfull");
        } else {
            request.setAttribute("messageError", messMan.getProperty(MessageManager.ROUTE_CREATING_ERROR));
            log.log(Level.INFO, "Creating new route: failed");
        }

        //String page = ConfigManager.getInstance().getProperty(ConfigManager.ROUTES_PAGE_PATH);
        String page = CommandHolder.getInstance().getCommandByName("cRoutes").execute(request, response);
        return page;
    }

}