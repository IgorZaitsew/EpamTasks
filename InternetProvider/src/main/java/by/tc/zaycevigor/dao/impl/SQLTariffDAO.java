package by.tc.zaycevigor.dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import by.tc.zaycevigor.dao.exception.ConnectionPoolException;
import by.tc.zaycevigor.dao.exception.DaoException;
import by.tc.zaycevigor.dao.TariffDAO;
import by.tc.zaycevigor.entity.Tariff;
import by.tc.zaycevigor.entity.TariffData;
import by.tc.zaycevigor.entity.criteria.SearchCriteria;

import static by.tc.zaycevigor.dao.util.Closer.close;
import static by.tc.zaycevigor.dao.util.Constant.*;
import static by.tc.zaycevigor.dao.util.IsUniqueCheck.isUniqueDatas;


public class SQLTariffDAO extends SqlDao implements TariffDAO {
    private static final String QUERY_GET_TARIFF_BY_CRITERIA = "SELECT * FROM tariffs WHERE name LIKE ? AND price >= ? AND price < ? " +
            "AND speed >= ? AND speed < ? AND id LIKE ?";
    private static final String QUERY_CHECK_NAME_USAGE = "SELECT COUNT(" + PARAMETER_TARIFF_NAME + ") FROM " +
            PARAMETER_TARIFF_TABLE_NAME + " WHERE " + PARAMETER_TARIFF_NAME + "=?";
    private static final String QUERY_ADD_TARIFF = "INSERT INTO " + PARAMETER_TARIFF_TABLE_NAME + " ("
            + PARAMETER_TARIFF_NAME + ", " + PARAMETER_TARIFF_PRICE + ", " + PARAMETER_TARIFF_SPEED + ") VALUES (?,?,?)";
    private static final String CON_POOL_CREATE_EXC = "Cannot create connection pool";
    private static final String ANY_VALUE = "%";
    private static final ConnectionPoolImpl pool;

    static {
        try {
            pool = ConnectionPoolImpl.create(url, login, password);
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }


    @Override
    public List<Tariff> find(SearchCriteria criteria) throws DaoException {
        Connection connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        List<Tariff> tariffList = new ArrayList<>();
        try {
            prepStatement = connection.prepareStatement(QUERY_GET_TARIFF_BY_CRITERIA);
            prepStatement.setString(1, criteria.getName());
            prepStatement.setInt(2, criteria.getMinPrice());
            prepStatement.setInt(3, criteria.getMaxPrice());
            prepStatement.setInt(4, criteria.getMinSpeed());
            prepStatement.setInt(5, criteria.getMaxSpeed());
            int id;
            if ((id = criteria.getId()) != 0) {
                prepStatement.setInt(6, id);
            } else {
                prepStatement.setString(6, ANY_VALUE);
            }
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                tariffList.add(createTariff(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {

            close(pool, resultSet, prepStatement, connection);
        }
        return tariffList;
    }

    @Override
    public List<Tariff> find(SearchCriteria criteria, int count) throws DaoException {
        Connection connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        List<Tariff> tariffList = new ArrayList<>();
        try {
            prepStatement = connection.prepareStatement(QUERY_GET_TARIFF_BY_CRITERIA);
            prepStatement.setString(1, criteria.getName());
            prepStatement.setInt(2, criteria.getMinPrice());
            prepStatement.setInt(3, criteria.getMaxPrice());
            prepStatement.setInt(4, criteria.getMinSpeed());
            prepStatement.setInt(5, criteria.getMaxSpeed());
            int id;
            if ((id = criteria.getId()) != 0) {
                prepStatement.setInt(6, id);
            } else {
                prepStatement.setString(6, ANY_VALUE);
            }
            resultSet = prepStatement.executeQuery();
            while (resultSet.next() && count > 0) {
                tariffList.add(createTariff(resultSet));
                count--;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {

            close(pool, resultSet, prepStatement, connection);
        }
        return tariffList;
    }

    @Override
    public boolean add(TariffData data) throws DaoException {
        Connection connection;
        connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        if (!isUniqueDatas(connection, String.valueOf(data.getName()), QUERY_CHECK_NAME_USAGE)) {
            return false;
        }
        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_ADD_TARIFF);

            prepStatement.setString(1, data.getName());
            prepStatement.setBigDecimal(2, data.getPrice());
            prepStatement.setInt(3, data.getSpeed());

            result = prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

    private Tariff createTariff(ResultSet resultSet) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setId(resultSet.getInt(PARAMETER_ID));
        tariff.setName(resultSet.getString(PARAMETER_NAME));
        tariff.setSpeed(resultSet.getFloat(PARAMETER_TARIFF_SPEED));
        tariff.setPrice(resultSet.getBigDecimal(PARAMETER_TARIFF_PRICE));
        return tariff;

    }
}
