package handler;
import query.resultsethandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by csx on 2017/12/7.
 */
public abstract class abstrusthandler<T> implements resultsethandler<List<T>> {
    @Override
    public List<T> handler(ResultSet rs) throws SQLException {
        List<T> list = new ArrayList<T>();
        while (rs.next()){
            list.add(getSigle(rs));
        }
        return list;
    }
    protected abstract T getSigle(ResultSet rs) throws SQLException;
}
