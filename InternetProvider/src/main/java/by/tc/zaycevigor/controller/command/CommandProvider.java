package by.tc.zaycevigor.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.tc.zaycevigor.controller.command.impl.*;

public class CommandProvider {

    private Map<String, Command> commands = new HashMap<String, Command>();

    public CommandProvider() {
        commands.put("goToAuthorizationPage", new GoToAuthorizationCommand());
        commands.put("authorization", new AuthorizationCommand());

        commands.put("goToContractRegistration", new GoToContractRegistrationCommand());
        commands.put("contractRegistration", new ContractRegistrationCommand());
        commands.put("goToContractRegistrationResult", new GoToContractRegistrationResultCommand());

        commands.put("userRegistration", new UserRegistrationCommand());
        commands.put("goToUserRegistration", new GoToUserRegistrationCommand());

        commands.put("change_locale", new ChangeLocale());
        commands.put("goToIndex", new GoToIndexPageCommand());
        commands.put("goToErrorPage", new GoToErrorCommand());
        commands.put("goToMainPage", new GoToMainPageCommand());

        commands.put("personalData", new ShowPersonalDataCommand());
        commands.put("exit", new ExitCommand());
        commands.put("tariffList", new ShowTariffListCommand());
        commands.put("goToTariffList", new GoToTariffListCommand());
        commands.put("show_tariff", new ShowSingleTariffCommand());

        commands.put("changeTariff", new ChangeTariffCommand());
        commands.put("goToChangeTariffPage", new GoToChangingTariffPageCommand());
        commands.put("goToChangeResult", new GoToChangingResultCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
