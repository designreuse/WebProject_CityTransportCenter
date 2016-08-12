/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Main Controller of the Web-project.
 * 
 */
package controller;

import controllermanagers.MessageManager;
import controllermanagers.ConfigManager;
import commands.Command;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dmytro Deinichenko
 */
public class MainController extends HttpServlet implements Servlet {
    
    private static Logger log = Logger.getLogger(MainController.class.getName());
    CommandHolder commandHolder = CommandHolder.getInstance();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pageView = null;
        try {
            Command command = commandHolder.getCommand(request);        //defining of command from web-page
            log.log(Level.INFO, "MainController instance: acquired Command: " + command.toString());
            pageView = command.execute(request, response);              //Command return a name of web-page to show results
        } catch (ServletException ex) {
            log.log(Level.SEVERE, "MainController error: ServletException");
            ex.printStackTrace();                                       //generation of an error message
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));

                                                                        //call JSP-page with error message
            pageView = ConfigManager.getInstance().getProperty(ConfigManager.ERROR_PAGE_PATH);
        } catch (IOException ex) {
            log.log(Level.SEVERE, "MainController error: IOException");
            ex.printStackTrace();
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));

                                                                        //call JSP-page with error message
            pageView = ConfigManager.getInstance().getProperty(ConfigManager.ERROR_PAGE_PATH);
        }
                                                                        //calling of responding web-page
        RequestDispatcher dispatcher = request.getRequestDispatcher(pageView);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
