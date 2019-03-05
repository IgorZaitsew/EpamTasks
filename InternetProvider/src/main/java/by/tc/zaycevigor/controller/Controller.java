package by.tc.zaycevigor.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.CommandException;
import by.tc.zaycevigor.controller.command.CommandProvider;
import org.apache.log4j.Logger;


public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);

    private static final long serialVersionUID = 7857329058902L;
    private static final String PARAMETER_COMMAND = "command";
    private final CommandProvider provider = new CommandProvider();


    public Controller() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter(PARAMETER_COMMAND);
        Command command = provider.getCommand(commandName);
        try {
            command.execute(request, response);
        } catch (CommandException e) {
            log.error(e);
            throw new ServletException(e);
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
