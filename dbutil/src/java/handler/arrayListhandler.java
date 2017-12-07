package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import query.rowresult;

/**
 * Created by csx on 2017/12/7.
 */
public class arrayListhandler extends abstrusthandler<Object[]> {
    @Override
    protected Object[] getSigle(ResultSet rs) throws SQLException {
        return rowresult.toArray(rs);
    }
}
