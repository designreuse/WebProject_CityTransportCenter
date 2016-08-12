/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for creation of new time intervals.
 * 
 */
package commands;

import controller.CommandHolder;
import controllermanagers.MessageManager;
import dao.ITimetableDAO;
import dao.JDBCEntityDAOFactory;
import entities.Timetable;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dmytro Deinichenko
 */
public class CreateTimeIntervalCommand  extends Command {

    private static Logger log = Logger.getLogger(CreateTimeIntervalCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        log.log(Level.INFO, "CreateTimeIntervalCommand instance: created");
        MessageManager messMan = MessageManager.getInstance();
        
        //Date intervalSQLDate = null;
        Date intervalDate = null;
        ITimetableDAO newIntervalDAO = JDBCEntityDAOFactory.getInstance().getTimetableDAO();
        String direction = request.getParameter("direction");
        log.log(Level.INFO, "direction: " + direction);
        String from = request.getParameter("from");
        log.log(Level.INFO, "from: " + from);
        String to = request.getParameter("to");
        log.log(Level.INFO, "to: " + to);
        String interval = request.getParameter("interval");
        log.log(Level.INFO, "interval: " + interval);
        SimpleDateFormat sdfInterval = new SimpleDateFormat("hh:mm:ss");
        try {
            // Parsing for java.sql.Date
            //intervalSQLDate = new Date(sdfInterval.parse(request.getParameter("interval")).getTime());
            intervalDate = sdfInterval.parse(request.getParameter("interval"));
        } catch (ParseException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        int routeRef = Integer.parseInt(request.getParameter("toroute").replaceAll("\\s*---.*", "").substring(1));
        log.log(Level.INFO, "routeref: " + routeRef);
        
        Timetable newInterval = new Timetable(direction, from, to, intervalDate, routeRef);
        
        if (newIntervalDAO.create(newInterval)){
            request.setAttribute("messageResult", messMan.getProperty(MessageManager.INTERVAL_CREATING_SUCCESS));
            log.log(Level.INFO, "Creating new time interval: successfull");
        } else {
            request.setAttribute("messageError", messMan.getProperty(MessageManager.INTERVAL_CREATING_ERROR));
            log.log(Level.INFO, "Creating new time interval: failed");
        }
        
        String page = CommandHolder.getInstance().getCommandByName("cTimeMaps").execute(request, response);
        return page;
    }
    
}
