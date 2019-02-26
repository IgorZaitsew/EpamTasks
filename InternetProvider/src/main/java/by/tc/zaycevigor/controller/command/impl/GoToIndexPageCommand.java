package by.tc.zaycevigor.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tc.zaycevigor.controller.command.Command;
import by.tc.zaycevigor.controller.command.util.CreatorFullURL;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.service.InternetService;
import by.tc.zaycevigor.service.ServiceProvider;

public class GoToIndexPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider provider = ServiceProvider.getInstance();
     //   InternetService internetService = provider.getLibraryService();

     //   List<Tariff> tariffs = internetService.find("");

       // request.setAttribute("tariffs", tariffs);

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/default.jsp");
        dispatcher.forward(request, response);

    }

}
