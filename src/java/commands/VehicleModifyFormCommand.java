/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for entering to vehicle modifying form.
 * 
 */
package commands;

import controllermanagers.ConfigManager;
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
public class VehicleModifyFormCommand extends Command {

    private static Logger log = Logger.getLogger(VehicleModifyFormCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.log(Level.INFO, "VehicleModifyFormCommand instance: created");
        
        request.setAttribute("idvehicle", request.getParameter("idvehicle"));
        request.setAttribute("markvehicle", request.getParameter("markvehicle"));
        request.setAttribute("modelvehicle", request.getParameter("modelvehicle"));
        request.setAttribute("typevehicle", request.getParameter("typevehicle"));
        request.setAttribute("statusvehicle", request.getParameter("statusvehicle"));
        
        String page = ConfigManager.getInstance().getProperty(ConfigManager.MODIFY_VEHICLE_FORM);
        return page;
    }
    
}
