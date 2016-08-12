/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for entering to login page. 
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
public class LoginCommand extends Command {

    private static Logger log = Logger.getLogger(LoginCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.log(Level.INFO, "LoginCommand instance: created");
        String page = ConfigManager.getInstance().getProperty(ConfigManager.LOGIN_PAGE_PATH);
        return page;
    }

}
