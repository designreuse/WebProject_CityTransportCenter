/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command of entering to assigning vehicles form. 
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
 * @author Dmytro Deinichenko
 */
public class AssignVehicleFormCommand extends Command {

    private static Logger log = Logger.getLogger(AssignVehicleFormCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        log.log(Level.INFO, "AssignVehicleFormCommand instance: created");
        
        request.setAttribute("idvehicle", request.getParameter("idvehicle"));
        request.setAttribute("markvehicle", request.getParameter("markvehicle"));
        request.setAttribute("modelvehicle", request.getParameter("modelvehicle"));
        request.setAttribute("typevehicle", request.getParameter("typevehicle"));
        request.setAttribute("statusvehicle", request.getParameter("statusvehicle"));
        
        IRoutesDAO routesDAO = JDBCEntityDAOFactory.getInstance().getRoutesDAO();
        List<Marshroutes> listRoutes = routesDAO.findAll();
        request.setAttribute("routeslist", listRoutes);
        
        String page = ConfigManager.getInstance().getProperty(ConfigManager.ASSIGN_VEHICLE_FORM);
        return page;
    }
    
}
