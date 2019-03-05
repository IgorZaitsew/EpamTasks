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
import by.tc.zaycevigor.entity.criteria.SearchCriteria;
import org.apache.log4j.Logger;


public class SQLTariffDAO extends SqlDao implements TariffDAO {
    private static final String QUERY_GET_TARIFF_BY_CRITERIA = "SELECT * FROM tariffs WHERE name LIKE ? AND price >= ? AND price < ? " +
            "AND speed >= ? AND speed < ? ";
    private static final String CON_POOL_CREATE_EXC = "Cannot create connection pool";
    private static final ConnectionPoolImpl pool;
    private static Logger log = Logger.getLogger(SQLTariffDAO.class);

    static {
        try {
            pool = ConnectionPoolImpl.create(url, login, password);
        } catch (SQLException e) {
            log.error(CON_POOL_CREATE_EXC);
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
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                tariffList.add(createTariff(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {

            close(resultSet, prepStatement, connection);
        }
        return tariffList;
    }

    private Tariff createTariff(ResultSet resultSet) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setId(Integer.parseInt(resultSet.getString("id")));
        tariff.setName(resultSet.getString("name"));
        tariff.setSpeed(Float.parseFloat(resultSet.getString("speed")));
        tariff.setPrice(Float.parseFloat(resultSet.getString("price")));
        return tariff;

    }

    private void close(ResultSet rs, PreparedStatement st, Connection connection) throws DaoException {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        }

        if (connection != null) {
            pool.releaseConnection(connection);
        }
    }
}
