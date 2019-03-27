package by.tc.zaycevigor.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.tc.zaycevigor.controller.command.impl.admin.*;
import by.tc.zaycevigor.controller.command.impl.constant.*;
import by.tc.zaycevigor.controller.command.impl.guest.*;
import by.tc.zaycevigor.controller.command.impl.user.*;

public class CommandProvider {

    private Map<String, Command> commands = new HashMap<String, Command>();

    public CommandProvider() {
        commands.put("goToAuthorizationPage", new GoToAuthorizationCommand());
        commands.put("authorization", new AuthorizationCommand());

        commands.put("goToContractRegistration", new GoToContractRegistrationCommand());
        commands.put("contractRegistration", new ContractRegistrationCommand());

        commands.put("userRegistration", new UserRegistrationCommand());

        commands.put("change_locale", new ChangeLocale());
        commands.put("goToIndex", new GoToIndexPageCommand());
        commands.put("goToErrorPage", new GoToErrorCommand());
        commands.put("goToMainPage", new GoToMainPageCommand());

        commands.put("personalData", new ShowPersonalDataCommand());
        commands.put("goToPersonalDataCommand", new GoToPersonalDataCommand());
        commands.put("up_balance", new UpBalanceCommand());
        commands.put("exit", new ExitCommand());
        commands.put("tariffList", new ShowTariffListCommand());
        commands.put("goToTariffList", new GoToTariffListCommand());
        commands.put("show_tariff", new ShowSingleTariffCommand());

        commands.put("changeTariff", new ChangeTariffCommand());
        commands.put("goToChangeTariffPage", new GoToChangingTariffPageCommand());
        commands.put("goToChangeResult", new GoToChangingResultCommand());
        commands.put("goToUserList", new GoToUserListCommand());
        commands.put("user_list", new ShowUserListCommand());

        commands.put("user_delete", new UserDeleteCommand());
        commands.put("goToUserDeleteConfirm", new GoToUserDeletePageCommand());
        commands.put("goToUserDeleteSuccess", new GoToUserDeleteSuccessCommand());

        commands.put("goToUserAddSuccess", new GoToUserAddSuccessCommand());

        commands.put("user_info", new ShowUserInfoCommand());
        commands.put("goToUserInfoPage", new GoToUserInfoPageCommand());

        commands.put("tariff_add_page", new GoToTariffAddPageCommand());
        commands.put("goToTariffAddSuccessPage", new GoToTariffAddSuccessPageCommand());
        commands.put("tariff_add", new TariffAddCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
