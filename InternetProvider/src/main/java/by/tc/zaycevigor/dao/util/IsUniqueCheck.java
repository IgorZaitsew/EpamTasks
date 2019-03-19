package by.tc.zaycevigor.dao.util;

import by.tc.zaycevigor.dao.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.tc.zaycevigor.dao.util.Constant.*;

public final class IsUniqueCheck {
    private static final String QUERY_CHECK_CITY_USAGE = "SELECT COUNT("+PARAMETER_CITY+
            ") FROM "+ PARAMETER_USER_TABLE_NAME +" WHERE "+PARAMETER_CITY+"=?";
    private static final String QUERY_CHECK_STREET_USAGE = "SELECT COUNT("+PARAMETER_STREET+
            ") FROM "+ PARAMETER_USER_TABLE_NAME +" WHERE "+PARAMETER_STREET+"=?";
    private static final String QUERY_CHECK_HOUSE_NUMBER_USAGE = "SELECT COUNT("+PARAMETER_HOUSE_NUMBER+
            ") FROM "+ PARAMETER_USER_TABLE_NAME +" WHERE "+PARAMETER_HOUSE_NUMBER+"=?";

    private IsUniqueCheck(){}

    private static final Logger log = Logger.getLogger(IsUniqueCheck.class);
    public static boolean isUniqueDatas(Connection connection, String... args) throws DaoException {
        for (int i = 0; i < args.length - 1; i+=2) {
            if (!isUniqueData(connection,args[i], args[i + 1])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isUniqueData(Connection connection,String data, String query ) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, data);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) > 0) {
                    return false;
                }
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        }
        return true;
    }

    public static boolean isUniqueAddress(Connection connection,String city,String street,String houseNumber) throws DaoException {
        if(!isUniqueData(connection,houseNumber,QUERY_CHECK_HOUSE_NUMBER_USAGE)){
            if(!isUniqueData(connection,street,QUERY_CHECK_STREET_USAGE)){
                if (!isUniqueData(connection,city,QUERY_CHECK_CITY_USAGE)){
                    return false;
                }
            }
        }
        return true;
    }
}
