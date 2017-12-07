package handler;

import query.resultsethandler;
import query.rowresult;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by csx on 2017/12/7.
 */
public class columnhandler<T> implements resultsethandler<T> {
    private int columnIndex;
    private Class<T> clz;
    public columnhandler(int columnIndex,Class<T> clz){
        this.clz = clz;
        this.columnIndex = columnIndex;
    }
    @Override
    public T handler(ResultSet rs) throws SQLException {
        return rs.next() ? rowresult.toColumn(columnIndex,clz,rs):null;
    }
}
