package by.tc.zaycevigor.dao.impl;

import java.util.Properties;
import java.util.ResourceBundle;

import by.tc.zaycevigor.dao.exception.NotDBDriverException;

public abstract class SqlDao {
    protected static final String driver;
    protected static final String url;
    protected static final String login;
    protected static final String password;

    private static final Properties properties = new Properties();
    private static final String DB_DRIVER = "dbDriver";
    private static final String DB_URL = "dbUrl";
    private static final String DB_LOGIN = "dbLogin";
    private static final String DB_PASSWORD = "dbPassword";

    private static final String DB_PROPERTIES_FILE_PATH = "database";


    static {

        ResourceBundle jdbcProperties = ResourceBundle.getBundle(DB_PROPERTIES_FILE_PATH);

        driver = jdbcProperties.getString(DB_DRIVER);
        url = jdbcProperties.getString(DB_URL);
        login = jdbcProperties.getString(DB_LOGIN);
        password = jdbcProperties.getString(DB_PASSWORD);

        properties.put("user", login);
        properties.put("password", password);

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new NotDBDriverException("Can't find driver.", e);
        }
    }

    public SqlDao() {
    }
}
