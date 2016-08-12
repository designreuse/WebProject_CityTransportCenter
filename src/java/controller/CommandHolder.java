/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: Main holder for all Commands of Controller.
 * 
 */
package controller;

import commands.AssignVehicleCommand;
import commands.AssignVehicleFormCommand;
import commands.CheckLoginCommand;
import commands.Command;
import commands.CreateNewUserCommand;
import commands.CreateRouteCommand;
import commands.CreateTimeIntervalCommand;
import commands.CreateVehicleCommand;
import commands.DeleteIntervalCommand;
import commands.DeleteRouteCommand;
import commands.DeleteVehicleCommand;
import commands.ExportRoutesCommand;
import commands.ExportTimeIntervalsCommand;
import commands.ExportVehiclesCommand;
import commands.LoginCommand;
import commands.LogoutCommand;
import commands.ModifyRouteCommand;
import commands.ModifyVehicleCommand;
import commands.NoCommand;
import commands.RouteDetailsCommand;
import commands.RouteModifyFormCommand;
import commands.RoutesCommand;
import commands.SetLangCommand;
import commands.TimeIntervalFormCommand;
import commands.TimeMapsCommand;
import commands.VehicleModifyFormCommand;
import commands.VehiclesCommand;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dmytro Deinichenko
 */
public class CommandHolder {

    private static volatile CommandHolder instance;
    private HashMap<String, Class<? extends Command>> commands = new HashMap<String, Class<? extends Command>>();
    private static Logger log = Logger.getLogger(CommandHolder.class.getName());

    private CommandHolder() {
        // Adding Comands on first access to this Object;
        commands.put("cLogin", LoginCommand.class);
        commands.put("cRoutes", RoutesCommand.class);
        commands.put("cVehicles", VehiclesCommand.class);
        commands.put("cTimeMaps", TimeMapsCommand.class);
        commands.put("cCreateRoute", CreateRouteCommand.class);
        commands.put("cCreateVehicle", CreateVehicleCommand.class);
        commands.put("cDeleteVehicle", DeleteVehicleCommand.class);
        commands.put("cCheckLogin", CheckLoginCommand.class);
        commands.put("cCreateNewUser", CreateNewUserCommand.class);
        commands.put("cTimeIntervalCreationForm", TimeIntervalFormCommand.class);
        commands.put("cCreateTimeInterval", CreateTimeIntervalCommand.class);
        commands.put("cDeleteInterval", DeleteIntervalCommand.class);
        commands.put("cModifyVehicleForm", VehicleModifyFormCommand.class);
        commands.put("cModifyVehicle", ModifyVehicleCommand.class);
        commands.put("cAssignVehicleForm", AssignVehicleFormCommand.class);
        commands.put("cAssignVehicle", AssignVehicleCommand.class);
        commands.put("cRouteModifyForm", RouteModifyFormCommand.class);
        commands.put("cModifyRoute", ModifyRouteCommand.class);
        commands.put("cRouteDetails", RouteDetailsCommand.class);
        commands.put("cRouteDelete", DeleteRouteCommand.class);
        commands.put("cLogout", LogoutCommand.class);
        commands.put("cSetLang", SetLangCommand.class);
        
        commands.put("cExportRoutes", ExportRoutesCommand.class);
        commands.put("cExportVehicles", ExportVehiclesCommand.class);
        commands.put("cExportTimeIntervals", ExportTimeIntervalsCommand.class);        
        
        log.log(Level.INFO, "CommandHolder instance created.");
    }

    public static CommandHolder getInstance() {
        CommandHolder localInst = instance;
        if (localInst == null) {
            synchronized (CommandHolder.class) {
                localInst = instance;
                if (localInst == null) {
                    instance = localInst = new CommandHolder();
                }
            }
        }
        return localInst;
    }
    
    public synchronized Command getCommand(HttpServletRequest request) {
        String commandName;
        Class<? extends Command> commandClass;
        Command command = null;
        
        commandName = request.getParameter("command");           //getting Command-name from request
        log.log(Level.INFO, "acquired commandName:" + commandName);
        //getting of a handler class; if a handler-class is absent in container - setting by a Command on default;
        commandClass = commands.getOrDefault(commandName, NoCommand.class);
        try {
            command = commandClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return command;
    }
    
        public synchronized Command getCommandByName(String requestedCommand) {

        Class<? extends Command> commandClass;
        Command command = null;
        
        log.log(Level.INFO, "acquired commandName:" + requestedCommand);
        //getting of a handler class; if a handler-class is absent in container - setting by a Command on default;
        commandClass = commands.getOrDefault(requestedCommand, NoCommand.class);
        try {
            command = commandClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return command;
    }
}
