package by.tc.zaycevigor.dao.impl;


import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private static final String QUERY_GET_TARIFF_BY_CRITERIA = "SELECT * FROM " + PARAMETER_TARIFF_TABLE_NAME + " WHERE " + PARAMETER_TARIFF_NAME +
            " LIKE ? AND " + PARAMETER_TARIFF_PRICE + " >= ? AND " + PARAMETER_TARIFF_PRICE + " < ? " +
            "AND " + PARAMETER_TARIFF_SPEED + " >= ? AND " + PARAMETER_TARIFF_SPEED + " < ? AND " + PARAMETER_ID + " LIKE ?";
    private static final String QUERY_GET_TARIFF_BY_ID = "SELECT * FROM " + PARAMETER_TARIFF_TABLE_NAME + " WHERE " + PARAMETER_ID + " >= ? LIMIT ?";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(*) FROM " + PARAMETER_TARIFF_TABLE_NAME;
    private static final String QUERY_CHECK_NAME_USAGE = "SELECT COUNT(" + PARAMETER_TARIFF_NAME + ") FROM " +
            PARAMETER_TARIFF_TABLE_NAME + " WHERE " + PARAMETER_TARIFF_NAME + "=?";
    private static final String QUERY_CHECK_ID_USAGE = "SELECT COUNT(" + PARAMETER_ID + ") FROM " +
            PARAMETER_TARIFF_TABLE_NAME + " WHERE " + PARAMETER_ID + "=?";
    private static final String QUERY_ADD_TARIFF = "INSERT INTO " + PARAMETER_TARIFF_TABLE_NAME + " ("
            + PARAMETER_TARIFF_NAME + ", " + PARAMETER_TARIFF_PRICE + ", " + PARAMETER_TARIFF_SPEED + ") VALUES (?,?,?)";
    private static final String CON_POOL_CREATE_EXC = "Cannot create connection pool";
    private static final String ANY_VALUE = "%";
    private static final String QUERY_DELETE_TARIFF = "DELETE FROM " + PARAMETER_TARIFF_TABLE_NAME + " WHERE " + PARAMETER_ID + " =?";
    private static final String QUERY_READJUST_TARIFF = "UPDATE " + PARAMETER_TARIFF_TABLE_NAME + " SET " + PARAMETER_NAME +
            " =?, " + PARAMETER_TARIFF_SPEED + " =?, " + PARAMETER_TARIFF_PRICE + " =? " + " WHERE " + PARAMETER_ID + " =?";
    private static final String QUERY_GET_PRICE_LIST = "SELECT " + PARAMETER_ID + "," + PARAMETER_TARIFF_PRICE + " FROM " + PARAMETER_TARIFF_TABLE_NAME;

    private static final ConnectionPoolImpl pool;

    static {
        try {
            pool = ConnectionPoolImpl.create(url, login, password);
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    /**
     * return tariff list satisfying the specified criteria
     *
     * @param criteria
     * @return
     * @throws DaoException
     */
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

    /**
     * return tariff list satisfying the specified criteria with count size
     *
     * @param criteria
     * @param count
     * @return
     * @throws DaoException
     */
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
    public List<Tariff> find(int id, int limit) throws DaoException {
        Connection connection = pool.getConnection();
        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;
        List<Tariff> tariffList = new ArrayList<>();
        try {
            prepStatement = connection.prepareStatement(QUERY_GET_TARIFF_BY_ID);
            prepStatement.setInt(1, id);
            prepStatement.setInt(2, limit);

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
    public int getTariffCount() throws DaoException {
        Connection connection = pool.getConnection();
        PreparedStatement prepStatement = null;
        ResultSet resultSet;
        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_SELECT_COUNT);
            resultSet = prepStatement.executeQuery();
            if (resultSet != null) {
                resultSet.last();
                result = resultSet.getInt(1);
            } else {
                result = 0;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {

            close(pool, prepStatement, connection);
        }
        return result;
    }

    /**
     * add tariff with data to DB
     *
     * @param data
     * @return
     * @throws DaoException
     */
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

    /**
     * delete the instance if tariff by result set
     *
     * @param tariffId
     * @return
     * @throws DaoException
     */
    @Override
    public boolean deleteTariff(int tariffId) throws DaoException {
        Connection connection = pool.getConnection();
        PreparedStatement prepStatement = null;
        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_DELETE_TARIFF);
            prepStatement.setLong(1, tariffId);
            result = prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

    @Override
    public boolean readjust(int tariffId, TariffData tariffData) throws DaoException {
        Connection connection;
        connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        if (isUniqueDatas(connection, String.valueOf(tariffId), QUERY_CHECK_ID_USAGE)) {
            return false;
        }
        int result;
        try {
            prepStatement = connection.prepareStatement(QUERY_READJUST_TARIFF);

            prepStatement.setString(1, tariffData.getName());
            prepStatement.setInt(2, tariffData.getSpeed());
            prepStatement.setBigDecimal(3, tariffData.getPrice());
            prepStatement.setInt(4, tariffId);
            result = prepStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
        return result > 0;
    }

    /**
     * returns list of all tariff prices with ids
     *
     * @return
     * @throws DaoException
     */
    @Override
    public Map<Integer, BigDecimal> getPriceList() throws DaoException {
        Connection connection;
        connection = pool.getConnection();

        PreparedStatement prepStatement = null;
        Map<Integer, BigDecimal> priceList = new HashMap<>();
        try {
            ResultSet resultSet;
            prepStatement = connection.prepareStatement(QUERY_GET_PRICE_LIST);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                priceList.put(resultSet.getInt(PARAMETER_ID), resultSet.getBigDecimal(PARAMETER_TARIFF_PRICE));
            }
        } catch (SQLException e) {
            throw new DaoException(e);

        } finally {
            close(pool, prepStatement, connection);
        }
        return priceList;
    }


    /**
     * creates the instance of tariff by result set
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Tariff createTariff(ResultSet resultSet) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setId(resultSet.getInt(PARAMETER_ID));
        tariff.setName(resultSet.getString(PARAMETER_NAME));
        tariff.setSpeed(resultSet.getFloat(PARAMETER_TARIFF_SPEED));
        tariff.setPrice(resultSet.getBigDecimal(PARAMETER_TARIFF_PRICE));
        return tariff;

    }
}
