package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import query.rowresult;
/**
 * Created by csx on 2017/12/7.
 */
public class columnlisthandler<T> extends abstrusthandler<T> {
    private int columnIndex;
    private Class<T> clz;
    public columnlisthandler(int columnIndex,Class<T> clz){
        this.clz = clz;
        this.columnIndex = columnIndex;
    }
    @Override
    protected T getSigle(ResultSet rs) throws SQLException {
        return rowresult.toColumn(columnIndex,clz,rs);
    }
}
