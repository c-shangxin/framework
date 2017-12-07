package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import query.rowresult;

/**
 * Created by csx on 2017/12/7.
 */
public class beanlisthandler<T> extends abstrusthandler<T>{
    private Class<T> clz;
    public beanlisthandler(Class<T>clz){
            this.clz = clz;
    }
    @Override
    protected T getSigle(ResultSet rs) throws SQLException {
        return rowresult.toBean(clz,rs);
    }
}
