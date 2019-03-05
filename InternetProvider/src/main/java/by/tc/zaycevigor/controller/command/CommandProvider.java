package by.tc.zaycevigor.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.tc.zaycevigor.controller.command.impl.*;

public class CommandProvider {

    private Map<String, Command> commands = new HashMap<String, Command>();

    public CommandProvider() {
        commands.put("authorization", new AuthorizationCommand());
        commands.put("goToAuthorizationPage", new GoToAuthorizationCommand());
        commands.put("goToRegistrationPage", new GoToRegistrationCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("registrationResult", new GoToMainPageCommand());
        commands.put("change_locale", new ChangeLocale());
        commands.put("goToIndex", new GoToIndexPageCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
