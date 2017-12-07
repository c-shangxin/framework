package query;

import javafx.beans.value.ObservableObjectValue;
import dbutil.dbutli;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by csx on 2017/12/7.
 */
public class sqlexecut {
    private Connection conn;
    public sqlexecut(Connection conn){
        this.conn = conn;
    }
    /**
     * 增删改的批量操作
     */
    public int[] executeBatchUpdate(String sql,Object[][] params) throws SQLException {
        checkcq(conn,sql);
        conn.setAutoCommit(false);
        PreparedStatement ps = conn.prepareStatement(sql);
        setBatchParams(ps,params);
        int[] rows = ps.executeBatch();
        conn.commit();
        return rows;
    }

    /**
     * 对数据库的增删改操作
     */
    public int executeUpdate(String sql,Object... params) throws SQLException {
        checkcq(conn,sql);
        int row = 0;
        PreparedStatement ps = conn.prepareStatement(sql);
        setParams(ps,params);
        row = ps.executeUpdate();
        return row;
    }

    /**
     * 得到ResultSet
     */
    private ResultSet getResult(String sql,resultsethandler rsh,Object... params) throws SQLException {
        check(conn,sql,rsh);
        PreparedStatement ps = conn.prepareStatement(sql);
        setParams(ps,params);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    /**
     * 查询一条数据,将结果集转换成一个Bean对象
     */
    public <T> T executQueryToBean(String sql,resultsethandler rsh,Object... params) throws SQLException {
        ResultSet rs = getResult(sql,rsh,params);
        T t = (T)rsh.handler(rs);
        dbutli.close(conn,null,rs);
        return t;
    }

    /**
     * 查询一条数据,将结果集转换成某一列的值
     */
    public Object excutQueryToColumn(String sql,resultsethandler rsh,Object... params) throws SQLException {
        ResultSet rs = getResult(sql,rsh,params);
        Object obj = rsh.handler(rs);
        dbutli.close(conn,null,rs);
        return obj;
    }
    /**
     * 查询一条数据,将结果集转成map集合
     */
    public Map<Object,Object> executeQueryToMap(String sql,resultsethandler rsh,Object... params) throws SQLException {
        Map<Object,Object> map = new HashMap<Object, Object>();
        ResultSet rs = getResult(sql, rsh, params);
        map = (Map<Object,Object>)rsh.handler(rs);
        dbutli.close(conn,null,rs);
        return map;
    }

    /**
     * 查询一条数据,将结果集转换成数组
     */
    public Object[] executeQueryToArray(String sql, resultsethandler rsh, Object... params) throws SQLException {

        ResultSet rs = getResult(sql, rsh, params);
        Object[] obj = (Object[]) rsh.handler(rs);
        dbutli.close(conn,null,rs);
        return obj;
    }

    /**
     * 查询多条数据，将结果集转换List<Bean>
     */
    public <T> List<T> executeQueryToBeanList(String sql, resultsethandler rsh, Object... params) throws SQLException {
        List<T> list = new ArrayList<T>();
        ResultSet rs = getResult(sql, rsh, params);
        list = (List<T>)rsh.handler(rs);
        dbutli.close(conn,null,rs);
        return list;
    }

    /**
     * 查询多条数据，将结果集转换某一列的List<Object>
     */
    public List<Object> executeQueryToColumnLists(String sql, resultsethandler rsh, Object... params) throws SQLException {
        List<Object> list = new ArrayList<Object>();
        ResultSet rs = getResult(sql,rsh,params);
        list = (List<Object>)rsh.handler(rs);
        dbutli.close(conn,null,rs);
        return list;
    }
    /**
     * 查询多条数据，将结果集转换List<Map<Object,Object>>
     */
    public List<Map<Object,Object>> executeQueryToMapList(String sql, resultsethandler rsh, Object... params) throws SQLException {
        List<Map<Object,Object>> list = new ArrayList<Map<Object, Object>>();
        ResultSet rs = getResult(sql, rsh, params);
        list = (List<Map<Object,Object>>)rsh.handler(rs);
        dbutli.close(conn,null,rs);
        return list;
    }

    /**
     * 查询多条数据，将结果集转换List<Object[]>
     */
    public List<Object[]> executeQueryToArrayList(String sql, resultsethandler rsh, Object... params) throws SQLException {
        List<Object[]> list = new ArrayList<Object[]>();
        ResultSet rs = getResult(sql, rsh, params);
        list = (List<Object[]>)rsh.handler(rs);
        dbutli.close(conn,null,rs);
        return list;
    }

    /**
     * 对sql语句设置值
     */
    private void setParams(PreparedStatement ps,Object... params) throws SQLException {
        if(params.length>0){
            for(int i=0;i<params.length;i++){
                ps.setObject(i+1,params[i]);
            }
        }
    }

    /**
     * 批量操作设置值
     */
    private void setBatchParams(PreparedStatement ps,Object[][] params) throws SQLException {
        if(params.length>0){
            for (Object[] obj:params) {
                for(int i=0;i<obj.length;i++){
                    ps.setObject(i+1,obj[i]);
                }
                ps.addBatch();
            }
        }
    }

    /**
     * 检查connection，sql，ResultSetHandler是否为空
     */
    private void check(Connection conn,String sql,resultsethandler rsh) throws SQLException {
        if(conn == null){
            throw new SQLException("not connection");
        }
        if(sql == null){
            throw new SQLException("not sql");
        }
        if(rsh == null){
            throw new SQLException("not ResultSetHandler");
        }
    }

    /**
     * 检查connection，sql是否为空
     */
    private void checkcq(Connection conn,String sql) throws SQLException {
        if(conn == null){
            throw new SQLException("not connection");
        }
        if(sql == null){
            throw new SQLException("not sql");
        }
    }



}
