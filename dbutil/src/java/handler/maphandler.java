package handler;
import query.rowresult;
import query.resultsethandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by csx on 2017/12/7.
 */
public class maphandler implements resultsethandler<Map<Object,Object>>{

    @Override
    public Map<Object, Object> handler(ResultSet rs) throws SQLException {
        return rs.next() ? rowresult.toMap(rs):null;
    }
}
