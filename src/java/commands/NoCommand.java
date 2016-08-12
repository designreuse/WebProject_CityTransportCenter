/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Default command for all undefined by Controller commands. 
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
public class NoCommand extends Command {
    
    private static Logger log = Logger.getLogger(NoCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.log(Level.INFO, "passed, go to main-page");
        String page = ConfigManager.getInstance().getProperty(ConfigManager.MAIN_PAGE_PATH);
        return page;
    }

}
