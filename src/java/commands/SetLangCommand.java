/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for setting of prefered language.
 * 
 */
package commands;

import controller.CommandHolder;
import controllermanagers.ConfigManager;
import java.io.IOException;
import java.util.Locale;
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
public class SetLangCommand extends Command {

    private static Logger log = Logger.getLogger(SetLangCommand.class.getName());
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        log.log(Level.INFO, "SetLangCommand instance: created");
        Locale curLocale = null;
        String setLocale = "";
        
        String backCommand = request.getParameter("backcommand");
        String reqLocale = request.getParameter("locale");
        if ("uk_UA".equalsIgnoreCase(reqLocale)){
            //curLocale = new Locale("uk","UA");
            setLocale = "uk_UA";
        } else {
            //curLocale = Locale.UK;
            setLocale = "en_EN";
        }
        HttpSession session = request.getSession();
        //session.setAttribute("locale", curLocale);
        session.setAttribute("locale", setLocale);
         String page = CommandHolder.getInstance().getCommandByName(backCommand).execute(request, response);
        return page;
        
    }
    
}
