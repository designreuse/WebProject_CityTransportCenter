/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for entering to Marshroutes page.
 * 
 */
package commands;

import controllermanagers.ConfigManager;
import dao.IRoutesDAO;
import dao.JDBCEntityDAOFactory;
import entities.Marshroutes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

public class RoutesCommand extends Command {

    private static Logger log = Logger.getLogger(RoutesCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.log(Level.INFO, "RoutesCommand instance: created");
        
        IRoutesDAO routesDAO = JDBCEntityDAOFactory.getInstance().getRoutesDAO();
        List<Marshroutes> listRoutes = routesDAO.findAll();
        
//        List<String[]> listRoutesString = new ArrayList<>();
//        Marshroutes lm = new Marshroutes();
//        String[] str = new String[3];
//        for (int i = 0; i < listRoutes.size(); i++){
//            lm = listRoutes.get(i);
//            str = new String[]{lm.getIdmarshroutes().toString(), lm.getRoutename(), lm.getDescription()}; 
//            listRoutesString.add(str);
//            log.log(Level.INFO, Arrays.deepToString(str));
//        }
        
        request.setAttribute("routeslist", listRoutes);
        log.log(Level.INFO, "RoutesCommand: routesDAO.findAll(): ListSize: " + listRoutes.size());
        String page = ConfigManager.getInstance().getProperty(ConfigManager.ROUTES_PAGE_PATH);
        return page;
    }

}