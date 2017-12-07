package query;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by csx on 2017/12/7.
 */
public interface resultsethandler<T> {
    T handler(ResultSet rs) throws SQLException;
}
