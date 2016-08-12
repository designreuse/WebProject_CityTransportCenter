/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Checking login information and allow access to member areas.
 * 
 */
package commands;

import security.BCrypt;
import controllermanagers.ConfigManager;
import controllermanagers.MessageManager;
import dao.IUsersDAO;
import dao.JDBCEntityDAOFactory;
import entities.Users;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import security.UserSessionChecker;

/**
 *
 * @author Dmytro Deinichenko
 */
public class CheckLoginCommand extends Command {
    private static Logger log = Logger.getLogger(CheckLoginCommand.class.getName());
    UserSessionChecker userSessionChecker;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.log(Level.INFO, "CheckLoginCommand instance: created");
        MessageManager messMan = MessageManager.getInstance();
        ConfigManager confMan = ConfigManager.getInstance();
        
        long start, finish;
        boolean check = false;
        HttpSession session = null;
        String page = null;
        
        String formLogin = request.getParameter("username");
        String formPass = request.getParameter("password");
        String remember = request.getParameter("remember");
        log.log(Level.INFO, "New user trying to log in: " + formLogin);
        log.log(Level.INFO, "Remember checker: " + remember);
        JDBCEntityDAOFactory entityDAOFactory = JDBCEntityDAOFactory.getInstance();
        IUsersDAO usersDAO = entityDAOFactory.getUserDAO();
        Users userCheck = usersDAO.findByLogin(formLogin);
        String lHash = userCheck.getPassword();
        start = System.nanoTime();
        check = BCrypt.checkpw(formPass, lHash);
        finish = System.nanoTime();
        log.log(Level.INFO, "Calculating hash: " + ((finish - start) / 1E+9) + " seconds.");
        
        
        session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//            log.log(Level.INFO, "Old session invalidated.");
//        }
        log.log(Level.INFO, "Checking login: " + check);
        
        if (check) {
            session = request.getSession(true);
            log.log(Level.INFO, "New session created: " + session.getId());
            userSessionChecker = new UserSessionChecker(formLogin, Objects.hashCode(lHash+session.getId()), userCheck.getUid());
            session.setAttribute("user", userSessionChecker);
            log.log(Level.INFO, "UserSessionChecker object added to the new session");
            if (remember != null){
                session.setMaxInactiveInterval(60*60*24*7);
                log.log(Level.INFO, "MaxInactiveInterval is set");
            }
            page = confMan.getProperty("MAIN_PAGE_PATH");
        } else {
//            page = CommandHolder.getInstance().getCommandByName("cLogin").execute(request, response);
            page = confMan.getProperty("LOGIN_PAGE_PATH");
        }
        return page;
    }
}
