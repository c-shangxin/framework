package handler;

import query.resultsethandler;

import java.nio.channels.ClosedSelectorException;
import java.sql.ResultSet;
import java.sql.SQLException;
import query.rowresult;

/**
 * Created by csx on 2017/12/7.
 */
public class beanhandler<T> implements resultsethandler<T> {
    private Class<T> clz;
    public beanhandler(Class<T> clz){
        this.clz = clz;
    }

    @Override
    public T handler(ResultSet rs) throws SQLException {
        return rs.next() ? rowresult.toBean(clz,rs):null;
    }
}
