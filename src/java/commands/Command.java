/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: General abstract class for all Commands of the project.
 * 
 */
package commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dmytro Deinichenko
 */
public abstract class Command {
    
    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException;
    
}
