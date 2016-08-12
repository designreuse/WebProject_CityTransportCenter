/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for deleting of time intervals.
 * 
 */
package commands;

import controller.CommandHolder;
import controllermanagers.MessageManager;
import dao.ITimetableDAO;
import dao.JDBCEntityDAOFactory;
import entities.Timetable;
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
public class DeleteIntervalCommand extends Command {

    private static Logger log = Logger.getLogger(DeleteIntervalCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        log.log(Level.INFO, "DeleteIntervalCommand instance: created");
        MessageManager messMan = MessageManager.getInstance();
        
        ITimetableDAO delIntervalDAO = JDBCEntityDAOFactory.getInstance().getTimetableDAO();
        int idDelInterval = Integer.parseInt(request.getParameter("iddelinterval"));
        
        Timetable delInterval = new Timetable(idDelInterval);
        
        if (delIntervalDAO.delete(delInterval)){
            request.setAttribute("messageResult", messMan.getProperty(MessageManager.INTERVAL_DELETE_SUCCESS));
            log.log(Level.INFO, "Deleting the vehicle: successfull");
        } else {
            request.setAttribute("messageError", messMan.getProperty(MessageManager.INTERVAL_DELETE_ERROR));
            log.log(Level.INFO, "Deleting the vehicle: failed");
        }
        
        String page = CommandHolder.getInstance().getCommandByName("cTimeMaps").execute(request, response);
        return page;
    }
    
}
