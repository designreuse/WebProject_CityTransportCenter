/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for modifying of vehicles.
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
public class ModifyVehicleCommand extends Command {
    
    private static Logger log = Logger.getLogger(ModifyVehicleCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        log.log(Level.INFO, "ModifyVehicleCommand instance: created");
        MessageManager messMan = MessageManager.getInstance();
        
        int modIdVehicle = Integer.parseInt(request.getParameter("idvehicle"));
        log.log(Level.INFO, "idvehicle = " + request.getParameter("idvehicle"));
        String modMarkVehicle = request.getParameter("mark");
        log.log(Level.INFO, "mark = " + request.getParameter("mark"));
        String modModelVehicle = request.getParameter("model");
        log.log(Level.INFO, "model = " + request.getParameter("model"));
        String modTypeVehicle = request.getParameter("vehicletype");
        log.log(Level.INFO, "vehicletype = " + request.getParameter("vehicletype"));
        String modStatusVehicle = request.getParameter("status");
        log.log(Level.INFO, "status = " + request.getParameter("status"));
        int idTypeVehicle;
        switch (modTypeVehicle) {
            case "Autobus":
                idTypeVehicle = 1;
                break;
            case "Trolleybus":
                idTypeVehicle = 2;
                break;
            default:
                idTypeVehicle = 3;
        }
        
        IVehiclesDAO vehiclesDAO = JDBCEntityDAOFactory.getInstance().getVehiclesDAO();
        Vehicles modVehicle = new Vehicles(modIdVehicle, modMarkVehicle, modModelVehicle, modStatusVehicle, idTypeVehicle);
        log.log(Level.INFO, "vehicle instance: " + modVehicle.toString());
        
        if (vehiclesDAO.update(modVehicle)){
            request.setAttribute("messageResult", messMan.getProperty(MessageManager.VEHICLE_UPDATE_SUCCESS));
            log.log(Level.INFO, "Creating new vehicle: successfull");
        } else {
            request.setAttribute("messageError", messMan.getProperty(MessageManager.VEHICLE_UPDATE_ERROR));
            log.log(Level.INFO, "Creating new vehicle: failed");
        }
        
        String page = CommandHolder.getInstance().getCommandByName("cVehicles").execute(request, response);
        return page;
    }
    
}
