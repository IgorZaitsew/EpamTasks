package by.tc.zaycevigor.controller;

public final class JspPageName {
    private JspPageName() {
    }

    public static final String MAIN_PAGE = "WEB-INF/pages/main.jsp";
    public static final String INDEX_PAGE = "index.jsp";
    public static final String DEFAULT_PAGE = "WEB-INF/pages/default.jsp";

    public static final String REGISTRATION_PAGE = "WEB-INF/pages/registration.jsp";
    public static final String GO_TO_REGISTRATION_PAGE = "/controller?command=goToRegistrationPage";

    public static final String AUTHORIZATION_PAGE = "WEB-INF/pages/authorization.jsp";
    public static final String GO_TO_AUTHORISATION_PAGE = "/controller?command=goToAuthorizationPage";

    public static final String DATA_PAGE = "WEB-INF/pages/personaldata.jsp";
    public static final String GO_TO_DATA_PAGE = "/controller?command=goToPersonalData";

    public static final String GO_TO_MAIN_PAGE = "/controller?command=registrationResult";
}
