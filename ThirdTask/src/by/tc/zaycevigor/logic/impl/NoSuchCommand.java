package by.tc.zaycevigor.logic.impl;

import by.tc.zaycevigor.controller.JspPageName;
import by.tc.zaycevigor.logic.CommandException;
import by.tc.zaycevigor.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

public class NoSuchCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return JspPageName.ERROR_PAGE;
    }
}
