/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for assigning of vehicles.
 * 
 */
package commands;

import controller.CommandHolder;
import controllermanagers.MessageManager;
import dao.IVehiclesDAO;
import dao.JDBCEntityDAOFactory;
import entities.Marshroutes;
import entities.Vehicles;
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
public class AssignVehicleCommand extends Command {

    private static Logger log = Logger.getLogger(AssignVehicleCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        log.log(Level.INFO, "AssignVehicleCommand instance: created");
        MessageManager messMan = MessageManager.getInstance();
        
        int assIdVehicle = Integer.parseInt(request.getParameter("idvehicle"));
        log.log(Level.INFO, "assIdVehicle = " + assIdVehicle);
        int toRoute = Integer.parseInt(request.getParameter("assignedto"));
        log.log(Level.INFO, "toRoute = " + toRoute);
        
        IVehiclesDAO vehiclesDAO = JDBCEntityDAOFactory.getInstance().getVehiclesDAO();
        Marshroutes assToRoute = new Marshroutes(toRoute);
        Vehicles assVehicle = new Vehicles(assIdVehicle);
            if (vehiclesDAO.assignToRoute(assVehicle, assToRoute)) {
                request.setAttribute("messageResult", messMan.getProperty(MessageManager.ASSIGN_VEHICLE_SUCCESS));
                log.log(Level.INFO, "Assigning vehicle: successfull");
            } else {
                request.setAttribute("messageError", messMan.getProperty(MessageManager.ASSIGN_VEHICLE_ERROR));
                log.log(Level.INFO, "Assigning vehicle: failed");
            }
        
        String page = CommandHolder.getInstance().getCommandByName("cVehicles").execute(request, response);
        return page;
    }
    
}
