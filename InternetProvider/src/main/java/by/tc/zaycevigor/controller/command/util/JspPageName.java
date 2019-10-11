package by.tc.zaycevigor.controller.command.util;

public final class JspPageName {
    private JspPageName() {
    }

    public static final String MAIN_PAGE = "WEB-INF/pages/secure/user/main.jsp";
    public static final String GO_TO_MAIN_PAGE = "/secure?command=goToMainPage";

    public static final String INDEX_PAGE = "index.jsp";
    public static final String GO_TO_INDEX_PAGE = "/controller?command=goToIndex";
    public static final String EXIT_COMMAND = "/secure?command=exit";
    public static final String DEFAULT_PAGE = "WEB-INF/pages/default.jsp";

    public static final String USER_REGISTRATION_COMMAND = "/controller?command=userRegistration&";
    public static final String GO_TO_USER_REGISTRATION = "/controller?command=goToUserRegistration&";

    public static final String ERROR_PAGE = "WEB-INF/pages/error/errorPage.jsp";
    public static final String GO_TO_ERROR_PAGE = "/controller?command=goToErrorPage&";

    public static final String CONTRACT_REGISTRATION_PAGE = "WEB-INF/pages/contractRegistration.jsp";
    public static final String GO_TO_CONTRACT_REGISTRATION_PAGE = "/controller?command=goToContractRegistration&";

    public static final String AUTHORIZATION_PAGE = "WEB-INF/pages/authorization.jsp";
    public static final String GO_TO_AUTHORISATION_PAGE = "/controller?command=goToAuthorizationPage&";

    public static final String PERSONAL_DATA_PAGE = "WEB-INF/pages/secure/user/personalData.jsp";
    public static final String SHOW_PERSONAL_DATA_PAGE = "/secure?command=personalData&";
    public static final String GO_TO_PERSONAL_DATA_PAGE = "/secure?command=goToPersonalDataCommand&";

    public static final String TARIFF_PAGE = "WEB-INF/pages/secure/user/tariffList.jsp";
    public static final String GO_TO_TARIFF_LIST_PAGE = "/secure?command=tariffList&id=0&name=%&min_price=0&max_price=0&min_speed=0&max_speed=0";

    public static final String SINGLE_TARIFF_PAGE = "WEB-INF/pages/secure/user/singleTariffInfo.jsp";

    public static final String GO_TO_CHANGE_TARIFF = "/secure?command=goToTariffList&";
    public static final String CHANGE_TARIFF_PAGE = "WEB-INF/pages/secure/user/changeTariff.jsp";

    public static final String GO_TO_CHANGE_RESULT = "/secure?command=goToChangeResult&";
    public static final String CHANGE_TARIFF_RESULT_PAGE = "WEB-INF/pages/secure/user/changeResult.jsp";

    public static final String GO_TO_USER_LIST_COMMAND = "/admin?command=goToUserList&";
    public static final String USER_LIST_PAGE = "WEB-INF/pages/secure/admin/userList.jsp";
    public static final String SHOW_USER_LIST = "/admin?command=user_list&";

    public static final String USER_DELETE_PAGE = "WEB-INF/pages/secure/admin/userDelete.jsp";
    public static final String USER_DELETE_SUCCESS_PAGE = "WEB-INF/pages/secure/admin/userDeleteSuccess.jsp";
    public static final String GO_TO_USER_DELETE_SUCCESS = "/admin?command=goToUserDeleteSuccess";

    public static final String TARIFF_DELETE_PAGE = "WEB-INF/pages/secure/admin/tariffDeletePage.jsp";
    public static final String TARIFF_DELETE_SUCCESS_PAGE = "WEB-INF/pages/secure/admin/tariffDeleteSuccess.jsp";
    public static final String GO_TO_TARIFF_DELETE_SUCCESS = "/admin?command=go_to_tariff_delete_success";


    public static final String USER_ADD_PAGE = "WEB-INF/pages/secure/admin/addUserPage.jsp";
    public static final String GO_TO_USER_ADD_PAGE = "/admin?command=user_add_page&";
    public static final String USER_ADD_SUCCESS_PAGE = "WEB-INF/pages/secure/admin/addUserSuccessPage.jsp";
    public static final String GO_TO_USER_ADD_SUCCESS_PAGE = "/admin?command=goToUserAddSuccess";
    public static final String USER_READJUST_PAGE = "WEB-INF/pages/secure/admin/userReadjustPage.jsp";
    public static final String GO_TO_USER_READJUST_PAGE = "/admin?command=go_to_user_readjust&";

    public static final String USER_INFO_PAGE = "WEB-INF/pages/secure/admin/userInfo.jsp";

    public static final String TARIFF_ADD_PAGE = "WEB-INF/pages/secure/admin/addTariffPage.jsp";
    public static final String TARIFF_ADD_SUCCESS_PAGE = "WEB-INF/pages/secure/admin/tariffAddSuccess.jsp";
    public static final String GO_TO_TARIFF_ADD_PAGE = "/admin?command=tariff_add_page&";
    public static final String GO_TO_TARIFF_ADD_SUCCESS_PAGE = "/admin?command=goToTariffAddSuccessPage&";
    public static final String TARIFF_READJUST_PAGE = "WEB-INF/pages/secure/admin/tariffReadjustPage.jsp";
    public static final String GO_TO_TARIFF_READJUST_PAGE = "/admin?command=go_to_tariff_readjust&";
}
