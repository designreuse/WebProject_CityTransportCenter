/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Command for creation of new users.
 * 
 */
package commands;

import controllermanagers.ConfigManager;
import controllermanagers.MessageManager;
import dao.IUsersDAO;
import dao.JDBCEntityDAOFactory;
import entities.Users;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import security.BCrypt;

/**
 *
 * @author Dmytro Deinichenko
 */
public class CreateNewUserCommand extends Command {
    
    private static Logger log = Logger.getLogger(CreateNewUserCommand.class.getName());
    String page;
    long start, finish;
    String hashedPassword;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.log(Level.INFO, "CreateNewUserCommand instance: created");
        MessageManager messMan = MessageManager.getInstance();
        ConfigManager confMan = ConfigManager.getInstance();
        
        
        String formUsername = request.getParameter("username");
        String formEmail = request.getParameter("email");
        String formPassword = request.getParameter("password");
        String formConfirmPassword = request.getParameter("confirm-password");
        
        if (!formPassword.equals(formConfirmPassword)){
            request.setAttribute("messageError", messMan.getProperty(MessageManager.PASSWORD_CONFIRM_ERROR));
            page = ConfigManager.getInstance().getProperty(ConfigManager.LOGIN_PAGE_PATH);
            return page;
        }
        start = System.nanoTime();
        hashedPassword = BCrypt.hashpw(formPassword, BCrypt.gensalt(12));
        finish = System.nanoTime();
        log.log(Level.INFO, "Creating new hash: " + ((finish - start) / 1E+9) + " seconds.");
        
        Users newUser = new Users(formUsername, formEmail, hashedPassword);
        newUser.setRole("User");
        newUser.setStatus("Active");
        IUsersDAO usersDAO = JDBCEntityDAOFactory.getInstance().getUserDAO();
        
        if (usersDAO.create(newUser)){
            request.setAttribute("messageResult", messMan.getProperty(MessageManager.NEWUSER_CREATING_SUCCESS));
            log.log(Level.INFO, "Creating new User: " + newUser.getLoginName() + " successfull");
            page = confMan.getProperty("LOGIN_PAGE_PATH");
        } else {
            request.setAttribute("messageError", messMan.getProperty(MessageManager.NEWUSER_CREATING_ERROR));
            log.log(Level.INFO, "Creating new User: " + newUser.getLoginName() + " failed");
            page = confMan.getProperty("MAIN_PAGE_PATH");
        }
        //page = confMan.getProperty("REGISTER_SUCCESS_PATH");
        //String page = CommandHolder.getInstance().getCommandByName("cLogin").execute(request, response);
        return page;
    }
    
}
