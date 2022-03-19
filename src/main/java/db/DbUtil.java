package db;

import constants.Constants;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Low level class concerned with interfacing with the database.
 * The only class that should access the methods in this class
 * is the Db class
 */
public class DbUtil {

    private static final BasicDataSource ds = new BasicDataSource();
    private static final Logger log = LogManager.getLogger(DbUtil.class);

    private DbUtil() {
    }

    private static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    static void executeSQL(final String sql) throws Exception {
        executeSQL(sql, new Object[]{});
    }

    public static void executeSQL(final String sql, final Object... parameters) throws SQLException {
        Connection con = getConnection();
        PreparedStatement s = con.prepareStatement(sql);
        setParams(s, parameters);
        System.out.println(s.toString());
        s.execute();
        con.close();
    }

    private static List<Map<String, Object>> toArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<>();

        while (rs.next()) {
            HashMap<String, Object> row = new HashMap<>(columns);
            for (int i = 1; i <= columns; ++i)
                row.put(md.getColumnName(i), rs.getObject(i));

            list.add(row);
        }

        return list;
    }

    public static void initialize() throws Exception {
        ds.setUrl(Constants.DB_URL);
        ds.setUsername(Constants.DB_USER);
        ds.setPassword(Constants.DB_PASSWORD);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
        final String s = "SHOW TABLES;";
        executeSQL(s);
        log.debug("Initialized database");
    }

    private static void setParams(PreparedStatement s, final Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            Object parameterValue = parameters[i];
            s.setObject(i + 1, parameterValue);
        }
    }

    /**
     *
     * @param sql
     * @param parameters
     * @return null if there is not at least one result
     * @throws Exception
     */
    static Map<String, Object> getOne(String sql, final Object... parameters) throws Exception {
        List<Map<String, Object>> list = getList(sql, parameters);

        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            log.error("DB query returned multiple results. Selecting result at position 0 - this might not be what you wanted");
            return list.get(0);
        } else {
            return null;
        }

    }

    static List<Map<String, Object>> getList(String sql, final Object... parameters) throws Exception {
        try (Connection a = getConnection()) {
            PreparedStatement ps = a.prepareStatement(sql);
            setParams(ps, parameters);
            return toArrayList(ps.executeQuery());
        }
    }
}
