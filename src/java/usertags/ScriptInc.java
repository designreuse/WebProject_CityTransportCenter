/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: User Tag for including of script declarations on jsp pages. 
 * 
 */
package usertags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Dmytro Deinichenko
 */
public class ScriptInc extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }
            out.println("<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->");
            out.println("<script src=\"./styles/js/jquery-2.1.4.min.js\"></script>");
            out.println("<!-- Include all compiled plugins (below), or include individual files as needed -->");
            out.println("<script src=\"/MyWebApp/styles/js/bootstrap.min.js\"></script>");

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in ScriptTag tag", ex);
        }
    }
    
}
