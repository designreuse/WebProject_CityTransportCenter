/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for deleting of vehicles.
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
public class DeleteVehicleCommand extends Command {

    private static Logger log = Logger.getLogger(DeleteVehicleCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.log(Level.INFO, "CreateVehicleCommand instance: created");
        MessageManager messMan = MessageManager.getInstance();

        IVehiclesDAO vehiclesDAO = JDBCEntityDAOFactory.getInstance().getVehiclesDAO();
        int idDelVehicle = Integer.parseInt(request.getParameter("iddelvehicle"));
                        
        Vehicles delVehicle = new Vehicles(idDelVehicle);
                
        if (vehiclesDAO.delete(delVehicle)){
            request.setAttribute("messageResult", messMan.getProperty(MessageManager.VEHICLE_DELETE_SUCCESS));
            log.log(Level.INFO, "Deleting the vehicle: successfull");
        } else {
            request.setAttribute("messageError", messMan.getProperty(MessageManager.VEHICLE_DELETE_ERROR));
            log.log(Level.INFO, "Deleting the vehicle: failed");
        }

        //String page = ConfigManager.getInstance().getProperty(ConfigManager.VEHICLES_PAGE_PATH);
        String page = CommandHolder.getInstance().getCommandByName("cVehicles").execute(request, response);
        return page;
    }
}
