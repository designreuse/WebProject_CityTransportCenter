/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for modifying of marshroutes.
 * 
 */
package commands;

import controller.CommandHolder;
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
public class ModifyRouteCommand extends Command {

    private static Logger log = Logger.getLogger(ModifyRouteCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.log(Level.INFO, "ModifyRouteCommand instance: created");
        MessageManager messMan = MessageManager.getInstance();
        
        Marshroutes modRoute = new Marshroutes(Integer.parseInt(request.getParameter("idroute")));
        modRoute.setRoutename(request.getParameter("routename"));
        modRoute.setDescription(request.getParameter("description"));
        
        IRoutesDAO routeDAO = JDBCEntityDAOFactory.getInstance().getRoutesDAO();
        log.log(Level.INFO, "Marshroute instance: " + modRoute.toString());
        
        if (routeDAO.update(modRoute)){
            request.setAttribute("messageResult", messMan.getProperty(MessageManager.ROUTE_UPDATE_SUCCESS));
            log.log(Level.INFO, "Marshroute update: successfull");
        } else {
            request.setAttribute("messageError", messMan.getProperty(MessageManager.ROUTE_UPDATE_ERROR));
            log.log(Level.INFO, "Marshroute update: failed");
        }
        
        String page = CommandHolder.getInstance().getCommandByName("cRoutes").execute(request, response);
        return page;
        
        
    }
    
}
