/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Manager of various messages that show internal Commands.
 * 
 */
package controllermanagers;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 *
 * @author Dmytro Deinichenko
 */

public class MessageManager {

    private static Logger log = Logger.getLogger(ConfigManager.class.getName());
    private static MessageManager instance;
    private ResourceBundle resourceBundle;

    //acquiring info from the file messages.properties
    private static final String BUNDLE_NAME = "controllermanagers.messages";
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "SERVLET_EXCEPTION_ERROR_MESSAGE";
    public static final String IO_EXCEPTION_ERROR_MESSAGE = "IO_EXCEPTION_ERROR_MESSAGE";
    public static final String ROUTE_CREATING_ERROR = "ROUTE_CREATING_ERROR";
    public static final String ROUTE_CREATING_SUCCESS = "ROUTE_CREATING_SUCCESS";
    public static final String VEHICLE_CREATING_ERROR = "VEHICLE_CREATING_ERROR";
    public static final String VEHICLE_CREATING_SUCCESS = "VEHICLE_CREATING_SUCCESS";
    public static final String VEHICLE_DELETE_ERROR = "VEHICLE_DELETE_ERROR";
    public static final String VEHICLE_DELETE_SUCCESS = "VEHICLE_DELETE_SUCCESS";
    public static final String PASSWORD_CONFIRM_ERROR = "PASSWORD_CONFIRM_ERROR";
    public static final String NEWUSER_CREATING_SUCCESS = "NEWUSER_CREATING_SUCCESS";
    public static final String NEWUSER_CREATING_ERROR = "NEWUSER_CREATING_ERROR";
    public static final String INTERVAL_CREATING_SUCCESS = "INTERVAL_CREATING_SUCCESS";
    public static final String INTERVAL_CREATING_ERROR = "INTERVAL_CREATING_ERROR";
    public static final String INTERVAL_DELETE_SUCCESS = "INTERVAL_DELETE_SUCCESS";
    public static final String INTERVAL_DELETE_ERROR = "INTERVAL_DELETE_ERROR";
    public static final String VEHICLE_UPDATE_SUCCESS = "VEHICLE_UPDATE_SUCCESS";
    public static final String VEHICLE_UPDATE_ERROR = "VEHICLE_UPDATE_ERROR";
    public static final String ASSIGN_VEHICLE_SUCCESS = "ASSIGN_VEHICLE_SUCCESS";
    public static final String ASSIGN_VEHICLE_ERROR = "ASSIGN_VEHICLE_ERROR";
    public static final String ROUTE_UPDATE_SUCCESS = "ROUTE_UPDATE_SUCCESS";
    public static final String ROUTE_UPDATE_ERROR = "ROUTE_UPDATE_ERROR";
    public static final String INTERVAL_LIST_DELETE_SUCCESS = "INTERVAL_LIST_DELETE_SUCCESS";
    public static final String INTERVAL_LIST_DELETE_ERROR = "INTERVAL_LIST_DELETE_ERROR";
    public static final String VEHICLE_LIST_DELETE_SUCCESS = "VEHICLE_LIST_DELETE_SUCCESS";
    public static final String VEHICLE_LIST_DELETE_ERROR = "VEHICLE_LIST_DELETE_ERROR";
    public static final String ROUTE_DELETE_SUCCESS = "ROUTE_DELETE_SUCCESS";
    public static final String ROUTE_DELETE_ERROR = "ROUTE_DELETE_ERROR";

    public static MessageManager getInstance() {
        MessageManager localInst = instance;
        if (localInst == null) {
            synchronized (MessageManager.class) {
                localInst = instance;
                if (localInst == null) {
                    instance = localInst = new MessageManager();
                }
            }
        }
        localInst.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        return localInst;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
