/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Configuration manager consists various properties for registered jsp-s.
 * 
 */
package controllermanagers;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 *
 * @author Dmytro Deinichenko
 */


public class ConfigManager {

    private static Logger log = Logger.getLogger(ConfigManager.class.getName());
    private static ConfigManager instance;
    private ResourceBundle resourceBundle;

    //acquiring info from the file config.properties
    private static final String BUNDLE_NAME = "controllermanagers.config";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    public static final String ROUTES_PAGE_PATH = "ROUTES_PAGE_PATH";
    public static final String VEHICLES_PAGE_PATH = "VEHICLES_PAGE_PATH";
    public static final String TIMEMAPS_PAGE_PATH = "TIMEMAPS_PAGE_PATH";
    public static final String REGISTER_SUCCESS_PATH = "REGISTER_SUCCESS_PATH";
    public static final String CREATE_TIME_INTERVAL_FORM="CREATE_TIME_INTERVAL_FORM";
    public static final String MODIFY_VEHICLE_FORM="MODIFY_VEHICLE_FORM";
    public static final String ASSIGN_VEHICLE_FORM = "ASSIGN_VEHICLE_FORM";
    public static final String MODIFY_ROUTE_FORM = "MODIFY_ROUTE_FORM";
    public static final String MARSHROUTE_DETAILS = "MARSHROUTE_DETAILS";
    

    public static ConfigManager getInstance() {
        ConfigManager localInst = instance;
        if (localInst == null) {
            synchronized (ConfigManager.class) {
                localInst = instance;
                if (localInst == null) {
                    instance = localInst = new ConfigManager();
                }
            }
        }
        localInst.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        return localInst;
    }
      
      
      public String getProperty(String key){
          return (String)resourceBundle.getObject(key);
      }
}
