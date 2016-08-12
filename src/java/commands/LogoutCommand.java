/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for logging out.
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dmytro Deinichenko
 */
public class LogoutCommand extends Command {

    private static Logger log = Logger.getLogger(LogoutCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        
        log.log(Level.INFO, "LogoutCommand instance: created");
        
        HttpSession session = request.getSession();
        session.invalidate();
        
        log.log(Level.INFO, "Session invalidated");
        String page = ConfigManager.getInstance().getProperty(ConfigManager.LOGIN_PAGE_PATH);
        return page;
    }
    
    
}
