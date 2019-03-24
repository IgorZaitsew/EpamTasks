package by.tc.zaycevigor.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tc.zaycevigor.controller.command.CommandProvider;

public class Controller extends HttpServlet {

    private static final long serialVersionUID = 7857329058902L;
    private static final String PARAMETER_COMMAND = "command";
    private final CommandProvider provider = new CommandProvider();


    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        perform(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        perform(req, resp);
    }

    private void perform (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String commandName = req.getParameter(PARAMETER_COMMAND);
        provider.getCommand(commandName).execute(req, resp);
    }
}

