package by.tc.zaycevigor.controller.command.util;

public final class JspPageName {
    private JspPageName() {
    }

    public static final String MAIN_PAGE = "WEB-INF/pages/secure/main.jsp";
    public static final String GO_TO_MAIN_PAGE = "/secure?command=goToMainPage";

    public static final String INDEX_PAGE = "index.jsp";
    public static final String GO_TO_INDEX_PAGE ="/controller?command=goToIndex";
    public static final String DEFAULT_PAGE = "WEB-INF/pages/default.jsp";

    public static final String USER_REGISTRATION_PAGE = "WEB-INF/pages/userRegistration.jsp";
    public static final String USER_REGISTRATION_COMMAND = "/controller?command=userRegistration&";
    public static final String GO_TO_USER_REGISTRATION = "/controller?command=goToUserRegistration&";

    public static final String ERROR_PAGE = "WEB-INF/pages/error/errorPage.jsp";
    public static final String GO_TO_ERROR_PAGE = "/controller?command=goToErrorPage&";

    public static final String CONTRACT_REGISTRATION_PAGE = "WEB-INF/pages/contractRegistration.jsp";
    public static final String GO_TO_CONTRACT_REGISTRATION_PAGE = "/controller?command=goToContractRegistration&";
    public static final String CONTRACT_REGISTRATION_RESULT_PAGE = "WEB-INF/pages/after_contract_reg.jsp";
    public static final String GO_TO_CONTRACT_REGISTRATION_RESULT_PAGE = "/controller?command=goToContractRegistrationResult&";

    public static final String AUTHORIZATION_PAGE = "WEB-INF/pages/authorization.jsp";
    public static final String GO_TO_AUTHORISATION_PAGE = "/controller?command=goToAuthorizationPage&";

    public static final String PERSONAL_DATA_PAGE = "WEB-INF/pages/secure/personalData.jsp";
    public static final String GO_TO_DATA_PAGE = "/secure?command=goToPersonalData&";

    public static final String TARIFF_PAGE = "WEB-INF/pages/secure/tariffList.jsp";
    public static final String GO_TO_TARIFF_LIST_PAGE = "/secure?command=goToTariffList&";

    public static final String SINGLE_TARIFF_PAGE = "WEB-INF/pages/secure/singleTariffInfo.jsp";

    public static final String GO_TO_CHANGE_TARIFF = "/secure?command=goToTariffList&";
    public static final String CHANGE_TARIFF_PAGE = "WEB-INF/pages/secure/changeTariff.jsp";

    public static final String GO_TO_CHANGE_RESULT = "/secure?command=goToChangeResult&";
    public static final String CHANGE_TARIFF_RESULT_PAGE = "WEB-INF/pages/secure/changeResult.jsp";
}
