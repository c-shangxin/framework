package handler;

import query.resultsethandler;
import query.rowresult;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by csx on 2017/12/7.
 */
public class arrayhandler implements resultsethandler {
    @Override
    public Object handler(ResultSet rs) throws SQLException {
        return rs.next()? rowresult.toArray(rs):null;
    }
}
