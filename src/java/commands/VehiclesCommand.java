/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for entering to Vehicles page.
 * 
 */
package commands;

import controllermanagers.ConfigManager;
import dao.IVehiclesDAO;
import dao.JDBCEntityDAOFactory;
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
public class VehiclesCommand extends Command {

    private static Logger log = Logger.getLogger(VehiclesCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.log(Level.INFO, "VehiclesCommand instance: created");
        
        IVehiclesDAO vehiclesDAO = JDBCEntityDAOFactory.getInstance().getVehiclesDAO();
        List<Vehicles> listVehicles = vehiclesDAO.findAll();

        request.setAttribute("vehicleslist", listVehicles);
        log.log(Level.INFO, "VehiclesCommand instance: created");
        String page = ConfigManager.getInstance().getProperty(ConfigManager.VEHICLES_PAGE_PATH);
        return page;
    }
}