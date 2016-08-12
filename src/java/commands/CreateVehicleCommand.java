/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for creation of new vehicles.
 * 
 */
package commands;

import controller.CommandHolder;
import controllermanagers.MessageManager;
import dao.IVehiclesDAO;
import dao.JDBCEntityDAOFactory;
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
public class CreateVehicleCommand extends Command {

    private static Logger log = Logger.getLogger(CreateVehicleCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.log(Level.INFO, "CreateVehicleCommand instance: created");
        MessageManager messMan = MessageManager.getInstance();

        IVehiclesDAO vehiclesDAO = JDBCEntityDAOFactory.getInstance().getVehiclesDAO();
        String vehicleMark = request.getParameter("mark");
        String vehicleModel = request.getParameter("model");
        String vehicleStaus = request.getParameter("status");
        String vehicleType = request.getParameter("vehicletype");
                        
        Vehicles creatingVehicle = new Vehicles(vehicleMark, vehicleModel, vehicleStaus, vehicleType);
                
        if (vehiclesDAO.create(creatingVehicle)){
            request.setAttribute("messageResult", messMan.getProperty(MessageManager.VEHICLE_CREATING_SUCCESS));
            log.log(Level.INFO, "Creating new vehicle: successfull");
        } else {
            request.setAttribute("messageError", messMan.getProperty(MessageManager.VEHICLE_CREATING_ERROR));
            log.log(Level.INFO, "Creating new vehicle: failed");
        }

        //String page = ConfigManager.getInstance().getProperty(ConfigManager.VEHICLES_PAGE_PATH);
        String page = CommandHolder.getInstance().getCommandByName("cVehicles").execute(request, response);
        return page;
    }
}
