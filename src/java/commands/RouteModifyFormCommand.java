/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for entering to modifying form for marshroutes. 
 * 
 */
package commands;

import controllermanagers.ConfigManager;
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
public class RouteModifyFormCommand extends Command {

    private static Logger log = Logger.getLogger(RouteModifyFormCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        log.log(Level.INFO, "RouteModifyFormCommand instance: created");
        
        request.setAttribute("idroute", request.getParameter("idroute"));
        request.setAttribute("routename", request.getParameter("routename"));
        request.setAttribute("routedescription", request.getParameter("routedescription"));
        
        String page = ConfigManager.getInstance().getProperty(ConfigManager.MODIFY_ROUTE_FORM);
        return page;
    }
    
    
    
}
