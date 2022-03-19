package db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Db {

    private static final Logger log = LogManager.getLogger(Db.class);

    public static void initialize() throws Exception {
        DbUtil.initialize();

    }

//    public static void insertApiRecord(ApiRecord r) throws Exception {
//        Object[] params = new Object[] { r.getUri(), r.getFirebaseId(), r.getIp(), r.getCode(), r.getDevmode(), r.getStarted(), r.getEnded(), r.getDuration() };
//        final String sql = "INSERT INTO api_records (uri, firebase_id, ip, code, dev_mode, started, ended, duration) VALUES (?,?,?,?,?,?,?,?)";
//        DbUtil.executeSQL(sql, params);
//    }
}
