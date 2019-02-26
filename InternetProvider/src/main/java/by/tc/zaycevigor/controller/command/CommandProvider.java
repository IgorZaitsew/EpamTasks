package by.tc.zaycevigor.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.tc.zaycevigor.controller.command.impl.AuthorizationCommand;
import by.tc.zaycevigor.controller.command.impl.ChangeLocale;
import by.tc.zaycevigor.controller.command.impl.GoToIndexPageCommand;
import by.tc.zaycevigor.controller.command.impl.GoToRegistrationCommand;
import by.tc.zaycevigor.controller.command.impl.RegistrationCommand;

public class CommandProvider {

    private Map<String, Command> commands = new HashMap<String, Command>();

    public CommandProvider() {
        commands.put("authorization", new AuthorizationCommand());
        commands.put("goToRegistrationPage", new GoToRegistrationCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("go_to_index", new GoToIndexPageCommand());
        commands.put("change_locale", new ChangeLocale());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
